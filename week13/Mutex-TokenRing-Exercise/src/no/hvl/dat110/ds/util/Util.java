package no.hvl.dat110.ds.util;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import no.hvl.dat110.ds.middleware.Message;
import no.hvl.dat110.ds.middleware.iface.OperationType;
import no.hvl.dat110.ds.middleware.iface.ProcessInterface;


public class Util {
	
	
	public static ProcessInterface getProcessStub(String stubID, int port) {
		
		ProcessInterface process = null;
		
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry(port);
			
			process = (ProcessInterface) registry.lookup(stubID);
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
				
		return process;
		
	}
	

	public static void printQueue(ProcessInterface process) throws RemoteException {
		System.out.println("");
		System.out.println("Queue info at process: "+process.getProcessID());
		System.out.println("===============================");
		List<Message> messages = process.getQueue();
		for(int i=0; i<messages.size(); i++) {
			Message message = messages.get(i);
			int procId = message.getProcessID();
			OperationType opt = message.getOptype();
			
			System.out.println(opt.toString()+":"+procId);
		}
		
		System.out.println("===============================");
		System.out.println("Balance: "+process.getBalance());
		System.out.println("===============================");
	}

}
