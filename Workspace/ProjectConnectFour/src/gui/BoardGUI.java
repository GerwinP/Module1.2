package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import connectFour.ConnectFourController;
import connectFour.Game;
import utils.PlayerColor;

@SuppressWarnings("serial")
public class BoardGUI extends JFrame implements Observer{

	private JPanel connectFourPanel;
	private JPanel rowChooserPanel;
	private JFrame boardFrame;
	private JButton[] buttons;
	private JButton[] rowChoosers;
	private static final int buttonAmount = 42;
	private static final int x = 6;
	private static final int y = 7;
	private static final int DIM = 7;
	
	/**
	 * Creates a new <code>BoardGUI</code> with a <code>Game</code>
	 * @param game
	 */
	public BoardGUI(){
		initialise();
	}
	
	private void initialise(){
		createBoardFrame();
	}
	
	private JFrame createBoardFrame(){
		boardFrame = new JFrame("Vier op een Rij");
		BorderLayout border = new BorderLayout();
		boardFrame.setLayout(border);
		boardFrame.add(createRowChooserPanel(), BorderLayout.NORTH);
		boardFrame.add(createConnectFourPanel(), BorderLayout.CENTER);
		boardFrame.setSize(700,700);
		boardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		boardFrame.setVisible(true);
		return boardFrame;
	}
	
	private JPanel createRowChooserPanel(){
		rowChooserPanel = new JPanel();
		GridLayout gridLayout = new GridLayout(1,7);
		rowChooserPanel.setLayout(gridLayout);
		rowChoosers = new JButton[y];
		for(int i = 0; i < y; i++){
			rowChoosers[i] = new JButton();
			rowChoosers[i].setBackground(Color.white);
			rowChoosers[i].setText("\\/");
			rowChoosers[i].setActionCommand(Integer.toString(i));
			rowChooserPanel.add(rowChoosers[i]);
		}
		return rowChooserPanel;
	}
	
	private JPanel createConnectFourPanel(){
		connectFourPanel = new JPanel();
		GridLayout gridLayout = new GridLayout(x,y);
		connectFourPanel.setLayout(gridLayout);
		buttons = new JButton[buttonAmount];
		for(int u = 0; u < x; u++){
			for(int v = 0; v < y; v++){
				int index = DIM * u + v;
				JButton button = new JButton();
				button.setBackground(Color.black);
				button.setText(Integer.toString(index));
				button.setEnabled(false);
				buttons[index] = button;
				connectFourPanel.add(buttons[index]);
			}
		}
		return connectFourPanel;
	}
	
	private void setBackground(int buttonIndex, PlayerColor color){
		if(color == PlayerColor.YELLOW){
			buttons[buttonIndex].setBackground(Color.yellow);	
		} else if(color == PlayerColor.RED){
			buttons[buttonIndex].setBackground(Color.red);
		}
		
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg1 instanceof Integer){
//			setBackground((int)arg1, game.getCurrentPlayer());
		}
		
	}
	
	public JButton[] getRowChoosers(){
		return rowChoosers;
	}
	
	public static void main(String[] args){
//		BoardGUI gui= new BoardGUI();
	}
}
