package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import utils.PlayerColor;
import connectFour.*;

public class TestBoard {

	public Board board;
	
	@Before
	public void setUp() throws Exception {
		board = new Board();
	}

	@Test
	public void testDeepCopy(){
		Board copyBoard = board.deepCopy();
		PlayerColor[] boardFields = board.getFields();
		PlayerColor[] copyFields = copyBoard.getFields();
		for(int i = 0; i < boardFields.length; i++){
			assertEquals("The " + i + "th field of board", copyFields[i], boardFields[i]);
		}
	}
	
	@Test
	public void testIsField(){
		assertEquals("With a correct field and index", board.isField(3), true);
		assertEquals("With a wrong field and index", board.isField(45), false);
		assertEquals("With a correct field, row and col", board.isField(3,4), true);
		assertEquals("With a wrong field, row and col", board.isField(6,7), false);
	}
	
	@Test
	public void testgetField(){
		assertEquals("With a none existing field", board.getField(45), null);
		assertEquals("With none existing row and col", board.getField(6,7), null);
		assertEquals("With existing field", board.getField(32), PlayerColor.EMPTY);
		assertEquals("With existing row and col", board.getField(3,4), PlayerColor.EMPTY);
		board.setField(32, PlayerColor.RED);
		board.setField(3,4,PlayerColor.YELLOW);
		assertEquals("With filled in field", board.getField(32), PlayerColor.RED);
		assertEquals("With filled in row and col", board.getField(3,4), PlayerColor.YELLOW);
	}
}
