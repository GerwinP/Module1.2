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
	
	public String getName(){
		return name;
	}
	
	public PlayerColor getPlayerColor(){
		return color;
	}
	
	public abstract int determineMove(Board board);
	
	public void makeMove(Board board){
		int keuze = determineMove(board);
		board.setField(keuze, getPlayerColor());
	}
}
