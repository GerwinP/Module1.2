package ss.week5;

public class TicTacToe{
	
	private static Player createPlayer(String s, Mark m){
		Player player;
		if(s.equals("-N")){
			player = new ComputerPlayer(new NaiveStrategy(), m);
		}else{
			player = new HumanPlayer(s, m);
		}
		return player;
	}
	
	public static void main(String[] args){
		Player player1 = createPlayer(args[0], Mark.OO);
		Player player2 = createPlayer(args[1], Mark.XX);
		Game game = new Game(player1, player2);
		game.start();
	}
}
