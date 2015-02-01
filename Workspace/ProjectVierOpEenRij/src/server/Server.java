package server;

import gui.BoardGUI;

import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import players.HumanPlayer;
import players.Player;
import utils.GameState;
import utils.PlayerColor;
import utils.ServerProtocol;
import connectFour.Board;
import connectFour.Game;

public class Server implements ServerProtocol{

	public Game game;
	public GameState gamestate;
	private int port;
	private List<ClientHandler> threads;
	public ServerSocket serverSocket = null;
	private static final String USAGE = "usage: " + Server.class.getName() + " <port>";
	private List<String> clientNames = new ArrayList<String>();
	public final String versie = "000";
	public List<ClientHandler> waitingForGame = new ArrayList<ClientHandler>();
	private List<ClientHandler> inGame = new ArrayList<ClientHandler>();
	private Board board;
	private BoardGUI gui;
	
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
	
	public void broadcastInGame(String msg){
		Iterator<ClientHandler> iterator = inGame.iterator();
		
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
		inGame.add(p1);
		inGame.add(p2);
		Player player1 = new HumanPlayer(p1.getClientName(), PlayerColor.RED);
		Player player2 = new HumanPlayer(p2.getClientName(), PlayerColor.YELLOW);
		game = new Game(player1, player2);
		game.setGameState("inprogress");
		gamestate = game.getGameState();
		System.out.println(gamestate.toString());
//		while(gamestate == GameState.INPROGRESS){
//			if(gamestate == GameState.FINISHED){
//				broadcastInGame("The winner is " + game.getWinner());
//			}
//		}
	}
	
	public void makeMove(int index){
		if(!game.board.isValidMove(index)){
			broadcastInGame("Move not valid");
		}else{
			game.board.setStone(index);
			broadcastInGame(MAKE_MOVE + " " + index);
		}
		
		System.out.println("makeMove " + index);
		
	}
}
