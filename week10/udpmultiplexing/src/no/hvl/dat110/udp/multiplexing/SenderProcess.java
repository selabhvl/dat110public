package no.hvl.dat110.udp.multiplexing;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SenderProcess {

	public static void main(String[] args) {

		if (args.length != 2) {
			throw new RuntimeException("usage: SenderProcess <remotehost> <remoteport>");
		}

		String host = args[0];
		int port = Integer.parseInt(args[1]);

		UDPSender sender = null;

		try {
			
			sender = new UDPSender(host, port);
			
		} catch (Exception ex) {

			System.out.println("SenderProcess " + ex.getMessage());
			ex.printStackTrace();
		}

		System.out.println("SenderProcess to " + host + ":" + port);

		String message = null;
		Scanner input;

		do {

			System.out.print("!");

			input = new Scanner(System.in);

			message = input.nextLine();

			sender.send(message.getBytes());

		} while (message != null && !(message.equals("")));

		if (input != null) {
			input.close();
		}

		if (sender != null) {
			sender.close();
		}

		System.out.println("SenderProcess terminate");

	}
}
