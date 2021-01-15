package no.hvl.dat110.tcpsockets;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPReceiver {

	private static int PORT = 8081;

	public static void main(String[] args) {

		ServerSocket welcomeSocket = null;
		char[] data = new char[4];

		try {
			System.out.println("TCP Receiver starting");

			welcomeSocket = new ServerSocket(PORT);

			Socket connectionSocket = welcomeSocket.accept();

			// TODO: check for reading bytes
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));

			System.out.println("TCP Receiver reading");
			inFromClient.read(data);

			for (char b : data) {
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
