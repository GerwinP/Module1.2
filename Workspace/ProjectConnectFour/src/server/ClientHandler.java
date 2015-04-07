package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import utils.ServerProtocol;
import utils.*;
import java.net.*;
import java.io.*;

public class ClientHandler extends Thread implements ServerProtocol {

	private Socket client;
	private Server server;
	private BufferedReader in;
	private BufferedWriter out;
	private String clientName;
	private String opties;
	private boolean ack = false;
	private boolean inGame = false;
	private ServerGame serverGame;

	public ClientHandler(Server serverSock, Socket clientSock)
			throws IOException {
		client = clientSock;
		server = serverSock;

		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		out = new BufferedWriter(new OutputStreamWriter(
				client.getOutputStream()));
	}

	public void run() {
		try {
			while (true) {
				// server.broadcast("[" + clientName + "]" + in.readLine());
				String msg = in.readLine();
				String[] splitMessage = msg.split("\\s+");
				if (splitMessage[0].equals(SEND_HELLO)) {
					clientName = splitMessage[2];
					server.addClientName(clientName);
					opties = splitMessage[1];
					announce();
					sendMessage(SEND_HELLO + " " + server.getVersie());
					ack = true;
				}else if (ack && !inGame && splitMessage[0].equals(SEND_PLAY)) {
					System.out.println("[" + clientName + " is waiting]");
					server.waitingForGame.add(this);
					if (server.waitingForGame.size() == 2) {
						System.out.println("Two players waiting");
						server.print(MAKE_GAME);
						server.makeGame();
					}
				}else if (ack && inGame && splitMessage[0].equals(SEND_MOVE)) {
					int index = Integer.parseInt(splitMessage[1]);
					makeMove(index);
				}else if(splitMessage[0].equals(CHAT)){
					StringBuilder builder = new StringBuilder();
					for(int x = 1; x < splitMessage.length; x++){
						if(builder.length() > 0){
							builder.append(" ");
						}
						builder.append(splitMessage[x]);
					}
					String message = builder.toString();
					server.broadcast("[" + clientName + "]" + message);
				}else if (splitMessage[0].equals(SEND_QUIT)) {
					sendMessage(SEND_QUIT);
					server.removeClientName(clientName);
					server.removeHandler(this);
				}else if(splitMessage[0].equals(SEND_GAME_OVER)){
					server.removeGame(serverGame);
				}else{
//					sendMessage(SEND_ERROR_INVALIDCOMMAND);
				}
			}
		} catch (IOException e) {
			shutDown();
		} catch( NullPointerException e){
			shutDown();
		}
	}

	public void setIngame(){
		if(inGame){
			inGame = false;
		}else{
			inGame = true;
		}
	}
	
	public void announce() throws IOException {
		server.broadcast("[" + clientName + " has entered]");
	}

	public void sendMessage(String msg) {
		try {
			out.write(msg);
			out.newLine();
			out.flush();
		} catch (IOException e) {

		}
	}

	public String getClientName() {
		return clientName;
	}

	public void setServerGame(ServerGame serverGame){
		this.serverGame = serverGame;
	}
	
	public ServerGame getServerGame(){
		return serverGame;
	}
	
	private void makeMove(int index) {
//		serverGame.makeMove(index, this);
	}
	
	private void shutDown() {
		server.removeHandler(this);
		server.broadcast("[" + clientName + " has left]");
	}
}
