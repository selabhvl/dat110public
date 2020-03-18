package no.hvl.dat110.clients;

import java.rmi.RemoteException;

import no.hvl.dat110.middleware.Config;
import no.hvl.dat110.process.iface.OperationType;
import no.hvl.dat110.process.iface.ProcessInterface;
import no.hvl.dat110.util.Util;

public class Client1 extends Thread {
	
	private long delay = 0;
	
	public Client1() {
		// no simulation of delay
	}
	
	public Client1(long delay) {
		this.delay = delay;
	}
	
	public void run() {
		try { 

			// Look up the registry for the remote object
			ProcessInterface p1 = Util.getProcessStub("process1", Config.PORT1);	

			p1.buildMessage(OperationType.WRITE);						// WRITE event - dummy not used for any purpose
			if(delay > 0)
				p1.multicastMessage(1000);								// SEND event - send a message to other processes using delay to simulate latency
			else
				p1.multicastMessage();									// SEND event - immediately no latency is assumed

			
		 }catch (RemoteException e) { 
			 e.printStackTrace(); 
		 }
	}

}
