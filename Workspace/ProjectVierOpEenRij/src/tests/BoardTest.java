package tests;

import java.util.concurrent.TimeUnit;

import connectFour.Board;
import connectFour.Game;
import gui.BoardGUI;
import utils.PlayerColor;

public class BoardTest {

	private int row = 6;
	private int col = 7;
	
	public BoardTest(){
		new BoardGUI();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int x = 0; x < row; x++){
			for(int y = 0; y < col; y++){
			//for(int y = 0; y < col; y++){
				Board.setStone(0,y);
			}
		}
		
	}
	
	public static void main(String[] args) {
		new BoardTest();

	}

}
