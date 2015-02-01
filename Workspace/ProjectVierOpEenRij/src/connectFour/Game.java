package connectFour;

/**
 * The game for Connect Four
 * @author Gerwin Puttenstein
 * @version 1.2
 */

import java.util.Observable;

import players.HumanPlayer;
import players.Player;
import gui.BoardGUI;
import utils.PlayerColor;
import utils.GameState;
import connectFour.Board;
import java.util.*;
import players.*;
import utils.*;
import gui.*;

public class Game extends Observable{

	private int maxCol = 7;
	private int maxRow = 6;
	private String playerColor;
	private Player currentplayer;;
	private Player winner;
	private GameState gamestate;
	private int horizontalCount = 0;
	private int verticalCount = 0;
	private int diagonalLeftCount = 0;
	private int diagonalRightCount = 0;
	public Board board;
	public BoardGUI boardgui;
	private Player[] players = new Player[2];

	/**
	 * The constructor of the <code>Game</code> class. 
	 * The contructor create a new <code>Board</code> and a new BoardGUI.
	 * The <code>BoardGUI</code> is visible or not determining the isVisible boolean.
	 * @param player1 the first player
	 * @param player2 the second player
	 * @param isVisible to determine if the <code>BoardGUI</code> is visible
	 */
	/*
	 * @ requires player1 != null;
	 * @ requires player2 != null;
	 */
	public Game(Player player1, Player player2, boolean isVisible){
		players[0] = player1;
		players[1] = player2;
		currentplayer = players[0];
		boardgui = new BoardGUI(this, isVisible);
		board = new Board(this, boardgui);
		board.setCurrentPlayer(player1);
		setGameState("inprogress");
	}
	/**
	 * Returns the state of the <code>Game</code>
	 * @return <code>gamestate</code>
	 */
	/*@ pure */public GameState getGameState() {
		return gamestate;
	}

	/**
	 * returns the current <code>Player</code> which may take turn
	 * @return <code>currentplayer</code>
	 */
	/*@ pure */public Player getCurrentPlayer() {
		return currentplayer;
	}

	/**
	 * Sets the <code>GameState</code> of the <code>Game</code> given the string
	 * @param <code>gameState</code>
	 */
	/*
	 * @ requires gameState != null;
	 */
	public void setGameState(String gameState) {
		if (gameState.equals("finished")) {
			gamestate = GameState.FINISHED;
		} else if (gameState.equals("draw")) {
			gamestate = GameState.DRAW;
		} else if (gameState.equals("inprogress")) {
			gamestate = GameState.INPROGRESS;
		} else if (gameState.equals("notstarted")) {
			gamestate = GameState.NOTSTARTED;
		} else {
			System.out.println("The gamestate '" + gameState
					+ "' is not supported.\n Try another one, maybe a typo?");
		}
	}
	
	/**
	 * Changes the current player for the next turn
	 */
	/*
	 * @ ensures getCurrentPlayer() != \old(getCurrentPlayer());
	 */
	public void nextTurn() {
		if (currentplayer == players[0]) {
			board.setCurrentPlayer(players[1]);
			currentplayer = players[1];
		} else {
			currentplayer = players[0];
			board.setCurrentPlayer(players[0]);
		}
	}

	/** 
	 * return the <code>PlayerColor</code> of the <code>currentplayer</code> as a <code>String</code>
	 * @return <code>playerColor</code>
	 */
	/*
	 * @ ensures \result.equals("RED") || \result.equals("YELLOW");
	 */
	public String toStringPlayer() {
		if (currentplayer.getPlayerColor() == PlayerColor.RED) {
			playerColor = "RED";
		} else {
			playerColor = "YELLOW";
		}
		return playerColor;
	}

