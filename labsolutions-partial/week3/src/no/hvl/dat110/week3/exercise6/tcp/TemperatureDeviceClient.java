package no.hvl.dat110.week3.exercise6.tcp;

import no.hvl.dat110.week3.exercise4.TemperatureSensor;

public class TemperatureDeviceClient {

	public static void main(String[] args) {

		String server = Configuration.SERVER;
		int serverport = Configuration.SERVERPORT;
		int N = Configuration.N;
		
		// TemperatureDeviceClient <server> <port>
		if (args.length > 0) {
			
			server = args[0];
			serverport = Integer.parseInt(args[1]);
			N = Integer.parseInt(args[2]);
		}
			
		TCPClient tcpclient = new TCPClient(server,serverport);
		
		TemperatureSensor sn = new TemperatureSensor();

		System.out.println("TCP client started: " + server + " #" + " - " + N);
		
		for (int i = 0; i < N; i++) {

				int temp = sn.read();
				
				tcpclient.report(Integer.toString(temp));
				
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
	
		System.out.println("TCP client stopped");
		
		System.exit(0);

	}
}
