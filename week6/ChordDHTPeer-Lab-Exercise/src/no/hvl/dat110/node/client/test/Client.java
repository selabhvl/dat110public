package no.hvl.dat110.node.client.test;

import java.io.IOException;
import java.rmi.RemoteException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import no.hvl.dat110.rpc.interfaces.NodeInterface;
import no.hvl.dat110.rpcserver.NodeServer;
import no.hvl.dat110.util.FileManager;
import no.hvl.dat110.util.Util;

/**
 * dat110
 * @author tdoy
 *
 */


public class Client {

	public static void startProcesses(Map<String, Integer> processes) {
		
		// start the processes
		processes.keySet().forEach(nodename -> {
			int port = processes.get(nodename);
			NodeServer nodeserver = new NodeServer(nodename, port);
			nodeserver.start();
		});
	}
	
	public static void distributeFiles(String[] files) throws RemoteException {
		
		// use the filemanager to distribute each file to the processes		
		FileManager fm = new FileManager();													// get the filemanager
		for(int i=0; i<files.length; i++) {													// iterate over the files and distribute them to the running nodes
			String filename = files[i];
			fm.distributeReplicastoPeers(filename);											// distribute the replicas to active peers
		}
	}
	
	public static void resolveSuccPred(Map<String, Integer> processes, List<NodeInterface> nodes) throws RemoteException {
				
		// collect the remote stubs in a list
		processes.keySet().forEach(nodename -> {
			int port = processes.get(nodename);
			NodeInterface node = Util.getProcessStub(nodename, port);
			nodes.add(node);
		});
		
		// sort the processes according to their identifiers (nodeid)		
		nodes.sort(Comparator.comparing(t -> {
			try {
				return t.getNodeID();
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
			return null;
		}));
		
		// resolve successors and predecessors
		for(int i=0; i<nodes.size(); i++) {
			NodeInterface node = nodes.get(i);

			int indexpluss = i+1;
			if(indexpluss == nodes.size())
				indexpluss = 0;
			int indexminus = i-1;
			if(indexminus == -1)
				indexminus = nodes.size()-1;
			NodeInterface succ = nodes.get(indexpluss);
			NodeInterface pred = nodes.get(indexminus);
			
			node.setSuccessor(succ);
			node.setPredecessor(pred);
		}
	}
	
	public static List<String> findPeersofFile(String filename) throws RemoteException {
		
		FileManager fm = new FileManager();													// get the filemanager
		Set<NodeInterface> activepeers = fm.requestActiveNodesForFile(filename);
		
		return Util.toString(new ArrayList<>(activepeers));
	}
	
	public static void printInfo(List<NodeInterface> nodes) {
		System.out.println("====");
		nodes.forEach(node -> {
			try {
				System.out.println(node.getNodeName()+"|"+node.getNodeID()+" | succ="+node.getSuccessor().getNodeName()
						+" | pred="+node.getPredecessor().getNodeName());
				System.out.println("keys="+node.getNodeKeys());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		});
		System.out.println("====");
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		
		String[] files = {"file1.txt","file2.txt","file3.txt","file4.txt","file5.txt"}; 	// we just limit to 5 files
		Map<String, Integer> processes = Util.getProcesses();								// get the processes (nodename, port)
		
		// start the 5 processes. Each process will be running on its own port
		startProcesses(processes);
		
		// resolve successor and predecessor
		List<NodeInterface> nodes = new ArrayList<>();
		resolveSuccPred(processes, nodes); 	
		
		// send replicas of each file to the process responsible for them
		distributeFiles(files);
		
		printInfo(nodes);
		
		System.exit(0);
	}
}
