package connectFour;

import gui.BoardGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

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
	
	/**
	 * Creates a new <code>Game</code> with two <code>Player</code> objects.
	 * Also creates a new <code>BoardGUI</code> and connects the <code>Game</code> and the <code>BoardGUI</code> together.
	 * For every button on the <code>BoardGUI</code> it adds an <code>ActionListener</code> to it.
	 */
	public ConnectFourController(){
		Player p1 = new HumanPlayer("Gerwin", PlayerColor.RED);
		Player p2 = new HumanPlayer("Henk", PlayerColor.YELLOW);
		game = new Game(p1,p2);
		gui = new BoardGUI();
		this.game.addObserver(this.gui);
		rowChoosers = this.gui.getRowChoosers();
		for(int i = 0; i < y; i++){
			rowChoosers[i].addActionListener(this);
		}
	}

	private void disableButtons(){
		for(int i = 0; i < y; i++){
			rowChoosers[i].setEnabled(false);
		}
	}
	
	/**
	 * The <code>ActionPerformed</code> method that reacts on button presses.
	 */
	public void actionPerformed(ActionEvent arg0) {
		int index = Integer.parseInt(arg0.getActionCommand());
//		if(game.gameOver()){
//			System.out.println("There is a winner");
//			disableButtons();
//		}
		game.takeTurn(index);
	}
}
