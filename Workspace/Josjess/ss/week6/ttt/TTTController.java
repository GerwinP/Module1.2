package ss.week6.ttt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class TTTController implements ActionListener{
	Game game;
	JButton replay;
	JButton[] buttons;

	public TTTController(JButton[] buttons, Game game, JButton replay){
		this.replay = replay;
		this.game = game;
		this.buttons = buttons;
		for(int i = 0; i < buttons.length; i++){
			buttons[i].addActionListener(this);
		}
		replay.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent ae) {
		boolean tTurn = false;
		if (ae.getSource() == replay){
			game.reset();
			replay.setEnabled(false);
		}
		else{
			for (int i = 0; i < 9 && !tTurn; i++){
				if(ae.getSource() == buttons[i]){
					game.takeTurn(i);
					tTurn = true;
				}
			}
			tTurn = false;
		}
	}

}
