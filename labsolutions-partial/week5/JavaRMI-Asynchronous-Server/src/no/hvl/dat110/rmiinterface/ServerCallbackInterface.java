package no.hvl.dat110.rmiinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerCallbackInterface extends Remote {
	
	public static final String SERVER_INAME = "ServerCallbackInterface";
	/**
	 * This method registers the clientcallback object that the server 
	 * can use to invoke the object's method when it is ready to give the result back
	 * This method must be called by client to invoke the remote method
	 * @param clientcallbackobj
	 * @param a
	 * @param b
	 * @throws RemoteException
	 */
	public void registerClientCallbackObject(ClientCallbackInterface clientcallbackobj) throws RemoteException;
	
	public void addNumbers(int a, int b) throws RemoteException;

}
