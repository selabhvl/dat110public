package no.hvl.dat110.tcpexample.system;

import no.hvl.dat110.tcpexample.client.*;

public class MultipleClients {

    // Exercise 6.1 simulating multiple client with multiple threads
    public static void main(String[] args) {

        Thread clientthread1 = new Thread() {

            public void run() {

                String[] args = {"thread 1 text message"};

                try {
                    EchoClient.main(args);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };

        Thread clientthread2 = new Thread() {

            public void run() {

                String[] args = {"thread 2 text message"};

                try {
                    EchoClient.main(args);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };

        try {

            clientthread1.start();
            clientthread2.start();

            clientthread1.join();
            clientthread2.join();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("System stopping ...");
        }
    }
}
