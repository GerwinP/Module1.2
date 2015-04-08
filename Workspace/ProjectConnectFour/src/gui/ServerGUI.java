package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import server.Server;

public class ServerGUI {
	
	private JFrame serverFrame;
	private JPanel logPanel;
	private JPanel clientPanel;
	private JPanel emptyPanel;
	private JTextArea logArea;
	private JTextArea clientArea;
	private Server server;
	
	public ServerGUI(int port){
		createServerFrame();
		startServer(port);
	}
	
	private void startServer(int port){
		server = new Server(port, this);
		server.start();
	}
	
	public void addMessage(String message){
		logArea.append(message + "\n");
	}
	
	public void addClients(List<String> clients){
		clientArea.setText(null);
		for(int i = 0; i < clients.size(); i++){
			clientArea.append(clients.get(i) + "\n");
		}
	}
	
	private JFrame createServerFrame(){
		serverFrame = new JFrame();
		GridLayout grid = new GridLayout(1,2);
		serverFrame.setLayout(grid);
		serverFrame.add(createLogPanel());
		serverFrame.add(createClientPanel());
		serverFrame.setSize(700,500);
		serverFrame.setVisible(true);
		return serverFrame;
	}
	
	private JPanel createLogPanel(){
		logPanel = new JPanel();
		BorderLayout border = new BorderLayout();
		logPanel.setLayout(border);
		JLabel logLabel = new JLabel("Server log:");
		Border logAreaBorder = BorderFactory.createLineBorder(Color.BLACK);
		logArea = new JTextArea();
		logArea.setEditable(false);
		logArea.setBorder(logAreaBorder);
		logPanel.add(logLabel, BorderLayout.NORTH);
		logPanel.add(logArea, BorderLayout.CENTER);
		logPanel.add(createEmptyPanel(), BorderLayout.WEST);
		logPanel.add(createEmptyPanel(), BorderLayout.SOUTH);
		return logPanel;
	}
	
	private JPanel createClientPanel(){
		clientPanel = new JPanel();
		BorderLayout border = new BorderLayout();
		clientPanel.setLayout(border);
		Border clientAreaBorder = BorderFactory.createLineBorder(Color.BLACK);
		clientArea = new JTextArea();
		clientArea.setEditable(false);
		clientArea.setBorder(clientAreaBorder);
		JLabel clientLabel = new JLabel("Clients");
		clientPanel.add(clientLabel, BorderLayout.NORTH);
		clientPanel.add(clientArea, BorderLayout.CENTER);
		clientPanel.add(createEmptyPanel(), BorderLayout.WEST);
		clientPanel.add(createEmptyPanel(), BorderLayout.EAST);
		clientPanel.add(createEmptyPanel(), BorderLayout.SOUTH);
		return clientPanel;
	}
	
	private JPanel createEmptyPanel(){
		emptyPanel = new JPanel();
		return emptyPanel;
	}
	
	public static void main(String[] args){
//		new ServerGUI();
	}
}
