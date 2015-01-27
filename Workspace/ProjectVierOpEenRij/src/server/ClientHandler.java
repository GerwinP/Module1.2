package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import utils.ServerProtocol;

public class ClientHandler extends Thread implements ServerProtocol {

	private Socket client;
	private Server server;
	private BufferedReader in;
	private BufferedWriter out;
	private String clientName;
	private String opties;
	private boolean ack = false;

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
//				server.broadcast("[" + clientName + "]" + in.readLine());
				String msg = in.readLine();
				String[] splitMessage = msg.split("\\s+");
				if (splitMessage[0].equals(SEND_HELLO)) {
					clientName = splitMessage[2];
					server.addClientName(clientName);
					opties = splitMessage[1];
					announce();
					sendMessage("hello " + server.getVersie());
					ack = true;
				}
				if(splitMessage[0].equals(SEND_PLAY) && ack){
					System.out.println("Another one");
					server.waitingForGame.add(client);
					if(server.waitingForGame.size() == 2){
						System.out.println("Two players waiting");
						sendMessage("makeGame");
						
					}
				}
			}
		} catch (IOException e) {
			shutDown();
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

	private void shutDown() {
		server.removeHandler(this);
		server.broadcast("[" + clientName + " has left]");
	}
}
