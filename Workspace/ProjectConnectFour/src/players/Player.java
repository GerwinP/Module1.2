package players;

import connectFour.Board;
import utils.PlayerColor;

public abstract class Player {
	
	private String name;
	private PlayerColor color;
	
	/**
	 * Creates a new player with a name and a color
	 * @param theName
	 * @param theColor
	 */
	public Player(String theName, PlayerColor theColor){
		name = theName;
		color = theColor;
	}
	
	/**
	 * Returns the name from the <code>Player</code>
	 * @return
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Returns the <code>PlayerColor</code> from this <code>Player</code>
	 * @return
	 */
	public PlayerColor getPlayerColor(){
		return color;
	}
	
	/**
	 * Abstract class for determining a move according to the current state of a <code>Board</code>
	 * and returns an possible move
	 * @param board
	 * @return
	 */
	public abstract int determineMove();
	
	/**
	 * Calls <code>determineMove</code> to get a possible move and plays this on the <code>Board</code>
	 * @param board
	 */
	public void makeMove(Board board){
		int keuze = determineMove();
		board.setField(keuze, getPlayerColor());
	}
}
