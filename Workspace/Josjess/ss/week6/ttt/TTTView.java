package ss.week6.ttt;

import java.awt.*;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class TTTView extends JFrame implements Observer{
	private JLabel  lb; 
	private JButton[] buttons;
	private JButton replay;
	
	public TTTView(Game game) {
	    super("TTTView");
	    init(game);
	}
	
	private void init(Game game) {
		Container c = getContentPane();
		c.setLayout(new GridLayout(0,3));
		buttons = new JButton[9];
		for(int x = 0; x < 9; x++){
			buttons[x] = new JButton();
			c.add(buttons[x]);
		}
		replay = new JButton("New Game?");
		replay.setEnabled(false);
		c.add(replay);
		
		lb = new JLabel("It is XX's turn");

		new TTTController(buttons, game, replay);

		game.addObserver(this);
		
		c.add(lb);
		setSize(600,400); 
		setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
	    	    Game game = new Game();
	    	     new TTTView(game);
	}

	public void update(Observable o, Object arg) {
		Board board = ((Game)o).getBoard();
		Mark mark = ((Game)o).getCurrent();
		
		for (int i = 0; i < 9; i++){
			buttons[i].setText(board.getField(i).toString());
		}
		if (board.hasWinner()){
			if (board.isWinner(mark)){
				lb.setText(mark.toString() + " is the winner");
			}
			else{
				lb.setText(mark.other().toString() + " is the winner");
			}
			
			replay.setEnabled(true);
			
			for (int i = 0; i < 9; i++){
				buttons[i].setEnabled(false);
			}
		}
		else{
			// Gelijk spel
			if (board.isFull()){
				for( int i = 0; i < 9; i++){
					buttons[i].setEnabled(false);
				}
				lb.setText("It's a draw");
				replay.setEnabled(true);
			}
			else{
				lb.setText("It's " + mark.toString() + "turn");
				for (int i = 0; i < 9; i++){
					if(!buttons[i].getText().equals("EMPTY")){
						buttons[i].setEnabled(false);
					}
					else{
						buttons[i].setEnabled(true);
					}
				}
			}
		}
	}
}
