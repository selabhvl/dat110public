### Lab Week 12: 22/03 - 26/03

#### Project 3: DHT Cooperative Mirroring with Consistency Protocols & Mutual Exclusion Algorithm
https://github.com/lmkr/dat110-project3-dht-startcode


#### Exercise 1: N-Fault Tolerant Sequencer in a single host (Chapter 8.2)

Assumption: The host is alive but the sequencer process has crashed.
The goal here is to make the single Sequencer N-fault tolerant in a single host. We will provide N number of SequencerManagers that are running on different ports.
We introduce N-sequencer managers (process group) that are positioned to monitor and re-create the sequencer when it fails. Each sequencer manager monitors its successor manager to detect if it's alive
This is a hierarchical process group architecture.
When the Sequence dies, the SequencerManager should detect as soon as RPC connection errors arise, it should then create and start a new Sequencer on the same port on which the old one was running.

The project is organized as follows:

- no.hvl.dat110.ds.clients: contains three client processes (client1, client2, & client3). Each client initiates some operations such as requestDeposit, requestInterest, or requestWithdrawal to the replica process. 

- no.hvl.dat110.ds.middleware: contains the 'process' class with three operations (deposit, interest, and withdrawal). ProcessContainer class is the 'server' for the 'process' class. This is where the registry is started and where the binding of the process implementation to the registry is done. Message class is used to construct the message to be passed between the processes. Sequencer class coordinates the ordering of events among the replicas by providing unique numbers to each event before forwarding to the replicas. The SequencerContainer is the 'server' for the Sequencer. The SequencerManager class has methods & logic to monitor the Sequencer and its own manager successor, and also recreate the Sequencer if the current one has failed. The SequenceManagerContainer is the 'server' for the SequencerManager.

- no.hvl.dat110.ds.middleware.iface: contains the process interface (ProcessInterface) where remotely-invocable methods are defined and the OperationType that defines what type of operation we want to perform. It also contains the SequenceManagerInterface.

- no.hvl.dat110.ds.sequencer.managers: contains 3 managers that should be started.

- no.hvl.dat110.ds.util: contains the Util class with various methods for obtaining registry or performing conversion and printing the clock states for each process.

- no.hvl.dat110.ds.test: contains JUnit test (FaultToleranceTest) class that is used to test the result whether the state of the resource (balance) is consistent across the processes after the operations.


##### Task 1: Implement these missing methods in the "Process" class (If you have implemented them in the previous labs, then copy your solution here!)

- private void sortQueue()

- public void requestDeposit(double amount)

- public void requestWithdrawal(double amount)

- public void applyOperation()

- private void multicastMessage(Message message)

- public void onMessageReceived(Message message)

##### Task 2: Implement these missing methods in the "Sequencer" class (Copy your solutions from previous lab)

- public void onMessageReceived(Message message)

- private void sendQueueMessagesToReplicas()

##### Task 3: Implement the missing part in the "doCheck" method in the "SequencerManager" class

- private void doCheck()

##### Testing fault-tolerance of the process group

- Start the 3 SequencerMgrs from the no.hvl.dat110.ds.sequencer.managers one after the other. Each manager has a specified time to live, after which it will shutdown. This is meant to simulate process failure and how the group managers monitor, react, and takeover.
- Run the FaultToleranceTest
- The test is timed to trigger every 40seconds, at which point one of the sequencer managers should be available and have started the sequencer on the same port.

If your logic in doCheck() is correct, the test should pass.

#### Exercise 2 - A distributed sequencer using token-ring (Additional challenge)
A single sequencer in a single host can be a bottleneck. What if we organize N replicated sequencers in form of a ring and then use the token ring algorithm to decide when a replica sequencer can forward received updates to the clients?
This exercise involves combining exercise 1 and exercise 2 from week11 lab. You can use 3 replicated sequencer processes and 3 clients' processes. A client (Client1, 2, and 3) can contact any sequencer randomly.
A token will be circulated among the sequencers and the sequencer that has the token will check if it has reached the ordering limit, at which point, it will trigger propagating the updates in its queue to the clients' processes. Alternatively, we can propagate to the sequencers and let each one contact its clients' processes.
- Modify the unit test in exercise 1 such that all the 3 sequencers are started.
- Make a new method getSequencerReplicas() for the 3 sequencers similar to getProcessReplicas() in the Util class.
- Modify sendMessageToSequencer(Message message) in the Process class such that a random sequencer is selected from getSequencerReplicas()
- Run the unit test and if everything works fine, the balance for each process should be the same.

- If your implementation is correct, the balance will be the same across all replicas at the end of the operations.
