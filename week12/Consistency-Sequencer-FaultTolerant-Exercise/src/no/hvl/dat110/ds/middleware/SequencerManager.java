package no.hvl.dat110.ds.middleware;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import no.hvl.dat110.ds.middleware.iface.ProcessInterface;
import no.hvl.dat110.ds.middleware.iface.SequencerManagerInterface;
import no.hvl.dat110.ds.util.Util;

/**
 * 
 * @author tdoy
 * 
 * Fault-Tolerance: Using a single sequencer can lead to reliability issue.
 * We introduce N-sequencer managers (process group) that are positioned to monitor and re-create the sequencer when it fails
 * Each sequencer manager monitors its successor manager to detect if it's alive
 * This is a hierarchical process group architecture
 *
 */
public class SequencerManager extends UnicastRemoteObject implements SequencerManagerInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int oldId;
	private SequencerManagerInterface successor;		// the successor of this manager (succid = this.id - 1)
	private boolean isMain;								// a variable to indicate that this is now the main manager that owns the current sequencer
	private int ttl = 100;								// how long should this manager live. Used to simulate fault-tolerance of process group
	
	public SequencerManager(int seqMgrId, int ttl) throws RemoteException {

		id = seqMgrId;
		oldId = seqMgrId;
		this.ttl = ttl;
		
		if(id == 1)
			isMain = true;
		
		Thread shutdownhook = new Thread(() -> System.out.println("sequencer-mgr"+seqMgrId+" is now shutting down...")); 
		Runtime.getRuntime().addShutdownHook(shutdownhook);
		
		setSuccessor();
		
		checkAlive();
	}

	@Override
	public void checkAlive() {

		Runnable task = () -> {
			boolean run = true;
			while(run) {
				try {
					doCheck();
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(--ttl == 1) {
					run = false;
					System.exit(1);
				}
			}
		};
		
		Thread thread = new Thread(task);
		thread.start();
		
	}

	private void doCheck() {
		
//		System.out.println("TTL = "+ttl);
		
		boolean alive;
		
		// TODO: 
		// check if this manager is the main (isMain), 
			//if yes try to get the Sequencer stub and let the sequencer acknowledge by calling the remote acknowledge method
			// if the attempt to get the sequencer fails, then the alive = false. Start a new Sequencer using the SequencerContainer
		// if this manager is not the main (isMain = false), 
			// Try to contact the successor-mgr by calling the remote acknowledge method on successor
				// if the contact to successor succeeded, then alive is true and update the id by adding 1 to the successor's id
			// if the contact failed, alive is false and decrement id by 1
			// if alive is false and the id is 1, then this manager is the Main (isMain is true)

	}
	
	@Override
	public boolean acknowledge() throws RemoteException {
		
		return true;
	}

	/**
	 * @param successor the successor to set
	 */
	private void setSuccessor() throws RemoteException {
		if(id > 1) {
			int succid = 0;
			succid = id-1;
			System.out.println("Trying to set the successor: SequencerMgr"+succid);
			int port = Util.getSequencerManagers().get("sequencer-mgr"+succid);
			SequencerManagerInterface seqmgr = (SequencerManagerInterface) Util.getProcessStub("sequencer-mgr"+succid, port);
			
			if(seqmgr != null)
				this.successor = seqmgr;
			else {
				System.out.println("Make sure you start the SequencerMgr"+succid+" first!");
				System.exit(1);
			}
		}
	}

	/**
	 * @return the id
	 */
	public int getId() throws RemoteException {
		return id;
	}

}
