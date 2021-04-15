package no.hvl.dat110.virtualdevices.display;

import java.net.*;
import java.nio.ByteBuffer;

public class IoTDisplayActuator {

	public static void main(String[] args) {

		UDPReceiver receiver = null;

		try {

			DisplayActuator display = new DisplayActuator();

			receiver = new UDPReceiver();

			while (true) {

				byte[] message = new byte[4];

				boolean ok = receiver.receive(message);
				int temp = ByteBuffer.wrap(message).getInt();

				if (ok) {
					display.write(Integer.toString(temp));
				} else
					display.write("DisplayActuator: Receive error");

			}
		} catch (SocketException ex) {
			System.out.println("DisplayActuator: " + ex.getMessage());
			ex.printStackTrace();

		}

		finally {
			if (receiver != null) {
				receiver.stop();

			}
		}
	}
}
