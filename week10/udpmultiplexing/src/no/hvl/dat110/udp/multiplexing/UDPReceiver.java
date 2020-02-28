package no.hvl.dat110.udp.multiplexing;

import java.io.*;
import java.net.*;

public class UDPReceiver {

	private DatagramSocket socket;

	public UDPReceiver(int port) throws SocketException {
		socket = new DatagramSocket(port);
	}

	public int receive(byte[] data) {

		DatagramPacket datagram = new DatagramPacket(data, data.length);

		try {

			socket.receive(datagram);

		} catch (IOException ex) {
			System.out.println("UDPSender: " + ex.getMessage());
	        ex.printStackTrace();
		}

		return datagram.getLength();
	}

	public void close() {

		if (socket != null) {
			socket.close();
		}
	}
}
