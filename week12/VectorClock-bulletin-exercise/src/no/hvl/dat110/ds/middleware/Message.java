package no.hvl.dat110.ds.middleware;

import java.io.Serializable;
import java.rmi.RemoteException;

import no.hvl.dat110.ds.middleware.iface.OperationType;

public class Message implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private VectorClock vectorclock;
	
	private int processID;
	
	private String processname;
	
	private OperationType optype;
	
	
	public Message(VectorClock vc, String procname) throws RemoteException {
		super();
		vectorclock = vc;
		processname = procname;
	}
	
	public int getProcessID() {
		return processID;
	}

	public OperationType getOptype() {
		return optype;
	}

	public void setOptype(OperationType optype) {
		this.optype = optype;
	}
	
	public VectorClock getVectorClock() {
		return vectorclock;
	}

	public String getProcessname() {
		return processname;
	}

	public void setProcessname(String processname) {
		this.processname = processname;
	}	

}
