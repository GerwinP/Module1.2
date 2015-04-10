package players;

import utils.PlayerColor;

public class HumanPlayer extends Player{

	/**
	 * Creates a new <code>HumanPlayer</code> a an extension of <code>Player</code>
	 * @param theName
	 * @param theColor
	 */
	public HumanPlayer(String theName, PlayerColor theColor) {
		super(theName, theColor);
	}

	/**
	 * An abstract method from <code>Player</code>. Not used by the <code>HumanPlayer</code>
	 */
	public int determineMove() {
		return 0;
	}
	
}
