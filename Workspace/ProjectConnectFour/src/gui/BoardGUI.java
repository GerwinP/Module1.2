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

import connectFour.Game;

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
	
	public BoardGUI(Game game){
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
			rowChoosers[i].addActionListener(new boardFrameController());
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
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args){
		Game game = new Game();
		BoardGUI gui= new BoardGUI(game);
	}
	
	class boardFrameController implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton source = (JButton)arg0.getSource();
			int index = Arrays.asList(rowChoosers).indexOf(source);
			
		}
		
	}
}
