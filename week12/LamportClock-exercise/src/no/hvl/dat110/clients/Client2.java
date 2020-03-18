package no.hvl.dat110.clients;

import java.rmi.RemoteException;

import no.hvl.dat110.process.Config;
import no.hvl.dat110.process.iface.ProcessInterface;
import no.hvl.dat110.util.Util;

public class Client2 extends Thread {
	
	public void run() {
		try { 
			
			// Get the process2 stub
			ProcessInterface p2 = Util.getProcessStub("process2", Config.PORT2);
			
			System.out.println("process2-"+p2.getProcessID()+": Initial Balance "+p2.getBalance());
			
			p2.doSomething();			// let's say an event happened and clock is incremented
			p2.requestInterest(0.01);
			
			p2.requestWithdrawal(200);
			p2.requestDeposit(100);

			
		 }catch (RemoteException e) { 
			 e.printStackTrace(); 
		 }
	}

}
