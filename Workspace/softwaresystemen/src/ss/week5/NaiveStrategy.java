package ss.week5;

import java.util.LinkedList;

public class NaiveStrategy implements Strategy{

	private static final String name = "Naive";
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public int determineMove(Board b, Mark m) {
		LinkedList<Integer> emptyFields = new LinkedList<Integer>();
		for(int i = 0; i < b.DIM*b.DIM; i++){
			if(b.isEmptyField(i)){
				emptyFields.add(i);
			}
		}
		double randomNumber = Math.random() * emptyFields.size();
		int toPlay = emptyFields.get((int)randomNumber);
		return (int)toPlay;
	}

}
