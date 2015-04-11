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
	
	/**
	 * The initial setup.
	 * And is called after every test.
	 * It creates two new Players, one computerplayer and one humanplayer.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		player1 = new HumanPlayer("Gerwin", PlayerColor.YELLOW);
		player2 = new ComputerPlayer("computer", PlayerColor.RED);
	}

	/**
	 * Tests if the getPlayerColor method from Player returns the correct PlayerColor
	 */
	@Test
	public void testGetColor() {
		assertEquals("Test getPlayerColor from player1", PlayerColor.YELLOW, player1.getPlayerColor());
		assertEquals("Test getPlayerColor from player2", PlayerColor.RED, player2.getPlayerColor());
	}
	
	/**
	 * Tests if the getName method from Player return the correct name
	 */
	@Test
	public void testGetName(){
		assertEquals("Test getName from player1", "Gerwin", player1.getName());
		assertEquals("Test getName from player2", "computer", player2.getName());
	}

	/**
	 * Tests if the determineMove from HumanPlayer and ComputerPlayer return the correct values,
	 * For HumanPlayer should that be 0, and for the ComputerPlayer a number between 0 and 6
	 */
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
