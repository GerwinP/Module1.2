package server;

import java.util.List;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	private int port;
	private List<ClientHandler> threads;
	public ServerSocket serverSocket = null;
	
	public Server(int portArg){
		port = portArg;
		threads = new ArrayList<ClientHandler>();
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("Could not create a ServerSocket on port " + port);
		}
	}
	
	public void run(){
		
	}
	
	public void print(String message){
		System.out.println(message);
	}
	
	public void broadcast(String msg){
		
	}
	
	public void addHandler(ClientHandler handler){
		
	}
	
	public void removeHandler(ClientHandler handler){
		
	}
}
