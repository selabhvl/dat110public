package no.hvl.dat110.rmiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerMainInterface extends Remote {
	
	public static final String SERVER_INAME = "SInterface";
	/**
	 * This method registers the clientcallback object that the server 
	 * can use to invoke the object's method when it is ready to give the result back
	 * This method must be called by client to invoke the remote method
	 * @param clientcallbackobj
	 * @throws RemoteException
	 */
	public void registerClientCallbackObject(ClientCallbackInterface clientcallbackobj) throws RemoteException;
	
	public void doOperation(int a, int b) throws RemoteException;	// The add method
	
	public void shutdown() throws RemoteException; 					// this method is invoked remotely by client to shut the server down

}
