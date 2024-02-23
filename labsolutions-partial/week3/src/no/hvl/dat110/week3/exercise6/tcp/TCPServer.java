package no.hvl.dat110.week3.exercise6.tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

	private ServerSocket welcomeSocket;
	
	public TCPServer(ServerSocket welcomeSocket) {
		this.welcomeSocket = welcomeSocket;
	}
	
	public void process() {
		
		try {
		
			System.out.println("SERVER ACCEPTING");
			
			Socket connectionSocket = welcomeSocket.accept();

			BufferedReader inFromClient = new BufferedReader(
					new InputStreamReader(connectionSocket.getInputStream()));
			
			String text = inFromClient.readLine();
			
			System.out.println("SERVER RECEIVED: " + text);
			
			inFromClient.close();
			
			connectionSocket.close();
			
		} catch (IOException ex) {
			
			System.out.println("TCPServer: " + ex.getMessage());
			ex.printStackTrace();
			System.exit(1);
			
		}
	}
}
