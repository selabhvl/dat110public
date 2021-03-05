### Lab Week 11: 16/03 - 20/03

#### Consistency and Mutual Exclusion

#### Exercise 1 - Consistency: Active Replication using sequencer & bounding ordering deviations (pg.401 & 398)

Active Replication: Using a single sequencer to provide a total order for all writes propagated to replicas plus using a bounded ordering deviation to initiate when updates should be performed.
A totally-ordered multicasting is crucial for replicated services to keep replicas consistent by ensuring that each replica execute the same operations in the same order.
The sequencer enforces a totally-ordered events for all processes in the distributed system.

The project is organized as follows:

- no.hvl.dat110.ds.clients: contains three client processes (client1, client2, & client3). Each client initiates some operations such as requestDeposit, requestInterest, or requestWithdrawal to the replica process. 

- no.hvl.dat110.ds.middleware: contains the 'process' class with three operations (deposit, interest, and withdrawal). ProcessContainer class is the 'server' for the 'process' class. This is where the registry is started and where the binding of the process implementation to the registry is done. Message class is used to construct the message to be passed between the processes. Sequencer class coordinates the ordering of events among the replicas by providing unique numbers to each event before forwarding to the replicas. The SequencerContainer is the 'server' for the Sequencer.

- no.hvl.dat110.ds.middleware.iface: contains the process interface (ProcessInterface) where remotely-invocable methods are defined and the OperationType that defines what type of operation we want to perform. 

- no.hvl.dat110.ds.util: contains the Util class with various methods for obtaining registry or performing conversion and printing the clock states for each process.

- no.hvl.dat110.ds.test: contains JUnit test (ActiveReplicationTest) class that is used to test the result whether the state of the resource (balance) is consistent across the processes after the operations.

##### Task-1.0: Run the project
Study the code and understand the connections between the classes. The configuration follows the same style we have used for previous Java RMI labs.
Run the project using the ActiveReplicationTest junit test and confirm that the the test fails because the final balance is different at each replica. You will find that a default value is used for balance in the 'Process' class (private double balance = 1000;	// default balance).

##### Task 1.1: Implement these missing methods in the "Process" class (If you have implemented them in the previous labs, then copy your solution here!)

- private void sortQueue()

- public void requestDeposit(double amount)

- public void requestWithdrawal(double amount)

- public void applyOperation()

- private void multicastMessage(Message message)

- public void onReceivedMessage(Message message)

##### Task 1.2: Implement these missing methods in the "Sequencer" class.

- public void onReceivedMessage(Message message)

- private void sendQueueMessagesToReplicas()

#####  Testing
- Run the ActiveReplicationTest unit test. Correct result will show that the events are totally-ordered in each replica and the final value of the datastore is the same at all replicas.

#### Exercise 2 - Mutual Exclusion: Token ring (pg.325-326)

In Token ring algorithm for mutual exclusion, processes are organized as a logical ring with a token circulating between its members.
A process is permitted to access its critical region only when it has the token. If, it has no need of the token, it simply forwards it to its successor.
This basic implementation uses a TokenManager. When the processes start, they contact the TokenManager to request for token. If a token has been issued already, the process received a null token and then wait until it receives the token from its predecessor.
Note that we have not implemented any ordering in this scheme, rather we are concerned about mutual exclusion access to the critical region.

##### Task 2.1: Implement these missing methods in the "Process" class

- private void sortQueue()

- public void requestDeposit(double amount)

- public void requestWithdrawal(double amount)

- public void applyOperation()

- public void forwardToken()

- public void onTokenReceived(Token token)


##### Task 2.2: Implement this missing method in the "TokenManager" class

- public void requestToken(ProcessInterface requester): Note that all processes will make requests to the manager at the beginning - so synchronize this method

#####  Testing
- Run the TokenRingMutexTest unit test. 