package no.hvl.dat110.week3.exercise6.udp;

import java.net.SocketException;
import java.net.UnknownHostException;

import no.hvl.dat110.week3.exercise4.TemperatureSensor;

public class TemperatureDeviceClient {

	public static void main(String[] args) throws SocketException, UnknownHostException {

		String server = Configuration.SERVER;
		int serverport = Configuration.SERVERPORT;
		int N = Configuration.N;
		
		// EchoClient <server> <port>
		if (args.length > 0) {
			
			server = args[0];
			serverport = Integer.parseInt(args[1]);
			N = Integer.parseInt(args[2]);
		}

		UDPEchoClient udpclient = new UDPEchoClient(server, serverport);

		TemperatureSensor sn = new TemperatureSensor();
		
		System.out.println("UDP client started");
		
		for (int i = 0; i < N; i++) {

			int temp = sn.read();
						
			udpclient.report(Integer.toString(temp));

			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			}

		
		udpclient.stop();
		
		System.out.println("UDP client stopped");

		System.exit(0);
	}
}
