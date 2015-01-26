package server;

import gui.ErrorGUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	private String name;
	private Socket sock = null;
	private BufferedReader in;
	private BufferedWriter out;
	
	public Client(String nameArg, String inetAddressArg, int portArg){
		name = nameArg;
		
		InetAddress host = null;
		int port = portArg;
		
		try {
			host = InetAddress.getByName(inetAddressArg);
		} catch (UnknownHostException e) {
			new ErrorGUI("Host could not be found");
		}
		
		try {
			sock = new Socket(host, port);
			out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		} catch (IOException e) {
			new ErrorGUI("Could not create a socket on address " + host + " and port " + port);
			System.exit(0);
		}
		sendMessage("hello " + "000 " + name);
	}
	
	public void sendMessage(String msg){
		try {
			out.write(msg);
			out.newLine();
			out.flush();
		}catch(IOException e){
		
		}
	}
	
	public String getName(){
		return name;
	}
}
