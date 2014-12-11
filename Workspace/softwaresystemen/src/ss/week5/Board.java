package ss.week5;

/**
 * Game student for the Tic Tac Toe game. Module 2 lab assignment.
 * 
 * @author Theo Ruys en Arend Rensink
 * @version $Revision: 1.4 $
 */
public class Board {

    // -- Constants --------------------------------------------------

    public static final int DIM = 3;
    private static final String[] NUMBERING = {" 0 | 1 | 2 ", "---+---+---",
        " 3 | 4 | 5 ", "---+---+---", " 6 | 7 | 8 "};
    private static final String LINE = NUMBERING[1];
    private static final String DELIM = "     ";

    // -- Instance variables -----------------------------------------

    /*@
       private invariant fields.length == DIM*DIM;
       invariant (\forall int i; 0 <= i & i < DIM*DIM;
           getField(i) == Mark.EMPTY || getField(i) == Mark.XX || getField(i) == Mark.OO);
     */
    /**
     * The DIM by DIM fields of the Tic Tac Toe student. See NUMBERING for the
     * coding of the fields.
     */
    private Mark[] fields;

    // -- Constructors -----------------------------------------------

    /*@
       ensures (\forall int i; 0 <= i & i < DIM * DIM; this.getField(i) == Mark.EMPTY);
     */
    /**
     * Creates an empty student.
     */
    public Board() {
    	fields = new Mark[DIM*DIM];
    	reset();
    }

    // -- Queries ----------------------------------------------------

    /*@
       ensures \result != this;
       ensures (\forall int i; 0 <= i & i < DIM * DIM; \result.getField(i) == this.getField(i));
     */
    /**
     * Creates a deep copy of this field.
     */
    public Board deepCopy() {
    	Board copyBoard = new Board();
    	copyBoard.fields = this.fields;
        return copyBoard;
    }


    /*@
       requires 0 <= row & row < DIM;
       requires 0 <= col & col < DIM;
     */
    /**
     * Calculates the index in the linear array of fields from a (row, col)
     * pair.
     * @return the index belonging to the (row,col)-field
     */
    public int index(int row, int col) {
    	int index = col + row*DIM;
        return index;
    }


    /*@
       ensures \result == (0 <= ix && ix < DIM * DIM);
     */
    /**
     * Returns true if <code>ix</code> is a valid index of a field on the student.
     * @return <code>true</code> if <code>0 <= ix < DIM*DIM</code>
     */
    /*@pure*/
    public boolean isField(int ix) {
        boolean isField = false;
    	if(0 <= ix && ix < DIM*DIM){
        	isField = true;
    	}
        return isField;
    }

    /*@
       ensures \result == (0 <= row && row < DIM && 0 <= col && col < DIM);
     */
    /**
     * Returns true of the (row,col) pair refers to a valid field on the student.
     * 
     * @return true if <code>0 <= row < DIM && 0 <= col < DIM</code>
     */
    /*@pure*/
    public boolean isField(int row, int col) {
    	return isField(index(row,col));
    }


    /*@
       requires this.isField(i);
       ensures \result == Mark.EMPTY || \result == Mark.XX || \result == Mark.OO;
     */
    /**
     * Returns the content of the field <code>i</code>.
     * 
     * @param i
     *            the number of the field (see NUMBERING)
     * @return the mark on the field
     */
    public Mark getField(int i) {
    	Mark mark = null;
    	if(isField(i)){
    		mark = fields[i];
    	}
    	return mark;
    }

    /*@
       requires this.isField(row,col);
       ensures \result == Mark.EMPTY || \result == Mark.XX || \result == Mark.OO;
     */
    /**
     * Returns the content of the field referred to by the (row,col) pair.
     * 
     * @param row
     *            the row of the field
     * @param col
     *            the column of the field
     * @return the mark on the field
     */
    public Mark getField(int row, int col) {
        return getField(index(row,col));
    }

    /*@
       requires this.isField(i);
       ensures \result == (this.getField(i) == Mark.EMPTY);
     */
    /**
     * Returns true if the field <code>i</code> is empty.
     * 
     * @param i
     *            the index of the field (see NUMBERING)
     * @return true if the field is empty
     */
    public boolean isEmptyField(int i) {
        return getField(i) == Mark.EMPTY;
    }

    /*@
       requires this.isField(row,col);
       ensures \result == (this.getField(row,col) == Mark.EMPTY);

     */
    /**
     * Returns true if the field referred to by the (row,col) pair it empty.
     * 
     * @param row
     *            the row of the field
     * @param col
     *            the column of the field
     * @return true if the field is empty
     */
    /*@pure*/
    public boolean isEmptyField(int row, int col) {
        return getField(index(row,col)) == Mark.EMPTY;
    }

    /*@
       ensures \result == (\forall int i; i <= 0 & i < DIM * DIM; this.getField(i) != Mark.EMPTY);
     */
    /**
     * Tests if the whole student is full.
     * 
     * @return true if all fields are occupied
     */
    /*@pure*/
    public boolean isFull() {
    	boolean isFull = true;
    	for(int i = 0; i < DIM*DIM; i++){
    		if(fields[i] == Mark.EMPTY){
    			isFull = false;
    		}
    	}
        return isFull;
    }

