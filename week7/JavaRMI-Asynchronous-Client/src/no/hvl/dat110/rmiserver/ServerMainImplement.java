package no.hvl.dat110.rmiserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import no.hvl.dat110.rmiinterface.ClientCallbackInterface;
import no.hvl.dat110.rmiinterface.ServerMainInterface;

/**
 * dat110: DS-Lab 2
 * @author tdoy
 *
 */
public class ServerMainImplement extends UnicastRemoteObject implements ServerMainInterface {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ClientCallbackInterface clientcallbackobj;

	protected ServerMainImplement() throws RemoteException {
		super();
		
		Thread shutdownhook = new Thread(() -> System.out.println("RPC Server is now shutting down...")); 
		Runtime.getRuntime().addShutdownHook(shutdownhook);
	}

	@Override
	public void registerClientCallbackObject(ClientCallbackInterface clientcallbackobj) throws RemoteException {
		
		this.clientcallbackobj = clientcallbackobj;
		
	}
	
	public void doOperation(int a, int b) throws RemoteException {
		
		// acknowledge client message
		clientcallbackobj.acknowledge("From Server: Message recieved!");		// send a message back to the client
				
		try {
			Thread.sleep(8000);  // wait for 8 sec and then add numbers - simulate long task
		} catch(Exception e) {
			//
		}
		int result = a + b;
		
		String resmsg = "Result received from server: "+ a + " + " + b + " = "+ result;

		clientcallbackobj.notify(resmsg);		// notify client through its registered object
	}

	@Override
	public void shutdown() throws RemoteException {
		
		System.out.println("RPC Server will shut down in 2 sec...");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(0);
		
	}

}
