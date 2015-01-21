package connectFour;

import utils.PlayerColor;
import utils.GameState;
import connectFour.Board;

public class Game {
	
	static int Dimension = 7;
	static int maxCol = 7;
	static int maxRow = 6;
	static String playerColor;
	static PlayerColor currentplayer = PlayerColor.RED;
	static PlayerColor winner;
	static GameState gamestate = GameState.NOTSTARTED;
	static int horizontalCount = 0;
	static int verticalCount = 0;
	static int diagonalLeftCount = 0;
	static int diagonalRightCount = 0;
	
	public static GameState getGameState(){
		return gamestate;
	}
	
	public static PlayerColor getCurrentPlayer(){
		return currentplayer;
	}
	
	public static void setGameState(String gameState){
		if(gameState.equals("finished")){
			gamestate = GameState.FINISHED;
		}else if(gameState.equals("draw")){
			gamestate = GameState.DRAW;
		}else if(gameState.equals("inprogress")){
			gamestate = GameState.INPROGRESS;
		}else if(gameState.equals("notstarted")){
			gamestate = GameState.NOTSTARTED;
		}else {
			System.out.println("The gamestate '" + gameState + "' is not supported.\n Try another one, maybe a typo?");
		}
	}
	
	public static void nextTurn(){
		if(currentplayer == PlayerColor.RED){
			currentplayer = PlayerColor.YELLOW;
		}else{
			currentplayer = PlayerColor.RED;
		}
	}
	
	public static String toStringPlayer(){
		if(currentplayer == PlayerColor.RED){
			playerColor = "RED";
		}else{
			playerColor = "YELLOW";
		}
		return playerColor;
	}
	
	public static void countHorizontal(int row, int col, int buttonNumber){
		//Check Left
		if(col != 0){
			boolean colorFound = true;
			for(int x = col-1;colorFound &&  x >= 0; x--){
				if(!Board.getColor(row,x).equals(playerColor)){
					colorFound = false;
				}else if(Board.getColor(row, x).equals(Board.getColor(buttonNumber))){
					horizontalCount++;
				}
			}
//			System.out.println("After left check: " + horizontalCount);
		}
		//Check Right
		if(col != maxCol-1){
			boolean colorFound = true;
			for(int x = col+1;colorFound &&  x >= col && x < maxCol; x++){
				if(!Board.getColor(row,x).equals(playerColor)){
					colorFound = false;
				}else if(Board.getColor(row, x).equals(Board.getColor(buttonNumber))){
					horizontalCount++;
				}
			}
//			System.out.println("After right check: " + horizontalCount);
		}
	}
	
	public static void countVertical(int row, int col, int buttonNumber){
		//Check Up
		if(row != 0){
			boolean colorFound = true;
			for(int x = row-1;colorFound && x >= 0; x--){
				if(!Board.getColor(x, col).equals(playerColor)){
					colorFound = false;
				}else if(Board.getColor(x, col).equals(Board.getColor(buttonNumber))){
					verticalCount++;
				}
			}
		}
		//Check Down
		if(row != maxRow-1){
			boolean colorFound = true;
			for(int x = row+1;colorFound && x >= row && x < maxRow; x++){
				if(!Board.getColor(x, col).equals(playerColor)){
					colorFound = false;
				}else if(Board.getColor(x, col).equals(Board.getColor(buttonNumber))){
					verticalCount++;
				}
			}
		}
	}
	
	public static void countDiagonalLeft(int row, int col, int buttonNumber){
		
	}
	
	public static void countDiagonalRight(int row, int col, int buttonNumber){
		
	}
	
