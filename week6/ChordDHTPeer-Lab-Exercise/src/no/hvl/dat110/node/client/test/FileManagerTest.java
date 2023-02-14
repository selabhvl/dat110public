/**
 * 
 */
package no.hvl.dat110.node.client.test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import no.hvl.dat110.rpc.interfaces.NodeInterface;
import no.hvl.dat110.util.FileManager;
import no.hvl.dat110.util.Util;

/**
 * @author tdoy
 *
 */
class FileManagerTest {

	/**
	 * Test method for {@link no.hvl.dat110.util.FileManager#createReplicaFiles(java.lang.String, int)}.
	 */
	@Test
	void testCreateReplicaFiles() {
		
		String file1_txt = "file1.txt";
		// actual BigInteger from implementation
		BigInteger[] replicas_expected = FileManager.createReplicaFiles(file1_txt, 4);
		
		// expected BigInteger for 4 replica addresses for file1.txt		
		List<BigInteger> file1 = new ArrayList<>();
		file1.add(new BigInteger("307182277615750537357932158175973126553"));	// file1.txt0
		file1.add(new BigInteger("207743256949653106004077389226747513300"));	// file1.txt1
		file1.add(new BigInteger("282451069880104095785105614328160380023"));	// file1.txt2
		file1.add(new BigInteger("146708157158825425053135111790787455915"));	// file1.txt3

		
		assertArrayEquals(file1.toArray(), replicas_expected);			// keys
	}

	/**
	 * Test method for {@link no.hvl.dat110.util.FileManager#distributeReplicastoPeers(java.lang.String)}.
	 * @throws RemoteException 
	 */
	@Test
	void testDistributeReplicastoPeers() throws RemoteException {
		
		// expected: file1.txt will be distributed to process2 and process4
		BigInteger[] process2_keys_expected = {new BigInteger("282451069880104095785105614328160380023"), new BigInteger("307182277615750537357932158175973126553")};
		BigInteger[] process4_keys_expected = {new BigInteger("207743256949653106004077389226747513300"), new BigInteger("146708157158825425053135111790787455915")};
		
		// actual: 
		String[] files = {"file1.txt"};
		Map<String, Integer> processes = Util.getProcesses();								// get the processes (nodename, port)
		List<NodeInterface> nodes = new ArrayList<>();										// list to store stubs
		Client.startProcesses(processes);													// start the registries for the processes, so we can get their stubs
		Client.resolveSuccPred(processes, nodes);											// organize the process as a ring and set pred and succ for each process
		Client.distributeFiles(files);														// distribute the files to the peers
		
		int port2 = processes.get("process2");
		int port4 = processes.get("process4");
		
		NodeInterface p2 = Util.getProcessStub("process2", port2);
		NodeInterface p4 = Util.getProcessStub("process4", port4);
		
		assertArrayEquals(p2.getNodeKeys().toArray(), process2_keys_expected);
		assertArrayEquals(p4.getNodeKeys().toArray(), process4_keys_expected);

	}

}
