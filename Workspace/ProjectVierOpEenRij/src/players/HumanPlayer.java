package players;

import utils.PlayerColor;
import connectFour.Board;

public class HumanPlayer extends Player{

	public HumanPlayer(String name, PlayerColor color){
		super(name,color);
	}
	
	@Override
	public int determineMove(Board board) {
		
		return 0;
	}

}
