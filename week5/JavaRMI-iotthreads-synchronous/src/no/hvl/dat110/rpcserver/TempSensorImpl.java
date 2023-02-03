package no.hvl.dat110.rpcserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import no.hvl.dat110.rpcinterface.TempSensorInterface;
import no.hvl.dat110.tempsensor.TemperatureSensor;

/**
 * For demonstration purpose in dat110 course
 */

public class TempSensorImpl extends UnicastRemoteObject implements TempSensorInterface{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TempSensorImpl() throws RemoteException {
		super();
	}
	
	// TODO
	// implement the remote methods defined in the interface here
	public void setTemperatur(int i) {
		
	}
	
	public int getTemperatur() {
		return 0;
	}
}
