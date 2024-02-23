package no.hvl.dat110.tcpexample.client;

import no.hvl.dat110.tcpexample.system.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class EchoClient {

	public static void main(String[] args) {

		String server = Configuration.SERVER;
		int serverport = Configuration.SERVERPORT;
		int N = Configuration.N;
		String text = null;

		// EchoClient <text>
		if (args.length == 1) {
			text = args[0];
		}

		// EchoClient <server> <port>
		if (args.length > 1) {
			
			server = args[0];
			serverport = Integer.parseInt(args[1]);
			N = Integer.parseInt(args[2]);
		}

		TCPEchoClient tcpclient = new TCPEchoClient(server, serverport);

		System.out.println("TCP client started: " + server + " #" + " - " + N);
		
		for (int i = 0; i < N; i++) {

			if (text != null) {
				
				String uptext = tcpclient.convert(text);
				System.out.println(uptext);
			}
			
		}
			
		System.out.println("TCP client stopped");
		
		// System.exit(0);

	}
}
