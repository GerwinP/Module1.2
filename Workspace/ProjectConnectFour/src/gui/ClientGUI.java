package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ClientGUI {

	private JFrame clientFrame;
	private JPanel clientsPanel;
	private JPanel buttonPanel;
	private JTextArea clientsArea;
	
	public ClientGUI(){
		createClientFrame();
	}
	
	private JFrame createClientFrame(){
		clientFrame = new JFrame();
		BorderLayout border = new BorderLayout();
		clientFrame.setLayout(border);
		
		clientFrame.setSize(700,700);
		clientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		clientFrame.setVisible(true);
		return clientFrame;
	}
	
	private JPanel createClientsPanel(){
		clientsPanel = new JPanel();
		clientsArea = new JTextArea();
		return clientsPanel;
	}
	
	private JPanel createButtonPanel(){
		buttonPanel = new JPanel();
		
		return buttonPanel;
	}
}
