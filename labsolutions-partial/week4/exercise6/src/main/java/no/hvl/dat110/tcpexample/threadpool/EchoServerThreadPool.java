package no.hvl.dat110.tcpexample.threadpool;

import no.hvl.dat110.tcpexample.system.Configuration;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class EchoServerThreadPool {
	
	public static void main(String[] args) {
		
		int serverport = Configuration.SERVERPORT;
		int N_THREADS = Configuration.THREAD;

		BlockingQueue<Socket> queue = new LinkedBlockingQueue<>();

		if (args.length > 0) {			
			
			serverport = Integer.parseInt(args[0]);
		}

		for (int i = 0; i<N_THREADS;i++) {

			System.out.println("Starting thread");
			EchoServerThreadPoolThread echothread = new EchoServerThreadPoolThread(queue);

			echothread.start();

		}
		System.out.println("TCP server starting # " + serverport);
		
		try (ServerSocket welcomeSocket = new ServerSocket(serverport)) {
				
			TCPEchoServerThreadPool server = new TCPEchoServerThreadPool(welcomeSocket, queue);

			while (true) {
				
				server.process();
				
			}
			
		} catch (IOException ex) {
			System.out.println("TCP server: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}
