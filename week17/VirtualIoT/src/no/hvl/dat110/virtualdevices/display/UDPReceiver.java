package no.hvl.dat110.virtualdevices.display;

import java.io.*;
import java.net.*;

import no.hvl.dat110.virtualdevices.common.Configuration;

public class UDPReceiver {

	private DatagramSocket socket;

	public UDPReceiver() throws SocketException {
		socket = new DatagramSocket(Configuration.serverport);
	}

	public boolean receive(byte[] message) {

		boolean received = true;
		
		DatagramPacket request = new DatagramPacket(message, message.length);
		
		try {
		
			System.out.print("?");
			
			socket.receive(request);
			
			System.out.print(".");
			
		} catch (IOException ex) {
			received = false;
		}
		
		return received;
	}
	
	public void stop() {
		
		if (socket != null) {
			socket.close();
		}
	}
}
