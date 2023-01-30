/**
 * 
 */
package no.hvl.dat110.rmiclient;

import java.io.Serializable;

/**
 * @author tdoy
 *
 */
public class Message implements Serializable {
	
	private static final long serialVersionUID = -7206052216865028840L;
	private boolean found = false;
	

	public boolean isFound() {
		return found;
	}

	public void setFound(boolean found) {
		this.found = found;
	}
	
	

}
