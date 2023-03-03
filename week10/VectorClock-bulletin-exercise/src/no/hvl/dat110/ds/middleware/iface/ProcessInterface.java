package no.hvl.dat110.ds.middleware.iface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import no.hvl.dat110.ds.middleware.Message;

public interface ProcessInterface extends Remote {
	
	public void buildMessage(OperationType optype) throws RemoteException;
	
	public void localEvent() throws RemoteException;
	
	public int getProcessID() throws RemoteException;
	
	public String getProcessName() throws RemoteException;
	
	public void multicastMessage() throws RemoteException;						// send to multiple processes
	
	public void multicastMessage(long delay) throws RemoteException;			// send to multiple processes and delay between sending
	
	public void sendMessage(String procName, int port) throws RemoteException;	// send to only one process
	
	public void onMessageReceived(Message message) throws RemoteException;
	
	public Vector<Integer> getVectorclock() throws RemoteException;
	
	public boolean isNewevent() throws RemoteException;
	
	public void setNewevent(boolean newevent) throws RemoteException;
	
	public Message getReceivedMessage() throws RemoteException;

}
