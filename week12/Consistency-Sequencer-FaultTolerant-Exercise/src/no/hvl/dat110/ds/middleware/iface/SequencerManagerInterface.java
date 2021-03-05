package no.hvl.dat110.ds.middleware.iface;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface SequencerManagerInterface extends Remote {
	
	public void checkAlive() throws RemoteException;
	
	public boolean acknowledge() throws RemoteException;
	
	public int getId() throws RemoteException;

}
