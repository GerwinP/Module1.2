package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import utils.PlayerColor;
import connectFour.*;

public class TestBoard {

	public Board board;
	private int maxFields = 42;
	
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
	
	@Test
	public void testIsEmptyField(){
		assertEquals("With an empty field 32", board.isEmptyField(32), true);
		assertEquals("With empty row and col 3,4", board.isEmptyField(3,4), true);
		board.setField(32, PlayerColor.RED);
		board.setField(3,4,PlayerColor.YELLOW);
		assertEquals("With an Red field 32", board.isEmptyField(32), false);
		assertEquals("With Yellow row and col 3,4", board.isEmptyField(3,4), false);
	}
	
	@Test
	public void testIsFull(){
		assertEquals("Start with an empty board", board.isFull(), false);
		for(int i = 0; i < 42; i++){
			board.setField(i, PlayerColor.RED);
		}
		assertEquals("End with full board", board.isFull(), true);
	}
	
	@Test
	public void testGameOver(){
//		assertEquals("With an empty board and no winner")
	}
}
