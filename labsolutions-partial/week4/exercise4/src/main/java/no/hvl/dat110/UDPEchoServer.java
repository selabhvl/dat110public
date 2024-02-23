package no.hvl.dat110;

import java.io.*;
import java.net.*;

public class UDPEchoServer {

	private final int MAX_TEXTLEN = 1024;

	private DatagramSocket serverSocket;

	public UDPEchoServer(int serverport) throws SocketException {

		serverSocket = new DatagramSocket(serverport);
	}

	public void process() {

		byte[] recvbuf = new byte[MAX_TEXTLEN];

		DatagramPacket request = new DatagramPacket(recvbuf, recvbuf.length);

		try {

			serverSocket.receive(request);
			
			String intext = new String(request.getData());
			
			System.out.println("SERVER RECEIVED: " + intext + " " + intext.length());

			String outtext = intext.toUpperCase();
			
			byte[] msg = outtext.getBytes();
		
			InetAddress ipaddress = request.getAddress();
			int port = request.getPort();

			DatagramPacket response = new DatagramPacket(msg, msg.length, ipaddress, port);

			System.out.println("SERVER SENDING:  " + outtext);

			serverSocket.send(response);

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
