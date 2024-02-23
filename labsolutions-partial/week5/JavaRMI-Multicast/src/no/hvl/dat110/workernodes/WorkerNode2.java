/**
 * 
 */
package no.hvl.dat110.workernodes;

import no.hvl.dat110.rmiserver.PassCrackServer;

/**
 * @author tdoy
 *
 */
public class WorkerNode2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PassCrackServer worker2 = new PassCrackServer(9092, "worker2");
		worker2.start();
	}

}
