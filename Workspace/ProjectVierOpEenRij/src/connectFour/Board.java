package connectFour;

import utils.PlayerColor;
import gui.BoardGUI;
import connectFour.Game;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

public class Board {

	static int Dimension = 7;
	static int buttonNumber;
	static boolean nextCheck = true;

	public static int indexButton(int row, int col) {
		return Dimension * row + col;
	}

	public static Color getColor(int row, int col) {
		buttonNumber = Dimension * row + col;
		Color background = BoardGUI.buttons[buttonNumber].getBackground();
		return background;
	}

	public static Color getColor(int buttonNumber) {
		Color background = BoardGUI.buttons[buttonNumber].getBackground();
		return background;
	}

	public static void setColor(int buttonNumber, PlayerColor playercolor) {
		if (playercolor == PlayerColor.RED) {
			BoardGUI.buttons[buttonNumber].setBackground(Color.RED);
		} else if (playercolor == PlayerColor.YELLOW) {
			BoardGUI.buttons[buttonNumber].setBackground(Color.YELLOW);
		} else {
			BoardGUI.buttons[buttonNumber].setBackground(Color.BLACK);
		}
	}

	public static void setStone(int row, int col) {
		// Check if place under the stone is free
		nextCheck = true;
		buttonNumber = Dimension * row + col;
		int buttonBelow = Dimension * (row + 1) + col;
		PlayerColor currentplayer = Game.getCurrentPlayer();

		if (getColor(buttonNumber) == Color.BLACK && getColor(buttonBelow) != Color.BLACK) {
			setColor(buttonNumber, currentplayer);
		} else {
			while (nextCheck && getColor(buttonBelow) == Color.BLACK) {
				try {
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (currentplayer == PlayerColor.RED) {
					setColor(buttonNumber, PlayerColor.EMPTY);
					setColor(buttonBelow, PlayerColor.RED);
				} else {
					setColor(buttonNumber, PlayerColor.EMPTY);
					setColor(buttonBelow, PlayerColor.YELLOW);
				}
				buttonBelow = buttonBelow + 7;
				buttonNumber = buttonNumber + 7;
				if (buttonBelow >= 42 || buttonNumber >= 42) {
					nextCheck = false;
				}
			}
		}
		Game.nextTurn();
	}
}
