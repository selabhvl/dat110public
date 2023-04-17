package no.hvl.dat110.ds.middleware;

import java.rmi.RemoteException;

import no.hvl.dat110.ds.middleware.iface.OperationType;
import no.hvl.dat110.ds.middleware.iface.ProcessInterface;

public class WaitTask {
	
	private ProcessInterface p;
	private String pname;
	private OperationType op;

	public WaitTask(ProcessInterface p, String processName) throws RemoteException, InterruptedException {
		this.p = p;
		this.pname = processName;
		this.op = null;
		waitforEvent();
	}
	
	public WaitTask(ProcessInterface p, String processName, OperationType op) throws RemoteException, InterruptedException {
		this.p = p;
		this.pname = processName;
		this.op = op;
		waitforEvent();
	}
	
	private void waitforEvent() throws RemoteException, InterruptedException {
		boolean arrived = false;
		while(!arrived) {

			Message rm = p.getReceivedMessage();
			
			if(rm != null) {
				if(op == null) {
					if(rm.getProcessname().equals(pname)) { 				// if there is a RECEIVE Event from process pname															// SEND event - send a message to process1
						arrived = true;
					}
				}else {
					if(rm.getOptype().name().equals(op.name()) && rm.getProcessname().equals(pname)) {			// check the OperationType and which process													
						arrived = true;
					}
				}
			}

			Thread.sleep(500);								// sleep for 0.5sec before trying again
		}
	}

}
