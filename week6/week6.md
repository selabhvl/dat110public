### Lab Week 6: 7/02 - 11/02

This exercise focuses on the Chord implementation of Distributed Hash Table (DHT). The aim is to use this exercise to further understand the concepts of name space, addressing, identifier, entities and scalable lookups in peer-to-peer distributed systems
The current lab exercises provide implementations for testing the concept of naming, addressing, address space and size, and replication.
The implementation contains a chord ring that is not dynamically linked together. Essential chord protocols (like join ring, stabilize ring, check predecessor, fix finger table, etc) have not been implemented. You can take this up as additional challenge.

#### Design
The idea is that we can replicate a resource (e.g. a file) and distribute the replicas to running processes (peers) that are arranged in a ring topology. This design provides fault-tolerance and high availability for the resource.
However, to achieve these qualities require an efficient naming system. The DHT system provides the mechanism to pair a resource to the address where it can be stored and located.
Given a resource (e.g. a file), we can lookup the DHT system for the server(s) storing this file. The DHT system uses the same address space for naming a resource and its access point (peers).


To get started you need to download and import the ChordDHT project into your eclipse IDE. You can find the project here: https://github.com/selabhvl/dat110public/tree/master/week6/ChordDHTPeer-Lab-Exercise
The project is divided into four packages:

- no.hvl.dat110.rpc.interfaces: contains an interface (NodeInterface) with methods that peers can invoke remotely

- no.hvl.dat110.rpcserver: contains the class 'Node' which implements the remote methods. It also contains the NodeServer where the registry can be started and Node stub can be made available.

- no.hvl.dat110.node.client: contains classes that can be used to test the correctness of the distributed hash table. DHNodeTest is a unit test class. The successor and predecessor of a peer are set in the Client class, first by sorting the processes by their identifiers and next by looking at the peers before and behind this peer.

- no.hvl.dat110.util: contains the Hash class for defining address space by using MD5 hash function and computing the identifier for an entity; the Util class provides method for getting a process stub and
checking if a key lies within a node/peer and its predecessor. (pred < key <= node).

#### Task 1 - Address space and size (no.hvl.dat110.util.Hash)

In this task, you will implement the methods hashOf() and addressSize() in the Hash class. You must use the MD5 hash algorithm because the test cases in the DHTNodeTest class are generated using MD5 hash.
MD5 compresses strings to 128bits, thus the address size will be 2^128 = 340282366920938463463374607431768211456.
Note that the peers (process1, process2, process3, process4, process5) have been given identifiers from the same address space. You will find this in the Node class: nodeID = Hash.hashOf(nodename);
Use the HashTest unit test class to test your implementation.

#### Task 2 - Creating Replicas of file using the address space (2^128) (no.hvl.dat110.util.FileManager)

This task requires that you replicate files using index from 0 to 3 (numReplicas = 4). That is, the index must be added to the filename to generate replicas. (e.g. for a file with name, "test", replicas will be:
test0, test1, test2, test3. Each replica will now be named using the hash function you have implemented in Task 1. Your task here is to implement this functionality in the createReplicaFiles() method in the FileManager class.
Use the FileManagerTest unit test class to test your implementation.


#### Task 3 - Distributing file replicas to peers
In the chord ring system, a peer has a predecessor and a successor. Identifiers (addresses) that are higher than the predecessor and lower or equal to the identifier of the peer are managed by the peer.
Our replica is thus distributed using the simple rule: pred < replica <= peer. If the replica's id is less than or equal to a peer's identifier and greater than the identifier of the peer's predecessor, we assign the replica to this peer.

Your tasks are to first implement the logic to check this rule: pred < replica <= peer in the checkInterval() method in the Util class. Given an identifier, id: check whether pred < id <= node. Use the CheckIntervalTest unit test class to test your checkInterval() implementation. 
Next, you should implement the distributeReplicastoPeers() in the FileManager class. Use the FileManagerTest unit test class to test your implementation.

#### Task 4 - Finding the peers/servers responsible for a file
To look up a file in a chord system, we need to perform the same process in Task 3, where we replicate the file and find the peers holding each replica according to the rule. The distributed system then returns the peers to the client.

The major task here is to implement the requestActiveNodesForFile() method in the FileManager. Given a filename, find all the peers that hold a copy of this file.
Use the DHTNodeTest unit test class to test your implementation.

#### Task 5 - findSuccessor of a key (optional challenge)
The findSuccessor(id) method is a core method in the DHT system for recursively or iteratively resolving a key from the current node/peer.
You may need to read pg. 247-249 of the DS book and the original paper on chord system.
To correctly implement this function, you need to maintain a finger table for each node and also implement findHighestPredecessor method that uses the finger table to find the closest predecessor peer to a key.
As an additional challenge, implement the findSuccessor method.
