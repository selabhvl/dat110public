package no.hvl.dat110virtualdevices.sensor;

import java.nio.ByteBuffer;

public class IoTTemperatureSensor {

	public static void main(String[] args) {

		try {

			TemperatureSensor sensor = new TemperatureSensor();

			UDPSender sender = new UDPSender();

			while (true) {

				int temp = sensor.read();
				System.out.print("Sending "+ temp);

				byte[] message = ByteBuffer.allocate(4).putInt(temp).array();
				
				boolean ok = sender.send(message);

				if (!ok) {
					System.out.println("TemperatureSensor : Send Error");
				}

				Thread.sleep(10000);

			}

		} catch (Exception ex) {
			System.out.println("TemperatureSensor: " + ex.getMessage());
			ex.printStackTrace();
		}

	}
}
