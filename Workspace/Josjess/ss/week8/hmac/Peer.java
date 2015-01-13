package ss.week8.hmac;

import org.apache.commons.codec.binary.Hex;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Peer for a simple client-server application
 *
 * @author Theo Ruys
 * @version 2005.02.21
 */
public class Peer implements Runnable {
    protected String name;
    protected Socket socket;
    protected BufferedReader in;
    protected BufferedWriter out;
    protected BufferedReader systemIn;
    private String HMAC_ALGORITHM = "HmacSHA1";
    private String KEY_STRING;
    private Mac mac;

	/*@
       requires (nameArg != null) && (sockArg != null);
	 */

    /**
     * Constructor. creates a peer object based inStream the given parameters.
     *
     * @param name   name of the Peer-proces
     * @param socket Socket of the Peer-proces
     */
    public Peer(String name, Socket socket, String keyString) throws IOException {
        this.name = name;
        this.socket = socket;
        this.KEY_STRING = keyString;
        in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
        systemIn = new BufferedReader(new InputStreamReader(System.in));

        try {
            mac = Mac.getInstance(HMAC_ALGORITHM);
            byte[] keyBytes = KEY_STRING.getBytes();
            SecretKeySpec signingKey = new SecretKeySpec(keyBytes, HMAC_ALGORITHM);
            mac.init(signingKey);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads strings of the stream of the socket-connection and writes the characters to the default output
     */
    public void run() {
        while (true) {
            try {
                String message = in.readLine();
                System.out.println("Received: " + message);
                handleMessages(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String messageContent) {
        if (messageContent.equals("exit")) {
            close();
        } else {
            String hmac = Hex.encodeHexString(mac.doFinal(messageContent.getBytes()));
            String message = "SHOW \"" + messageContent + "\" " + hmac;

            try {
                out.write(message + "\n");
                out.flush();
                System.out.println("sent message: " + message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Reads a string from the console and sends this string over the socket-connection to the Peer proces. On Peer.EXIT the method ends
     */
    public void handleTerminalInput() {
        String message = readString();
        if (message.equals("exit")) {
            close();
        } else {
            sendMessage(message);
        }
        handleTerminalInput();
    }

    /**
     * Closes the connection, the sockets will be terminated
     */
    public void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * returns name of the peer object
     */
    public String getName() {
        return name;
    }

    /**
     * read a line from the default input
     */
    public String readString() {
        String message = null;
        try {
            message = systemIn.readLine();
            return message;
        } catch (IOException e) {
            return "";
        }
    }


    public void handleMessages(String message) {
        Scanner scanner = new Scanner(message).useDelimiter("\\s+");
        switch (scanner.next()) {
            default:
                break;
        }
    }
}