package ss.week5;
import java.util.LinkedList;
import ss.week5.Board;


public class NaiveStrategy implements Strategy{

	public String getName() {
		return "Naive";
	}

	public int determineMove(Board b, Mark m) {
		LinkedList<Integer> leegveld = new LinkedList<Integer>();
		for(int i = 0; i < 9; i++){
			if(b.isEmptyField(i) == true){
				leegveld.add(i);
			}
		}
		int randomGetal = (int)Math.random() * leegveld.size();
		return leegveld.get(randomGetal);
	}
	

}
