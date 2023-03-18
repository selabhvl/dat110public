/**
 * 
 */
package no.hvl.dat110.ds.sequencer.managers;

import java.rmi.RemoteException;

import no.hvl.dat110.ds.middleware.Config;
import no.hvl.dat110.ds.middleware.SequencerManagerContainer;

/**
 * @author tdoy
 *
 */
public class SequencerMgr3 {

	/**
	 * @param args
	 * @throws RemoteException 
	 */
	public static void main(String[] args) throws RemoteException {
		
		new SequencerManagerContainer("sequencer-mgr3", Config.PORT7, 3, 250);		// live for 250sec

	}

}
