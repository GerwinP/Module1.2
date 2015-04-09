package players;

import java.util.Random;

import utils.PlayerColor;

public class ComputerPlayer extends Player{

	private final int amountColumns = 7;
	
	public ComputerPlayer(String theName, PlayerColor theColor){
		super(theName, theColor);
	}
	
	@Override
	public int determineMove() {
		Random random = new Random();
		int move = random.nextInt(amountColumns);
		return move;
	}

}
