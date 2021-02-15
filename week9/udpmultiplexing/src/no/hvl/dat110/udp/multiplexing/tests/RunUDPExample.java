package no.hvl.dat110.udp.multiplexing.tests;

import static org.junit.Assert.*;

import java.net.SocketException;
import java.net.UnknownHostException;

import org.junit.Test;

import no.hvl.dat110.udp.multiplexing.ReceiverProcess;
import no.hvl.dat110.udp.multiplexing.SenderProcess;

public class RunUDPExample {

	private static String TESTHOST = "localhost";
	private static String TESTPORT = "8080";

	@Test
	public void test() throws SocketException, UnknownHostException {

		System.out.println("UDP example starting ...");

		String[] senderargs = { TESTHOST, TESTPORT };
		String[] receiverargs = { TESTPORT };

		Runnable sp = () -> SenderProcess.main(senderargs);
		Runnable rp = () -> ReceiverProcess.main(receiverargs);

		Thread tsender = new Thread(sp);
		Thread treceiver = new Thread(rp);

		treceiver.start();
		tsender.start();

		try {
			tsender.join();
			treceiver.join();
		} catch (InterruptedException ex) {

			System.out.println("Main thread " + ex.getMessage());
			ex.printStackTrace();
		}

		System.out.println("UDP example stopped");
		
	}
}
