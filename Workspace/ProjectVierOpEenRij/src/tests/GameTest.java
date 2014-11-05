package tests;

import connectFour.*;
import gui.BoardGUI;
import utils.*;

public class GameTest {

	public GameTest(){
		new BoardGUI();
		testHorizontal();
	}
	
	private static void testSetGameState(String gamestate){
		Game.setGameState(gamestate);
	}
	
	private static boolean testHorizontal(){
		Board.setColor(35, PlayerColor.RED);
		Board.setColor(38, PlayerColor.RED);
		Board.setColor(39, PlayerColor.RED);
		Board.setStone(2);
		return false;
	}
	
	public static void main(String[] args) {
		new GameTest();
	}

}
