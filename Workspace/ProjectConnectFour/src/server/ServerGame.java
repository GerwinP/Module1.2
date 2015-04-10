package server;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import players.HumanPlayer;
import players.Player;
import utils.PlayerColor;
import utils.ServerProtocol;
import connectFour.Game;

public class ServerGame implements ServerProtocol, Observer{
	
	private Game game;
	private ClientHandler ch1;
	private ClientHandler ch2;
	private List<ClientHandler> chs = new ArrayList<ClientHandler>();
	private Server server;
	private Player player1;
	private Player player2;
	private String player1Name;
	private String player2Name;
	
	public ServerGame(ClientHandler ch1, ClientHandler ch2, Server server){
		this.ch1 = ch1;
		this.ch2 = ch2;
		chs.add(ch1);
		chs.add(ch2);
		player1Name = ch1.getClientName();
		player2Name = ch2.getClientName();
		this.server = server;
		player1 = new HumanPlayer(player1Name, PlayerColor.RED);
		player2 = new HumanPlayer(player2Name, PlayerColor.YELLOW);
		game = new Game(player1, player2);
		game.addObserver(this);
		initiate();
	}
	
	private void initiate(){
		server.broadcast(chs, MAKE_GAME + " " + player1Name + " " + player2Name);
		ch1.setIngame();
		ch2.setIngame();
	}
	
	private String getStringPlayer(){
		if(game.getCurrentPlayer() == player1.getPlayerColor()){
			return "player1";
		}else if(game.getCurrentPlayer() == player2.getPlayerColor()){
			return "player2";
		}else{
			return "geen speler";
		}
	}
	
	public void makeMove(int index, ClientHandler clienthandler){
		if(!game.gameOver()){
			game.takeTurn(index);
			checkGameOver();
		}
	}

	private void checkGameOver(){
		if(game.gameOver()){
			String winner = "draw";
			if(player1.getPlayerColor() == game.getCurrentPlayer()){
				winner = player1Name;
			}else if(player2.getPlayerColor() == game.getCurrentPlayer()){
				winner = player2Name;
			}
			server.broadcast(chs, SEND_GAME_OVER + " " + false + " "+ winner);
		}
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg1 instanceof Integer){
			int index = (int)arg1;
			server.broadcast(chs, MAKE_MOVE + " " + index);
			
		}
//		if(arg1.equals("Game over")){
//			String winner = "draw";
//			if(player1.getPlayerColor() == game.getCurrentPlayer()){
//				winner = player1Name;
//			}else if(player2.getPlayerColor() == game.getCurrentPlayer()){
//				winner = player2Name;
//			}
//			server.broadcast(chs, SEND_GAME_OVER + " " + false + winner);
//		}
	}
}