	/**
	 * Counts all the horizontal stone with the same color in the same row that are connected
	 * @param row the row to search in
	 * @param col the column to iterate over
	 */
	/*
	 * @ 	requires row >= 0 && row <= 5;
	 * 		requires col >= 0 && col <= 6;
	 * 		ensures horizontalCount >= 0;
	 */
	public void countHorizontal(int row, int col) {
		int buttonNumber = Board.getIndexButton(row, col);
		// Check Left of the new stone
		if (col > 0) {
			boolean colorFound = true;
			for (int x = col - 1; colorFound && x >= 0; x--) {
				if (!board.getColor(row, x).equals(playerColor)) {
					colorFound = false;
				} else if (board.getColor(row, x).equals(
					board.getColor(buttonNumber))) {
					horizontalCount++;
				}
			}
		}
		// Check Right of the new stone
		if (col < maxCol - 1) {
			boolean colorFound = true;
			for (int x = col + 1; colorFound && x >= col && x < maxCol; x++) {
				if (!board.getColor(row, x).equals(playerColor)) {
					colorFound = false;
				} else if (board.getColor(row, x).equals(
						board.getColor(buttonNumber))) {
					horizontalCount++;
				}
			}
		}
	}

	/**
	 * Count vertically under the new stone
	 * @param row the row the stone was placed in and where to iterate over
	 * @param col the column the stone was placed in
	 */
	/*
	 * @	requires row >= 0 && row <= 5;
	 * 		requires col >= 0 && col <= 6;
	 * 		ensures verticalCount >= 0;
	 */
	public void countVertical(int row, int col) {
		int buttonNumber = Board.getIndexButton(row, col);
		// Check Down
		if (row < maxRow - 1) {
			boolean colorFound = true;
			for (int x = row + 1; colorFound && x >= row && x < maxRow; x++) {
				if (!board.getColor(x, col).equals(playerColor)) {
					colorFound = false;
				} else if (board.getColor(x, col).equals(
						board.getColor(buttonNumber))) {
					verticalCount++;
				}
			}
		}
	}

	/**
	 * The method for counting diagonally from right down to left up
	 * @param row the row where the stone was placed
	 * @param col the column where the stone was placed
	 */
	/*
	 * @	requires row >= 0 && row <= 5;
	 * 		requires col >= 0 && col <= 6;
	 * 		ensures diagonalLeftCount >= 0;
	 */
	public void countDiagonalLeft(int row, int col){
		int buttonNumber = Board.getIndexButton(row, col);
		//Check left up
		if(row > 0 && col > 0){
			boolean colorFound = true;
			int y = col-1;
			for(int x = row-1; colorFound && row >= 0; x--){
				if(!board.getColor(x, y).equals(playerColor)){
					colorFound = false;
				}else if(board.getColor(x,y).equals(board.getColor(buttonNumber))){
					diagonalLeftCount++;
					y--;
				}
			}
		}
		
		//Check left down
		if(row < maxRow-1 && col < maxCol-1){
			boolean colorFound = true;
			int y = col+1;
			for(int x = row+1; colorFound && x < maxRow; x++){
				if(!board.getColor(x,y).equals(playerColor)){
					colorFound = false;
				}else if(board.getColor(x, y).equals(board.getColor(buttonNumber))){
					diagonalLeftCount++;
					y++;
				}
			}
		}
	}

	/**
	 * Method for counting diagonally from left down to right up
	 * @param row the row the new stone was placed
	 * @param col the column the new stone was placed
	 */
	/*
	 * @ 	requires row >= 0 && row <= 5;
	 * 		requires col >= 0 && col <= 6;
	 * 		ensures diagonalRightCount >= 0;
	 */
	public void countDiagonalRight(int row, int col) {
		int buttonNumber = Board.getIndexButton(row, col);
		//Check right up
		if(row < maxRow-1 && col > 0){
			boolean colorFound = true;
			int y = col + 1;
			for(int x = row -1; colorFound && x >= 0; x--){
				if(!board.getColor(x,y).equals(playerColor)){
					colorFound = false;
				}else if(board.getColor(x,y).equals(board.getColor(buttonNumber))){
					diagonalRightCount++;
					y++;
				}
			}
		}
		
		//Check right down
		if(row > 0 && col < maxRow-1){
			boolean colorFound = true;
			int y = col-1;
			for(int x = row+1; colorFound && x < maxRow; x++){
				if(!board.getColor(x,y).equals(playerColor)){
					colorFound = false;
				}else if(board.getColor(x,y).equals(board.getColor(buttonNumber))){
					diagonalRightCount++;
					y--;
				}
			}
		}
	}

