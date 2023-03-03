
package no.hvl.dat110.ds.middleware;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Vector;

/**
 * @author tdoy
 *
 */
public class VectorClock implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int index;						// the index for this process that indicates the position of its clock
	private Vector<Integer> vectorclock;
	
	public VectorClock(int numprocesses, int position) {
		this.index = position;
		vectorclock = new Vector<Integer>();
		vectorclock.setSize(numprocesses);
		for(int i=0; i<vectorclock.size(); i++) {
			vectorclock.set(i, 0);
		}
	}
	
	public VectorClock(Vector<Integer> vc, int index) {
		this.vectorclock = vc;
		this.index = index;
	}
	
	/**
	 * rule1: Increment local entry cj
	 */
	public void updateClockRule1() {
		
		// implement
	}
	
 
	/**
	 * rule2: If process_j receives a message with vector [d1, d2,...,dn] 
	 * Then, set each local entry ck = max{ck, dk}
	 * @param vc
	 */
	public void updateClockRule2(VectorClock vci) {
		
		// implement
		// get max{ck, dk}
		// set the local entry to the max
	}
	
	/**
	 * Two conditions must be fulfilled for this process Pj that received a message, m from process Pi to deliver m to an app 
	 * 1. ts(m)[i] = VCj[i]+1
	 * 2. ts(m)[k] <= VCj[k] for all k != i
	 * @param vi the vector clock time-stamped on the message ts(m) for Pi 
	 * @return true if both conditions are fulfilled and false otherwise.
	 */
	public boolean deliverMessage(VectorClock vi) {
		
		int pi = vi.getIndex();
		Vector<Integer> clock_vi = vi.getVectorclock();
		Vector<Integer> clock_vj = this.getVectorclock();
		
		/** condition 1 **/
		boolean expectedmsg = false;
		// implement
		
		/** condition 2 **/
		boolean prevmsgsdelivered = true;
		// implement
		
		
		return (expectedmsg && prevmsgsdelivered);
	}
	
	public void printClock() throws RemoteException {
		int cindex = index + 1;
		System.out.println("Vector clock at process"+ cindex+" := "+vectorclock);

	}
	
	public Vector<Integer> getVectorclock() {
		return vectorclock;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

}
