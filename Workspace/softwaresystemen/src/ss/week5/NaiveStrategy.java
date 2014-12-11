package ss.week5;

import java.util.HashSet;
import java.util.Set;

public class NaiveStrategy implements Strategy{

	private static final String name = "Naive";
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public int determineMove(Board b, Mark m) {
		Set<Integer> emptyFields = new HashSet<Integer>();
		for(int i = 0; i < b.DIM*b.DIM; i++){
			if(b.isEmptyField(i)){
				emptyFields.add(i);
			}
		}
		double randomNumber = Math.random() * emptyFields.size();
		return (int)randomNumber;
	}

}
