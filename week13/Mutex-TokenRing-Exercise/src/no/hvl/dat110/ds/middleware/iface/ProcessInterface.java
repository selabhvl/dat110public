package no.hvl.dat110.ds.middleware.iface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import no.hvl.dat110.ds.middleware.Message;
import no.hvl.dat110.ds.middleware.Token;

public interface ProcessInterface extends Remote {
	
	public void requestInterest(double interest) throws RemoteException;
	
	public void requestDeposit(double amount) throws RemoteException;
	
	public void requestWithdrawal(double amount) throws RemoteException;
	
	public double getBalance() throws RemoteException;
	
	public List<Message> getQueue() throws RemoteException;
	
	public int getProcessID() throws RemoteException;
	
	public void applyOperation() throws RemoteException;
	
	public void requestToken(ProcessInterface requester) throws RemoteException;
	
	public void forwardToken() throws RemoteException;
	
	public void onTokenReceived(Token token) throws RemoteException;
	
	public void setSuccessor(ProcessInterface successor) throws RemoteException;


}
