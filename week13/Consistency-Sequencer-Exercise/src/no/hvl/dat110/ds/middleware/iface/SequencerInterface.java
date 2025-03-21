package no.hvl.dat110.ds.middleware.iface;

import java.rmi.Remote;
import java.rmi.RemoteException;

import no.hvl.dat110.ds.middleware.Message;

public interface SequencerInterface extends Remote {
	
	public void onMessageReceived(Message message) throws RemoteException;

}
