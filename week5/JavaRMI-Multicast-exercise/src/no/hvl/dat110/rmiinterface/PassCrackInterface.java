package no.hvl.dat110.rmiinterface;


import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * dat110: DS-Lab 2
 * @author tdoy
 */

public interface PassCrackInterface extends Remote {
	
	public void registerWorkerCallbackObject(WorkerCallbackInterface workercallback) throws RemoteException;
	
	public void crackPassword(int keylength, String hashtocrack, String workername) throws RemoteException;
	
	public void shutdownWorker() throws RemoteException;

}
