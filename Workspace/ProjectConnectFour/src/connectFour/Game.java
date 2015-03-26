package connectFour;

import players.Player;

public class Game {
	
	public static final int NUMBER_PLAYERS = 2;
	
	private Board board;
	
	private Player[] players;
	
	private int current;
	
	public Game(Player p1, Player p2){
		board = new Board();
		players = new Player[NUMBER_PLAYERS];
		players[0] = p1;
		players[1] = p2;
		current = 0;
	}
	
	public void start(){
		boolean goOn = true;
		while(goOn){
			reset();
			play();
		}
	}
	
	private void reset(){
		current = 0;
		board.reset();
	}
	
	private void play(){
		while(!board.gameOver()){
			players[current].makeMove(board);
			current = (current+1) % NUMBER_PLAYERS;
		}
		
	}
}
