package no.hvl.dat110.rmiclient;

import java.rmi.RemoteException;

/**
 * For demonstration purpose in dat110 course
 */

import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;

import no.hvl.dat110.crack.PasswordUtility;
import no.hvl.dat110.rmiinterface.PassCrackInterface;
import no.hvl.dat110.rmiinterface.WorkerCallbackInterface;
import no.hvl.dat110.workernodes.Utility;


public class PassCrackCoordinatorClient {
	
	public static void main(String args[]) throws NoSuchAlgorithmException, RemoteException {

		PassCrackCoordinatorClient coordinator = new PassCrackCoordinatorClient();
		coordinator.execute();
	}
	
	public void execute() throws NoSuchAlgorithmException, RemoteException {
		
		int[] jobspasswordlen = {5,6}; 		// these are jobs that will be distributed to 2 workers
		
		// password = s0lbA
		String hashofpassword = PasswordUtility.generateHashWithoutSalt("s0lbA");
		System.out.println("This is the hash of the password we want to crack "+hashofpassword);

		// we'll multicast the jobs to each worker. Each worker takes a keylength to define the search space
		// ideally, the job should be distributed evenly among the workers. You can think about a solution to this
		// point-to-point communication
		Message message = new Message();
			
		int i=0;
		Map<String, Integer> workers = Utility.getWorkers();
		Iterator<String> workernodes = workers.keySet().iterator();
		while(workernodes.hasNext()) {
			WorkerCallbackInterface workercallback = new WorkerCallbackImpl(message);
			String workername = workernodes.next();
			int workerport = workers.get(workername);
			PassCrackInterface worker = Utility.getWorkerstub(workername, workerport);
			worker.registerWorkerCallbackObject(workercallback);			// register a callback obj	
			worker.crackPassword(jobspasswordlen[i++], hashofpassword, workername);
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
