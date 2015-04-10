package connectFour;

import java.util.Observable;

import players.Player;
import utils.PlayerColor;

/**
 * The class that makes the game start, progresses and stops with the correct result.
 * It also keeps track of the current player and knows the <code>Board</code>
 * @author Gerwin Puttenstein
 *
 */
public class Game extends Observable {
	
	public static final int NUMBER_PLAYERS = 2;
	public Board board;
	private Player[] players;
	private int current;
	private static final int DIM = 7;
	private boolean gameOver = false;
	
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
	 * Returns the current <code>PlayerColor</code>
	 * @return
	 */
	public PlayerColor getCurrentPlayer(){
		return players[current].getPlayerColor();
	}
	
	/**
	 * The method so a standalone game can be played.
	 * It receives an index, checks if this field is free, sets the field, sets its state to changed and notifies the Observers.
	 * Finally, it picks the next player
	 * @param index
	 */
	public void takeTurn(int index){
		if(!gameOver){
			int toPlay = index;
//			if(players[current] instanceof ComputerPlayer){
//				toPlay = players[current].determineMove(board);
//			}
			int field = board.checkForFreeSpot(toPlay, players[current].getPlayerColor());
			board.setField(field, players[current].getPlayerColor());
			setChanged();
			notifyObservers(field);
			int row = (field - toPlay) / DIM;
			if(board.gameOver(row, toPlay, players[current].getPlayerColor())){
				setChanged();
				notifyObservers("Game Over");
			}
			current = (current+1) % NUMBER_PLAYERS;
		} 
	}
	
	/**
	 * Returns true if the game is over.
	 * @return
	 */
	public boolean gameOver(){
		return gameOver;
	}
}
