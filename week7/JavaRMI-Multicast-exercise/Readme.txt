Java Remote Method Invocation (RMI)
The Java RMI is a language-based implementation of remote procedure call (RPC). 
This code example illustrates in a simple way how RMI is implemented.

The idea is that the server provides a single method called 'addNumbers' that can be remotely invoked by any client. 
The method contract (ComputeInterface.java) is made available to the client. 

To run this example on MacOS (single system), you need to do the following:

A. Compile the classes
Tdoy-MacPro:src tdoy$ javac no/hvl/dat110/rmiserver/*.java 
Tdoy-MacPro:src tdoy$ javac no/hvl/dat110/rmiinterface/*.java 
Tdoy-MacPro:src tdoy$ javac no/hvl/dat110/rmiclient/*.java 

B. Start the RMI registry
Tdoy-MacPro:src tdoy$ rmiregistry

C. Start the RMI server
Tdoy-MacPro:src tdoy$ java -cp /Users/tdoy/eclipse-workspace/JavaRMI/src/ no.hvl.dat110.rmiserver.ComputeServer

D. Run the client (and specify the two numbers to add in front of the classname)
Tdoy-MacPro:src tdoy$ java -cp /Users/tdoy/eclipse-workspace/JavaRMI/src/ no/hvl/dat110/rmiclient/ComputeClient 40 71

For windows, there is not much difference on how to compile/run a java program. 
cp needs to change to classpath and you need to use forward slash \ instead of / for your paths. (e.g. C:\Windows\eclipe-ws\...)
If you're still confused, just google it!


Questions:
What protocol is used here? 
How is the message passed?

Run wireshark

Repeat the steps A - D and check the packets captured by wireshark. 
In particular, pay attention to the Java RMI middleware protocol implemented on top of the TCP.



