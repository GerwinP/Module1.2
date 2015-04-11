package server;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.JButton;

import players.ComputerPlayer;
import utils.PlayerColor;
import utils.ServerProtocol;
import gui.*;
import connectFour.*;

public class Client extends Thread implements ServerProtocol{
	
	private String name;
	private Socket sock = null;
	private BufferedReader in;
	private BufferedWriter out;
	private boolean isConnected = false;
	private ClientGUI gui;
	private boolean inGame = false;
	private BoardGUI bgui;
	private JButton[] rowChoosers;
	private final int y = 7;
	private boolean myTurn = false;
	
	/**
	 * The constructor that creates a new <code>Client</code>.
	 * It gets a name, a portnumber, a host and a <code>ClientGUI</code>
	 * After it is created, it sends an hello message to the server.
	 * @param name
	 * @param host
	 * @param port
	 * @param gui
	 * @throws IOException
	 */
	public Client(String name, InetAddress host, int port, ClientGUI gui) throws IOException{
		this.name = name;
		this.gui = gui;
		sock = new Socket(host, port);
		isConnected = true;
		in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
		new ClientGUIController();
		sendMessage("hello " + "100 " + name);
	}

	/**
	 * When the clienthread is started and the <code>Client</code> is connected, it will process the incoming messages.
	 */
	public void run(){
		try{
			while(isConnected){
				String message = in.readLine();
				String[] splitMessage = message.split("\\s+");
				if(message != null && splitMessage[0].equals(SEND_QUIT)){
					shutDown();
				}else if(message!= null && splitMessage[0].equals(MAKE_GAME)){
					if(!inGame){
						System.out.println("Starting boardGui");
						makeGame(splitMessage[1], splitMessage[2]);
					}
				}else if(message != null && inGame && splitMessage[0].equals(MAKE_MOVE)){
					int index = Integer.parseInt(splitMessage[1]);
					makeMove(index);
				}else if(message != null && inGame && splitMessage[0].equals(SEND_GAME_OVER)){
					gameOver(splitMessage[2]);
				}else if(message != null && splitMessage[0].equals(SEND_ERROR_INVALIDNAME)){
//					print(SEND_ERROR_INVALIDNAME);
//					shutDown();
				}else if(message != null){
					print(message);
				}
			}
			System.out.println("No longer connected, terminating process");
			shutDown();
		}catch(IOException e){
			System.out.println("IO: Connection to the server lost. Terminating process");
			shutDown();
		}catch(NullPointerException e){
			System.out.println("NULL: Connection to the server lost. Terminating process");
			shutDown();
		}
	}
	
	/**
	 * When this method is called, the message that is given as an parameter will be printed to the <code>Client</code>.
	 * After that, the message will be written to the output stream of the socket. If that is not possible, the <code>Client</code> will terminate.
	 * @param msg
	 */
	public void sendMessage(String msg){
		print(msg);
		try {
			out.write(msg);
			out.newLine();
			out.flush();
		}catch(IOException e){
			shutDown();
		}
	}
	
	/**
	 * The method that will shutdown the client. 
	 * It will shutdown the socket input and output and then close the socket. 
	 * After that it will dispose the <code>ClientGUI</code> and then exit the program.
	 */
	public void shutDown(){
		try {
			sock.shutdownInput();
			sock.shutdownOutput();
			sock.close();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Just prints the message to System.out.
	 * @param message
	 */
	private void print(String message){
		System.out.println(message);
		gui.addMessage(message);
	}
	
	/**
	 * Returns the name of the client.
	 * @return
	 */
	public String getClientName(){
		return name;
	}
	
	/**
	 * Creates a new <code>ConnectFourController</code> with the two players that are going to play this game.
	 * @param name1
	 * @param name2
	 */
	private void makeGame(String name1, String name2){
		inGame = true;
		new BoardGUIController();
		if(name.equals(name2)){
			bgui.setButtons(false);
		}else if(name.equals(name1)){
			myTurn = true;
		}
	}
	
	/**
	 * Makes a move on the <code>Board</code>
	 * @param index
	 */
	private void makeMove(int index){
		bgui.setBackground(index);
		setTurn();
		bgui.nextPlayer();
	}
	
	private void setTurn(){
		if(myTurn){
			myTurn = false;
			bgui.setButtons(false);
		}else{
			myTurn = true;
			bgui.setButtons(true);
		}
	}
	
	private void gameOver(String winner){
		inGame = false;
		print("The game is over");
		print("The winner is: " + winner);
		bgui.disableGUI();
	}
	
	/**
	 * The class that controlls the <code>ClientGUI</code>
	 * @author Gerwin
	 *
	 */
	class ClientGUIController extends WindowAdapter implements ActionListener{

		/**
		 * Adds the actionlisteners to the buttons on the <code>ClientGUI</code>
		 * Also adds an windowListener to the <code>JFrame</code> of the <code>ClientGUI</code>
		 */
		public ClientGUIController(){
			gui.play.addActionListener(this);
			gui.quit.addActionListener(this);
			gui.chat.addActionListener(this);
			gui.clientFrame.addWindowListener(this);
		}
		
		/**
		 * It makes sure that if the window is closing the frame that created the windowevent will be disposed
		 */
		public void windowClosing(WindowEvent e){
			Window w = e.getWindow();
			w.dispose();
		}
		
		/**
		 * Makes sure that if the window is closed, the <code>Client</code> will safely shutdown.
		 */
		public void windowClosed(WindowEvent e){
			sendMessage(SEND_QUIT);
		}
		
		/**
		 * Handles the button input.
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getActionCommand().equals("quit")){
				gui.clientFrame.dispose();
			} else if(arg0.getActionCommand().equals("play")){
				sendMessage(SEND_PLAY);
			} else if(arg0.getActionCommand().equals("chat")){
				sendMessage(CHAT + " " + gui.getChatText());
				gui.clearChatText();
			}
		}
		
	}
	
	class BoardGUIController implements ActionListener{

		public BoardGUIController(){
			bgui = new BoardGUI(name);
			rowChoosers = bgui.getRowChoosers();
			bgui.hint.addActionListener(this);
			for(int i = 0; i < y; i++){
				rowChoosers[i].addActionListener(this);
			}
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getActionCommand().equals("hint")){
				int col = new ComputerPlayer("", PlayerColor.EMPTY).determineMove();
				int index = new Board().checkForFreeSpot(col, PlayerColor.RED);
				bgui.setHint(index);
			}else{
				int index = Integer.parseInt(arg0.getActionCommand());
				sendMessage(SEND_MOVE + " " + index);
			}
		}
		
	}
}
