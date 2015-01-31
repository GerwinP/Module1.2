package server;

import gui.BoardGUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import connectFour.Game;
import players.HumanPlayer;
import players.Player;
import utils.GameState;
import utils.PlayerColor;
import utils.ServerProtocol;

public class ClientHandler extends Thread implements ServerProtocol {

	private Socket client;
	private Server server;
	private BufferedReader in;
	private BufferedWriter out;
	private String clientName;
	private String opties;
	private boolean ack = false;
	private boolean inGame = false;

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
				}
				if (ack && !inGame && splitMessage[0].equals(SEND_PLAY)) {
					System.out.println("Another one");
					server.waitingForGame.add(this);
					if (server.waitingForGame.size() == 2) {
						System.out.println("Two players waiting");
						server.print(MAKE_GAME);
						server.makeGame();
						server.broadcastInGame(MAKE_GAME);
						inGame = true;
					}
				}
				if (ack && inGame && splitMessage[0].equals(SEND_MOVE)) {
					int index = Integer.parseInt(splitMessage[1]);
					server.makeMove(index);
					System.out.println(MAKE_MOVE);
				}
				if (splitMessage[0].equals(SEND_QUIT)) {
					server.removeClientName(clientName);
					server.removeHandler(this);
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

	public String getClientName() {
		return clientName;
	}

	private void makeMove(int index) {

	}

	private void shutDown() {
		server.removeHandler(this);
		server.broadcast("[" + clientName + " has left]");
	}
}