    /*@
       ensures \result == this.isFull() || this.hasWinner();

     */
    /**
     * Returns true if the game is over. The game is over when there is a winner
     * or the whole student is full.
     * 
     * @return true if the game is over
     */
    /*@pure*/
    public boolean gameOver() {
        return hasWinner() || isFull();
    }

    /**
     * Checks whether there is a row which is full and only contains the mark
     * <code>m</code>.
     * 
     * @param m
     *            the mark of interest
     * @return true if there is a row controlled by <code>m</code>
     */
    public boolean hasRow(Mark m) {
    	boolean hasRow = false;
    	for(int col = 0;!hasRow && col < DIM; col++){
        	boolean isEqual = true;
    		for(int row = 0;isEqual && row < DIM; row++){
    			if(getField(row,col) == m){
    				hasRow = true;
    				isEqual = true;
    			}else{
    				hasRow = false;
    				isEqual = false;
    			}
    		}
    	}
        return hasRow;
    }

    /**
     * Checks whether there is a column which is full and only contains the mark
     * <code>m</code>.
     * 
     * @param m
     *            the mark of interest
     * @return true if there is a column controlled by <code>m</code>
     */
    public boolean hasColumn(Mark m) {
    	boolean hasColumn = false;
    	for(int row = 0;!hasColumn && row < DIM; row++){
        	boolean isEqual = true;
    		for(int col = 0;isEqual && col < DIM; col++){
    			if(getField(row,col) == m){
    				hasColumn = true;
    				isEqual = true;
    			}else{
    				hasColumn = false;
    				isEqual = false;
    			}
    		}
    	}
        return hasColumn;
    }

    /**
     * Checks whether there is a diagonal which is full and only contains the
     * mark <code>m</code>.
     * 
     * @param m
     *            the mark of interest
     * @return true if there is a diagonal controlled by <code>m</code>
     */
    public boolean hasDiagonal(Mark m) {
    	int row = 0;
    	int hasDiagonalLeft = 0;
    	for(int col = 0; col < DIM; col++){
    		if(getField(row, col) == m){
    			hasDiagonalLeft++;	
    		}
    		row++;
    	}
    	row = 0;
    	int hasDiagonalRight = 0;
    	for(int col = 2; col >= 0; col--){
    		if(getField(row,col) == m){
    			hasDiagonalRight++;	
    		}
    		row++;
    	}
        return hasDiagonalLeft==3 || hasDiagonalRight==3;
    }

    /*@
       requires m == Mark.XX | m == Mark.OO;
       ensures \result == this.hasRow(m) ||
                                this.hasColumn(m) |
                                this.hasDiagonal(m);
     */
    /**
     * Checks if the mark <code>m</code> has won. A mark wins if it controls at
     * least one row, column or diagonal.
     * 
     * @param m
     *            the mark of interest
     * @return true if the mark has won
     */
    /*@pure*/
    public boolean isWinner(Mark m) {
        return hasRow(m) || hasColumn(m) || hasDiagonal(m);
    }

    /*@
       ensures \result == isWinner(Mark.XX) | \result == isWinner(Mark.OO);

     */
    /**
     * Returns true if the game has a winner. This is the case when one of the
     * marks controls at least one row, column or diagonal.
     * 
     * @return true if the student has a winner.
     */
    /*@pure*/
    public boolean hasWinner() {
        return isWinner(Mark.XX) || isWinner(Mark.OO);
    }

    /**
     * Returns a String representation of this student. In addition to the current
     * situation, the String also shows the numbering of the fields.
     * 
     * @return the game situation as String
     */
    public String toString() {
        String s = "";
        for (int i = 0; i < DIM; i++) {
            String row = "";
            for (int j = 0; j < DIM; j++) {
                row = row + " " + getField(i, j).toString() + " ";
                if (j < DIM - 1) {
                    row = row + "|";
                }
            }
            s = s + row + DELIM + NUMBERING[i * 2];
            if (i < DIM - 1) {
                s = s + "\n" + LINE + DELIM + NUMBERING[i * 2 + 1] + "\n";
            }
        }
        return s;
    }

    // -- Commands ---------------------------------------------------

    /*@
       ensures (\forall int i; 0 <= i & i < DIM * DIM; this.getField(i) == Mark.EMPTY);
     */
    /**
     * Empties all fields of this student (i.e., let them refer to the value
     * Mark.EMPTY).
     */
    public void reset() {
    	for(int i = 0; i < DIM*DIM; i++){
    		setField(i, Mark.EMPTY);
    	}
    }

    /*@
       requires this.isField(i);
       ensures this.getField(i) == m;
     */
    /**
     * Sets the content of field <code>i</code> to the mark <code>m</code>.
     * 
     * @param i
     *            the field number (see NUMBERING)
     * @param m
     *            the mark to be placed
     */
    public void setField(int i, Mark m) {
    	fields[i] = m;
    }

    /*@
       requires this.isField(row,col);
       ensures this.getField(row,col) == m;

     */
    /**
     * Sets the content of the field represented by the (row,col) pair to the
     * mark <code>m</code>.
     * 
     * @param row
     *            the field's row
     * @param col
     *            the field's column
     * @param m
     *            the mark to be placed
     */
    public void setField(int row, int col, Mark m) {
    	fields[index(row,col)] = m;
    }

}
