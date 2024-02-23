package no.hvl.dat110.tcpexample.threadpool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class TCPEchoServerThreadPool {

    private ServerSocket welcomeSocket;
    private BlockingQueue<Socket> queue;

    public TCPEchoServerThreadPool(ServerSocket welcomeSocket, BlockingQueue<Socket> queue) {
        this.welcomeSocket = welcomeSocket;
        this.queue = queue;
    }

    public void process() {

        try {

            System.out.println("SERVER ACCEPTING");

            Socket connectionSocket = welcomeSocket.accept();

            // insert connection socket into queue of connection to be handled
            queue.add(connectionSocket);

        } catch (IOException ex) {

            System.out.println("TCPServer: " + ex.getMessage());
            ex.printStackTrace();
            System.exit(1);

        }
    }
}
