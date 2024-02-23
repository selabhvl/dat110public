package no.hvl.dat110.week3.exercise4;

public class TemperatureMeasurement {

	private int temp = 0;

    
    // 4.4 - ensure mutual exclusion on measurement object methods
	public synchronized int getTemperature() {
		
		try {
			wait(); // 4.5 - wait until a new temperature is available
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return temp;
	}

	// 4.4 - ensure mutual exclusion on measurement object methods
	public synchronized void setTemperature(int temp) {
		
		this.temp = temp;
		
		notifyAll(); // 4.5 - wakeup all display threads waiting for new sensor reading
		
	}
}
