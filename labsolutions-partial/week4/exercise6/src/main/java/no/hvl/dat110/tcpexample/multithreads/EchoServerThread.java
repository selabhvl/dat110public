package no.hvl.dat110.tcpexample.multithreads;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

// exercise 6.2

public class EchoServerThread extends Thread {

    private Socket connectionSocket = null;

    public EchoServerThread(Socket conn) {
        this.connectionSocket = conn;
    }

    @Override
    public void run() {

        try {

            System.out.println("SERVER THREAD RUNNING");

            BufferedReader inFromClient = new BufferedReader(
                    new InputStreamReader(connectionSocket.getInputStream()));

            DataOutputStream outToClient =
                    new DataOutputStream(connectionSocket.getOutputStream());

            String text = inFromClient.readLine();

            System.out.println("SERVER THREAD RECEIVED: " + text);

            // Exercise 6.1 simulate server side processing
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String outtext = text.toUpperCase();

            System.out.println("SERVER SENDING: " + outtext);

            outToClient.write(outtext.getBytes());
            outToClient.flush();

            outToClient.close();
            inFromClient.close();

            connectionSocket.close();

        } catch (IOException ex) {

            System.out.println("TCPServer: " + ex.getMessage());
            ex.printStackTrace();
            System.exit(1);

        }
    }
}
