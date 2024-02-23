package no.hvl.dat110.week3.exercise6.tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class TCPClient {

	private String server;
	private int port;
	
	public TCPClient(String server, int port) {
		this.server = server;
		this.port = port;
	}
	
	public void report (String text) {
	
		String outtext = null;
		
		try {
	
			Socket clientSocket = new Socket(server, port);
    
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
   	
			outToServer.write(text.getBytes());
						
			outToServer.close();
			
			clientSocket.close();
			
		} catch (IOException ex) {
			
			System.out.println("TCP client: " + ex.getMessage());
			ex.printStackTrace();
			System.exit(1);
			
		}
		
	}
}
