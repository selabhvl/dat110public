package no.hvl.dat110.rpcserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import no.hvl.dat110.rpcinterface.TempSensorInterface;

/**
 * For demonstration purpose in dat110 course
 */

public class TempSensorImpl extends UnicastRemoteObject implements TempSensorInterface{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String temp;
	
	public TempSensorImpl() throws RemoteException {
		super();
	}

	@Override
	public String getTemperature() throws RemoteException {	
		return temp;
	}

	@Override
	public void setTemperature(String temp) throws RemoteException {

		this.temp = temp;
	}


}
