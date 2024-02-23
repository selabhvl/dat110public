package no.hvl.dat110.week3.exercise6.udp;

import java.io.*;
import java.net.*;


public class UDPEchoServer {

	private DatagramSocket serverSocket;

	public UDPEchoServer(int serverport) throws SocketException {

		serverSocket = new DatagramSocket(serverport);
	}

	public void process() {

		byte[] recvbuf = new byte[Configuration.MAXTEXTLEN];

		DatagramPacket request = new DatagramPacket(recvbuf, recvbuf.length);

		try {

			serverSocket.receive(request);
			
			String intext = new String(request.getData());
			
			System.out.println("SERVER RECEIVED: " + intext);


		} catch (IOException ex) {
			
			System.out.println("UDPServer: " + ex.getMessage());
			ex.printStackTrace();
			
		} 
	}

	public void stop() {

		if (serverSocket != null) {
			serverSocket.close();
		}

	}
}
