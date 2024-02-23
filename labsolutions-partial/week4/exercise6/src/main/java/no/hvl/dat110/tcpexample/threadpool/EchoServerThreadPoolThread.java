package no.hvl.dat110.tcpexample.threadpool;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

// exercise 6.2

public class EchoServerThreadPoolThread extends Thread {

    private BlockingQueue<Socket> queue;

    public EchoServerThreadPoolThread(BlockingQueue<Socket> queue) {

        this.queue = queue;
    }

    @Override
    public void run() {

        try {

            System.out.println("SERVER THREAD RUNNING");

            System.out.println("SERVER THREAD WAITING");

            Socket connectionSocket = queue.take();

            BufferedReader inFromClient = new BufferedReader(
                    new InputStreamReader(connectionSocket.getInputStream()));

            DataOutputStream outToClient =
                    new DataOutputStream(connectionSocket.getOutputStream());

            String text = inFromClient.readLine();

            System.out.println("SERVER THREAD RECEIVED: " + text);

            Thread.sleep(5000);


            String outtext = text.toUpperCase();

            System.out.println("SERVER SENDING: " + outtext);

            outToClient.write(outtext.getBytes());
            outToClient.flush();

            outToClient.close();
            inFromClient.close();

            connectionSocket.close();

        } catch (Exception ex) {

            System.out.println("TCPServer: " + ex.getMessage());
            ex.printStackTrace();
            System.exit(1);

        }
    }
}