package ss.week5;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class SmartStrategy implements Strategy{

	private static final String name = "Smart";
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public int determineMove(Board b, Mark m) {
		int DIM = b.DIM;
		int toPlay = -1;
		boolean moveDetermined = false;
		if(b.isEmptyField(1, 1)){
			toPlay = b.index(1, 1);
			moveDetermined = true;
			System.out.println("0: " + moveDetermined);
		}else if(!moveDetermined){
			//Check if there is a guaranteed win
			boolean winner = false;
			for(int i = 0;!winner && i < DIM*DIM; i++){
				Board copyB = b.deepCopy();
				if(copyB.isEmptyField(i)){
					copyB.setField(i, m);
					if(copyB.hasWinner()){
						toPlay = i;
						winner = true;
					}
				}
			}
			if(toPlay != -1){
				moveDetermined = true;
			}
			System.out.println("1: " + moveDetermined);
		}if(!moveDetermined){
			//Check if other player can win
			Mark enemy;
			if(m == Mark.OO){
				enemy = Mark.XX;
			}else{
				enemy = Mark.OO;
			}
			boolean loosing = false;
			for(int i = 0; !loosing && i < DIM*DIM; i++){
				Board copyB = b.deepCopy();
				if(copyB.isEmptyField(i)){
					copyB.setField(i, enemy);
					if(copyB.isWinner(enemy)){
						toPlay = i;
						loosing = true;
					}
				}
			}
			if(toPlay != -1){
				moveDetermined = true;
			}
			System.out.println("2: " + moveDetermined);
		}if(!moveDetermined){
			//If none of the above applies, choose random empty field.
			LinkedList<Integer> emptyFields = new LinkedList<Integer>();
			for(int i = 0; i < DIM*DIM; i++){
				if(b.isEmptyField(i)){
					emptyFields.add(i);
				}
			}
			double randomNumber = Math.random() * emptyFields.size();
			toPlay = emptyFields.get((int)randomNumber);
			System.out.println("3: " + moveDetermined);
		}
		return toPlay;
	}
}
