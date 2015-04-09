package connectFour;

import java.util.Observable;

import players.ComputerPlayer;
import players.HumanPlayer;
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
		gameOver = false;
		board.reset();
	}
	
	/**
	 * Waits for input from a <code>Player</code> until the <code>Game</code> is over
	 */
	private void play(){
		while(!board.gameOver()){
			if(players[current] instanceof HumanPlayer){
				takeTurn(players[current].determineMove());
			}
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
			boolean isWinner = board.isWinner(row, toPlay, players[current].getPlayerColor());
			gameOver = isWinner || board.isFull();
			if(gameOver){
				setChanged();
				notifyObservers("Game Over");
			}
			current = (current+1) % NUMBER_PLAYERS;
		} else{
			
		}
	}
	
	public boolean gameOver(){
		return gameOver;
	}
	
	public static void main(String[] args){
//		Player p1 = new HumanPlayer("Gerwin", PlayerColor.RED);
//		Player p2 = new HumanPlayer("Josje", PlayerColor.YELLOW);
//		Game game = new Game(p1, p2);
	}
}
