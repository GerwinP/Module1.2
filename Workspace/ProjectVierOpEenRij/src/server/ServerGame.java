package server;

import java.util.ArrayList;
import java.util.List;

import players.HumanPlayer;
import players.Player;
import utils.PlayerColor;
import utils.ServerProtocol;
import connectFour.Game;

public class ServerGame implements ServerProtocol{
	
	private Game game;
	private ClientHandler ch1;
	private ClientHandler ch2;
	private List<ClientHandler> chs = new ArrayList<ClientHandler>();
	private Server server;
	private Player player1;
	private Player player2;
	
	public ServerGame(ClientHandler ch1, ClientHandler ch2, Server server){
		this.ch1 = ch1;
		this.ch2 = ch2;
		chs.add(ch1);
		chs.add(ch2);
		this.server = server;
		player1 = new HumanPlayer(ch1, ch1.getName(), PlayerColor.RED);
		player2 = new HumanPlayer(ch2, ch2.getName(), PlayerColor.YELLOW);
		game = new Game(player1, player2);
		initiate();
	}
	
	private void initiate(){
		game.setGameState("inprogress");
		server.broadcast(chs, MAKE_GAME);
	}
	
	private String getStringPlayer(){
		if(game.getCurrentPlayer() == player1){
			return "player1";
		}else if(game.getCurrentPlayer() == player2){
			return "player2";
		}else{
			return "geen speler";
		}
	}
	
	public void makeMove(int index, ClientHandler clienthandler){
		System.out.println(getStringPlayer());
		if(game.getCurrentPlayer() == player1){
			game.board.setCurrentPlayer(player1);
			game.board.setStone(index);
			server.broadcast(chs, MAKE_MOVE + " " + index);
		}else if(((HumanPlayer)player2).getClientHandler() == clienthandler &&  game.getCurrentPlayer() == player2){
			game.board.setCurrentPlayer(player2);
			game.board.setStone(index);
			server.broadcast(chs, MAKE_MOVE + " " + index);
		}
	}
}
