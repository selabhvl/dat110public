package no.hvl.dat110.rmiclient;

import java.rmi.RemoteException;


import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.paukov.combinatorics3.Generator;

import no.hvl.dat110.crack.PasswordUtility;
import no.hvl.dat110.rmiinterface.PassCrackInterface;
import no.hvl.dat110.rmiinterface.WorkerCallbackInterface;
import no.hvl.dat110.workernodes.Utility;

/**
 * For demonstration purpose in dat110 course
 * @author tdoy
 */

public class PassCrackCoordinatorClient {
	
	public static void main(String args[]) throws NoSuchAlgorithmException, RemoteException {

		PassCrackCoordinatorClient coordinator = new PassCrackCoordinatorClient();
		coordinator.execute();
	}
	
	private void execute() throws NoSuchAlgorithmException, RemoteException {
		
		int[] jobspasswordlen = {6, 5}; 		// these are jobs that will be distributed to 2 workers
		
		// password = s0lbA
		String hashofpassword = PasswordUtility.generateHashWithoutSalt("s0lbA");
		System.out.println("This is the hash of the password we want to crack "+hashofpassword);

		/**
		 *  we'll multicast the jobs to each worker. Each worker takes a keylength to define the search space
		 */
	
		Message message = new Message();
		
		int i=0;
		Map<String, Integer> workers = Utility.getWorkers();
		Iterator<String> workernodes = workers.keySet().iterator();
		while(workernodes.hasNext()) {
			
			// TODO
			
			// retrieve the next worker and pass the message as the argument in the constructor

			// get the port of the registry on which the worker object is located

			// get a reference to the worker using the Utility class
			
			// create an instance of the workercallbackimpl
			
			// register the workercallbackimpl on worker	
			
			// call the crackPassword remote method

		}
		
		while(!message.isFound()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
		System.exit(0);
		
	}

}
