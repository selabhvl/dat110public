package no.hvl.dat110.rmiclient;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import no.hvl.dat110.rmiinterface.ClientCallbackInterface;

public class ClientCallbackImplement extends UnicastRemoteObject implements ClientCallbackInterface {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean notified = false;

	protected ClientCallbackImplement() throws RemoteException {
		super();
	}

	@Override
	public void notify(int result) throws RemoteException {
		System.out.println("Result received from server: "+ result);
		notified = true;
	}

	@Override
	public void acknowledge(String message) throws RemoteException {
		
		System.out.println("message");
	}

	public boolean isNotified() {
		return notified;
	}

}
