package no.hvl.dat110.tcpexample.multithreads;

import no.hvl.dat110.tcpexample.multithreads.EchoServerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPEchoServerMultiThreads {

    private ServerSocket welcomeSocket;

    public TCPEchoServerMultiThreads(ServerSocket welcomeSocket) {
        this.welcomeSocket = welcomeSocket;
    }

    public void process() {

        try {

            System.out.println("SERVER ACCEPTING");

            Socket connectionSocket = welcomeSocket.accept();

            // create and start new thread to handle the request
            EchoServerThread echothread = new EchoServerThread(connectionSocket);

            echothread.start();

        } catch (IOException ex) {

            System.out.println("TCPServer: " + ex.getMessage());
            ex.printStackTrace();
            System.exit(1);

        }
    }
}
