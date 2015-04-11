package connectFour;

import utils.PlayerColor;

/**
 * The class that contains the logic and the rules for the Connect Four Game.
 * It also keeps track of the fields that are filled.
 * @author Gerwin Puttenstein
 *
 */
public class Board{

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
	 * Returns the index of a field on the <code>Board</code>
	 * given the row and the column
	 * @param row
	 * @param col
	 * @return the index of a field
	 */
	/*
	 * @ requires row >= 0 && row <= 5;
	 * 	 requires col >= 0 && col <= 6;
	 * 	 ensures index == col + row * this.maxCol;
	 */
	private int index(int row, int col){
		int index = col + row * maxCol;
		return index;
	}
	
	/**
	 * A getter for the <code>PlayerColor[]</code> fields.
	 * @return
	 */

	/*@ pure */public PlayerColor[] getFields(){
		return fields;
	}
	
	/**
	 * Checks if the given index is a field on the <code>Board</code>
	 * @param index
	 * @return if the index is a field
	 */
	/*
	 * @ requires index  >= 0 && index <= 41;
	 * 	 ensures \result == isField;
	 */
	public boolean isField(int index){
		boolean isField = false;
		if(0 <= index && index < maxFields){
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
	/*@ pure */public boolean isField(int row, int col){
		return isField(index(row,col));
	}
	
	/**
	 * Gets the <code>PlayerColor</code> of a field <code>index</code>
	 * @param index
	 * @return </code>PlayerColor</code> of the given field
	 */
	/*
	 * @ requires index >= 0 && index <= 41;
	 * 	 ensures color == PlayerColor.YELLOW || color == PlayerColor.RED;
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
	/*@ pure */public PlayerColor getField(int row, int col){
		return getField(index(row,col));
	}
	
	
	/**
	 * Checks if the <code>index</code> corresponds to an empty field
	 * @param index
	 * @return true if the field is empty
	 */
	/*
	 * @ requires index >= 0 && index <= 41;
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
	/*@ pure */public boolean isEmptyField(int row, int col){
		return isEmptyField(index(row,col));
	}
	
	/**
	 * Checks if the whole <code>Board</code> is full
	 * @return true if the <code>Board</code> is full
	 */
	/*
	 * @ ensures \result == isFull;
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
	/*
	 * @ requires row >= 0 && row <= 5;
	 * 	 requires col >= 0 && col <= 6;
	 * 	 requires color != null && (color == PlayerColor.YELLOW || color == PlayerColor.RED);
	 */
	public boolean gameOver(int row, int col, PlayerColor color){
		return isWinner(row, col, color) || isFull();
	}
	
	/**
	 * Checks if the given <code>PlayerColor</code> is a winner
	 * @param color
	 * @return true if the <code>PlayerColor</code> is a winner
	 */
	/*
	 * @ requires row >= 0 && row <= 5;
	 * 	 requires col >= 0 && col <= 6;
	 * 	 requires color != null && (color == PlayerColor.YELLOW || color == PlayerColor.RED);
	 * 	 ensures \result == isWinner
	 */
	public boolean isWinner(int row, int col, PlayerColor color){
		countHorizontal(row, col, color);
		countVertical(row, col, color);
		countDiagonalLeft(row, col, color);
		countDiagonalRight(row, col, color);
		boolean isWinner = false;
		if(getHorizontalCount() >= 3 || getVerticalCount() >= 3 || getDiagonalLeftCount() >= 3 || getDiagonalRightCount() >= 3){
			isWinner = true;
		}
		return isWinner;
	}
	/**
	 * Counts all the horizontal fields up and down from the given pair (row,col)
	 * that are the <code>PlayerColor</code> that is given and connected
	 * @param row
	 * @param col
	 * @param color
	 * @return the amount of adjacent fields with the same <code>PlayerColor</code>
	 */
	/*
	 * @ requires row >= 0 && row <= 5;
	 * 	 requires col >= 0 && col <= 6;
	 * 	 requires color != null && (color == PlayerColor.YELLOW || color == PlayerColor.RED);
	 * 	 ensures getHorizontalCount() => 0;
	 */
	public void countHorizontal(int row, int col, PlayerColor color){
		horizontalCount = 0;
		//Check left
		if(col>0){
			boolean colorFound = true;
			//@ loop_invariant col-1 >= x && x >= 0;
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
			//@ loop_invariant col+1 <= x && x <= this.maxCol;
			for(int x = col + 1; colorFound && x >= col && x < maxCol; x++){
				if(!getField(row,x).equals(color)){
					colorFound = false;
				} else if(getField(row,x).equals(color)){
					horizontalCount++;
				}
			}
		}
	}
	
	/**
	 * Counts all the vertical fields left and right from the given pair (row,col)
	 * that are the <code>PlayerColor</code> that is given and connected
	 * @param row
	 * @param col
	 * @param color
	 * @return the amount of adjacent fields with the same <code>PlayerColor</code>
	 */
	/*
	 * @ requires row >= 0 && row <= 5;
	 * 	 requires col >= 0 && col <= 6;
	 * 	 requires color != null && (color == PlayerColor.YELLOW || color == PlayerColor.RED);
	 * 	 ensures getVerticalCount() => 0;
	 */
	public void countVertical(int row, int col, PlayerColor color){
		verticalCount = 0;
		//Check down
		if(row < maxRow-1){
			boolean colorFound = true;
			//@ loop_invariant x >= row+1 && x < this.maxRow;
			for(int x = row + 1; colorFound && x >= row && x < maxRow; x++){
				if(!getField(x, col).equals(color)){
					colorFound = false;
				}else if(getField(x,col).equals(color)){
					verticalCount++;
				}
			}
		}
	}
	
	/**
	 * Counts all the diagonal fields from right up to left down from the given pair (row,col)
	 * that are the <code>PlayerColor</code> that is given and connected
	 * @param row
	 * @param col
	 * @param color
	 * @return the amount of adjacent fields with the same <code>PlayerColor</code>
	 */
	/*
	 * @ requires row >= 0 && row <= 5;
	 * 	 requires col >= 0 && col <= 6;
	 * 	 requires color != null && (color == PlayerColor.YELLOW || color == PlayerColor.RED);
	 * 	 ensures getDiagonalLeftCount() => 0;
	 */
	public void countDiagonalLeft(int row, int col, PlayerColor color){
		diagonalLeftCount = 0;
		//Check left up
		if(row > 0 && col > 0){
			boolean colorFound = true;
			int y = col-1;
			//@ loop_invariant 0 <= x && x <= row-1;
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
			//@ loop_invariant maxRow > x && x >= row+1;
			for(int x = row+1; colorFound && x < maxRow; x++){
				if(!getField(x,y).equals(color)){
					colorFound = false;
				}else if(getField(x,y).equals(color)){
					diagonalLeftCount++;
					y++;
				}
			}
		}
	}
	
	/**
	 * Counts all the diagonal fields from left up to right down from the given pair (row,col)
	 * that are the <code>PlayerColor</code> that is given and connected
	 * @param row
	 * @param col
	 * @param color
	 * @return the amount of adjacent fields with the same <code>PlayerColor</code>
	 */
	/*
	 * @ requires row >= 0 && row <= 5;
	 * 	 requires col >= 0 && col <= 6;
	 * 	 requires color != null && (color == PlayerColor.YELLOW || color == PlayerColor.RED);
	 * 	 ensures getDiagonalRightCount() => 0;
	 */
	public void countDiagonalRight(int row, int col, PlayerColor color){
		diagonalRightCount = 0;
		//Check right up
		if(row < maxRow-1 && col > 0){
			boolean colorFound = true;
			int y = col + 1;
			//@ loop_invariant 0 <= x && x <= row-1;
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
			//@ loop_invariant x < this.maxRow && x >= row+1;
			for(int x = row + 1; colorFound && x < maxRow; x++){
				if(!getField(x,y).equals(color)){
					colorFound = false;
				}else if(getField(x,y).equals(color)){
					diagonalRightCount++;
					y--;
				}
			}
		}
	}
	
	
	/**
	 * Resets the <code>Board</code> by setting all the fields to empty
	 */
	//@ ensures (\forall int i; i < this.maxFields; getField(i) == PlayerColor.EMPTY);
	public void reset(){
		//@ loop_invariant 0 <= i && i < this.maxFields;
		for(int i = 0; i < maxFields; i++){
			setField(i, PlayerColor.EMPTY);
		}
	}
	
	/**
	 * Sets a field on the <code>Board</code> with the given <code>index</code> in the <code>color</code> that is given
	 * @param index
	 * @param color
	 */
	/*
	 * @ requires index >= 0 && index < this.maxFields;
	 * 	 ensures getField(index) == color;
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
	/* pure */public void setField(int row, int col, PlayerColor color){
		setField(index(row,col), color);
	}
	
	/**
	 * Checks if the spot in a column is free so it drops completely down.
	 * If the column is full, the program terminates, because it is not a valid move.
	 * If the field is found and the column is not full, the field is returned.
	 * @param col
	 * @param color
	 * @return
	 */
	/*
	 * @ requires col >= 0 && col <= 6;
	 * 	 requires color != null && (color == PlayerColor.YELLOW || color == PlayerColor.RED);
	 * 	 ensures field >= 0 && field <= this.maxFields;
	 */
	public int checkForFreeSpot(int col, PlayerColor color){
		boolean placeFound = true;
		int field = col;
		for(int i = 0;placeFound && i < maxRow; i++){
			field = col + i * maxCol;
			if(isField(field+7) && !isEmptyField(field+7)){
				placeFound = false;
			}
		}
		for(int j = 0; j < maxCol; j++){
			if(field == j && !isEmptyField(field)){
				field = -1;
			}
		}
		return field;
	}
	
	/**
	 * Returns the horizontalCount
	 * @return
	 */
	/* pure */public int getHorizontalCount(){
		return horizontalCount;
	}

	/**
	 * Returns the verticalCount
	 * @return
	 */
	/* pure */public int getVerticalCount(){
		return verticalCount;
	}
	
	/**
	 * Returns the diagonalLeftCount
	 * @return
	 */
	/* pure */public int getDiagonalLeftCount(){
		return diagonalLeftCount;
	}
	
	/**
	 * Returns the diagonalRightCount
	 * @return
	 */
	/* pure */public int getDiagonalRightCount(){
		return diagonalRightCount;
	}
}
