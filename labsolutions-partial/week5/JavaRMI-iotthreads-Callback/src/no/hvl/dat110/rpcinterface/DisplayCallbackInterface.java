package no.hvl.dat110.rpcinterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DisplayCallbackInterface extends Remote {
	
	
	public void notifyTemp(String temp) throws RemoteException;
	
	public boolean isExit() throws RemoteException;
	
	public void setExit(boolean exit) throws RemoteException;

}
