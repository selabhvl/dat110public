package no.hvl.dat110.tcpexample.multithreads;

import no.hvl.dat110.tcpexample.system.Configuration;
import no.hvl.dat110.tcpexample.threadpool.EchoServerThreadPoolThread;
import no.hvl.dat110.tcpexample.threadpool.TCPEchoServerThreadPool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class EchoServerMultiThread {
	
	public static void main(String[] args) {
		
		int serverport = Configuration.SERVERPORT;

		System.out.println("TCP server starting # " + serverport);
		
		try (ServerSocket welcomeSocket = new ServerSocket(serverport)) {
				
			TCPEchoServerMultiThreads server = new TCPEchoServerMultiThreads(welcomeSocket);

			while (true) {
				
				server.process();
				
			}
			
		} catch (IOException ex) {
			System.out.println("TCP server: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
