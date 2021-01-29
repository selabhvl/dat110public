Java Remote Method Invocation (RMI)
The Java RMI is a language-based implementation of remote procedure call (RPC). 
This code example illustrates in a simple way how RMI is implemented.

The idea is that the server provides a single method called 'addNumbers' that can be remotely invoked by any client. 
The method contract (ComputeInterface.java) is made available to the client. 

The server provides the actual implementation (ComputeImpl) of the interface (ComputeInterface) where the addition of the two numbers
are performed and the result returned as an integer.

The server creates an instance of the implementation class, obtains a stub, and binds this stub to the registry with a name.
The registry is started and listens on a port (this is the access point for this server). 

The client will use the interface to obtain a stub from the registry that contacts the server end point through the server port.
Client can now invoke the method exposed in the interface and obtain the result as if it were a local method.

Under the hood, the client's stub marshalls the method's paramaters, contacts the rpcserver, and forwards the marshalled 
parameter to the server's stub. The server's stub receives the parameters, unmarshalled, and invoke the server's method.
The result obtained is marshalled by the server's stub and sent as a reply to the client's stub. The client receives the
server's message, unmarshalled the reply, and present the result to the client.


To run this example
1. Run the ComputeServer.java (located in no.hvl.dat110.rmiserver)
2. Run the ComputeClient.java (located in no.hvl.dat110.rmiclient)

You should now see the result of the addition performed by the server's method.
You should manually terminate the ComputeServer from the console otherwise port 9000 will remain opened.
(For security purposes, make sure you don't leave ports loosely opened on your machine)

