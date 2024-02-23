package no.hvl.dat110.week3.exercise6.udp;

import java.io.*;
import java.net.*;

public class UDPEchoClient {

	private DatagramSocket clientSocket;
	private InetAddress serveradr;
	private int port;
	
	public UDPEchoClient(String server, int port) throws SocketException,UnknownHostException {
	    
		 clientSocket = new DatagramSocket();
		 serveradr = InetAddress.getByName(server);
		 this.port = port;
		 
	}

	public void report(String text) {
				
		byte[] msg = text.getBytes();
		
		try {
	        
        	DatagramPacket request = new DatagramPacket(msg, msg.length, serveradr, port);
        	        	
        	clientSocket.send(request);
        	
        } catch (IOException ex) {
        	
            System.out.println("UDPEchoClient: " + ex.getMessage());
            ex.printStackTrace();
            
        } 
		
	}
	
	public void stop() {
		
		if (clientSocket != null) {
			clientSocket.close();
		}
		
	}
}
