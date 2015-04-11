package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import utils.PlayerColor;
import connectFour.*;

public class TestBoard {

	public Board board;
	private int maxFields = 42;
	
	/**
	 * The initial setup.
	 * Called before every test.
	 * It creates a new Board.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		board = new Board();
	}
	
	/**
	 * Tests for several inputs if it is a field on the Board or not. 
	 * Both tested with existing fields and non existing fields.
	 */
	@Test
	public void testIsField(){
		assertEquals("With a correct field and index", true, board.isField(3));
		assertEquals("With a wrong field and index", false, board.isField(45));
		assertEquals("With a correct field, row and col", true, board.isField(3,4));
		assertEquals("With a wrong field, row and col", false, board.isField(6,7));
	}
	
	/**
	 * Test for the getField method. It should return the PlayerColor of a field.
	 * Tested with a none existing field, an empty field and a colored field.
	 */
	@Test
	public void testgetField(){
		assertEquals("With a none existing field", null, board.getField(45));
		assertEquals("With none existing row and col", null, board.getField(6,7));
		assertEquals("With existing field", PlayerColor.EMPTY, board.getField(32));
		assertEquals("With existing row and col", PlayerColor.EMPTY, board.getField(3,4));
		board.setField(32, PlayerColor.RED);
		board.setField(3,4,PlayerColor.YELLOW);
		assertEquals("With filled in field", PlayerColor.RED, board.getField(32));
		assertEquals("With filled in row and col", PlayerColor.YELLOW, board.getField(3,4));
	}
	
	/**
	 * Test for the isEmptyField method.
	 * Tests if a field is empty. First checked two empty fields, after that colored those fields and checked those again
	 */
	@Test
	public void testIsEmptyField(){
		assertEquals("With an empty field 32", true, board.isEmptyField(32));
		assertEquals("With empty row and col 3,4", true, board.isEmptyField(3,4));
		board.setField(32, PlayerColor.RED);
		board.setField(3,4,PlayerColor.YELLOW);
		assertEquals("With an Red field 32", false, board.isEmptyField(32));
		assertEquals("With Yellow row and col 3,4", false, board.isEmptyField(3,4));
	}
	
	/**
	 * Tests if the board is full.
	 * Start with an empty board.
	 * Filled the board and called the method again.
	 */
	@Test
	public void testIsFull(){
		assertEquals("Start with an empty board", false, board.isFull());
		for(int i = 0; i < maxFields; i++){
			board.setField(i, PlayerColor.RED);
		}
		assertEquals("End with full board", true, board.isFull());
	}
	
	/**
	 * Test for the horizontalCount. The method checks if there is a row of four on the horizontal.
	 * First check for a stone with no row, and then create a row and check again.
	 */
	@Test
	public void testCountHorizontal(){
		board.countHorizontal(2,3,PlayerColor.RED);
		assertEquals("Test with no horizontal line", 0, board.getHorizontalCount());
		board.setField(2,2,PlayerColor.RED);
		board.setField(2,1,PlayerColor.RED);
		board.setField(2,4,PlayerColor.RED);
		board.countHorizontal(2,3,PlayerColor.RED);
		assertEquals("Test with a horizontal line", 3, board.getHorizontalCount());
	}
	
	/**
	 * Test for the verticalCount. Checks if there is a vertical row of four.
	 * First check for a stone with no row, then create a vertical row and check again.
	 */
	@Test
	public void testVerticalCount(){
		board.countVertical(2, 2, PlayerColor.YELLOW);
		assertEquals("Test with no vertical line", 0 , board.getVerticalCount());
		board.setField(4,2,PlayerColor.YELLOW);
		board.setField(3,2,PlayerColor.YELLOW);
		board.setField(5,2,PlayerColor.YELLOW);
		board.countVertical(2,2,PlayerColor.YELLOW);
		assertEquals("Test with a vertical line", 3 , board.getVerticalCount());
	}
	
	/**
	 * Test for the diagonalLeftCount. Checks if there is a diagonal row of four from right to left.
	 * First check for a stone with no row, then create a diagonal row and check again.
	 */
	@Test
	public void testCountDiagonalLeft(){
		board.countDiagonalLeft(3,2,PlayerColor.RED);
		assertEquals("Test with no diagonal line", 0, board.getDiagonalLeftCount());
		board.setField(2,1,PlayerColor.RED);
		board.setField(4,3,PlayerColor.RED);
		board.setField(5, 4, PlayerColor.RED);
		board.countDiagonalLeft(3,2,PlayerColor.RED);
		assertEquals("Test with a diagonal line", 3, board.getDiagonalLeftCount());
	}
	
	/**
	 * Test for the diagonalRightCount. Checks if there is a diagonal row from left to right.
	 * First check for a stone with no row, then create a diagonal row and check again.
	 */
	@Test
	public void testCountDiagonalRight(){
		board.countDiagonalRight(2,3,PlayerColor.YELLOW);
		assertEquals("Test with no diagonal line", 0, board.getDiagonalRightCount());
		board.setField(1,4,PlayerColor.YELLOW);
		board.setField(3,2,PlayerColor.YELLOW);
		board.setField(4, 1, PlayerColor.YELLOW);
		board.countDiagonalRight(2, 3, PlayerColor.YELLOW);
		assertEquals("Test with a diagonal line", 3, board.getDiagonalRightCount());
	}
	
	/**
	 * Test for the checkForFreeSpot. It should make sure that a stone will be always at the lowest point of a column.
	 * So first an empty row, then set some fields, check again, set some more fields and check again.
	 */
	@Test
	public void testCheckForFreeSpot(){
		assertEquals("Test with no other field filled", 38, board.checkForFreeSpot(3,  PlayerColor.RED));
		board.setField(38, PlayerColor.RED);
		board.setField(31, PlayerColor.YELLOW);
		board.setField(24, PlayerColor.RED);
		assertEquals("Test with 3 fields filled", 17, board.checkForFreeSpot(3, PlayerColor.RED));
		board.setField(17, PlayerColor.RED);
		board.setField(10, PlayerColor.RED);
		assertEquals("Test with 5 fields filled", 3, board.checkForFreeSpot(3, PlayerColor.RED));
	}
}
