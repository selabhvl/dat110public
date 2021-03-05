package no.hvl.dat110.ds.clients;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import no.hvl.dat110.ds.middleware.Config;
import no.hvl.dat110.ds.middleware.iface.ProcessInterface;

public class Client1 extends Thread {
	
	public void run() {
		
		try { 
			
			// Get the registry  - running on local machine's IP
			Registry registry = LocateRegistry.getRegistry(Config.PORT1);
			
			// Look up the registry for the remote object
			ProcessInterface p1 = (ProcessInterface) registry.lookup("process1");				
			
			System.out.println("process1-"+p1.getProcessID()+": Initial Balance "+p1.getBalance());
			
			Thread.sleep(15000);
			
			p1.requestDeposit(100);
			
			p1.requestInterest(0.01);
			
			p1.requestWithdrawal(200);
			
			
		 }catch (RemoteException | NotBoundException | InterruptedException e) { 
			 e.printStackTrace(); 
		 }
	}

}
