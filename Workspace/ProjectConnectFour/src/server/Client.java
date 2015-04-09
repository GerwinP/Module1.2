package server;

import gui.BoardGUI;

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
import java.net.UnknownHostException;

import utils.ServerProtocol;
import connectFour.Board;
import utils.*;

import java.net.*;
import java.io.*;

import gui.*;
import connectFour.*;

public class Client extends Thread implements ServerProtocol{
	
	private static final String USAGE = "usage: java week7.cmdchat.Client <name> <address> <port>";
	
//	public static void main(String[] args){
//		if (args.length != 3) {
//			System.out.println(USAGE);
//			System.exit(0);
//		}
//		
//		InetAddress host = null;
//		int port = 0;
//		
//		try {
//			host = InetAddress.getByName(args[1]);
//		} catch (UnknownHostException e) {
//			print("ERROR: no valid hostname!");
//			System.exit(0);
//		}
//		
//		try {
//			port = Integer.parseInt(args[2]);
//		} catch (NumberFormatException e) {
//			print("ERROR: no valid portnummer!");
//			System.exit(0);
//		}
//		
//		try {
//			Client client = new Client(args[0], host, port);
//			client.sendMessage(args[0]);
//			client.start();
//			
//			do{
//				String input = readIn();
//				client.sendMessage(input);
//			}while(true);
//			
//		} catch (IOException e) {
//			print("ERROR: couldn't construct a client object!");
//			System.exit(0);
//		}
//
//	}
	
	private String name;
	private Socket sock = null;
	private BufferedReader in;
	private BufferedWriter out;
	private boolean isConnected = false;
	private BoardGUI boardgui;
	private Board board;
	private ClientGUI gui;
	
	public Client(String name, String ipaddress, String portString){
		this.name = name;
		InetAddress host = null;
		int port = 0;
		try {
			host = InetAddress.getByName(ipaddress);
		} catch (UnknownHostException e) {
			print("Error: no valid hostname");
			System.exit(0);
		}
		try{
			port = Integer.parseInt(portString);
		} catch(NumberFormatException e){
			print("Error: no valid portnumber");
			System.exit(0);
		}
//		try{
//			Client client = new Client(this.name, host, port);
//		}catch(IOException e){
//			print("ERROR: Could not construct a client object");
//			System.exit(0);
//		}
		
		
	}
	
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

	public void run(){
		try{
			while(isConnected){
				String message = in.readLine();
				String[] splitMessage = message.split("\\s+");
				if(message != null && splitMessage[0].equals(SEND_QUIT)){
					shutDown();
				}else if(message!= null && splitMessage[0].equals(MAKE_GAME)){
					System.out.println("Starting boardGui");
					new ConnectFourController();
				}else if(message != null && splitMessage[0].equals(MAKE_MOVE)){
					int index = Integer.parseInt(splitMessage[1]);
					makeMove(index);
				}else if(message != null && splitMessage[0].equals(SEND_GAME_OVER)){
					print("The game is over");
					print("The winner is: " + splitMessage[2]);
					sendMessage(SEND_GAME_OVER);
				}else if(message != null && splitMessage[0].equals(SEND_ERROR_INVALIDNAME)){
					
				}
				
				
				else if(message != null){
					print(message);
				}else{
					isConnected = false;
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
	
	public void shutDown(){
		try {
//			out.flush();
			System.out.println("Shutdown");
			sock.shutdownInput();
			sock.shutdownOutput();
			sock.close();
			gui.clientFrame.dispose();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void print(String message){
		System.out.println(message);
	}
	
	public String getClientName(){
		return name;
	}
	
	private void makeMove(int index){
//		board.setField(index);
	}
	
	public static String readIn(){
		String input = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			input = in.readLine();
		} catch (IOException e) {
			System.exit(0);
//			e.printStackTrace();
		}
		return (input == null) ? "" : input;
	}
	
	class ClientGUIController extends WindowAdapter implements ActionListener{

		public ClientGUIController(){
			gui.play.addActionListener(this);
			gui.quit.addActionListener(this);
			gui.clientFrame.addWindowListener(this);
		}
		
		public void windowClosing(WindowEvent e){
			Window w = e.getWindow();
			w.dispose();
		}
		
		public void windowClosed(WindowEvent e){
			sendMessage("quit");
		}
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(arg0.getActionCommand().equals("quit")){
				sendMessage("quit");
			} else if(arg0.getActionCommand().equals("play")){
				sendMessage("play");
			}
			
		}
		
	}
}
