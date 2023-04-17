package no.hvl.dat110.ds.clients;

import java.rmi.RemoteException;

import no.hvl.dat110.ds.middleware.Config;
import no.hvl.dat110.ds.middleware.WaitTask;
import no.hvl.dat110.ds.middleware.iface.OperationType;
import no.hvl.dat110.ds.middleware.iface.ProcessInterface;
import no.hvl.dat110.ds.util.Util;

public class Client2 extends Thread {
	
	private boolean wait = false;
	private String procName;
	
	public Client2() {
		// no simulation of waiting
	}
	
	public Client2(boolean wait, String procName) {
		this.wait = wait;
		this.procName = procName;
	}
	
	public void run() {
		try { 

			// Look up the registry for the remote object
			ProcessInterface p2 = Util.getProcessStub("process2", Config.PORT2);

			// p2 is waiting until it receives a WRITE event from procName (e.g. process1): 
			if(wait)
				new WaitTask(p2, procName, OperationType.WRITE);
			
			p2.buildMessage(OperationType.READ);						// READ event message optype happened after a write event from P1
			p2.multicastMessage();										// SEND event - send a message to other processes
			
		 }catch (RemoteException | InterruptedException e) { 
			 e.printStackTrace(); 
		 }
	}

}
