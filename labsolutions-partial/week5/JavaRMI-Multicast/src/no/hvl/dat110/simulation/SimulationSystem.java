package no.hvl.dat110.simulation;

import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;

import no.hvl.dat110.rmiclient.PassCrackCoordinatorClient;
import no.hvl.dat110.rmiserver.PassCrackServer;

public class SimulationSystem {

	// here, we run both the server and the client in this simulation class. In the exercise, you will run
	// the rpc servers separately from the rpc client.
	
	public static void main(String[] args) throws InterruptedException, NoSuchAlgorithmException, RemoteException {
		
		System.out.println("System starting ... ");
		
		// start the 2 rpc packcrackserver
		PassCrackServer worker1 = new PassCrackServer(9091, "worker1");
		worker1.start();	
		PassCrackServer worker2 = new PassCrackServer(9092, "worker2");
		worker2.start();
		
		// start clients
		PassCrackCoordinatorClient coordinator = new PassCrackCoordinatorClient();
		coordinator.execute();
		
		System.out.println("System shutting ... ");	
		System.exit(0);
		
	}

}
