package no.hvl.dat110.ds.clients;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import no.hvl.dat110.ds.middleware.Config;
import no.hvl.dat110.ds.middleware.iface.ProcessInterface;

public class Client2 extends Thread {
	
	public Client2() {
		
	}
	
	public void run() {
		try { 
			// Get the registry  - running on local machine's IP
			Registry registry = LocateRegistry.getRegistry(Config.PORT2);
			// Look up the registry for the remote object
			ProcessInterface p2 = (ProcessInterface) registry.lookup("process2");
			
			System.out.println("process2-"+p2.getProcessID()+": Initial Balance "+p2.getBalance());
			
			p2.requestToken(p2);

			p2.requestInterest(0.01);
			
			p2.requestWithdrawal(200);
			
			p2.requestDeposit(100);
			
			
		 }catch (RemoteException | NotBoundException e) { 
			 e.printStackTrace(); 
		 }
	}

}
