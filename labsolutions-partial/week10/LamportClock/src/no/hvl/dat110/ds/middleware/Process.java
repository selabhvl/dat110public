package no.hvl.dat110.ds.middleware;

import java.rmi.AccessException;

/**
 * @author tdoy
 * Based on Section 6.2: Distributed Systems - van Steen and Tanenbaum (2017)
 * For demo/teaching purpose at dat110 class
 */

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import no.hvl.dat110.ds.middleware.iface.OperationType;
import no.hvl.dat110.ds.middleware.iface.ProcessInterface;
import no.hvl.dat110.ds.util.LamportClock;
import no.hvl.dat110.ds.util.Util;

public class Process extends UnicastRemoteObject implements ProcessInterface {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Message> queue;					// queue for this process
	private int processID;
	private LamportClock clock;						// lamport clock
	private double balance = 1000;					// default balance
	private Map<String, Integer> replicas;			// list of other processes including self known to this process
	

	protected Process(int id) throws RemoteException {
		super();
		processID = id;
		clock = new LamportClock();
		queue = Collections.synchronizedList(new ArrayList<Message>());	
		replicas = Util.getProcessReplicas();
	}
	
	private void updateDeposit(double amount) throws RemoteException {
		clock.increment();
		balance += amount;
	}
	
	private void updateInterest(double interest) throws RemoteException {
		clock.increment();
		double intvalue = balance*interest;
		balance += intvalue;
	}
	
	private void updateWithdrawal(double amount) throws RemoteException {
		clock.increment();
		balance -= amount;
	}
	
	// sort queue first on the clock and then the processID to break any clock tie
	private void sortQueue() {
		synchronized(queue) {
			queue.sort(Comparator.comparing(Message::getClock).thenComparing(Message::getProcessID));
		}
	}
	
	// client initiated method
	@Override
	public void requestInterest(double interest) throws RemoteException {
		// 		
		clock.increment();
		Message message = new Message(clock.getClock(), processID);	// set the timestamp of message & process ID
		message.setOptype(OperationType.INTEREST);					// set the type of message - deposit or interest					
		message.setInterest(interest); 								// add interest to calculate as part of message
		
		queue.add(message); 							// add to queue
		//sortQueue();									// sort the queue according to timestamp and processID

		// multicast clock + message to other processes
		multicastMessage(message);

	}
	
	// client initiated method
	@Override
	public void requestDeposit(double amount) throws RemoteException {
		// 		
		clock.increment();
		Message message = new Message(clock.getClock(), processID);	// set the timestamp of message & process ID
		message.setOptype(OperationType.DEPOSIT);					// set the type of message - deposit or interest
		message.setDepositamount(amount); 							// add amount to deposit as part of message
		
		queue.add(message); 							// add to queue
		//sortQueue();									// sort the queue according to timestamp and processID
		
		// multicast clock + message to other processes
		multicastMessage(message);

	}
	
	// client initiated method
	@Override
	public void requestWithdrawal(double amount) throws RemoteException {
		// 		
		clock.increment();
		Message message = new Message(clock.getClock(), processID);	// set the timestamp of message & process ID
		message.setOptype(OperationType.WITHDRAWAL);				// set the type of message - deposit or interest
		message.setWithdrawamount(amount);							// add amount to withdraw as part of message
				
		queue.add(message); 										// upon receipt of a message from client add to queue
		//sortQueue();												// sort the queue according to timestamp and processID

		// multicast clock + message to other processes
		multicastMessage(message);

	}
	
	@Override
	public synchronized void onAcknowledgementReceived(Message message) throws RemoteException {
		
		int index = exist(message);
		if(index != -1) {
			if(message.isAcknowledged()) {
				queue.get(index).incrementAck();
			}
			if(queue.get(index).getNumAck() == replicas.size()-1)
				queue.get(index).setAcknowledged(true);	
		}				
	}
	
	public void applyOperation() throws RemoteException {
		// optimization later: if the head of queue is acknowledged, pop it out and process it.
		sortQueue();
		for(int i=0; i<queue.size(); i++) {

			Message message = queue.get(i);
			
			if(message.isAcknowledged()) {			

				OperationType optype = message.getOptype();

				switch(optype) {
				
					case DEPOSIT: 
						updateDeposit(message.getDepositamount());
						break;
						
					case INTEREST: 
						updateInterest(message.getInterest());
						break;
						
					case WITHDRAWAL: 
						updateWithdrawal(message.getWithdrawamount());
						break;
						
					default: break;
				}

			} 
		}
		
	}
	
	@Override
	public void onMessageReceived(Message message) throws RemoteException {
		
		if(exist(message) == -1)
			queue.add(message);							// upon receipt of a message, add clock to queue	
		
		sortQueue();									// sort the queue according to timestamp and processID
		
		int sndclock = message.getClock();				// check the clock of the sending process
		int ownclock = clock.getClock();
		
		ownclock = Math.max(ownclock, sndclock);		// get the clock that is higher and update the local clock
		clock.adjustClock(ownclock);
		clock.increment();								// increment the local clock
		
		// multicast acknowledgement to other processes including self
		message.setAcknowledged(true);
		multicastAcknowledgement(message);
	}
	
	private void multicastAcknowledgement(Message message) throws AccessException, RemoteException {
		
		replicas.keySet().forEach(k -> {
			int port = replicas.get(k);
			try {
				ProcessInterface p = Util.getProcessStub(k, port);
				
				p.onAcknowledgementReceived(message);				
				
			} catch (RemoteException e) {

				e.printStackTrace();
			}
		});
	}
	
	// multicast message to other processes including self
	private void multicastMessage(Message message) throws AccessException, RemoteException {
		
		replicas.keySet().forEach(k -> {
			int port = replicas.get(k);
			try {
				ProcessInterface p = Util.getProcessStub(k, port);
				
				p.onMessageReceived(message);				
				Thread.sleep(100);
			} catch (InterruptedException | RemoteException e) {

				e.printStackTrace();
			}
		});

	}
	
	private int exist(Message message) {
		
		for(int i=0; i<queue.size(); i++) {
			
			Message c = queue.get(i);
			if(message.getOptype()==c.getOptype() && message.getClock()==c.getClock() && message.getProcessID()
					== c.getProcessID())
				return i;
		}
		
		return -1;
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
	public void doLocalEvent() throws RemoteException {
		// to simulate new events
		try {
			Thread.sleep(500);
			clock.increment();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	
}
