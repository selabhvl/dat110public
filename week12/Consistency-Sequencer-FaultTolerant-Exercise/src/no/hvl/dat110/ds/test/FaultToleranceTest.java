package no.hvl.dat110.ds.test;


import java.rmi.RemoteException;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.hvl.dat110.ds.clients.Client1;
import no.hvl.dat110.ds.clients.Client2;
import no.hvl.dat110.ds.clients.Client3;
import no.hvl.dat110.ds.middleware.Config;
import no.hvl.dat110.ds.middleware.ProcessContainer;
import no.hvl.dat110.ds.middleware.iface.ProcessInterface;
import no.hvl.dat110.ds.util.Util;

class FaultToleranceTest {
	
	@BeforeEach
	void setUp() throws Exception {

		//	
		// start the processes
		new ProcessContainer("process1", 10001, Config.PORT1);
		new ProcessContainer("process2", 10002, Config.PORT2);
		new ProcessContainer("process3", 10003, Config.PORT3);

	}

	@Test
	void test() throws InterruptedException {
		for(int i=0; i<4; i++) {
			testIteration();
			Thread.sleep(40000);
		}
	}
	
	private void testIteration() throws InterruptedException {
		ProcessInterface p1 = (ProcessInterface) Util.getProcessStub("process1", Config.PORT1);
		ProcessInterface p2 = (ProcessInterface) Util.getProcessStub("process2", Config.PORT2);
		ProcessInterface p3 = (ProcessInterface) Util.getProcessStub("process3", Config.PORT3);
		
		Client1 c1 = new Client1();
		Client2 c2 = new Client2();
		Client3 c3 = new Client3();
		
		c1.start();
		c2.start();
		c3.start();
		
		c1.join();
		c2.join();
		c3.join();
		
		double p1finalbal = 0;
		double p2finalbal = 1;
		double p3finalbal = 2;
		try {
			p1finalbal = p1.getBalance();
			p2finalbal = p2.getBalance();
			p3finalbal = p3.getBalance();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		Assert.assertNotEquals(1000, p1finalbal, 0);
		Assert.assertNotEquals(1000, p2finalbal, 0);
		Assert.assertNotEquals(1000, p3finalbal, 0);
		Assert.assertEquals(p1finalbal, p2finalbal, 0);
		Assert.assertEquals(p1finalbal, p3finalbal, 0);
	}

}
