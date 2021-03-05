/**
 * 
 */
package no.hvl.dat110.ds.sequencer.managers;

import java.rmi.RemoteException;

import no.hvl.dat110.ds.middleware.Config;
import no.hvl.dat110.ds.middleware.SequencerManagerContainer;

/**
 * @author tdoy
 */
public class SequencerMgr1 {

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		
		new SequencerManagerContainer("sequencer-mgr1", Config.PORT5, 1, 50);		// live for 50sec
	}

}
