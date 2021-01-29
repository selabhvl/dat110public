/**
 * 
 */
package no.hvl.dat110.rmiclient;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import no.hvl.dat110.rmiinterface.WorkerCallbackInterface;

/**
 * @author tdoy
 *
 */
public class WorkerCallbackImpl extends UnicastRemoteObject implements WorkerCallbackInterface {

	private static final long serialVersionUID = -4446537814151198424L;

	public WorkerCallbackImpl() throws RemoteException{
		super();
	}

	@Override
	public void acknowledge(String workername) throws RemoteException {
		
		System.out.println("Task received by "+workername);
		
	}

	@Override
	public void foundPassword(String password, long duration, String workername) throws RemoteException {
		
		System.out.println("Password found: "+password+" | search takes "+duration+" milisecond | by "+workername);

	}

}
