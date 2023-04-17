package no.hvl.dat110.ds.middleware;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Timer;
import java.util.TimerTask;

import no.hvl.dat110.ds.middleware.SequencerManager.WaitTask;
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
	private Timer timer;
	private boolean running;
	
	public SequencerManager(int seqMgrId, int ttl) throws RemoteException {

		id = seqMgrId;
		oldId = seqMgrId;
		this.ttl = ttl;
		
		if(id == 1) {
			isMain = true;
			System.out.println("Creating the first Sequencer on port: "+Config.PORT4);
			new SequencerContainer("sequencer", Config.PORT4);
		}
		
		Thread shutdownhook = new Thread(() -> System.out.println("sequencer-mgr"+seqMgrId+" is now shutting down...")); 
		Runtime.getRuntime().addShutdownHook(shutdownhook);
		
		setSuccessor();
		
		checkAlive();
	}

	@Override
	public void checkAlive() {
		running = true;
		timer = new Timer();
		timer.scheduleAtFixedRate(new WaitTask(), 1000, 1000);
		
	}
	
	public class WaitTask extends TimerTask {

		@Override
		public void run() {
			
			if(running) {
				doCheck();
				if(--ttl == 1) {
					running = false;
				}
			} else {
				timer.cancel();
				System.exit(1);
			}			
		}
	}

	private void doCheck() {
		
		boolean alive;
		
		if(isMain) {
			alive = true;	
			// TODO:
			//(1) if yes try to get the Sequencer stub and let the sequencer acknowledge by calling the remote acknowledge method
			//(2) if the attempt to get the sequencer fails, then alive = false. Start a new Sequencer using the SequencerContainer
			// to achieve the above, use try-catch. (1) should be in the try clause and (2) in the catch clause
			
		} else {
			try {
				alive = successor.acknowledge();
				// update id based on successor's current id
				id = successor.getId() + 1; 
			} catch(Exception e) {
				System.out.println("Cannot contact successor mgr");
				alive = false;
				--id;
			}
			
			if(!alive && id == 1) {
				isMain = true;					// becomes the main Manager
				System.out.println("sequencer-mgr"+oldId+"  is now the main manager");
			}
		}
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
