package no.hvl.dat110.util;

/**
 * dat110
 * @author tdoy
 */

import java.math.BigInteger;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.hvl.dat110.rpc.interfaces.NodeInterface;

public class Util {
	 
	public static int numReplicas = 4;  
	
	/**
	 * @param id
	 * @param pred
	 * @param node
	 * @return true if (pred < id <= node) or false otherwise
	 */
	public static boolean computeLogic(BigInteger id, BigInteger pred, BigInteger node) {
		 
		// Task: given an identifier, id: check whether pred < id <= node
		
		
		return false;
	}
	
	public static List<String> toString(List<NodeInterface> list) throws RemoteException {
		List<String> nodestr = new ArrayList<String>();
		list.forEach(node -> 
			{
				try {
					nodestr.add(node.getNodeName());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		);

		
		return nodestr;
	}
	
	public static BigInteger[] createReplicaFiles(String filename, int nreplicas) {
		
		// Task:given a filename, create nreplicas (1 to nreplicas)- idea, append each index to the filename before hash
		
		// hash the replica using the Hash.hashOf() and store it in an array
		
		// return the replicas as array of BigInteger
		
		return null;
	}
	
	public static NodeInterface getProcessStub(String name, int port) {
		
		NodeInterface nodestub = null;
		Registry registry = null;
		try {
			// Get the registry for this worker node
			registry = LocateRegistry.getRegistry(port);		
			
			nodestub = (NodeInterface) registry.lookup(name);	// remote stub
			
		} catch (NotBoundException | RemoteException e) {
			return null;			// if this call fails, then treat the node to have left the ring...or unavailable
		}
		
		return nodestub;
	}
	
	/**
	 * Do not modify! This is a static group of 5 processes with their names and the registry ports from which their stubs can
	 * be looked up.
	 * @return
	 */
	public static Map<String, Integer> getProcesses(){
		
		Map<String, Integer> processes = new HashMap<>();
		processes.put("process1", 9091);
		processes.put("process2", 9092);
		processes.put("process3", 9093);
		processes.put("process4", 9094);
		processes.put("process5", 9095);
		
		return processes;
	}

}
