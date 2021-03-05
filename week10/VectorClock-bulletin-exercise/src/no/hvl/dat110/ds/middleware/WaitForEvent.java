package no.hvl.dat110.ds.middleware;

import java.rmi.RemoteException;

import no.hvl.dat110.ds.middleware.iface.OperationType;
import no.hvl.dat110.ds.middleware.iface.ProcessInterface;

public class WaitForEvent {
	
	private ProcessInterface p;
	private String pname;
	private OperationType op;
	
	private boolean happened = false;

	public WaitForEvent(ProcessInterface p, String processName) throws RemoteException {
		this.p = p;
		this.pname = processName;
		this.op = null;
		happened = waitforEvent();
	}
	
	public WaitForEvent(ProcessInterface p, String processName, OperationType op) throws RemoteException {
		this.p = p;
		this.pname = processName;
		this.op = op;
		happened = waitforEvent();
	}
	
	private boolean waitforEvent() throws RemoteException {
		boolean stop = p.isNewevent();
		while(!stop) {
			
			if(!p.isNewevent())
				continue;
			Message rm = p.getReceivedMessage();
			
			if(op == null) {
				if(rm.getProcessname().equals(pname)) { 				// if there is a RECEIVE Event from process pname															// SEND event - send a message to process1
					p.setNewevent(false);
					stop = true;
				}
			}else {
				if(rm.getOptype().toString().equals(op.toString()) && 
						rm.getProcessname().equals(pname)) {			// check the OperationType and which process													
					p.setNewevent(false);
					stop = true;
				}
			}
			try {
				Thread.sleep(500);								// sleep for 0.5sec before trying again
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return stop;
	}

	public boolean isHappened() {
		return happened;
	}

}
