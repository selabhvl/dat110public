package no.hvl.dat110.rmiserver;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import no.hvl.dat110.rmiinterface.ComputeInterface;

/**
 * For demonstration purpose in dat110 course
 * This class is the implementation of the exposed addNumbers method in our interface.
 * @author tdoy Tosin D. Oyetoyan
 */

public class ComputeImpl extends UnicastRemoteObject implements ComputeInterface {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected ComputeImpl() throws RemoteException {
		super();
	}

	public int addNumbers(int a, int b) {
		
		int sum = a + b;
		
		try {
			Thread.sleep(5000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		return sum;
	}
	
	public void stop() throws RemoteException, NotBoundException {	
		System.exit(0);
		
	}
}
