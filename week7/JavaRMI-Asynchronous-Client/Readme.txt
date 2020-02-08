Java Remote Method Invocation (RMI) - Asynchronous RPC Client

The Java RMI is a language-based implementation of remote procedure call (RPC). 
This code example illustrates an example of implementing an asynchronous RPC client.

This implementation uses a callback mechanism whereby client is notified when server result becomes available.
The client also spawns a new thread to wait for the server result while the main process is unblocked and could continue its normal process.

To run the example code:
Import the project into your IDE

1. Run the RPC Server (ComputerServer.java)
2. Run the RPC Client (ComputeClient.java)