	/**
	 * The method that calls all the counting methods.
	 * Then checks if there is a winner
	 * Then calls <code>nextTurn()</code> if there is no winner
	 * @param row the row the stone was placed in
	 * @param col the column the stone was placed in
	 * @param color the color of the player that placed the stone
	 */
	/*
	 * @	requires row >= 0 && row <= 5;
	 * 		requires col >= 0 && col <= 6;
	 * 		requires color != null;
	 */
	public void countColor(int row, int col, PlayerColor color) {
		playerColor = color.toString();

		// Check horizontal
		countHorizontal(row, col);
		// Check vertical
		countVertical(row, col);
		// Check DiagonalLeft
		countDiagonalLeft(row, col);
		// Check DiagonalRight
		countDiagonalRight(row, col);

		// Check if one of them has four in a row
		if (horizontalCount >= 3 || verticalCount >= 3
				|| diagonalLeftCount >= 3 || diagonalRightCount >= 3) {
			setGameState("finished");
			winner = currentplayer;
		}
		if (getGameState() != GameState.FINISHED
				|| getGameState() != GameState.DRAW) {
			notifyObservers("nextturn");
			nextTurn();
		}
		resetCount();
	}

	/**
	 * resets all the counts for checking if there is a winner
	 */
	/*
	 * @	ensures getHorizontalCount() == 0;
	 * 		ensures getVerticalCount() == 0;
	 * 		ensures getDiagonalLeftCount == 0;
	 * 		ensures getDiagonalRightCount == 0;
	 */
	public void resetCount() {
		horizontalCount = 0;
		verticalCount = 0;
		diagonalLeftCount = 0;
		diagonalRightCount = 0;
	}
	
	/**
	 * Returns the current <code>horizontalCount</code>
	 * @return <code>horizontalCount</code>
	 */
	/* @ pure */public int getHorizontalCount() {
		return horizontalCount;
	}
	 
	/**
	 * Returns the current <code>verticalCount</code>
	 * @return <code>verticalCount</code>
	 */
	/* @ pure */public int getVerticalCount() {
		return verticalCount;
	}

	/**
	 * Returns the current <code>diagonalLeftCount</code>
	 * @return <code>diagonalLeftCount</code>
	 */
	/* @ pure */public int getDiagonalLeftCount() {
		return diagonalLeftCount;
	}

	/**
	 * Returns the current <code>diagonalRightCount</code>
	 * @return <code>diagonalRightCount</code>
	 */
	/* @ pure */public int getDiagonalRightCount() {
		return diagonalRightCount;
	}

	/**
	 * Returns the <code>Player</code> that won the game
	 * @return <code>winner</code>
	 */
	/* @ pure */public Player getWinner() {
		return winner;
	}

	/**
	 * Checks if the game has the <code>GameState</code> <code>Gamestate.FINISHED</code>
	 * @return <code>gamestate == GameState.FINISHED</code>
	 */
	public boolean isWinner(){
		if(gamestate==GameState.FINISHED){
			notifyObservers("winner");
		}
		return gamestate == GameState.FINISHED;
	}
	
	/**
	 * Main method for <code>Game</code>
	 * Is used for starting a stand alone game.
	 * DISCLAIMER: DOES NOT WORK!
	 * @param args
	 */
	public static void main(String[] args){
		Player player1 = new HumanPlayer("Gerwin", PlayerColor.RED);
		Player player2 = new HumanPlayer("Josje", PlayerColor.YELLOW);
		new Game(player1, player2, true);
	}
}
