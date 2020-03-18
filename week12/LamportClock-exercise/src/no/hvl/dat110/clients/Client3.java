package no.hvl.dat110.clients;

import java.rmi.RemoteException;

import no.hvl.dat110.process.Config;
import no.hvl.dat110.process.iface.ProcessInterface;
import no.hvl.dat110.util.Util;

public class Client3 extends Thread {
	
	public void run() {
		try { 
			
			// Get the process3 stub
			ProcessInterface p3 = Util.getProcessStub("process3", Config.PORT3);
			
			System.out.println("process3-"+p3.getProcessID()+": Initial Balance "+p3.getBalance());
			
			p3.requestWithdrawal(200);
			
			p3.requestInterest(0.01);
			
			p3.doSomething();					// let's say an event happened and clock is incremented
			
			p3.requestDeposit(100);

			
		 }catch (RemoteException e) { 
			 e.printStackTrace(); 
		 }
	}
}
