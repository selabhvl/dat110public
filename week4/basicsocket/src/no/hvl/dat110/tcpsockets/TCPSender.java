package no.hvl.dat110.tcpsockets;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TCPSender {

	private static String HOST = "localhost";
	private static int PORT = 8081;

	public static void main(String[] args) {

		Socket clientSocket = null;
		DataOutputStream outToReceiver;

		byte[] data = { 1, 2, 3, 4 };

		try {

			clientSocket = new Socket(HOST, PORT);

			outToReceiver = new DataOutputStream(clientSocket.getOutputStream());

			outToReceiver.write(data);

			outToReceiver.close();
			
			clientSocket.close();

		} catch (IOException ex) {

			System.out.println("TCP client: " + ex.getMessage());
			ex.printStackTrace();
			System.exit(1);
		}

	}
}
