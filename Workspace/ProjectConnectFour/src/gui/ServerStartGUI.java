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

import server.Server;

public class ServerStartGUI {
	
	private JPanel infoPanel;
	private JPanel okPanel;
	private JFrame serverFrame;
	private JTextField port;
	private JButton okButton;
	
	public ServerStartGUI(){
		createServerFrame();
	}
	
	private JFrame createServerFrame(){
		serverFrame = new JFrame();
		BorderLayout border = new BorderLayout();
		serverFrame.setLayout(border);
		serverFrame.add(createInfoPanel(), BorderLayout.NORTH);
		serverFrame.add(createOkPanel(), BorderLayout.CENTER);
		serverFrame.setSize(300,100);
		serverFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		serverFrame.setVisible(true);
		return serverFrame;
	}
	
	private JPanel createInfoPanel(){
		infoPanel = new JPanel();
		GridLayout grid = new GridLayout(1,1);
		infoPanel.setLayout(grid);
		JLabel portLabel = new JLabel("Port number");
		port = new JTextField();
		infoPanel.add(portLabel);
		infoPanel.add(port);
		return infoPanel;
	}
	
	private JPanel createOkPanel(){
		okPanel = new JPanel();
		okButton = new JButton("Start Server");
		okPanel.add(okButton);
		okButton.addActionListener(new ServerGUIController());
		okButton.setActionCommand("start server");
		return okPanel;
	}
	
	public static void main(String[] args){
		new ServerStartGUI();
	}
	
	class ServerGUIController implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getActionCommand().equals("start server")){
				String portString = port.getText();
				int portNumber = 0;
				try{
					portNumber = Integer.parseInt(portString);
				} catch(NumberFormatException e){
					System.out.println("NaN");
					System.exit(0);
				}
				new ServerGUI(portNumber);
				serverFrame.dispose();
			}
			
		}
		
	}
}