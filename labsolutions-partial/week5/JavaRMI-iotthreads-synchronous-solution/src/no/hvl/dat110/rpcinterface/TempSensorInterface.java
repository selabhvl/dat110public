package no.hvl.dat110.rpcinterface;

/**
 * For demonstration purpose in dat110 course
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TempSensorInterface extends Remote {
	
	public static final int SERVER_PORT = 9091;
	
	public static final String REMOTE_IFACE_NAME = "TempSensorInterface";
	
	public String getTemperature() throws RemoteException;				// blocking call
	
	public void setTemperature(String temp) throws RemoteException;		// blocking call

}
