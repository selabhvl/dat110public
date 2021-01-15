package no.hvl.dat110.udpsockets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPSender {

	// host name and port with whom to communicate
	private static String HOSTNAME = "localhost";
	private static int HOSTPORT = 8081;

	public static void main(String[] args) {

		byte[] data = { 0, 0, 0, 1 };
		DatagramSocket socket = null;

		System.out.println("UDPSender starting");

		try {

			// create socket
			socket = new DatagramSocket();

			// create a datagram
			InetAddress address = InetAddress.getByName(HOSTNAME);
			DatagramPacket datagram = new DatagramPacket(data, data.length, address, HOSTPORT);

			System.out.println("UDPSender sending");
			socket.send(datagram);

		} catch (Exception ex) {
			System.out.println("UDPSender: " + ex.getMessage());
			ex.printStackTrace();

		} finally {

			if (socket != null) {
				socket.close();
			}

		}

		System.out.println("UDPSender stopped");
	}
}
