package no.hvl.dat110.ds.middleware;


/**
 * @author tdoy
 * Based on Section 6.2: Distributed Systems - van Steen and Tanenbaum (2017)
 * For demo/teaching purpose in dat110 class
 */

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import no.hvl.dat110.ds.middleware.VectorProcess.QueueTask;
import no.hvl.dat110.ds.middleware.iface.OperationType;
import no.hvl.dat110.ds.middleware.iface.ProcessInterface;
import no.hvl.dat110.ds.util.ProcessConfig;
import no.hvl.dat110.ds.util.Util;

public class VectorProcess extends UnicastRemoteObject implements ProcessInterface {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private VectorClock vectorclock;
	private int processID;
	private String processName;
	private List<ProcessConfig> replicas;
	private Message msg;
	private boolean newevent = false;
	private boolean started = false;
	private Message receivedMessage;
	private Timer timer;
//	private ExecutorService backgroundExec = Executors.newCachedThreadPool();
	
	private List<Message> queue;			// Warning this queue is not thread safe. It is your job to make it thread safe

	protected VectorProcess(String procName, int procid) throws RemoteException {
		super();
		processID = procid;
		processName = procName;
		replicas = Util.getProcessReplicas();
		int procIndex = indexOf(procName);								// return the position of this process in the process list
		vectorclock = new VectorClock(replicas.size(), procIndex);
		queue = new ArrayList<>();
	}
	
	private int indexOf(String procName) {
		
		for(int i=0; i<replicas.size(); i++) {
			ProcessConfig pc = replicas.get(i);
			if(pc.getProcessName().equals(procName))
				return i;
		}
		
		return -1;
	}
	
	@Override
	public void buildMessage(OperationType optype) throws RemoteException {
		msg = new Message(vectorclock, processName);
		msg.setOptype(optype);
	}

	// used to simulate local event within this process
	@Override
	public void localEvent() {		
		vectorclock.updateClockRule1();			// increment local clock entry
	}
	
	// example of how to send a message to remote processes
	@Override
	public void sendMessage(String procName, int port) {
		
		vectorclock.updateClockRule1();			// increment local clock entry
		try {
			ProcessInterface p = Util.getProcessStub(procName, port);	
			p.onMessageReceived(msg);
			
		} catch (RemoteException e) {

			e.printStackTrace();
		}
	}
	
	@Override
	public void multicastMessage() throws RemoteException {
		
		// implement 
		
		// increment local clock entry
		
		// make a new instance of the vector clock
		
		// create a new instance of message using the constructor that takes the vectorclock and processname as parameters
		// set the OperationType of the message to the original message
		
		// Multicast message: warning! - only multicast to other processes

	}
	
	@Override
	public void multicastMessage(long delay) throws RemoteException {

		// implement - variant of multicastMessage() but uses a delay between sending (e.g. Thread.sleep())
		
		// increment local clock entry
		
		// make a new instance of the vector clock
		
		// create a new instance of message using the constructor that takes the vectorclock and processname as parameters
		// set the OperationType of the message to the original message
		
		// Multicast message: warning! - only multicast to other processes
	}
	
	@Override
	public void onMessageReceived(Message message) throws RemoteException {
		
		// implement
		
		/** check that messages preceding this message have been delivered. if true, deliver message **/
		// - check by calling the deliverMessage() method from the VectorClock class
		
		/** otherwise, queue the message **/
		
		// check the queue periodically to know when to deliver the message by calling the checkQueue() method
		
	}
	
	private void deliverMessage(Message message) throws RemoteException {

		receivedMessage = message;
		vectorclock.updateClockRule2(message.getVectorClock());							// apply clock update rules: get the max for each local entry and increment the local clock	
		
		// printing to the console
		System.out.println(processName+" delivered "+message.getOptype().name()+" message to application");
		vectorclock.printClock();
	}
	
	/**
	 * Start a background thread to check the queue every 1sec
	 */
	private void checkQueue() {
		
		timer = new Timer();
		
		timer.scheduleAtFixedRate(new QueueTask(), 1000, 1000);
	}
	
	class QueueTask extends TimerTask {
		
		@Override
		public void run() {
			if(!started)
				timer.cancel();
			else {
				processMessage();
				if(queue.isEmpty()) 
					started = false;
			}
		}
		
	}
	
	private void processMessage() {
		System.out.println(this.processName+": Size of queue: "+queue.size());
		List<Message> dup = new ArrayList<>(queue);
		dup.forEach(m -> {
			if(vectorclock.deliverMessage(m.getVectorClock())) {
				try {
					deliverMessage(m);
					queue.remove(index(m));
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	private int index(Message m) {
		for(int i=0; i<queue.size(); i++) {
			if(queue.get(i).getProcessID() == m.getProcessID())
				return i;
		}
		return -1;
	}
	
	@Override
	public int getProcessID() throws RemoteException {
		return processID;
	}
	
	@Override
	public Vector<Integer> getVectorclock() throws RemoteException {
		return vectorclock.getVectorclock();
	}
	
	@Override
	public boolean isNewevent() throws RemoteException {
		return newevent;
	}
	
	@Override
	public void setNewevent(boolean newevent) throws RemoteException {
		this.newevent = newevent;
	}
	
	@Override
	public Message getReceivedMessage() throws RemoteException {
		return receivedMessage;
	}

	public String getProcessName() throws RemoteException {
		return processName;
	}	
	
}
