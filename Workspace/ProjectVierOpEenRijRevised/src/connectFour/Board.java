package connectFour;

import connectFour.Game;
import gui.BoardGUI;
import utils.PlayerColor;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import players.Player;
import gui.*;
import utils.*;
import players.*;

public class Board {

	//initialisatie van de variabelen
	private int MaxRow = 6;
	private int MaxCol = 7;
	public static int DIM = 7;
	private int buttonNumber;
	public static int x;
	public static int y;
	private Game game;
	private BoardGUI boardgui;
	private PlayerColor currentColor;
	private boolean isClient = false;

	/**
	 * Contructor used by the <code>ServerGame</code>
	 * <code>ServerGame</code> has a different contructor because the <code>ServerGame</code> has a <code>Game</code> to hold
	 * @param game the <code>Game</code> that the <code>ServerGame</code> keeps
	 * @param gui the <code>BoardGUI</code> that the <code>ServerGame</code> keeps
	 */
	/*
	 * @	requires game != null;
	 * 		requires gui != null;
	 * 		ensures this.game == game;
	 * 		ensures this.boardgui == gui;
	 */
	public Board(Game game, BoardGUI gui){
		this.game = game;
		boardgui = gui;
	}
	
	/**
	 * The constructor the <code>Client</code> uses.
	 * This is because the <code>Client</code> only needs a <code>Board</code> to keep track off
	 * @param gui the <code>BoardGUI</code> from the <code>Client</code>
	 */
	/*
	 * @	requires gui != null;
	 * 		ensures this.boardgui == gui;
	 * 		ensures this.currentColor == PlayerColor.RED;
	 * 		ensures isClient;
	 */
	public Board(BoardGUI gui){
		boardgui = gui;
		currentColor = PlayerColor.RED;
		isClient = true;
	}
	
	/**
	 * Method to switch from one <code>PlayerColor</code> to the other <code>PlayerColor</code>
	 */
	/*
	 * @ ensures currentColor == PlayerColor.RED || currentColor == PlayerColor.YELLOW;
	 */
	private void otherColor(){
		if(currentColor == PlayerColor.RED){
			currentColor = PlayerColor.YELLOW;
		}else{
			currentColor = PlayerColor.RED;
		}
	}
	
	/**
	 * The method to get the index of a button given the row and column
	 * @param row the row of a button
	 * @param col the column of a button
	 * @return <code>DIM * row + col</code>. So the index of a button
	 */
	/*
	 * @	requires row >= 0 && row <= 5;
	 * 		requires col >= 0 && col <= 6;
	 * 		ensures \result >= 0 && <= 41;
	 */
	public static int getIndexButton(int row, int col) {
		return DIM * row + col;
	}

	/**
	 * The method to get the color of a button given the row and column
	 * @param row the row where the button is
	 * @param col the column where the button is
	 * @return the color of the button as a <code>String</code>
	 */
	/*
	 * @	requires row >= 0 && row <= 5;
	 * 		requires col >= 0 && col <= 6;
	 * 		ensures \result.equals("BLACK") || \result.equals("RED") || \result.equals("YELLOW");
	 */
	public String getColor(int row, int col) {
		buttonNumber = getIndexButton(row, col);
		return getColor(buttonNumber);
	}

	/**
	 * The method to get the color of a button given the index of that button
	 * @param buttonNumber the number of the button
	 * @return the color of the button as a <code>String</code>
	 */
	/*
	 * @	requires buttonNumber >= 0 && buttonNumber <= 41;
	 * 		ensures \result.equals("BLACK") || \result.equals("RED") || \result.equals("YELLOW");
	 */
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

	/**
	 * The method to set the color of a button
	 * @param buttonNumber the button that has to be colored
	 * @param playercolor the color the button has to get
	 */
	/*
	 * @	requires buttonNumber >= 0 && buttonNumber <= 41;
	 * 		requires playercolor != null;
	 */
	public void setColor(int buttonNumber, PlayerColor playercolor) {
		if (playercolor == PlayerColor.RED) {
			boardgui.buttons[buttonNumber].setBackground(Color.RED);
		} else if (playercolor == PlayerColor.YELLOW) {
			boardgui.buttons[buttonNumber].setBackground(Color.YELLOW);
		} else {
			boardgui.buttons[buttonNumber].setBackground(Color.BLACK);
		}
	}

	/**
	 * The method to set the <code>currentplayer</code> to the one given as parameter
	 * Also the <code>PlayerColor</code> of that <code>Player</code> is set
	 * @param player 
	 */
	/*
	 * @	requires player != null;
	 * 		ensures currentplayer == player;
	 * 		ensures currentColor == currentplayer.getPlayerColor();
	 */
	public void setCurrentPlayer(Player player){
		Player currentplayer = player;
		currentColor = currentplayer.getPlayerColor();
	}
	
	/**
	 * The method to set a stone on a given column.
	 * The stone drops until it finds a stone beneath him
	 * @param col the column the stone in placed in
	 */
	/*
	 * @	requires col >= 0 && col <= 6;
	 * 		
	 */
	public void setStone(int col){
		boolean goOn = true;
		for(int row = 0;goOn && row < MaxRow; row++){
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
		if(isClient){
			otherColor();
		}
	}
	
	/**
	 * Returns the stone that was last set by <code>setStone(col)</code>
	 * @return the buttonNumber
	 */
	/* @ pure */public int getLastSetStone(){
		return buttonNumber;
	}
	
	/**
	 * Checks if the given index is a valid column to place a stone
	 * @param index
	 * @return if the move is valid
	 */
	/*
	 * @	requires index >= 0 && index <= 6;
	 */
	public boolean isValidMove(int index){
		boolean isValid = false;
		if(index >= 0 && index < 7){
			isValid = true;
		}
		return isValid;
	}
	
	/**
	 * Returns the x coordinate of a button
	 * @return
	 */
	/* @ pure */public int getX(){
		return x;
	}
	
	/**
	 * Returns the y coordinate of a button
	 * @return
	 */
	/* @ pure */public int getY(){
		return y;
	}
	
	/**
	 * Sets the coordinates of a button by calculating is from the buttonNumber.
	 * @param buttonNumber the number of the button
	 * @param col the column the button is in
	 */
	/*
	 * @	requires buttonNumber >= 0 && buttonNumber <= 41;
	 * 		requires col >= 0 && col <= 6;
	 * 		ensures x >= 0 && x <= 5;
	 * 		ensures y == col;
	 */
	public static void setCoordinates(int buttonNumber,int col){
		int button = buttonNumber - col;
		x = button / 7;
		y = col;
	}
}
