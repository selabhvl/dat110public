package no.hvl.dat110.rmiinterface;


import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * dat110: DS-Lab 2
 * @author tdoy
 */

public interface WorkerCallbackInterface extends Remote {
	
	public void acknowledge(String workername) throws RemoteException;
	
	public void foundPassword(String password, long duration, String workername) throws RemoteException;

}
