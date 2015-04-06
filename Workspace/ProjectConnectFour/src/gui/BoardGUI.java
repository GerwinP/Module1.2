package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import connectFour.Game;
import utils.PlayerColor;

/**
 * The class for creating a <code>BoardGUI</code>
 * It is the view for the Connect Four game.
 * @author Gerwin Puttenstein
 *
 */
@SuppressWarnings("serial")
public class BoardGUI extends JFrame implements Observer{

	private JPanel connectFourPanel;
	private JPanel rowChooserPanel;
	private JPanel infoPanel;
	private JFrame boardFrame;
	private JButton[] buttons;
	private JButton[] rowChoosers;
	private static final int buttonAmount = 42;
	private static final int x = 6;
	private static final int y = 7;
	private static final int DIM = 7;
	
	/**
	 * Creates a new <code>BoardGUI</code>.
	 */
	public BoardGUI(){
		initialise();
	}
	
	/**
	 * Creates a new <code>BoardFrame</code> that displays the <code>Board</code>
	 */
	private void initialise(){
		createBoardFrame();
	}
	
	/**
	 * Creates and returns a <code>BoardFrame</code> with the buttons on it.
	 * @return
	 */
	private JFrame createBoardFrame(){
		boardFrame = new JFrame("Vier op een Rij");
		BorderLayout border = new BorderLayout();
		boardFrame.setLayout(border);
		boardFrame.add(createRowChooserPanel(), BorderLayout.NORTH);
		boardFrame.add(createConnectFourPanel(), BorderLayout.CENTER);
		boardFrame.add(createInfoPanel(), BorderLayout.EAST);
		boardFrame.setSize(700,700);
		boardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		boardFrame.setVisible(true);
		return boardFrame;
	}
	
	/**
	 * Creates the seven <code>JButtons</code> that can be used to choose a row on the <code>Board</code>
	 * and the method returns the rowchoosers as a <code>JPanel</code>
	 * @return
	 */
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
	
	/**
	 * Creates the visual representation of the ConnectFour board.
	 * It creates 42 <code>JButton</code> which are initially set to black.
	 * @return
	 */
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
	
	private JPanel createInfoPanel(){
		infoPanel = new JPanel();
		return infoPanel;
	}
	
	/**
	 * The method that lets the <code>BoardGUI</code> change the Background of the buttons
	 * given an index and a <code>PlayerColor</code>
	 * @param buttonIndex
	 * @param color
	 */
	private void setBackground(int buttonIndex, PlayerColor color){
		if(color == PlayerColor.YELLOW){
			buttons[buttonIndex].setBackground(Color.yellow);	
		} else if(color == PlayerColor.RED){
			buttons[buttonIndex].setBackground(Color.red);
		}
		
	}
	
	private void disableButtons(){
		for(int i = 0; i < y; i++){
			rowChoosers[i].setEnabled(false);
		}
	}
	
	/**
	 * The update method that the <code>BoardGUI</code> uses in the Observer-Oberservable pattern.
	 * It reacts to changes in the <code>Game</code> and makes sure that the colors on the <code>BoardGUI</code>
	 * represent the state of the <code>Game</code>
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg1 instanceof Integer){
			Game game = (Game)arg0;
			setBackground((int)arg1, game.getCurrentPlayer());
		} else if(arg1.equals("Game Over")){
			disableButtons();
			JLabel winner = new JLabel("There is a winner");
			infoPanel.add(winner);
		}
		
	}
	
	/**
	 * A getter for accessing the rowChooser buttons.
	 * @return
	 */
	public JButton[] getRowChoosers(){
		return rowChoosers;
	}
}
