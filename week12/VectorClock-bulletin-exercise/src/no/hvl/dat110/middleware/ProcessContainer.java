package no.hvl.dat110.middleware;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;

import no.hvl.dat110.process.iface.ProcessInterface;


public class ProcessContainer {
	
	public ProcessContainer(String procName, int procId, int port) {
		try {
			// create registry and start it 
			Registry registry = LocateRegistry.createRegistry(port);
			
			// Make a new instance of the implementation class
			ProcessInterface proc = new VectorProcess(procName, procId);
			
			// bind the remote object (stub) in the registry			
			registry.bind(procName, proc);
			
			System.out.println(procName+ " process registry is running");
		}catch(Exception e) {
			System.err.println("Process Container: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Random r = new Random();
		
		new ProcessContainer("process1", r.nextInt(10000), Config.PORT1);
		new ProcessContainer("process2", r.nextInt(10000), Config.PORT2);
		new ProcessContainer("process3", r.nextInt(10000), Config.PORT3);
	}

}
