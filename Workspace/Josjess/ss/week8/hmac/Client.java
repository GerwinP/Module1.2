package ss.week8.hmac;


import java.io.IOException;
import java.net.Socket;

public class Client {
    private String name = "client2";
    private int port = 2010;
    private Socket sock;
    //private String key = "0xabababababababab";
    private String key = "0xababababababababab";

    public void run(){

        // try to open a Socket to the server
        try {
            sock = new Socket("localhost", port);
        } catch (IOException e) {
            System.out.println("ERROR: could not create a socket on " + "localhost" + " and port " + port);
        }

        // create Peer object and start the two-way communication
        try {
            Peer client = new Peer(name, sock, key);
            Thread streamInputHandler = new Thread(client);
            streamInputHandler.start();
            client.handleTerminalInput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Start een Client-applicatie op.
     */
    public static void main(String[] args) {
        new Client().run();
    }
}

