package no.hvl.dat110.ds.clients;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import no.hvl.dat110.ds.middleware.Config;
import no.hvl.dat110.ds.middleware.iface.ProcessInterface;

public class Client3 extends Thread {
	
	public void run() {
		try { 
			// Get the registry  - running on local machine's IP
			Registry registry = LocateRegistry.getRegistry(Config.PORT3);
			// Look up the registry for the remote object
			ProcessInterface p3 = (ProcessInterface) registry.lookup("process3");				

			System.out.println("process3-"+p3.getProcessID()+": Initial Balance "+p3.getBalance());
			
			p3.requestWithdrawal(200);
			
			p3.requestInterest(0.01);
			
			p3.requestDeposit(100);
			
			
		 }catch (RemoteException | NotBoundException e) { 
			 e.printStackTrace(); 
		 }
	}
}
