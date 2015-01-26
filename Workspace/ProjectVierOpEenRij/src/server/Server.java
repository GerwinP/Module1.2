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
	private ServerSocket serverSocket = null;

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
				ClientHandler clientHandler = new ClientHandler(clientSocket, this);
				addHandler(clientHandler);
				clientHandler.start();
			}
		} catch (IOException e) {

		}
	}

	public void print(String message) {
		System.out.println(message);
	}

	public void broadcast(String msg) {
		Iterator<ClientHandler> iterator = threads.iterator();
		
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
}
