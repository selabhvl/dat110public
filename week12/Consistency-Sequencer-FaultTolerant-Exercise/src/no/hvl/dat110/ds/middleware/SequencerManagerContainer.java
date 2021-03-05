package no.hvl.dat110.ds.middleware;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import no.hvl.dat110.ds.middleware.iface.SequencerManagerInterface;


public class SequencerManagerContainer {
	
	public SequencerManagerContainer(String procName, int port, int procid, int ttl) {
		try {
			// create registry and start it if not started 
			Registry registry = LocateRegistry.createRegistry(port);
			
			// Make a new instance of the implementation class
			SequencerManagerInterface proc = new SequencerManager(procid, ttl);
			
			// bind the remote object (stub) in the registry			
			registry.bind(procName, proc);
			
			System.out.println(procName+ " process registry is running");
		}catch(Exception e) {
			System.err.println("Process Container: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args) throws RemoteException {
//		
//		new SequencerContainer("sequencer", Config.PORT4);
//		
//		// create N-fault-tolerance sequencer managers
//		new SequencerManagerContainer("sequencer-mgr1", Config.PORT5, 1, 10);		// live for 11sec
//		new SequencerManagerContainer("sequencer-mgr2", Config.PORT6, 2, 20);		// live for 21sec
//		new SequencerManagerContainer("sequencer-mgr3", Config.PORT7, 3, 30);		// live for 31sec
//		
//		// set the successors
//		SequencerManagerInterface mgr1 = (SequencerManagerInterface) Util.getProcessStub("sequencer-mgr1", Config.PORT5);
//		SequencerManagerInterface mgr2 = (SequencerManagerInterface) Util.getProcessStub("sequencer-mgr2", Config.PORT6);
//		SequencerManagerInterface mgr3 = (SequencerManagerInterface) Util.getProcessStub("sequencer-mgr3", Config.PORT7);
//		
//		mgr1.setMain(true);
//		mgr2.setSuccessor(mgr1);
//		mgr3.setSuccessor(mgr2);
//	}

}
