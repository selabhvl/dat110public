package no.hvl.dat110.ds.util;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.hvl.dat110.ds.middleware.Config;
import no.hvl.dat110.ds.middleware.Message;
import no.hvl.dat110.ds.middleware.iface.OperationType;
import no.hvl.dat110.ds.middleware.iface.ProcessInterface;


public class Util {
	
	
	public static Object getProcessStub(String stubID, int port) {
		
		Object process = null;
		
		Registry registry;
		try {
			registry = LocateRegistry.getRegistry(port);
			
			process = registry.lookup(stubID);
		} catch (RemoteException | NotBoundException e) {
//			e.printStackTrace();
		}
				
		return process;
		
	}
	
	public static Map<String, Integer> getProcessReplicas() {
		Map<String, Integer> replicas = new HashMap<>();
		// assume we have 3 replicas + a sequencer
		replicas.put("process1", Config.PORT1);
		replicas.put("process2", Config.PORT2);
		replicas.put("process3", Config.PORT3);
		replicas.put("sequencer", Config.PORT4);
		
		return replicas;
	}
	
	public static Map<String, Integer> getSequencerManagers(){
		
		Map<String, Integer> seqmgrs = new HashMap<>();
		// assume we have 3 sequencer managers
		seqmgrs.put("sequencer-mgr1", Config.PORT5);
		seqmgrs.put("sequencer-mgr2", Config.PORT6);
		seqmgrs.put("sequencer-mgr3", Config.PORT7);
		
		return seqmgrs;
	}

	public static void printClock(ProcessInterface process) throws RemoteException {
		System.out.println("");
		System.out.println("Queue info at process: "+process.getProcessID());
		System.out.println("===============================");
		List<Message> messages = process.getQueue();
		for(int i=0; i<messages.size(); i++) {
			Message message = messages.get(i);
			int clock = message.getClock();
			int procId = message.getProcessID();
			OperationType opt = message.getOptype();
			
			System.out.println(opt.toString()+":"+clock+":"+procId);
		}
		
		System.out.println("===============================");
		System.out.println("Balance: "+process.getBalance());
		System.out.println("===============================");
	}

}
