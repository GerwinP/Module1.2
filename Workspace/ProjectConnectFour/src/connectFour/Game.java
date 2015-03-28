package connectFour;

import java.util.Observable;

import gui.BoardGUI;
import players.HumanPlayer;
import players.Player;
import utils.PlayerColor;

public class Game extends Observable {
	
	public static final int NUMBER_PLAYERS = 2;
	
	private Board board;
	
	private BoardGUI gui;
	
	private Player[] players;
	
	private int current;
	
	public Game(Player p1, Player p2){
		board = new Board();
		gui = new BoardGUI(this);
		players = new Player[NUMBER_PLAYERS];
		players[0] = p1;
		players[1] = p2;
		current = 0;
	}
	
	public void start(){
		boolean goOn = true;
		while(goOn){
			reset();
			play();
		}
	}
	
	private void reset(){
		current = 0;
		board.reset();
	}
	
	private void play(){
		while(!board.gameOver()){
			players[current].makeMove(board);
			current = (current+1) % NUMBER_PLAYERS;
		}
	}
	
	public PlayerColor getCurrentPlayer(){
		return players[current].getPlayerColor();
	}
	
	public void takeTurn(){
		
	}
	
	public static void main(String[] args){
		Player p1 = new HumanPlayer("Gerwin", PlayerColor.RED);
		Player p2 = new HumanPlayer("Josje", PlayerColor.YELLOW);
		Game game = new Game(p1, p2);
	}
}
