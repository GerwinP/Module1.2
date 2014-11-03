package connectFour;

import utils.PlayerColor;
import gui.BoardGUI;
import connectFour.Game;

import java.awt.Color;

public class Board {
	
	static int Dimension = 7;
	int buttonNumber;
	
	public static int indexButton(int row, int col){
		return Dimension * row + col;
	}
	
	public Color getBackgroundColor(int row, int col){
		buttonNumber = Dimension * row + col;
		Color background = BoardGUI.buttons[buttonNumber].getBackground();
		return background;
	}
	
	public Color getBackgroundColor(int buttonNumber){
		Color background = BoardGUI.buttons[buttonNumber].getBackground();
		return background;
	}
	
	public void setBackgroundColor(int buttonNumber, PlayerColor playercolor ){
		if(playercolor == PlayerColor.RED){
			BoardGUI.buttons[buttonNumber].setBackground(Color.RED);
		}else if(playercolor == PlayerColor.YELLOW){
			BoardGUI.buttons[buttonNumber].setBackground(Color.YELLOW);
		}else{
			BoardGUI.buttons[buttonNumber].setBackground(Color.BLACK);
		}
	}
	
	public void setStone(int row, int col){
		//Check if place under the stone is free
		buttonNumber = Dimension * row + col;
		int buttonBelow = Dimension * (row + 1) + col;
		if(getBackgroundColor(buttonBelow) == Color.BLACK){
			if(Game.getCurrentPlayer() == PlayerColor.RED){
				setBackgroundColor(buttonNumber, PlayerColor.NOCOLOR);
				setBackgroundColor(buttonBelow, PlayerColor.RED);
			}else{
				setBackgroundColor(buttonNumber, PlayerColor.NOCOLOR);
				setBackgroundColor(buttonBelow, PlayerColor.YELLOW);
			}
		}
	}
}
