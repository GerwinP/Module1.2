package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StartGUI {
	
	private JPanel startPanel;
	private JPanel infoPanel;
	private JFrame startFrame;
	private JButton startButton;
	private JButton startMPButton;
	private JTextField nameField;
	private JTextField ipField;
	private JTextField portField;
	
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
		startFrame.add(createInfoPanel(), BorderLayout.NORTH);
		startFrame.setSize(300, 300);
		startFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		startFrame.setVisible(true);
		return startFrame;
	}
	
	private JPanel createStartPanel(){
		startPanel = new JPanel();
		GridLayout grid = new GridLayout(2,1);
		startPanel.setLayout(grid);
		startButton = new JButton("Start standalone");
		startMPButton = new JButton("Start multiplayer");
		startPanel.add(startButton);
		startPanel.add(startMPButton);
		startButton.addActionListener(new startFrameController());
		return startPanel;
	}
	
	private JPanel createInfoPanel(){
		infoPanel = new JPanel();
		GridLayout grid = new GridLayout(4,1);
		infoPanel.setLayout(grid);
		nameField = new JTextField();
		ipField = new JTextField();
		portField = new JTextField();
		JLabel nameLabel = new JLabel("Name");
		JLabel ipLabel = new JLabel("Ip adres");
		JLabel portLabel = new JLabel("Port");
		infoPanel.add(nameLabel);
		infoPanel.add(nameField);
		infoPanel.add(ipLabel);
		infoPanel.add(ipField);
		infoPanel.add(portLabel);
		infoPanel.add(portField);
		return infoPanel;
	}
	
	public static void main(String[] args){
		new StartGUI();
	}
	
	class startFrameController implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
		}
		
	}
}
