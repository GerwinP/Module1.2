package server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientStarter {

	private static InetAddress host;
	private static int port;
	
	public static void checkArguments(String[] arguments){
		host = null;
		port = 0;
		
		try {
			host = InetAddress.getByName(arguments[1]);
		} catch (UnknownHostException e) {
			System.out.println("ERROR: no valid hostname!");
			System.exit(0);
		}
		
		try {
			port = Integer.parseInt(arguments[2]);
		} catch (NumberFormatException e) {
			System.out.println("ERROR: no valid portnummer!");
			System.exit(0);
		}
	}
	
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
		checkArguments(arguments);
		Client.startClient(arguments[0], host, port);
		
	}
	
}
