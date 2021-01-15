package no.hvl.dat110.tcpsockets;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPSender {

	private static String HOST = "localhost";
	private static int PORT = 8081;

	public static void main(String[] args) {

		Socket clientSocket = null;
		DataOutputStream outToServer;

		byte[] data = { 1, 2, 3, 4 };

		try {

			clientSocket = new Socket(HOST, PORT);

			outToServer = new DataOutputStream(clientSocket.getOutputStream());

			outToServer.write(data);

			outToServer.close();
			clientSocket.close();

		} catch (IOException ex) {

			System.out.println("TCP client: " + ex.getMessage());
			ex.printStackTrace();
			System.exit(1);
		}

	}
}
