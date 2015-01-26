package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

 

public class StartGUI extends JFrame implements Observer, ActionListener{

	private JPanel startPanel;
	private JFrame start;
	private JPanel chooseColorPanel;
	private JPanel informationPanel;
	
	public StartGUI(){
		super("Welcome");
		initialise();
	}
	
	public void initialise(){
		startFrame();
	}
	
	public JFrame startFrame(){
		start = new JFrame("Connect Four");
		BorderLayout border = new BorderLayout();
		start.setLayout(border);
		start.add(chooseColorPanel());
		start.add(informationPanel(), BorderLayout.CENTER);
		start.setVisible(true);
		start.setSize(200,300);
		start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return start;
	}
	
	private JPanel chooseColorPanel(){
		chooseColorPanel = new JPanel();
		JRadioButton red = new JRadioButton("red");
		JRadioButton yellow = new JRadioButton("yellow");
		JRadioButton pink = new JRadioButton("pink");
		JRadioButton green = new JRadioButton("green");
		chooseColorPanel.add(red);
		chooseColorPanel.add(yellow);
		chooseColorPanel.add(pink);
		chooseColorPanel.add(green);
		return chooseColorPanel;
	}
	
	private JPanel informationPanel(){
		informationPanel = new JPanel();
		JLabel nameLabel = new JLabel("Name:");
		JLabel portLabel = new JLabel("Port:");
		JLabel ipLabel = new JLabel("IP address: ");
		TextField nameField = new TextField(20);
		TextField ipField = new TextField(20);
		TextField portField = new TextField(20);
		nameLabel.setLabelFor(nameField);
		portLabel.setLabelFor(portField);
		ipLabel.setLabelFor(ipField);
		JButton confirm = new JButton("Confirm");
		
		
		informationPanel.add(nameLabel);
		informationPanel.add(nameField);
		informationPanel.add(ipLabel);
		informationPanel.add(ipField);
		informationPanel.add(portLabel);
		informationPanel.add(portField);
		informationPanel.add(confirm);
		return informationPanel;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args){
		new StartGUI();
	}

}
