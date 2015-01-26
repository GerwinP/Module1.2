package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import utils.ServerProtocol;

public class ClientHandler extends Thread implements ServerProtocol{

	private Socket client;
	private Server server;
	private BufferedReader in;
	private BufferedWriter out;

	public ClientHandler(Socket clientSock, Server serverSock) {
		client = clientSock;
		server = serverSock;

		try {
			in = new BufferedReader(new InputStreamReader(
					client.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(
					client.getOutputStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			while (true) {
				String message = in.readLine();
				String[] splitMessage = message.split("\\s+");
				if(splitMessage[0].equals(SEND_HELLO)){
					
				}
			}
		} catch (IOException e) {

		}
	}
	
	public void sendMessage(String msg){
		try{
			out.write(msg);
			out.newLine();
			out.flush();
		}catch(IOException e){
			
		}
	}
}
