package no.hvl.dat110.ds.middleware;

import java.io.Serializable;
import java.rmi.RemoteException;

import no.hvl.dat110.ds.middleware.iface.OperationType;

public class Message implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private int processID;
	private OperationType optype;
	private double depositamount;
	private double interest;
	private double withdrawamount;
	
	public Message() throws RemoteException {
		super();
	}

	public int getProcessID() {
		return processID;
	}

	public void setProcessID(int processID) {
		this.processID = processID;
	}

	public OperationType getOptype() {
		return optype;
	}

	public void setOptype(OperationType optype) {
		this.optype = optype;
	}

	public double getDepositamount() {
		return depositamount;
	}

	public void setDepositamount(double depositamount) {
		this.depositamount = depositamount;
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public double getWithdrawamount() {
		return withdrawamount;
	}

	public void setWithdrawamount(double withdrawamount) {
		this.withdrawamount = withdrawamount;
	}	

}
