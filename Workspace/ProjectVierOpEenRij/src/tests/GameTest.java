package tests;

import connectFour.*;
import gui.BoardGUI;
import utils.*;

public class GameTest {

	private static PlayerColor yellow = PlayerColor.YELLOW;
	private static PlayerColor red = PlayerColor.RED;
	
	public GameTest(){
		new BoardGUI();
//		testHorizontal();
//		testVertical();
		testDiagonalLeft();
		testEndValues();
	}

	private static void testEndValues(){
		System.out.println("After all checks h: " + Game.getHorizontalCount());
		System.out.println("After all checks v: " + Game.getVerticalCount());
		System.out.println("After all checks dl: " + Game.getDiagonalLeftCount());
		System.out.println("After all checks dr: " + Game.getDiagonalRightCount());
		System.out.println("The gamestate: " + Game.getGameState());
		System.out.println("The winner: " + Game.getWinner());
	}
	
	private static void testVertical(){
		Board.setColor(23, red);
		Board.setColor(30, red);
		Board.setColor(37, red);
		Board.setStone(2);
	}
	
	private static void testHorizontal(){
		Board.setColor(36, yellow);
		Board.setColor(37, yellow);
		Board.setColor(39, yellow);
		Board.setColor(38, red);
		
		Board.setColor(29, red);
		Board.setColor(31, red);
		Board.setColor(32, red);
		Board.setStone(2);
	}
	
	private static void testDiagonalLeft(){
		for(int x = 0; x < 3; x++){
			Board.setStone(2);
		}
		for(int x = 0; x < 2; x++){
			Board.setStone(3);
		}
		Board.setColor(24, red);
		Board.setColor(32, red);
		Board.setColor(40, red);
		Board.setColor(39, yellow);
		
		Board.setStone(1);
		Board.setStone(2);
	}
		
	public static void main(String[] args) {
		new GameTest();
	}

}
