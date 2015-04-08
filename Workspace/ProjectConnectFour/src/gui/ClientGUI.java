package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import server.Client;

public class ClientGUI {

	public JFrame clientFrame;
	private JPanel clientsPanel;
	private JPanel buttonPanel;
	private JPanel emptyPanel;
	private JTextArea clientsArea;
	public JButton play;
	public JButton quit;
	private Client client;
	
	public ClientGUI(String name, InetAddress host, int port){
		createClientFrame();
		startClient(name,host,port);
	}
	
	private void startClient(String name, InetAddress host, int port){
		try {
			client = new Client(name, host, port, this);
			client.start();
		} catch (IOException e) {
			System.out.println("Can not create client");
			System.exit(0);
		}
	}
	
	public void addMessage(String message){
		clientsArea.append(message + "\n");
	}
	
	private JFrame createClientFrame(){
		clientFrame = new JFrame();
		BorderLayout border = new BorderLayout();
		clientFrame.setLayout(border);
		clientFrame.add(createClientsPanel(), BorderLayout.CENTER);
		clientFrame.add(createButtonPanel(), BorderLayout.SOUTH);
		clientFrame.setSize(400,400);
//		clientFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		clientFrame.setVisible(true);
		return clientFrame;
	}
	
	private JPanel createClientsPanel(){
		clientsPanel = new JPanel();
		clientsArea = new JTextArea();
		clientsArea.setEditable(false);
		Border clientAreaBorder = BorderFactory.createLineBorder(Color.BLACK);
		clientsArea.setBorder(clientAreaBorder);
		JLabel clients = new JLabel("Clients");
		BorderLayout border = new BorderLayout();
		clientsPanel.setLayout(border);
		clientsPanel.add(clients, BorderLayout.NORTH);
		clientsPanel.add(clientsArea, BorderLayout.CENTER);
		clientsPanel.add(createEmptyPanel(), BorderLayout.SOUTH);
		clientsPanel.add(createEmptyPanel(), BorderLayout.WEST);
		clientsPanel.add(createEmptyPanel(), BorderLayout.EAST);
		return clientsPanel;
	}
	
	private JPanel createButtonPanel(){
		buttonPanel = new JPanel();
		play = new JButton("Play");
		play.setActionCommand("play");
		quit = new JButton("Quit");
		quit.setActionCommand("quit");
		BorderLayout border = new BorderLayout();
		buttonPanel.setLayout(border);
		JPanel buttonsPanel = new JPanel();
		GridLayout grid = new GridLayout(1,2);
		buttonsPanel.setLayout(grid);
		buttonsPanel.add(play, BorderLayout.NORTH);
		buttonsPanel.add(quit, BorderLayout.SOUTH);
		buttonPanel.add(buttonsPanel, BorderLayout.CENTER);
		buttonPanel.add(createEmptyPanel(), BorderLayout.SOUTH);
		buttonPanel.add(createEmptyPanel(), BorderLayout.EAST);
		buttonPanel.add(createEmptyPanel(), BorderLayout.WEST);
		return buttonPanel;
	}
	
	private JPanel createEmptyPanel(){
		emptyPanel = new JPanel();
		return emptyPanel;
	}
	
	public static void main(String[] args){
//		new ClientGUI();
	}
}
