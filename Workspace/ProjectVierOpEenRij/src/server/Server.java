package server;

import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	private int port;
	private List<ClientHandler> threads;
	public ServerSocket serverSocket = null;
	private static final String USAGE = "usage: " + Server.class.getName() + " <port>";
	private List<String> clientNames = new ArrayList<String>();
	public final String versie = "000";
	public List<Socket> waitingForGame = new ArrayList<Socket>();
	
	public static void main(String[] args){
		if (args.length != 1) {
			System.out.println(USAGE);
			System.exit(0);
		}
		
		Server server = new Server(Integer.parseInt(args[0]));
		server.run();
	}
	
	public Server(int portArg) {
		port = portArg;
		threads = new ArrayList<ClientHandler>();
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("Could not create a ServerSocket on port "
					+ port);
		}
	}

	public void run() {
		try {
			while (true) {
				Socket clientSocket = serverSocket.accept();
				ClientHandler clientHandler = new ClientHandler(this, clientSocket);
				addHandler(clientHandler);
				clientHandler.start();
				if(waitingForGame.size() == 2){
					print("Two players waiting");
				}
				
			}
		} catch (IOException e) {
			System.out.println("Stuk");
		}
	}

	public void print(String message) {
		System.out.println(message);
	}
	
	public void broadcast(String msg) {
		Iterator<ClientHandler> iterator = threads.iterator();
		
		print(msg);
		
		while(iterator.hasNext()){
			iterator.next().sendMessage(msg);
		}
	}

	public void addHandler(ClientHandler handler) {
		threads.add(handler);
	}

	public void removeHandler(ClientHandler handler) {
		threads.remove(handler);
	}
	
	public void addClientName(String name){
		clientNames.add(name);
	}
	
	public void removeClientName(String name){
		clientNames.remove(name);
	}
	
	public String getVersie(){
		return versie;
	}
}
