package connectFour;

import java.util.Observable;

import utils.PlayerColor;

public class Board extends Observable{

	private static final int maxRow = 6;
	private static final int maxCol =7;
	private static final int maxFields = maxRow * maxCol;
	private PlayerColor[] fields;
	private int horizontalCount = 0;
	private int verticalCount = 0;
	private int diagonalLeftCount = 0;
	private int diagonalRightCount = 0;
	
	/**
	 * Creates a new empty <code>Board</code>
	 */
	public Board(){
		fields = new PlayerColor[maxFields];
		reset();
	}
	
	/**
	 * Creates a deepcopy of the <code>Board</code>
	 * @return a copy of the <code>Board</code>
	 */
	public Board deepCopy(){
		Board copyBoard = new Board();
		for(int i = 0; i < fields.length; i++){
			copyBoard.fields[i] = this.fields[i];
		}
		return copyBoard;
	}
	
	/**
	 * Returns the index of a field on the <code>Board</code>
	 * given the row and the column
	 * @param row
	 * @param col
	 * @return the index of a field
	 */
	private int index(int row, int col){
		int index = col + row * maxCol;
		return index;
	}
	
	public PlayerColor[] getFields(){
		return fields;
	}
	
	/**
	 * Checks if the given index is a field on the <code>Board</code>
	 * @param index
	 * @return if the index is a field
	 */
	public boolean isField(int index){
		boolean isField = false;
		if(0 <= index && index <= maxFields){
			isField = true;
		}
		return isField;
	}
	
	/**
	 * Checks if the given row and column belong to a field on the <code>Board</code>
	 * @param row
	 * @param col
	 * @return if the row and column belong to a field
	 */
	public boolean isField(int row, int col){
		return isField(index(row,col));
	}
	
	/**
	 * Gets the <code>PlayerColor</code> of a field <code>index</code>
	 * @param index
	 * @return </code>PlayerColor</code> of the given field
	 */
	public PlayerColor getField(int index){
		PlayerColor color = null;
		if(isField(index)){
			color = fields[index];
		}
		return color;
	}
	
	/**
	 * Gets the <code>PlayerColor</code> of the pair (row,col)
	 * @param row
	 * @param col
	 * @return <code>PlayerColor</code> of the given pair (row,col)
	 */
	public PlayerColor getField(int row, int col){
		return getField(index(row,col));
	}
	
	
	/**
	 * Checks if the <code>index</code> corresponds to an empty field
	 * @param index
	 * @return true if the field is empty
	 */
	public boolean isEmptyField(int index){	
		return getField(index) == PlayerColor.EMPTY;
	}
	
	/**
	 * Checks if the pair (row,col) corresponds to an empty field
	 * @param row
	 * @param col
	 * @return true if the field is empty
	 */
	public boolean isEmptyField(int row, int col){
		return isEmptyField(index(row,col));
	}
	
	/**
	 * Checks if the whole <code>Board</code> is full
	 * @return true if the <code>Board</code> is full
	 */
	public boolean isFull(){
		boolean isFull = true;
		for(int i = 0; i < maxFields; i++){
			if(fields[i] == PlayerColor.EMPTY){
				isFull = false;
			}
		}
		return isFull;
	}
	
	/**
	 * Checks if the game is over, so if there is a winner or if the <code>Board</code> is full
	 * @return true if the game is over
	 */
	public boolean gameOver(){
		return hasWinner() || isFull();
	}
	
	/**
	 * Checks if the given <code>PlayerColor</code> is a winner
	 * @param color
	 * @return true if the <code>PlayerColor</code> is a winner
	 */
	public boolean isWinner(PlayerColor color){
		
		return false;
	}
	
	/**
	 * Checks if the <code>Game</code> has a winner
	 * @return true if there is a winner
	 */
	public boolean hasWinner(){
		return isWinner(PlayerColor.RED) || isWinner(PlayerColor.YELLOW);
	}
	
