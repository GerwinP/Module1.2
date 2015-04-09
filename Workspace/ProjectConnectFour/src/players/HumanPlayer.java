package players;

import utils.PlayerColor;

public class HumanPlayer extends Player{

	public HumanPlayer(String theName, PlayerColor theColor) {
		super(theName, theColor);
	}

	public int determineMove() {
		return 0;
	}
	
}
