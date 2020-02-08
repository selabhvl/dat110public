/**
 * 
 */
package no.hvl.dat110.workernodes;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

import no.hvl.dat110.rmiinterface.PassCrackInterface;

/**
 * @author tdoy
 *
 */
public class Utility {

	public static final String[] keyspace = {"a", "b", "c", "d", "e", "f", "g", "h", "i",
			"j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y","z"};
	
	public static String[] getKeyspace() {
		String[] upper = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
				"J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y","Z"};
		String[] lower = {"a", "b", "c", "d", "e", "f", "g", "h", "i",
				"j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y","z"};
		String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
		String[] keyspace = new String[upper.length+lower.length+numbers.length];
		
		int i=0;
		for(String s : lower) {
			keyspace[i++] = s;
		}
		for(String s : upper) {
			keyspace[i++] = s;
		}
		for(String s : numbers) {
			keyspace[i++] = s;
		}
		
		return keyspace;
	}
	
	public static Map<String, Integer> getWorkers(){
		
		Map<String, Integer> workers = new HashMap<>();
		workers.put("worker1", 9091);
		workers.put("worker2", 9092);
		
		return workers;
	}
	
	public static PassCrackInterface getWorkerstub(String name, int port) {
		
		PassCrackInterface workerstub = null;
		Registry registry = null;
		try {			
			// Get the registry for this worker node
			registry = LocateRegistry.getRegistry(port);
			
			// Look up the registry for the worker remote object
			workerstub = (PassCrackInterface) registry.lookup(name);

		} catch(Exception e) {
			//System.err.println("Error "+e.getMessage());
			//e.getStackTrace();
		}
		
		return workerstub;
	}

}
