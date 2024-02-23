package no.hvl.dat110.rmiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientCallbackInterface extends Remote {
	
	/**
	 *  methods that server must call to notify any client that implements this interface of the addNumber results
	 *  The method is called by the server callback method
	 */
	public void notify(int result) throws RemoteException;
	
	public boolean isNotified() throws RemoteException;
	
	public void acknowledge(String msg) throws RemoteException;
	
}
