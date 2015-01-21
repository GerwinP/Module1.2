package connectFour;

import gui.BoardGUI;
import utils.PlayerColor;
import utils.GameState;
import connectFour.Board;

public class Game {

	private int maxCol = 7;
	private int maxRow = 6;
	private String playerColor;
	private PlayerColor currentplayer = PlayerColor.RED;
	private PlayerColor winner;
	private GameState gamestate = GameState.NOTSTARTED;
	private int horizontalCount = 0;
	private int verticalCount = 0;
	private int diagonalLeftCount = 0;
	private int diagonalRightCount = 0;
	public Board board;
	public BoardGUI boardgui;

	public Game(){
		boardgui = new BoardGUI(this);
		board = new Board(this, boardgui);
	}
	
	public GameState getGameState() {
		return gamestate;
	}

	public PlayerColor getCurrentPlayer() {
		return currentplayer;
	}

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

	public void nextTurn() {
		if (currentplayer == PlayerColor.RED) {
			currentplayer = PlayerColor.YELLOW;
		} else {
			currentplayer = PlayerColor.RED;
		}
	}

	public String toStringPlayer() {
		if (currentplayer == PlayerColor.RED) {
			playerColor = "RED";
		} else {
			playerColor = "YELLOW";
		}
		return playerColor;
	}

	public void countHorizontal(int row, int col) {
		int buttonNumber = Board.getIndexButton(row, col);
		// Check Left
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
			// System.out.println("After left check: " + horizontalCount);
		}
		// Check Right
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
			// System.out.println("After right check: " + horizontalCount);
		}
	}

	public void countVertical(int row, int col) {
		int buttonNumber = Board.getIndexButton(row, col);
		// Check Up
		if (row > 0) {
			boolean colorFound = true;
			for (int x = row - 1; colorFound && x >= 0; x--) {
				if (!board.getColor(x, col).equals(playerColor)) {
					colorFound = false;
				} else if (board.getColor(x, col).equals(
						board.getColor(buttonNumber))) {
					verticalCount++;
				}
			}
		}
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

	public void countDiagonalLeft(int row, int col){
		System.out.println("Row " + row + ", " + " Col " + col);
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
					System.out.println(" x: " + x + " y: " + y);
					System.out.println("After up count1: " + diagonalLeftCount);
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

	public void countColor(int row, int col, PlayerColor color) {
		System.out.println("Count color is aangeroepen");
		playerColor = color.toString();
		System.out.println("Playercolor: " + playerColor);
		

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
		System.out.println(isWinner());
		if (getGameState() != GameState.FINISHED
				|| getGameState() != GameState.DRAW) {
			nextTurn();
		}
		resetCount();
	}

	public void resetCount() {
		horizontalCount = 0;
		verticalCount = 0;
		diagonalLeftCount = 0;
		diagonalRightCount = 0;
	}

	public int getHorizontalCount() {
		return horizontalCount;
	}

	public int getVerticalCount() {
		return verticalCount;
	}

	public int getDiagonalLeftCount() {
		return diagonalLeftCount;
	}

	public int getDiagonalRightCount() {
		return diagonalRightCount;
	}

	public PlayerColor getWinner() {
		return winner;
	}

	public boolean isWinner(){
		return gamestate == GameState.FINISHED;
	}
	
	public static void main(String[] args){
		new Game();
	}
}
