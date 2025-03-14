package no.hvl.dat110.ds.clients;

import java.rmi.RemoteException;

import no.hvl.dat110.ds.middleware.Config;
import no.hvl.dat110.ds.middleware.iface.ProcessInterface;
import no.hvl.dat110.ds.util.Util;

public class Client3 extends Thread {
	
	public void run() {
		try { 
			// Look up the registry for the remote object
			ProcessInterface p3 = Util.getProcessStub("process3", Config.PORT3);				
			
			p3.localEvent();			// p3 can perform local event

			
		 }catch (RemoteException e) { 
			 e.printStackTrace(); 
		 }
	}
}
