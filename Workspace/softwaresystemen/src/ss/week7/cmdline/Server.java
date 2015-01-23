
package ss.week7.cmdline;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Server. 
 * @author  Theo Ruys
 * @version 2005.02.21
 */
public class Server {
    private static final String USAGE = "usage: " + Server.class.getName() + " <name> <port>";

    /** Start een Server-applicatie op. */
    public static void main(String[] args) {
    	if(args.length != 2){
    		System.out.println(USAGE);
    		System.exit(0);
    	}
    	
    	String name = args[0];
    	int port = 0;
    	ServerSocket sock = null;
    	
    	//Parse the port
    	try{
    		port = Integer.parseInt(args[1]);	
    	}catch(NumberFormatException e){
    		System.out.println(USAGE);
    		System.out.println("ERROR: port " + args[1] + " is not an integer");
    		System.exit(0);
    	}
    	
    	//Try to open a Socket
    	try{
    		sock = new ServerSocket(port);
    	}catch(IOException e){
    		System.out.println("Could not create a ServerSocket on port " + port);
    	}
    	
    	try{
    		while(true){
    			Socket clientSocket = sock.accept();
    			System.out.println("Client connected");
    			Peer client = new Peer(name, clientSocket);
    			Thread streamInputHandler = new Thread(client);
    			streamInputHandler.start();
    			client.handleTerminalInput();
    		}
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    }

} // end of class Server
