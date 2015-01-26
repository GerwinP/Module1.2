
package ss.week7.cmdchat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Server. 
 * @author  Theo Ruys
 * @version 2005.02.21
 */
public class Server{
	private static final String USAGE = "usage: " + Server.class.getName() + " <port>";

	/** Start een Server-applicatie op. */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.out.println(USAGE);
			System.exit(0);
		}
		
		Server server = new Server(Integer.parseInt(args[0]));
		server.run();
		
	}


	private int port;
	private List<ClientHandler> threads;
	public ServerSocket serverSocket = null;
    /** Constructs a new Server object */
	public Server(int portArg) {
		port = portArg;
		threads = new ArrayList<ClientHandler>();
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("Could not create a ServerSocket on port " + port);
		}
	}
	
	/**
	 * Listens to a port of this Server if there are any Clients that 
	     * would like to connect. For every new socket connection a new
	     * ClientHandler thread is started that takes care of the further
	     * communication with the Client. 
	 */
	public void run() {
		try {
			while(true){
				Socket clientSocket = serverSocket.accept();
				ClientHandler clientHandler = new ClientHandler(this, clientSocket);
				addHandler(clientHandler);
//				clientHandler.announce();
				clientHandler.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void print(String message){
		System.out.println(message);
	}
	
	/**
	 * Sends a message using the collection of connected ClientHandlers
	     * to all connected Clients.
	 * @param msg message that is send
	 */
	public void broadcast(String msg) {
		Iterator<ClientHandler> iterator = threads.iterator();
		
		print(msg);
		
		while(iterator.hasNext()){
			iterator.next().sendMessage(msg);
		}
	}
	
	/**
	 * Add a ClientHandler to the collection of ClientHandlers.
	 * @param handler ClientHandler that will be added
	 */
	public void addHandler(ClientHandler handler) {
		threads.add(handler);
	}
	
	/**
	 * Remove a ClientHandler from the collection of ClientHanlders. 
	 * @param handler ClientHandler that will be removed
	 */
	public void removeHandler(ClientHandler handler) {
		threads.remove(handler);
	}
	
} // end of class Server
