package connectFour;

import gui.BoardGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import players.ComputerPlayer;
import players.HumanPlayer;
import players.Player;
import utils.PlayerColor;

/**
 * The controller for the Connect Four game
 * @author Gerwin Puttenstein
 *
 */
public class ConnectFourController implements ActionListener{
	
	private Game game;
	private BoardGUI gui;
	private static final int y = 7;
	private JButton[] rowChoosers;
	private Player p1;
	private Player p2;
	
	/**
	 * Creates a new <code>Game</code> with two <code>Player</code> objects.
	 * Also creates a new <code>BoardGUI</code> and connects the <code>Game</code> and the <code>BoardGUI</code> together.
	 * For every button on the <code>BoardGUI</code> it adds an <code>ActionListener</code> to it.
	 */
	public ConnectFourController(String p1, String p2){
		this.p1 = new HumanPlayer(p1, PlayerColor.RED);
		if(p2.equals("2354365256545")){
			this.p2 = new ComputerPlayer("Computer", PlayerColor.YELLOW);
		}else{
			this.p2 = new HumanPlayer(p2, PlayerColor.YELLOW);
		}
		game = new Game(this.p1,this.p2);
		gui = new BoardGUI();
		this.game.addObserver(this.gui);
		rowChoosers = this.gui.getRowChoosers();
		for(int i = 0; i < y; i++){
			rowChoosers[i].addActionListener(this);
		}
	}
	
	/**
	 * The <code>ActionPerformed</code> method that reacts on button presses.
	 */
	public void actionPerformed(ActionEvent arg0) {
		int index = Integer.parseInt(arg0.getActionCommand());
		game.takeTurn(index);
		if(p2 instanceof ComputerPlayer){
			System.out.println(p2.determineMove());
			game.takeTurn(p2.determineMove());
		}
	}
}
