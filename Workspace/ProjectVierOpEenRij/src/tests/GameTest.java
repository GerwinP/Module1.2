package tests;

import connectFour.*;
import utils.*;

public class GameTest {

	private PlayerColor yellow = PlayerColor.YELLOW;
	private PlayerColor red = PlayerColor.RED;
	private Game game;
	
	public GameTest(){
		game = new Game();
		game.setGameState("inprogress");
//		testHorizontal();
//		testVertical();
//		testDiagonalLeft();
//		testDiagonalRight();
//		testDiagonalExtreme();
//		testDiagonalLeftBoth();
		testNonStatic();
//		testEndValues();
	}

	private void testEndValues(){
		System.out.println("After all checks h: " + game.getHorizontalCount());
		System.out.println("After all checks v: " + game.getVerticalCount());
		System.out.println("After all checks dl: " + game.getDiagonalLeftCount());
		System.out.println("After all checks dr: " + game.getDiagonalRightCount());
		System.out.println("The gamestate: " + game.getGameState());
		System.out.println("The winner: " + game.getWinner());
	}
	
	private void testVertical(){
		game.board.setColor(23, red);
		game.board.setColor(30, red);
		game.board.setColor(37, red);
		game.board.setStone(2);
	}
	
	private void testHorizontal(){
		game.board.setColor(36, yellow);
		game.board.setColor(37, yellow);
		game.board.setColor(39, yellow);
		game.board.setColor(38, red);
		
		game.board.setColor(29, red);
		game.board.setColor(31, red);
		game.board.setColor(32, red);
		game.board.setStone(2);
	}
	
	private void testDiagonalLeft(){
		for(int x = 0; x < 3; x++){
			game.board.setStone(2);
		}
		for(int x = 0; x < 2; x++){
			game.board.setStone(3);
		}
		game.board.setColor(24, red);
		game.board.setColor(32, red);
//		game.board.setColor(16, red);
		game.board.setColor(40, red);
		game.board.setColor(39, yellow);
		
		game.board.setStone(1);
		game.board.setStone(2);
//		game.board.setStone(5);
	}
		
	private void testDiagonalRight(){
		for(int x = 0; x < 3; x++){
			game.board.setStone(4);
		}
		for(int y = 0; y < 2; y++){
			game.board.setStone(3);
		}
		game.board.setColor(24, red);
		game.board.setColor(37, yellow);
		game.board.setColor(30, red);
		game.board.setColor(36, red);
		
		game.board.setStone(5);
		game.board.setStone(4);
	}
	
	public void testDiagonalLeftBoth(){
		game.board.setColor(40, red);
		game.board.setColor(16, red);
		game.board.setColor(32, red);
		game.board.setColor(8, red);
		
		game.board.setColor(15, yellow);
		game.board.setColor(23, yellow);
		game.board.setColor(31, yellow);
		game.board.setColor(39, yellow);
		
		game.board.setStone(3);
	}
	
	public void testDiagonalExtreme(){
		game.board.setColor(34, yellow);
		game.board.setColor(41, yellow);
		game.board.setStone(6);
	}
	
	public void testNonStatic(){
		game.board.setColor(1, red);
	}
	
	public static void main(String[] args) {
		new GameTest();
	}

}
