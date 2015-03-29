package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StartGUI {
	
	private JPanel startPanel;
	private JPanel infoPanel;
	private JFrame startFrame;
	private JButton startButton;
	
	public StartGUI(){
		initialise();
	}
	
	private void initialise(){
		createStartFrame();
	}
	
	private JFrame createStartFrame(){
		startFrame = new JFrame("Welcome");
		BorderLayout border = new BorderLayout();
		startFrame.setLayout(border);
		startFrame.add(createStartPanel(), BorderLayout.SOUTH);
		startFrame.setSize(300, 300);
		startFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		startFrame.setVisible(true);
		return startFrame;
	}
	
	private JPanel createStartPanel(){
		startPanel = new JPanel();
		GridLayout grid = new GridLayout(1,1);
		startPanel.setLayout(grid);
		startButton = new JButton("Start");
		startPanel.add(startButton);
//		startButton.addActionListener(l);
		return startPanel;
	}
	
	public static void main(String[] args){
		new StartGUI();
	}
}
