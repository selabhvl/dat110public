/**
 * 
 */
package no.hvl.dat110.util;

import java.io.Serializable;

/**
 * @author tdoy
 *
 */
public class LamportClock implements Serializable {

	private static final long serialVersionUID = 5030947794470613310L;
	
	private int clock = 0;
	
	
	public void increment() {
		
		clock++;
	}
	
	public void adjustClock(int clock) {
		
		this.clock = clock;
	}
	/**
	 * @return the clock
	 */
	public int getClock() {
		
		return clock;
	}

}
