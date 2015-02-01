package players;

import server.ClientHandler;
import utils.PlayerColor;
import connectFour.Board;
import server.*;
import utils.*;
import connectFour.*;

public class HumanPlayer extends Player{

	public ClientHandler clientHandler;
	
	public HumanPlayer(ClientHandler clientHandler, String name, PlayerColor color){
		super(name,color);
		this.clientHandler = clientHandler;
	}
	
	public HumanPlayer(String name, PlayerColor color){
		super(name,color);
	}
	
	public ClientHandler getClientHandler(){
		return clientHandler;
	}
	
	@Override
	public int determineMove(Board board) {
		
		return 0;
	}

}
