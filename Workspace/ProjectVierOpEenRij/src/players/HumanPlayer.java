package players;

import server.ClientHandler;
import utils.PlayerColor;
import connectFour.Board;

public class HumanPlayer extends Player{

	private ClientHandler clientHandler;
	
	public HumanPlayer(ClientHandler clientHandler, String name, PlayerColor color){
		super(name,color);
		this.clientHandler = clientHandler;
	}
	
	public ClientHandler getClientHandler(){
		return clientHandler;
	}
	
	@Override
	public int determineMove(Board board) {
		
		return 0;
	}

}
