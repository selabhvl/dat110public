package no.hvl.dat110.ds.middleware;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import no.hvl.dat110.ds.middleware.iface.ProcessInterface;

/**
 * 
 * @author tdoy
 * 
 * Mutual Exclusion using Token Ring Algorithm.
 * Basic implementation with no fault-tolerance
 *
 */
public class TokenManager extends UnicastRemoteObject implements ProcessInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int nextid = 0;										// unique token id 
	public static final String TOKENMANAGER = "tokmanager";		// name of the token manager
	
	public TokenManager() throws RemoteException {

	}
	
	// TODO: all processes will make requests to the manager at the beginning - so synchronize
	@Override
	public synchronized void requestToken(ProcessInterface requester) throws RemoteException {
		// TODO
		// check that nextid == 0.
			// if yes, increment nextid and create a Token object with nextid as parameter
				// send the token object to the requester by using the calling the onTokenReceived remote method.
			// if no, send null to the requester
	}

	@Override
	public void requestInterest(double interest) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void requestDeposit(double amount) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void requestWithdrawal(double amount) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getBalance() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Message> getQueue() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getProcessID() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void applyOperation() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTokenReceived(Token token) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void forwardToken() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSuccessor(ProcessInterface successor) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