	public static void countColor(int row, int col, PlayerColor color){
		playerColor = color.toString();
		System.out.println("Playercolor: " + playerColor);
		System.out.println(row + ", " + col);
		int buttonNumber = Dimension * row + col;
		
		//Check horizontal
		countHorizontal(row, col, buttonNumber);
		//Check vertical
		countVertical(row, col, buttonNumber);
		//Check DiagonalLeft
		countDiagonalLeft(row, col, buttonNumber);
		//Check DiagonalRight
		countDiagonalRight(row, col, buttonNumber);


		//Check Diagonal Left Up
		//Dit werkt niet, het moet 1 forloopje worden.
//		if(row != 0 || col != 0 || (row != 0 && col != 0)){
//			boolean colorFound = true;
//			int y = row-1;
//			for(int x = col-1; colorFound && x >= 0 && y >= 0; x--){
//				if(!Board.getColor(y, x).equals(playerColor)){
//					colorFound = false;
//				}else if(Board.getColor(y,x).equals(Board.getColor(buttonNumber))){
//					diagonalLeftCount++;
//					y++;
//					System.out.println("y: " + y + " x: " + x);
//					System.out.println("After up count: " + diagonalLeftCount);
//				}
//			}
//		}
		
		
		
		if(row != 0 || col != 0 || (row != 0 && col != 0)){
			boolean colorFound = true;
			for(int x = col-1;colorFound && x >= 0; x--){
				for(int y = row-1;colorFound && y >= 0; y=y-7){
					if(!Board.getColor(y, x).equals(playerColor)){
						colorFound = false;
					}else if(Board.getColor(y,x).equals(Board.getColor(buttonNumber))){
						diagonalLeftCount++;
						System.out.println("y: " + y + " x: " + x);
						System.out.println("After up count: " + diagonalLeftCount);
					}
				}
			}
		}
		
		if(row != maxRow-1 || col != maxCol-1 || (row != maxRow-1 && col != maxCol-1)){
			
		}
		
		//Check Diagonal Left Down
		//Dit werkt niet, het moet 1 forloopje worden
		if(row != maxRow-1 || col != maxCol-1 || (row != maxRow-1 && col != maxCol-1)){
			boolean colorFound = true;
			for(int x = col;colorFound && x >= col && x < maxCol; x++){
				for(int y = row;colorFound && y >= row && y < maxRow; y++){
					if(!Board.getColor(y,x).equals(playerColor)){
						colorFound = false;
					}else if(Board.getColor(y,x).equals(Board.getColor(buttonNumber))){
						diagonalLeftCount++;
						System.out.println("y: " + y + " x: " + x);
						System.out.println("After down count: " + diagonalLeftCount);
					}
				}
			}
		}
		//Check Diagonal Right Up
		//Dit werkt niet, het moet 1 forloopje worden
		if(row != 0 || col != maxCol-1 || (row != 0 && col != maxCol-1)){
			for(int x = col; x >= 0; x--){
				for(int y = row; y >= 0; y--){
					if(Board.getColor(y, x).equals(Board.getColor(buttonNumber))){
						diagonalRightCount++;
					}
				}
			}
		}
		//Check Diagonal Right Down
		//Dit werkt niet, het moet 1 forloopje worden
		if(row != maxRow -1 || col != 0 || (row != maxRow-1 && col != 0)){
			for(int x = col; x >= col && x < maxCol; x++){
				for(int y = row; y >= row && y < maxRow; y++){
					if(Board.getColor(y, x).equals(Board.getColor(buttonNumber))){
						diagonalRightCount++;
					}
				}
			}
		}
		//Check if one of them has four in a row
		if(horizontalCount >= 3){
			horizontalCount++;
		}
		if(verticalCount >=3){
			verticalCount++;
		}
		if(diagonalLeftCount >= 3){
			diagonalLeftCount++;
		}
		if(diagonalRightCount >= 3){
			diagonalRightCount++;
		}
		countFour();
		if(getGameState() != GameState.FINISHED || getGameState() != GameState.DRAW){
			nextTurn();
		}
		resetCount();
	}
	
	public static void countFour(){
		if(horizontalCount >= 4 || verticalCount >= 4 || diagonalLeftCount >= 4 || diagonalRightCount >= 4){
			setGameState("finished");
			winner = currentplayer;
		}
	}
	
	public static void resetCount(){
		horizontalCount = 0;
		verticalCount = 0;
		diagonalLeftCount = 0;
		diagonalRightCount = 0; 
	}
	
	public static int getHorizontalCount(){
		return horizontalCount;
	}
	
	public static int getVerticalCount(){
		return verticalCount;
	}
	
	public static int getDiagonalLeftCount(){
		return diagonalLeftCount;
	}
	
	public static int getDiagonalRightCount(){
		return diagonalRightCount;
	}
	
	public static PlayerColor getWinner(){
		return winner;
	}
	
}
