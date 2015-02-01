package players;

import connectFour.Board;
import utils.PlayerColor;
import utils.*;
import connectFour.*;

public abstract class Player {
	
	private String name;
	private PlayerColor color;
	
	public Player(String nameArg, PlayerColor colorArg){
		name = nameArg;
		color = colorArg;
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
		board.setStone(keuze);
	}
}
