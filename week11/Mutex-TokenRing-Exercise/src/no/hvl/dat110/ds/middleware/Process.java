package no.hvl.dat110.ds.middleware;


/**
 * @author tdoy
 * For demo/teaching purpose at dat110 class
 * Mutual Exclusion using Token Ring Algorithm.
 * Basic implementation with no fault-tolerance
 */

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import no.hvl.dat110.ds.middleware.iface.OperationType;
import no.hvl.dat110.ds.middleware.iface.ProcessInterface;
import no.hvl.dat110.ds.util.Util;

public class Process extends UnicastRemoteObject implements ProcessInterface {
	

	private static final long serialVersionUID = 1L;

	private List<Message> queue;					// queue for this process
	private int processID;							// id of this process
	private double balance = 1000;					// default balance (each replica has the same). Our goal is to avoid concurrent access 
	private Token token = null;						// token to be passed in the ring
	private ProcessInterface successor;				// each process has the knowledge of its successor
	private ExecutorService backgroundExec = Executors.newCachedThreadPool();
	
	protected Process(int id) throws RemoteException {
		super();
		processID = id;
		queue = new ArrayList<Message>();	
	}
	
	private void updateDeposit(double amount) throws RemoteException {

		balance += amount;
	}
	
	private void updateInterest(double interest) throws RemoteException {

		double intvalue = balance*interest;
		balance += intvalue;
	}
	
	private void updateWithdrawal(double amount) throws RemoteException {

		balance -= amount;
	}
	
	// client initiated method
	@Override
	public void requestInterest(double interest) throws RemoteException {
		
		// TODO 		
		
		// make a new message instance and set the following:
		
		// set the type of message - interest (get it from OperationType)
		
		// set the process ID
		
		// set the interest
		
		// add message to queue

	}
	
	// client initiated method
	@Override
	public void requestDeposit(double amount) throws RemoteException {

		// TODO 		
		
		// make a new message instance and set the following
		
		// set the type of message - deposit (get it from OperationType)
		
		// set the process ID
		
		// set the deposit amount
		
		// add message to queue

	}
	
	// client initiated method
	@Override
	public void requestWithdrawal(double amount) throws RemoteException {

		// TODO 	
		
		// make a new message instance and set the following
		
		// set the type of message - withdrawal
		
		// set the process ID
		
		// set the withdrawal amount
		
		// add message to queue

	}	

	@Override
	public void forwardToken() throws RemoteException {
		
		// TODO
		
		// create a new token object and pass the old token's Id as its parameter
		
		Token token_new = new Token(token.getTokenId());
		
		// set the old token to null
		token = null;
		
		// forward the new token to the successor by calling the onTokenReceived remote method
		successor.onTokenReceived(token_new);
	}

	/**
	 * @param successor the successor to set
	 */
	public void setSuccessor(ProcessInterface successor) throws RemoteException {
		this.successor = successor;
	}
	
	public void applyOperation() throws RemoteException {

		// TODO
		
		// iterate over the queue
		
		// for each message in the queue, check the operation type
		
		// call the appropriate update method for the operation type and pass the value to be updated

		Util.printQueue(this);
		// clear the queue
		queue.clear();
				
	}
	
	@Override
	public double getBalance() throws RemoteException {
		return balance;
	}
	
	@Override
	public int getProcessID() throws RemoteException {
		return processID;
	}
	
	@Override
	public List<Message> getQueue() throws RemoteException {
		return queue;
	}

	@Override
	public void onTokenReceived(Token token) throws RemoteException {
		
		// TODO
		
		// check whether token is null
		
		// if no, set the token object to the received token
		
		// call applyOperation method
		
		// forward the token to the successor process by calling the forwardToken method

	}

	/**
	 * Give this job to a different thread
	 */
	@Override
	public void requestToken(ProcessInterface requester) throws RemoteException {
		
		backgroundExec.execute(new Runnable() {

			@Override
			public void run() {

				ProcessInterface tokmanager = Util.getProcessStub(TokenManager.TOKENMANAGER, Config.PORT4);
				try {
					tokmanager.requestToken(requester);
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				
			}
			
		});
	
	}
	
}
