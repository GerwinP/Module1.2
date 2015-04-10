package players;

import java.util.Random;

import utils.PlayerColor;

public class ComputerPlayer extends Player{

	private final int amountColumns = 7;
	
	/**
	 * Creates the <code>ComputerPlayer</code> as an extension of <code>Player</code>
	 * @param theName
	 * @param theColor
	 */
	public ComputerPlayer(String theName, PlayerColor theColor){
		super(theName, theColor);
	}
	
	@Override
	/**
	 * Determines a random move that is then past to the <code>Game</code>
	 */
	public int determineMove() {
		Random random = new Random();
		int move = random.nextInt(amountColumns);
		return move;
	}

}
