package ss.week5;

public class PerfectStrategy implements Strategy{

	private static final int losing = 0;
    private static final int neutral = 1;
    private static final int winning = 2;

	public String getName() {
		return "Perfect";
	}

	public int determineMove(Board b, Mark m) {
		return bestMove(b,m, true)[0];
	}
	
	 private int[] bestMove(Board b, Mark m, boolean player) {
	        int bestQual = -1;
	        int bestMove = -1;

	        for (int move = 0; move < 9; move++) {
	            if (b.isEmptyField(move)) {
	                int qual = -1;

	                Board copy = b.deepCopy();
	                copy.setField(move, m);

	                if(copy.isWinner(m)){
	                    qual = winning;
	                } else if (copy.isWinner(m.other())){
	                    qual = losing;
	                } else {
	                    int oppQual = bestMove(copy, m.other(), !player)[1];

	                    if (oppQual == winning) {
	                        qual = losing;
	                    } else if (oppQual == losing) {
	                        qual = winning;
	                    } else {
	                        qual = neutral;
	                    }
	                }

	                if (qual >= bestQual) {
	                    bestQual = qual;
	                    bestMove = move;
	                }
	            }
	        }

	        return new int[]{bestMove, bestQual};
	    }

}

//5.12 de strategy zou in principe wel werken voor schaak, alleen is schaak veel ingewikkelder en zou er dus voor de verschillende pionnen een vershillende strategy ingevuld worden.
