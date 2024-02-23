package no.hvl.dat110.rpcserver;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashSet;
import java.util.Set;

import no.hvl.dat110.rpc.interfaces.NodeInterface;
import no.hvl.dat110.util.Hash;

/**
 * dat110: DS Lab2
 */

public class Node extends UnicastRemoteObject implements NodeInterface {
	
	private BigInteger nodeID;							// BigInteger value of hash of IP address/name of the Node
	private String nodename;							// IP address/name of the node 
	private int port;									// port on which the registry for this node is running
	private NodeInterface successor;
	private NodeInterface predecessor;
	private Set<BigInteger> keys;
	
	private static final long serialVersionUID = 1L;
	
	public Node(String nodename) throws RemoteException {
		super();
		keys = new HashSet<BigInteger>();
		this.nodename = nodename;									// may use a different name such as "IP address" for a single machine simulation
		nodeID = Hash.hashOf(nodename);								// use the MD5  from Hash class
	}
	
	@Override
	public BigInteger getNodeID() throws RemoteException {
		
		return nodeID;
	}

	@Override
	public String getNodeName() throws RemoteException {
		
		return nodename;
	}

	@Override
	public int getPort() throws RemoteException {
		
		return port;
	}

	@Override
	public void setSuccessor(NodeInterface succ) throws RemoteException {
		
		successor = succ;
	}

	@Override
	public void setPredecessor(NodeInterface pred) throws RemoteException {
		
		predecessor = pred;
	}

	@Override
	public NodeInterface getPredecessor() throws RemoteException {
		
		return predecessor;
	}

	@Override
	public NodeInterface getSuccessor() throws RemoteException {
		
		return successor;
	}

	@Override
	public Set<BigInteger> getNodeKeys() throws RemoteException {
		
		return keys;
	}

	@Override
	public void addKey(BigInteger id) throws RemoteException {
		
		keys.add(id);
	}

	@Override
	public NodeInterface findSuccessor(BigInteger key) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}
