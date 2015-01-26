package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client extends Thread{
	
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your username");
		String[] arguments = new String[3];
		arguments[0] = scanner.nextLine();
		System.out.println("Enter ip address");
		arguments[1] = scanner.nextLine();
		System.out.println("Enter port number");
		arguments[2] = scanner.nextLine();
		scanner.close();
		
		InetAddress host = null;
		int port = 0;
		
		try {
			host = InetAddress.getByName(arguments[1]);
		} catch (UnknownHostException e) {
			print("ERROR: no valid hostname!");
			System.exit(0);
		}
		
		try {
			port = Integer.parseInt(arguments[2]);
		} catch (NumberFormatException e) {
			print("ERROR: no valid portnummer!");
			System.exit(0);
		}
		
		try {
			Client client = new Client(arguments[0], host, port);
			client.sendMessage(arguments[0]);
			client.start();
			
			do{
				String input = readIn();
				client.sendMessage(input);
			}while(true);
			
		} catch (IOException e) {
			print("ERROR: couldn't construct a client object!");
			System.exit(0);
		}

	}
	
	private String name;
	private Socket sock = null;
	private BufferedReader in;
	private BufferedWriter out;
	private boolean isConnected = false;
	
	public Client(String name, InetAddress host, int port) throws IOException{
		this.name = name;
		sock = new Socket(host, port);
		isConnected = true;
		in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		out = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
		sendMessage(this.name);
		//sendMessage("hello " + "000 " + name);
	}
	
	public void run(){
		try{
			while(isConnected){
				String message = in.readLine();
				if(message != null){
					print(message);
				}else{
					isConnected = false;
				}
			}
		}catch(IOException e){
			System.out.println("Connection to the server lost. Terminating process");
			shutDown();
		}
	}
	
	public void sendMessage(String msg){
		try {
			out.write(msg);
			out.newLine();
			out.flush();
		}catch(IOException e){
		
		}
	}
	
	public void shutDown(){
		try {
			sock.shutdownInput();
			sock.shutdownOutput();
			sock.close();
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
	
	public static String readIn(){
		String input = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			input = in.readLine();
		} catch (IOException e) {
		}
		return (input == null) ? "" : input;
	}
}
