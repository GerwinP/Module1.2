package server;

import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import utils.ServerProtocol;
import connectFour.Game;

public class Server implements ServerProtocol{

	public Game game;
	private int port;
	private List<ClientHandler> threads;
	public ServerSocket serverSocket = null;
	private static final String USAGE = "usage: " + Server.class.getName() + " <port>";
	private List<String> clientNames = new ArrayList<String>();
	public final String versie = "100";
	public List<ClientHandler> waitingForGame = new ArrayList<ClientHandler>();
	private List<ServerGame> servergames = new ArrayList<ServerGame>();
	
	public static void main(String[] args){
		if (args.length != 1) {
			System.out.println(USAGE);
			System.exit(0);
		}
		
		Server server = new Server(Integer.parseInt(args[0]));
		server.run();
	}
	
	public Server(String arg){
		Server server = new Server(Integer.parseInt(arg));
//		server.run();
		System.out.println("Server started on port " + arg);
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
			}
		} catch (IOException e) {
			System.out.println("Stuk");
		}
	}

	public void print(String message) {
		System.out.println(message);
	}
	
	public void broadcast(List<ClientHandler> chs, String msg){
		Iterator<ClientHandler> iterator = chs.iterator();
		print(msg);
		while(iterator.hasNext()){
			iterator.next().sendMessage(msg);
		}
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
	
	public void makeGame(){
		ClientHandler p1 = waitingForGame.get(0);
		ClientHandler p2 = waitingForGame.get(1);
		waitingForGame.remove(p1);
		waitingForGame.remove(p2);
		ServerGame serverGame = new ServerGame(p1, p2, this);
		servergames.add(serverGame);
		p1.setServerGame(serverGame);
		p2.setServerGame(serverGame);
	}
	
	public void removeGame(ServerGame serverGame){
		servergames.remove(serverGame);
	}
}
