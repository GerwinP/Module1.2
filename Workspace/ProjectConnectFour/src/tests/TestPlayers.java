package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import players.ComputerPlayer;
import players.HumanPlayer;
import players.Player;
import utils.PlayerColor;

public class TestPlayers {

	private Player player1;
	private Player player2;
	
	@Before
	public void setUp() throws Exception {
		player1 = new HumanPlayer("Gerwin", PlayerColor.YELLOW);
		player2 = new ComputerPlayer("computer", PlayerColor.RED);
	}

	@Test
	public void testGetColor() {
		assertEquals("Test getPlayerColor from player1", PlayerColor.YELLOW, player1.getPlayerColor());
		assertEquals("Test getPlayerColor from player2", PlayerColor.RED, player2.getPlayerColor());
	}
	
	@Test
	public void testGetName(){
		assertEquals("Test getName from player1", "Gerwin", player1.getName());
		assertEquals("Test getName from player2", "computer", player2.getName());
	}

	@Test
	public void testDetermineMove(){
		assertEquals("Test determineMove() from player1", 0, player1.determineMove());
		int index = player2.determineMove();
		boolean inRange = false;
		if(index >= 0 && index <= 6){
			inRange = true;
		}
		assertEquals("Test determineMove() from player2", true, inRange);
	}
}
