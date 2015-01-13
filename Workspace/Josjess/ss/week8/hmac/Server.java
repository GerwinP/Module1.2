package ss.week8.hmac;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Server.
 *
 * @author Theo Ruys
 * @version 2005.02.21
 */
public class Server {
    private int port = 2010;
    private ServerSocket server;
    private String name = "server";
    private List<ServerPeer> clients = new ArrayList<ServerPeer>();

    public void run() {
        // try to open a Socket to the server
        try {
            server = new ServerSocket(2010);
        } catch (IOException e) {
            System.out.println("ERROR: could not create a  server socket at port " + port);
        }

        int clientNo = 1;
        try {
            while (true) {
                Socket socket = server.accept();
                System.out.println("Client connected!");
                ServerPeer serverPeer = new ServerPeer(socket, this, clientNo);
                Thread streamInputHandler = new Thread(serverPeer);
                streamInputHandler.start();
                clients.add(serverPeer);
                clientNo++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void broadcast(ServerPeer sender, String message) {
        for (ServerPeer i : clients) {
            if (!sender.equals(i)) {
                i.sendMessage(message);
            }
        }
    }


    public static void main(String[] args) {
        new Server().run();
    }

}
