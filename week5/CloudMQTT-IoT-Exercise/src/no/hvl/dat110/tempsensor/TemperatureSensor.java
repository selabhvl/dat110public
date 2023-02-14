package no.hvl.dat110.tempsensor;

import java.util.Random;

public class TemperatureSensor {


	public int read() {

		Random ran = new Random();
		
		return ran.nextInt(40);
	}
}
