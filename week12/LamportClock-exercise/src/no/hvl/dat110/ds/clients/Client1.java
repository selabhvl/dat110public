package no.hvl.dat110.ds.clients;

import java.rmi.RemoteException;

import no.hvl.dat110.ds.middleware.Config;
import no.hvl.dat110.ds.middleware.iface.ProcessInterface;
import no.hvl.dat110.ds.util.Util;

public class Client1 extends Thread {
	
	public void run() {
		
		try { 

			// Get the process1 stub		
			ProcessInterface p1 = Util.getProcessStub("process1", Config.PORT1);
			
			System.out.println("process1-"+p1.getProcessID()+": Initial Balance "+p1.getBalance());
			
			p1.doLocalEvent();			// let's say an event happened and clock is incremented
			
			p1.requestDeposit(100);
			
			p1.requestWithdrawal(200);
			
			Thread.sleep(3000);
			
			p1.applyOperation();
			
		 }catch (RemoteException | InterruptedException  e) { 
			 e.printStackTrace(); 
		 }
	}

}
