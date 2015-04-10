package server;

import gui.ServerGUI;

import java.util.Iterator;
import java.util.List;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import utils.ServerProtocol;
import connectFour.Game;

public class Server extends Thread implements ServerProtocol{

	public Game game;
	private int port;
	private List<ClientHandler> threads;
	public ServerSocket serverSocket = null;
	private static final String USAGE = "usage: " + Server.class.getName() + " <port>";
	private List<String> clientNames = new ArrayList<String>();
	public final String versie = "100";
	public List<ClientHandler> waitingForGame = new ArrayList<ClientHandler>();
	private List<ServerGame> servergames = new ArrayList<ServerGame>();
	private ServerGUI gui;
	
	public Server(int portArg, ServerGUI servergui) {
		port = portArg;
		threads = new ArrayList<ClientHandler>();
		gui = servergui;
		System.out.println("Server started on port " + portArg);
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
		gui.addMessage(message);
	}
	
	public void broadcast(List<ClientHandler> chs, String msg){
		Iterator<ClientHandler> iterator = chs.iterator();
		print("Message: " + msg);
		while(iterator.hasNext()){
			iterator.next().sendMessage(msg);
		}
	}
	
	public void broadcast(String msg) {
		Iterator<ClientHandler> iterator = threads.iterator();
		
		print("Message: " + msg);
		
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
		updateClientList();
	}
	
	public void removeClientName(String name){
		clientNames.remove(name);
		updateClientList();
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
	
	public void updateClientList(){
		gui.addClients(clientNames);
	}
	
	public boolean checkClientNames(String name){
		boolean nameAvailable = true;
		for(int i = 0; nameAvailable && i < clientNames.size(); i++){
			if(clientNames.get(i).equals(name)){
				nameAvailable = false;
			}
		}
		return nameAvailable;
	}
	
	private void shutDown(){
		System.out.println("Shutting down");
		System.exit(0);
	}
	
	class ServerGUIController extends WindowAdapter implements ActionListener{

		public void WindowClosing(WindowEvent e){
			Window w = e.getWindow();
			w.dispose();
		}
		
		public void WindowClosed(WindowEvent e){
			shutDown();
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
