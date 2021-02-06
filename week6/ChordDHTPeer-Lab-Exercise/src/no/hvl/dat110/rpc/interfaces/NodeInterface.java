package no.hvl.dat110.rpc.interfaces;

/**
 * dat110
 * @author tdoy
 *
 */

import java.math.BigInteger;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;


public interface NodeInterface extends Remote {
	
	public BigInteger getNodeID() throws RemoteException;
	
	public Set<BigInteger> getNodeKeys() throws RemoteException;
	
	public String getNodeName() throws RemoteException;
	
	public int getPort() throws RemoteException;
	
	public void setSuccessor(NodeInterface succ) throws RemoteException;
	
	public void setPredecessor(NodeInterface pred) throws RemoteException;
	
	public NodeInterface getPredecessor() throws RemoteException;
	
	public NodeInterface getSuccessor() throws RemoteException;
	
	public void addKey(BigInteger id) throws RemoteException;
	
	public NodeInterface findSuccessor(BigInteger key) throws RemoteException;

	
}
