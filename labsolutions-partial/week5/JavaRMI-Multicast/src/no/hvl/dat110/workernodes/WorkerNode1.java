/**
 * 
 */
package no.hvl.dat110.workernodes;

import no.hvl.dat110.rmiserver.PassCrackServer;

/**
 * @author tdoy
 *
 */
public class WorkerNode1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PassCrackServer worker1 = new PassCrackServer(9091, "worker1");
		worker1.start();
	}

}
