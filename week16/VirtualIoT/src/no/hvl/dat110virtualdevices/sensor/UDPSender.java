package no.hvl.dat110virtualdevices.sensor;

import java.io.*;
import java.net.*;

import no.hvl.dat110.virtualdevices.common.Configuration;

public class UDPSender {

	private DatagramSocket socket = null;
	private InetAddress address;
	private int port;
	
	public UDPSender() throws SocketException,UnknownHostException {
	    
		 socket = new DatagramSocket();
		 address = InetAddress.getByName(Configuration.server);
		 port = Configuration.serverport;
		 
	}
	
	public boolean send(byte[] message) {

        boolean sent = true;
        try {
        
        	DatagramPacket request = new DatagramPacket(message, message.length, address, port);
        	
        	System.out.print(".");
        	
        	socket.send(request);
        	
			System.out.println("!");
        
        } catch (IOException ex) {
            System.out.println("UDPSender: " + ex.getMessage());
            ex.printStackTrace();
            sent = false;
        }
        
        return sent;
	}
	
	public void stop() {
		
		if (socket != null) {
			socket.close();
		}
		
	}
}