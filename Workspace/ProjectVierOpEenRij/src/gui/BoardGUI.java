package gui;

import connectFour.Board;
import connectFour.Game;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("serial")
public class BoardGUI extends JFrame implements Observer, ActionListener{
	
	private JPanel connectFourPanel;
	private JPanel rowChooserPanel;
	private JFrame boardFrame;
	public JButton[] buttons;
	public JButton[] rowChoosers;
	public Game game;
	
	public BoardGUI(){
		super("ConnectFour");
		initialise();
	}
	
	public void initialise(){
		boardFrameFrame();
	}
	
	public JFrame boardFrameFrame(){
		boardFrame = new JFrame("Vier Op Een Rij");
		BorderLayout border = new BorderLayout();
		boardFrame.setLayout(border);
		boardFrame.add(rowChooserPanel(), BorderLayout.NORTH);
		boardFrame.add(connectFourPanel(), BorderLayout.CENTER);
		boardFrame.setVisible(true);
		boardFrame.setSize(700,700);
		boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return boardFrame;
	}
	
	private JPanel rowChooserPanel(){
		rowChooserPanel = new JPanel();
		GridLayout gridLayout = new GridLayout(1,7);
		rowChooserPanel.setLayout(gridLayout);
		rowChoosers = new JButton[7];
		for(int i = 0; i < 7; i++){
			rowChoosers[i] = new JButton();
			rowChoosers[i].setBackground(Color.white);
			rowChoosers[i].setText("\\/");
			rowChoosers[i].addActionListener(new boardFrameController());
			rowChooserPanel.add(rowChoosers[i]);
		}
		return rowChooserPanel;
	}
	
	private JPanel connectFourPanel(){
		connectFourPanel = new JPanel();
		GridLayout gridlayout = new GridLayout(6,7);
		connectFourPanel.setLayout(gridlayout);
		buttons = new JButton[42];
		for(int x = 0; x < 6; x++){
			for(int y = 0; y < 7; y++){
				int index = Board.getIndexButton(x, y);
				buttons[index] = new JButton();
				buttons[index].setBackground(Color.black);
				buttons[index].setText(Integer.toString(index));
				buttons[index].setEnabled(false);
				connectFourPanel.add(buttons[index]);
			}
		}
		return connectFourPanel;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	class boardFrameController implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton)e.getSource();
			int index = Arrays.asList(rowChoosers).indexOf(source);
			System.out.println("move " + index);
		}

	}
}
