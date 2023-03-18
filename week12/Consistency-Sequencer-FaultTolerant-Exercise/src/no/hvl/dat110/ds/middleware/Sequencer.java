package no.hvl.dat110.ds.middleware;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import no.hvl.dat110.ds.middleware.iface.ProcessInterface;
import no.hvl.dat110.ds.util.Util;

/**
 * 
 * @author tdoy
 * 
 * Active Replication: Using a single sequencer to provide a total order for all writes propagated to replicas
 * plus using a bounded ordering deviation to initiate when updates should be performed.
 *
 */
public class Sequencer extends UnicastRemoteObject implements ProcessInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int nextid = 0;									// unique id (timestamp)
	private List<Message> queue;							// queue for storing the writes from the replicas
	public static final int ORDERINGLIMIT = 4;				// bounding for ordering. When should sequencer multicast writes?
	private Map<String, Integer> replicas;					// list of other processes including self known to this process
	public static final String SEQUENCER = "sequencer";		// name of this sequencer
	
	public Sequencer() throws RemoteException {

		queue = new ArrayList<>();
		replicas = Util.getProcessReplicas();
		replicas.remove(SEQUENCER);
		
		Thread shutdownhook = new Thread(() -> System.out.println("Sequencer is now shutting down...")); 
		Runtime.getRuntime().addShutdownHook(shutdownhook);

	}
	
	// TODO: all processes will make request to the sequencer - so synchronize
	@Override
	public void onMessageReceived(Message message) throws RemoteException {
		// TODO
		// increment nextid (time stamp)
		// set the nextid as the clock for the message: use setClock
		// add the message to the queue			
		// check if the ordering limit has been reached. If yes, multicast queue messages to all the replicas by calling the sendQueueMessagesToReplicas 
		// and reset nextid

	}
	
	private void sendQueueMessagesToReplicas() throws RemoteException {
		// TODO
		// iterate over each replica, 
			// get the port for each process
			// get the process stub: use Util
			// using the stub, call the onReceivedMessage remote method and forward all the messages in the queue to this remote process
		
		// clear the queue when done
		
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
	public boolean acknowledge() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

}
