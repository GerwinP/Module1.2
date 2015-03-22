package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

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
		assertEquals("Compare empty board and copy", copyBoard, board);
	}
}
