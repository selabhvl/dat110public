package no.hvl.dat110.udpexample.client;

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

	public String convert(String text) {
				
		byte[] msg = text.getBytes();
		byte[] recvbuffer = new byte[msg.length];
		
		String outtext = null;
		
		try {
	        
        	DatagramPacket request = new DatagramPacket(msg, msg.length, serveradr, port);
        	        	
        	clientSocket.send(request);
        	
			DatagramPacket response = new DatagramPacket(recvbuffer, recvbuffer.length);
			
			clientSocket.receive(response);
						
			outtext = new String(recvbuffer);
			
        } catch (IOException ex) {
        	
            System.out.println("UDPEchoClient: " + ex.getMessage());
            ex.printStackTrace();
            
        } 
		
		return outtext;
		
	}
	
	public void stop() {
		
		if (clientSocket != null) {
			clientSocket.close();
		}
		
	}
}
