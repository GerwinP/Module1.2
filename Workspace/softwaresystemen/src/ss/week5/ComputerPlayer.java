package ss.week5;

public class ComputerPlayer extends Player{

	private Strategy strategy;
	
	public ComputerPlayer(Strategy strategy, Mark mark){
		super(strategy + "-" + mark, mark);
		this.strategy = strategy;
	}
	
	public ComputerPlayer(Mark mark){
		this(new NaiveStrategy(), mark);
	}
	
	@Override
	public int determineMove(Board board) {
		return strategy.determineMove(board, super.getMark());
	}
}
