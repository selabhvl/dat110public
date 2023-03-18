package no.hvl.dat110.ds.middleware;


/**
 * @author tdoy
 * For demo/teaching purpose at dat110 class
 */

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import no.hvl.dat110.ds.middleware.iface.OperationType;
import no.hvl.dat110.ds.middleware.iface.ProcessInterface;
import no.hvl.dat110.ds.util.Util;

public class Process extends UnicastRemoteObject implements ProcessInterface {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Message> queue;					// queue for this process
	private int processID;							// id of this process
	private double balance = 1000;					// default balance (each replica has the same). Our goal is to keep the balance consistent
	private Map<String, Integer> replicas;			// list of other processes including self known to this process 
	
	protected Process(int id) throws RemoteException {
		super();
		processID = id;
		queue = new ArrayList<Message>();	
		replicas = Util.getProcessReplicas();
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
	
	private void sortQueue() {
		// TODO
		// sort the queue by the clock (unique time stamped given by the sequencer)
	}
	
	// client initiated method
	@Override
	public void requestInterest(double interest) throws RemoteException {
		// TODO 		
		// make a new message instance and set the following:
		// set the type of message - interest
		// set the process ID
		// set the interest

		// send the message to the sequencer by calling the sendMessageToSequencer
		

	}
	
	// client initiated method
	@Override
	public void requestDeposit(double amount) throws RemoteException {
		// TODO 		
		// make a new message instance and set the following
		// set the type of message - deposit
		// set the process ID
		// set the deposit amount

		// send the message to the sequencer

	}
	
	// client initiated method
	@Override
	public void requestWithdrawal(double amount) throws RemoteException {
		// TODO 		
		// make a new message instance and set the following
		// set the type of message - withdrawal
		// set the process ID
		// set the withdrawal amount

		// send the message to the sequencer

	}
	
	private void sendMessageToSequencer(Message message) throws RemoteException {
		// TODO
		// get the port for the sequencer
		// get the sequencer stub: use Util class
		// using the sequencer stub, call the remote onReceivedMessage method to send the message to the sequencer
		// use a try-catch on the above (onReceivedMessage) to detect/catch NullPointerException
		// in the catch clause, print the message "can't contact the sequencer" to the console
	}
	
	public void applyOperation() throws RemoteException {
		// TODO
		
		// iterate over the queue
		
		// for each message in the queue, check the operation type
		
		// call the appropriate update method for the operation type and pass the value to be updated
		
		Util.printClock(this);
		
	}
	
	@Override
	public void onMessageReceived(Message message) throws RemoteException {
		// TODO
		// upon receipt of a message, add message to the queue	
		// check the ordering limit, if equal to queue size, start to process the following:
			// sort the queue according to time stamped by the sequencer
			// apply operation and commit
			// clear the queue

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
	public boolean acknowledge() throws RemoteException {
		
		return false;
	}
	
}
