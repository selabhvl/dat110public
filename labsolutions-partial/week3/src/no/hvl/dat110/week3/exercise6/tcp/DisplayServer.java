package no.hvl.dat110.week3.exercise6.tcp;

import java.io.IOException;
import java.net.ServerSocket;


public class DisplayServer {
	
	public static void main(String[] args) {
		
		int serverport = Configuration.SERVERPORT;
		
		if (args.length > 0) {			
			
			serverport = Integer.parseInt(args[0]);
		}
		
		System.out.println("TCP server starting # " + serverport);
		
		try (ServerSocket welcomeSocket = new ServerSocket(serverport)) {
				
			TCPServer server = new TCPServer(welcomeSocket);

			while (true) {
				
				server.process();
				
			}
			
		} catch (IOException ex) {
			System.out.println("TCP server: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