	/**
	 * Counts all the horizontal fields up and down from the given pair (row,col)
	 * that are the <code>PlayerColor</code> that is given and connected
	 * @param row
	 * @param col
	 * @param color
	 * @return the amount of adjacent fields with the same <code>PlayerColor</code>
	 */
	private int countHorizontal(int row, int col, PlayerColor color){
		//Check left
		if(col>0){
			boolean colorFound = true;
			for(int x = col - 1; colorFound && x >= 0; x--){
				if(!getField(row,x).equals(color)){
					colorFound = false;
				}else if(getField(row,x).equals(color)){
					horizontalCount++;
				}
			}
		}
		//Check right
		if(col < maxCol - 1){
			boolean colorFound = true;
			for(int x = col + 1; colorFound && x >= col && x < maxCol; x++){
				if(!getField(row,x).equals(color)){
					colorFound = false;
				} else if(getField(row,x).equals(color)){
					horizontalCount++;
				}
			}
		}
		return horizontalCount++;
	}
	
	/**
	 * Counts all the vertical fields left and right from the given pair (row,col)
	 * that are the <code>PlayerColor</code> that is given and connected
	 * @param row
	 * @param col
	 * @param color
	 * @return the amount of adjacent fields with the same <code>PlayerColor</code>
	 */
	private int countVertical(int row, int col, PlayerColor color){
		//Check down
		if(row < maxRow-1){
			boolean colorFound = true;
			for(int x = row + 1; colorFound && x >= row && x < maxRow; x++){
				if(!getField(x, col).equals(color)){
					colorFound = false;
				}else if(getField(x,col).equals(color)){
					verticalCount++;
				}
			}
		}
		return verticalCount;
	}
	
	/**
	 * Counts all the diagonal fields from right up to left down from the given pair (row,col)
	 * that are the <code>PlayerColor</code> that is given and connected
	 * @param row
	 * @param col
	 * @param color
	 * @return the amount of adjacent fields with the same <code>PlayerColor</code>
	 */
	private int countDiagonalLeft(int row, int col, PlayerColor color){
		//Check left up
		if(row > 0 && col > 0){
			boolean colorFound = true;
			int y = col-1;
			for(int x = row-1; colorFound && row >= 0; x--){
				if(!getField(x,y).equals(color)){
					colorFound = false;
				}else if(getField(x,y).equals(color)){
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
				if(!getField(x,y).equals(color)){
					colorFound = false;
				}else if(getField(x,y).equals(color)){
					diagonalLeftCount++;
					y++;
				}
			}
		}
		return diagonalLeftCount;
	}
	
	/**
	 * Counts all the diagonal fields from left up to right down from the given pair (row,col)
	 * that are the <code>PlayerColor</code> that is given and connected
	 * @param row
	 * @param col
	 * @param color
	 * @return the amount of adjacent fields with the same <code>PlayerColor</code>
	 */
	private int countDiagonalRight(int row, int col, PlayerColor color){
		//Check right up
		if(row < maxRow-1 && col > 0){
			boolean colorFound = true;
			int y = col + 1;
			for(int x = row -1; colorFound && x >= 0; x--){
				if(!getField(x,y).equals(color)){
					colorFound = false;
				}else if(getField(x,y).equals(color)){
					diagonalRightCount++;
					y++;
				}
			}
		}
		//Check right down
		if(row > 0 && col < maxRow-1){
			boolean colorFound = true;
			int y = col-1;
			for(int x = row + 1; colorFound && x < maxRow; x++){
				if(!getField(x,y).equals(color)){
					colorFound = false;
				}else if(getField(x,y).equals(color)){
					diagonalRightCount++;
					y--;
				}
			}
		}
		
		return diagonalRightCount;
	}
	
	
	/**
	 * Resets the <code>Board</code> by setting all the fields to empty
	 */
	public void reset(){
		for(int i = 0; i < maxFields; i++){
			setField(i, PlayerColor.EMPTY);
		}
	}
	
	/**
	 * Sets a field on the <code>Board</code> with the given <code>index</code> in the <code>color</code> that is given
	 * @param index
	 * @param color
	 */
	public void setField(int index, PlayerColor color){
		fields[index] = color;
	}
	
	/**
	 * Sets a field on the <code>Board</code> with the given pair (row, col) in the <code>color</code> given
	 * @param row
	 * @param col
	 * @param color
	 */
	public void setField(int row, int col, PlayerColor color){
		fields[index(row,col)] = color;
	}
}
