package no.hvl.dat110.udp.multiplexing;

import java.io.*;
import java.net.*;

public class UDPSender {

	private DatagramSocket socket = null;
	private InetAddress address;
	private int port;

	public UDPSender(String server, int port) throws SocketException, UnknownHostException {

		socket = new DatagramSocket();
		address = InetAddress.getByName(server);
		this.port = port;

	}

	public boolean send(byte[] data) {

		boolean sent = true;
		
		try {

			DatagramPacket datagram = 
					new DatagramPacket(data, data.length, address, port);

			socket.send(datagram);

		} catch (IOException ex) {
			System.out.println("UDPSender: " + ex.getMessage());
			ex.printStackTrace();
			sent = false;
		}

		return sent;
	}

	public void close() {

		if (socket != null) {
			socket.close();
		}
	}
}
