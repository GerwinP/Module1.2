package ss.week5;

public class ComputerPlayer extends Player{
	
	//------------- Constructors-------------
	Strategy strategy;
	
	public ComputerPlayer (Mark mark, Strategy strategy){
		super(strategy +"-"+ mark, mark);
		this.strategy = strategy;
	}
	
	public ComputerPlayer (Mark mark){
		this(mark, new NaiveStrategy());
	}
	
	//public String getName(){
	//	return strategy +"-"+ mark;
	//}
	
	public int determineMove(Board board) {
		return strategy.determineMove(board, super.getMark());
	}
}

