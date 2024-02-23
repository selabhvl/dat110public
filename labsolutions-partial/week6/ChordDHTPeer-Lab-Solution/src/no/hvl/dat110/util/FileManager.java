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
	
	public static BigInteger[] createReplicaFiles(String filename, int nreplicas) {
		
		// not implemented
		
		return null;
	}
	
    /**
     * 
     * @throws RemoteException 
     */
    public void distributeReplicastoPeers(String filename) throws RemoteException {

    	BigInteger[] replicafiles = createReplicaFiles(filename, Util.numReplicas);
    	
		// resolve the node address for each file replica	
    	Map<String, Integer> processes = Util.getProcesses();
		
		processes.keySet().forEach(name -> {
			int port = processes.get(name);
			NodeInterface node = Util.getProcessStub(name, port);
			
			try {
				BigInteger nodeID = node.getNodeID();
				BigInteger predID = node.getPredecessor().getNodeID();
				
				for(int i=0; i<replicafiles.length; i++) {
					BigInteger fileID = (BigInteger) replicafiles[i];
					
					
					// pred < key <= node
					boolean isServer = Util.checkInterval(fileID, predID.add(BigInteger.ONE), nodeID);
					
					if(isServer)
						node.addKey(fileID);	
				}
			}catch(RemoteException e) {
				//
			}
		});	
		
    }
	
	/**
	 * 
	 * @param filename
	 * @return list of active nodes having the replicas of this file
	 * @throws RemoteException 
	 */
	public Set<NodeInterface> requestActiveNodesForFile(String filename) throws RemoteException {
		
		// generate the N replica keys from the filename
		BigInteger[] replicafiles = createReplicaFiles(filename, Util.numReplicas);

		Set<NodeInterface> peers = new HashSet<>(); 							// change to List ??
		
		Map<String, Integer> processes = Util.getProcesses();
		
		processes.keySet().forEach(name -> {
			int port = processes.get(name);
			NodeInterface node = Util.getProcessStub(name, port);
			
			try {
				for(int i=0; i<replicafiles.length; i++) {
					BigInteger fileID = (BigInteger) replicafiles[i];
					//BigInteger nodeID = node.getNodeID();
					//BigInteger predID = node.getPredecessor().getNodeID();
					
					// pred < key <= node
					//boolean isServer = Util.checkInterval(fileID, predID.add(BigInteger.ONE), nodeID);
					
					if(node.getNodeKeys().contains(fileID))
						peers.add(node);	
				}
			}catch(RemoteException e) {
				//
			}
		});
		
		return peers;
	}

}
