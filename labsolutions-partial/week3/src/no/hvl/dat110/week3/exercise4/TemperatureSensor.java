package no.hvl.dat110.week3.exercise4;


public class TemperatureSensor {

	private static final int RANGE = 20;

	public int read() {

		long seconds = System.currentTimeMillis();

		double temp = RANGE * Math.sin(seconds / 1000);

		return (int) Math.ceil(temp);
	}
}
