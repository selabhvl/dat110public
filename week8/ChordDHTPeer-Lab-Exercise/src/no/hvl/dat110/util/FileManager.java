package no.hvl.dat110.util;


/**
 * @author tdoy
 * dat110 - DSLab 2
 */

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import no.hvl.dat110.rpc.interfaces.NodeInterface;

public class FileManager {
	
    /**
     * 
     * @throws RemoteException 
     */
    public void distributeReplicastoPeers(String filename) throws RemoteException {
    	
    	// Task: Given a filename, make replicas and distribute them to all active peers such that: pred < replica <= peer
    	
    	// create replicas of the filename
    	
		// collect the 5 processes from the Util class	
		
    	// iterate over the processes
    	
    	// iterate over the replicas
    	
    	// for each replica, add the replica to the peer if the condition: pred < replica <= peer is satisfied	
		
    }
	
	/**
	 * 
	 * @param filename
	 * @return list of active nodes having the replicas of this file
	 * @throws RemoteException 
	 */
	public Set<NodeInterface> requestActiveNodesForFile(String filename) throws RemoteException {
		
		Set<NodeInterface> peers = new HashSet<>(); 							
		
		// Task: Given a filename, find all the peers that hold a copy of this file
		
		// see the distributeReplicastoPeers(filename): same rules.
		

		
		return peers;
	}

}
