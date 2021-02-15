package no.hvl.dat110.udp.multiplexing;

import java.net.*;
import java.util.Arrays;

public class ReceiverProcess {

	public static void main(String[] args) {

		if (args.length < 1) {
			throw new RuntimeException("usage: ReceiverProcess <port>");
		}

		int port = Integer.parseInt(args[0]);
		UDPReceiver receiver = null;

		try {

			receiver = new UDPReceiver(port);

			System.out.println("ReceiverProcess@" + InetAddress.getLocalHost().getHostAddress() + ":" + port);
			
		} catch (Exception ex) {

			System.out.println("ReceiverProcess " + ex.getMessage());
			ex.printStackTrace();
		}

		byte[] data = new byte[255];

		boolean stop = false;

		while (!stop) {

			System.out.print("?");

			int len = receiver.receive(data);

			String message = (new String(Arrays.copyOfRange(data, 0, len)));

			System.out.println("[" + message.length() + "]" + message);
			
			if (message.equals("")) {
				stop = true;
			}
		}

		System.out.println("ReceiverProcess terminate");

		if (receiver != null) {
			receiver.close();
		}
	}

}
