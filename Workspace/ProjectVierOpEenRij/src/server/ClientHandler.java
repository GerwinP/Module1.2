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

	public ClientHandler(Socket clientSock, Server serverSock)
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
//				String message = in.readLine();
				server.broadcast("[" + clientName + "]" + in.readLine());
//				String[] splitMessage = message.split("\\s+");
//				if (splitMessage[0].equals(SEND_HELLO)) {
//
//				}
			}
		} catch (IOException e) {
			shutDown();
		}
	}

	public void announce() throws IOException {
		clientName = in.readLine();
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
