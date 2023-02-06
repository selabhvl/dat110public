/**
 * 
 */
package no.hvl.dat110.node.client.test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.hvl.dat110.rpc.interfaces.NodeInterface;
import no.hvl.dat110.util.FileManager;
import no.hvl.dat110.util.Util;

/**
 * @author tdoy
 *
 */
class FileManagerTest {

	Map<String, BigInteger> process;
	Map<String, String> esucclist;
	Map<String, String> epredlist;
	Map<String, List<BigInteger>> nodeKeys;
	String[] files = {"file1.txt","file2.txt","file3.txt","file4.txt","file5.txt"}; 	// we just limit to 5 files
	
	Map<String,List<String>> filepeers;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		// we use MD5 = 128bits digest e.g. MD5(process1) = 53937554629190552131995290006614509577
		process = new HashMap<>();
		process.put("process1", new BigInteger("53937554629190552131995290006614509577"));
		process.put("process2", new BigInteger("15618062003214643351512781541041391612"));
		process.put("process3", new BigInteger("66910184482037901621933403444034052414"));
		process.put("process4", new BigInteger("210821560651360572675896360671414673172"));
		process.put("process5", new BigInteger("121411138451101288395601026024677976156"));
		
		// processes as a ring (clockwise)
		/*
		 *  process2--process1--process3
		 *  	|				 	|
		 * 	process4------------process5	
		 */
		
		// successor list
		esucclist = new HashMap<>();
		esucclist.put("process2", "process1");					// succ(process2) = process1
		esucclist.put("process1", "process3");
		esucclist.put("process3", "process5");
		esucclist.put("process5", "process4");
		esucclist.put("process4", "process2");
		
		// predecessor list
		epredlist = new HashMap<>();
		epredlist.put("process2", "process4");					// pred(process2) = process4
		epredlist.put("process1", "process2");
		epredlist.put("process3", "process1");
		epredlist.put("process5", "process3");
		epredlist.put("process4", "process5");
		
		// keys per node
		nodeKeys = new HashMap<>();
		List<BigInteger> keys = new ArrayList<>();
		keys.add(new BigInteger("106898375461510930699891848002818230949"));
		keys.add(new BigInteger("111933751583767510917536098155773044324"));
		keys.add(new BigInteger("102409483064276404020174989928858243542"));
		keys.add(new BigInteger("80150142447853624415004495970167216355"));
		keys.add(new BigInteger("80787213940621454660676758942935538622"));
		nodeKeys.put("process5", keys);
		
		nodeKeys.put("process3", new ArrayList<>());
		
		keys = new ArrayList<>();
		keys.add(new BigInteger("146708157158825425053135111790787455915"));
		keys.add(new BigInteger("199621117681418889672016094417121817743"));
		keys.add(new BigInteger("207743256949653106004077389226747513300"));
		keys.add(new BigInteger("168047408651558619100268873553763300399"));
		keys.add(new BigInteger("203652729595564922954169376161886197154"));
		nodeKeys.put("process4", keys);
		
		keys = new ArrayList<>();
		keys.add(new BigInteger("28379499046649929853551099560986503310"));
		keys.add(new BigInteger("38614402654317098457835935453908077616"));
		keys.add(new BigInteger("47976767092063389574292498102343842280"));
		nodeKeys.put("process1", keys);
		
		keys = new ArrayList<>();
		keys.add(new BigInteger("289072684510559077471687861044814513399"));
		keys.add(new BigInteger("232266669478378750921271570318251527404"));
		keys.add(new BigInteger("212812066000176083916901751737929718379"));
		keys.add(new BigInteger("282451069880104095785105614328160380023"));
		keys.add(new BigInteger("263412444306651309180712725199060671153"));
		keys.add(new BigInteger("307182277615750537357932158175973126553"));
		keys.add(new BigInteger("339001094065270715770842887880463587525"));
		nodeKeys.put("process2", keys);
		
	}

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
