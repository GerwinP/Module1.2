package gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import connectFour.ConnectFourController;

/**
 * The class that creates a new <code>StartGUI</code>
 * With an inner class that contains the controller for the <code>StartGUI</code>
 * @author Gerwin Puttenstein
 *
 */
public class StartGUI {
	
	private JPanel startPanel;
	private JPanel infoPanel;
	private JFrame startFrame;
	private JButton startButton;
	private JButton startMPButton;
	private JTextField nameField;
	private JTextField ipField;
	private JTextField portField;
	
	private String name;
	private String ipadress;
	private String port;
	
	/**
	 * Initialises a new <code>StartGUI</code>
	 */
	public StartGUI(){
		initialise();
	}
	
	/**
	 * Creates a new frame for the <code>StartGUI</code>
	 */
	private void initialise(){
		createStartFrame();
	}
	
	/**
	 * Returns a <code>JFrame</code> for the startframe
	 * with a infopanel and a startpanel.
	 * @return
	 */
	private JFrame createStartFrame(){
		startFrame = new JFrame("Welcome");
		BorderLayout border = new BorderLayout();
		startFrame.setLayout(border);
		startFrame.add(createStartPanel(), BorderLayout.SOUTH);
		startFrame.add(createInfoPanel(), BorderLayout.NORTH);
		startFrame.setSize(300, 300);
		startFrame.setResizable(false);
		startFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		startFrame.setVisible(true);
		return startFrame;
	}
	
	/**
	 * Creates and returns a <code>JPanel</code> which contains two buttons to start a server or stand alone game
	 * @return
	 */
	private JPanel createStartPanel(){
		startPanel = new JPanel();
		GridLayout grid = new GridLayout(2,1);
		startPanel.setLayout(grid);
		startButton = new JButton("Start standalone");
		startMPButton = new JButton("Start multiplayer");
		startPanel.add(startButton);
		startPanel.add(startMPButton);
		startButton.setActionCommand("standalone");
		startMPButton.setActionCommand("multiplayer");
		startButton.addActionListener(new startFrameController());
		startMPButton.addActionListener(new startFrameController());
		return startPanel;
	}
	
	/**
	 * Creates and returns a <code>JPanel</code> with empty textfields where the information for connecting to a server have to be entered.
	 * It also add labels to these textfields 
	 * @return
	 */
	private JPanel createInfoPanel(){
		infoPanel = new JPanel();
		GridLayout grid = new GridLayout(4,1);
		infoPanel.setLayout(grid);
		nameField = new JTextField();
		ipField = new JTextField("localhost");
		portField = new JTextField("4545");
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
	
	/**
	 * Starts a new <code>StartGUI</code>
	 * @param args
	 */
	public static void main(String[] args){
		new StartGUI();
	}
	
	/**
	 * The controller class for the start menu.
	 * @author Gerwin Puttenstein
	 *
	 */
	class startFrameController implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getActionCommand().equals("standalone")){
				name = nameField.getText();
				if(name.equals(null) || name.equals("")){
					name = "Player";
				}
				new ConnectFourController(name, "2354365256545");
				startFrame.dispose();
			} else if(arg0.getActionCommand().equals("multiplayer")){
				//Create a new client
				name = nameField.getText();
				ipadress = ipField.getText();
				port = portField.getText();
				InetAddress host = null;
				int portNumber = 0;
				
				try {
					host = InetAddress.getByName(ipadress);
				} catch (UnknownHostException e) {
					System.out.println("ERROR: no valid hostname!");
					System.exit(0);
				}
				
				try {
					portNumber = Integer.parseInt(port);
				} catch (NumberFormatException e) {
					System.out.println("ERROR: no valid portnummer!");
					System.exit(0);
				}
				new ClientGUI(name, host, portNumber);
				startFrame.dispose();
			}
		}
		
	}
}
