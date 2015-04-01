package connectFour;

import gui.BoardGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ConnectFourController implements ActionListener{
	
	private Game game;
	private BoardGUI gui;
	private static final int y = 7;
	private JButton[] rowChoosers;
	
	public ConnectFourController(Game game, BoardGUI gui){
		this.game = game;
		this.gui = gui;
		rowChoosers = this.gui.getRowChoosers();
		for(int i = 0; i < y; i++){
			rowChoosers[i].addActionListener(this);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton source = (JButton)arg0.getSource();
		int index = Integer.parseInt(arg0.getActionCommand());
		System.out.println(index);
	}
}
