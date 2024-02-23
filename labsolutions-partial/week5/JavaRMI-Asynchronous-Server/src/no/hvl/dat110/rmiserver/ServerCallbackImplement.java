package no.hvl.dat110.rmiserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import no.hvl.dat110.rmiinterface.ClientCallbackInterface;
import no.hvl.dat110.rmiinterface.ServerCallbackInterface;

public class ServerCallbackImplement extends UnicastRemoteObject implements ServerCallbackInterface {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ClientCallbackInterface clientcallbackobj;
		
	// TODO: use ThreadPool to manage threads lifecycle and OS resources
	protected ServerCallbackImplement() throws RemoteException {
		super();
	}

	@Override
	public synchronized void registerClientCallbackObject(ClientCallbackInterface clientcallbackobj) throws RemoteException {
		
		this.clientcallbackobj = clientcallbackobj;
		
	}
	
	/*
	 * Hint: For multhithreadiing server and multiple client callback requirements
	 * To use clientcallbackobj correctly for multiple clients, this method should be modified to
	 * include ClientCallbackInterface clientcallbackobj as one of its parameters.
	 * i.e., addNumbers(int a, int b, ClientCallbackInterface clientcallbackobj)
	 * This way, we ensure that the result of the computation is returned to the correct client.
	 */
	
	@Override
	public synchronized void addNumbers(int a, int b) throws RemoteException {
		
		// spawn a new thread to handle client request here		
		Thread thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					Thread.sleep(5000);  // wait and then add numbers - simulate long task
				} catch(Exception e) {
					//
				}
				int result = a + b;

				try {
					clientcallbackobj.notify(result);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				
			}
			
		});
		
		thread.start();

	}

}
