package server;

import gui.ErrorGUI;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Client {

	public String name;
	
	public Client(String nameArg, String inetAddressArg, int portArg){
		name = nameArg;
		
		InetAddress host = null;
		int port = portArg;
		
		try {
			host = InetAddress.getByName(inetAddressArg);
		} catch (UnknownHostException e) {
			new ErrorGUI("Host could not be found");
		}
	}
}
