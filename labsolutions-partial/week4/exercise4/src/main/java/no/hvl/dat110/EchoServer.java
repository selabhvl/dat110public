package no.hvl.dat110;

import java.net.SocketException;
import java.net.UnknownHostException;

public class EchoServer {

	public static void main(String[] args) throws SocketException, UnknownHostException {

		int SERVER_PORT = 8080;
		int serverport;

		if (args.length > 0) {			
			
			serverport = Integer.parseInt(args[0]);
		}

		System.out.println("UDP Server starting ... #" + SERVER_PORT);

		UDPEchoServer udpserver = new UDPEchoServer(SERVER_PORT);
				
		while (true) {
			
			udpserver.process();
			
		}
	}
}
