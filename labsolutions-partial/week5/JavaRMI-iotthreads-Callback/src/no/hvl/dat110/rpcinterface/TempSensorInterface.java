package no.hvl.dat110.rpcinterface;

/**
 * Lab 2: dat110 course
 */

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface TempSensorInterface extends Remote {
	
	public static final int SERVER_PORT = 9091;
	
	public static final String REMOTE_IFACE_NAME = "TempSensorInterface";
	
	public void registerDisplayCallback(DisplayCallbackInterface displaycallbackobj) throws RemoteException;
	
	public void putTemperature(String temp) throws RemoteException;	
	
	public void setExit(boolean exit) throws RemoteException;
	
	public boolean isExit() throws RemoteException;
	
	public void shutdown() throws RemoteException;

}
