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
	
	/**
	 * Creates a new <code>Game</code> with two <code>Player</code>s
	 * Also adds an <code>Observer</code> to the <code>BoardGUI</code>
	 * @param p1
	 * @param p2
	 */
	public Game(Player p1, Player p2){
		board = new Board();
		players = new Player[NUMBER_PLAYERS];
		players[0] = p1;
		players[1] = p2;
		current = 0;
	}
	
	/**
	 * Resets the <code>Game</code> and then starts a new <code>Game</code>
	 */
	public void start(){
		boolean goOn = true;
		while(goOn){
			reset();
			play();
		}
	}
	
	/**
	 * Calls the reset method from <code>Board</code>
	 * and sets the current <code>Player</code> to p1
	 */
	private void reset(){
		current = 0;
		board.reset();
	}
	
	/**
	 * Waits for input from a <code>Player</code> untill the <code>Game</code> is over
	 */
	private void play(){
		while(!board.gameOver()){
			players[current].makeMove(board);
			current = (current+1) % NUMBER_PLAYERS;
		}
	}
	
	/**
	 * Returns the current <code>PlayerColor</code>
	 * @return
	 */
	public PlayerColor getCurrentPlayer(){
		return players[current].getPlayerColor();
	}
	
	public void takeTurn(int index){
		int field = board.checkForFreeSpot(index, players[current].getPlayerColor());
		board.setField(field, players[current].getPlayerColor());
		setChanged();
		notifyObservers(field);
		current = (current+1) % NUMBER_PLAYERS;
	}
	
	public static void main(String[] args){
		Player p1 = new HumanPlayer("Gerwin", PlayerColor.RED);
		Player p2 = new HumanPlayer("Josje", PlayerColor.YELLOW);
		Game game = new Game(p1, p2);
	}
}
