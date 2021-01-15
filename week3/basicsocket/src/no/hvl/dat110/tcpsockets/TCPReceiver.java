package no.hvl.dat110.tcpsockets;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPReceiver {

	private static int PORT = 8081;

	public static void main(String[] args) {

		ServerSocket welcomeSocket = null;
		byte[] data = new byte[4];

		try {
			System.out.println("TCP Receiver starting");

			welcomeSocket = new ServerSocket(PORT);

			Socket connectionSocket = welcomeSocket.accept();

			DataInputStream inFromClient = new DataInputStream(connectionSocket.getInputStream());
			
			System.out.println("TCP Receiver reading");
			inFromClient.read(data);

			for (byte b : data) {
				System.out.print((byte) b);
			}
			System.out.println();

			inFromClient.close();

			connectionSocket.close();

			welcomeSocket.close();

		} catch (IOException ex) {

			System.out.println("TCPServer: " + ex.getMessage());
			ex.printStackTrace();
			System.exit(1);

		}
		System.out.println("TCP Receiver stopping");
	}

}
