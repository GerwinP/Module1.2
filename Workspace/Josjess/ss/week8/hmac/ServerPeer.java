package ss.week8.hmac;

import org.apache.commons.codec.binary.Hex;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.Socket;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class ServerPeer implements Runnable {
	private Socket socket;
	private BufferedReader in;
	private BufferedWriter out;
	private Server server;
	private final String HMAC_ALGORITHM = "HmacSHA1";
	private final String KEY_STRING = "0xabababababababab";
	private Mac mac;
	protected int clientNo;

	/*
	 * @ requires (nameArg != null) && (sockArg != null);
	 */

	/**
	 * Constructor. creates a peer object based inStream the given parameters.
	 * 
	 * @param name
	 *            name of the Peer-proces
	 * @param socket
	 *            Socket of the Peer-proces
	 */
	public ServerPeer(Socket socket, Server server, int clientNo)
			throws IOException {
		this.socket = socket;
		this.server = server;
		this.clientNo = clientNo;
		in = new BufferedReader(new InputStreamReader(
				this.socket.getInputStream()));
		out = new BufferedWriter(new OutputStreamWriter(
				this.socket.getOutputStream()));

		try {
			mac = Mac.getInstance(HMAC_ALGORITHM);
			byte[] keyBytes = KEY_STRING.getBytes();
			SecretKeySpec signingKey = new SecretKeySpec(keyBytes,
					HMAC_ALGORITHM);
			mac.init(signingKey);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads strings of the stream of the socket-connection and writes the
	 * characters to the default output
	 */
	public void run() {
		while (true) {
			try {
				String message = in.readLine();
				handleMessages(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void sendMessage(String message) {
		if (message.equals("exit")) {
			close();
		} else {
			try {
				out.write(message + "\n");
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
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

	public void handleMessages(String message) {
		Scanner scanner = new Scanner(message).useDelimiter("\\s+");
		switch (scanner.next()) {
		case "SHOW":
			int i1 = message.indexOf('"');
			int i2 = message.indexOf('"', i1 + 1);
			String messageContent = message.substring(i1 + 1, i2);
			String hmac = message.substring(i2 + 2, message.length());
			if (hmac.equals(Hex.encodeHexString(mac.doFinal(messageContent
					.getBytes())))) {
				sendMessage("OK");
				System.out
						.println("client " + clientNo + ": " + messageContent);
				server.broadcast(this, messageContent);
			} else {
				System.out.println("client " + clientNo
						+ " wrong authentication: " + message);
				sendMessage("AUTHERROR");
			}
			break;
		default:
			break;
		}
	}
}