package no.hvl.dat110.util;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import no.hvl.dat110.middleware.Config;
import no.hvl.dat110.process.iface.ProcessInterface;


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
	
	public static List<ProcessConfig> getProcessReplicas() {
		List<ProcessConfig> replicas = new ArrayList<>();
		// assume we have 3 replicas
		replicas.add(new ProcessConfig("process1", Config.PORT1));
		replicas.add(new ProcessConfig("process2", Config.PORT2));
		replicas.add(new ProcessConfig("process3", Config.PORT3));
		
		return replicas;
	}
	
	public static void printClock(ProcessInterface process, String procname) throws RemoteException {
		System.out.println("");
		Vector<Integer> vclock = process.getVectorclock();
		System.out.println("Vector clock at "+procname+" : (id="+process.getProcessID()+") := "+vclock);
		System.out.println("===============================");

	}

}
