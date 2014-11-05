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
		for(int x = 36; x <= 38 ; x++){
			Board.setColor(x, PlayerColor.RED);
		}
		Board.setStone(0);
		System.out.println(Game.getHorizontalCount());
		return false;
	}
	
	public static void main(String[] args) {
		new GameTest();
	}

}
