package connectFour;

import utils.GameState;
import utils.PlayerColor;
import gui.BoardGUI;
import connectFour.Game;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import players.Player;

public class Board {

	private int MaxRow = 6;
	private int MaxCol = 7;
	public static int DIM = 7;
	private int buttonNumber;
	private static int x;
	private static int y;
	private Game game;
	private BoardGUI boardgui;
	private PlayerColor currentColor;

	//Server constructor
	public Board(Game game, BoardGUI gui){
		this.game = game;
		boardgui = gui;
	}
	
	//Client constructor
	public Board(BoardGUI gui){
		boardgui = gui;
		currentColor = PlayerColor.RED;
	}
	
	private void otherColor(PlayerColor color){
		if(color == PlayerColor.RED){
			currentColor = PlayerColor.YELLOW;
		}else{
			currentColor = PlayerColor.RED;
		}
	}
	
	public static int getIndexButton(int row, int col) {
		return DIM * row + col;
	}

	public String getColor(int row, int col) {
		buttonNumber = getIndexButton(row, col);
		return getColor(buttonNumber);
	}

	public String getColor(int buttonNumber) {
		Color background = boardgui.buttons[buttonNumber].getBackground();
		String color;
		if(background == Color.BLACK){
			color = "BLACK";
		}else if(background == Color.RED){
			color = "RED";
		}else if(background == Color.YELLOW){
			color = "YELLOW";
		}else{
			color = "NOTFOUND";
		}
		return color;
	}

	public void setColor(int buttonNumber, PlayerColor playercolor) {
		if (playercolor == PlayerColor.RED) {
			boardgui.buttons[buttonNumber].setBackground(Color.RED);
		} else if (playercolor == PlayerColor.YELLOW) {
			boardgui.buttons[buttonNumber].setBackground(Color.YELLOW);
		} else {
			boardgui.buttons[buttonNumber].setBackground(Color.BLACK);
		}
	}

	public void setCurrentPlayer(){
		Player currentplayer = game.getCurrentPlayer();
		currentColor = currentplayer.getPlayerColor();
	}
	
	public void setStone(int col){
		boolean goOn = true;
		boolean inprogress = (game.getGameState() == GameState.INPROGRESS);
		for(int row = 0;inprogress && goOn && row < MaxRow; row++){
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			buttonNumber = getIndexButton(row, col);
			int buttonNext = MaxCol * (row+1) + col;
			if(buttonNext < 42 && !getColor(buttonNext).equals("BLACK")){
				goOn = false;
			}
			if(row == 0 && getColor(buttonNumber).equals("BLACK")){
				setColor(buttonNumber, currentColor);
			}else{
				int previousButton = MaxCol * (row-1) + col;
				if(getColor(buttonNumber).equals("BLACK")){
					setColor(previousButton, PlayerColor.EMPTY);
					setColor(buttonNumber, currentColor);
				}
			}
		}
		setCoordinates(buttonNumber, col);
		game.countColor(x,y, currentColor);
	}
	
	public int getLastSetStone(){
		return buttonNumber;
	}
	
	public boolean isValidMove(int index){
		boolean isValid = false;
		if(index >= 0 && index < 7){
			isValid = true;
		}
		return isValid;
	}
	
	public static void setCoordinates(int buttonNumber,int col){
		int button = buttonNumber - col;
		x = button / 7;
		y = col;
	}
}
