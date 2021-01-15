package no.hvl.dat110.udpsockets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPReceiver {


		private static int PORT = 8081;
		
		public static void main(String[] args) {

			DatagramSocket socket = null;
			byte[] data = new byte[4];
			
			System.out.println("UDPReceiver starting");
			
			try {
			
				socket = new DatagramSocket(PORT);
		
				DatagramPacket datagram = new DatagramPacket(data, data.length);
			
				System.out.println("UDPReceiver waiting");
				socket.receive(datagram);

				for (byte b : data) {
					System.out.print(b);
				}
				
				System.out.println();
				
			} catch (IOException ex) {
				System.out.println("UDPReceiver: " + ex.getMessage());
		        ex.printStackTrace();
			} finally {
				if (socket != null) {
					socket.close();
				}
				
			System.out.println("UDPReceiver stopped");
		}
	}
}
