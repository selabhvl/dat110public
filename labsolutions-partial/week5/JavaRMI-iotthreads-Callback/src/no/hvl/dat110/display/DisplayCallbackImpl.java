package no.hvl.dat110.display;

import java.io.Serializable;
import java.rmi.RemoteException;

import no.hvl.dat110.rpcinterface.DisplayCallbackInterface;

/**
 * dat110 - DS Lab2
 * @author tdoy
 *
 */

public class DisplayCallbackImpl implements DisplayCallbackInterface, Serializable {

	private static final long serialVersionUID = 1L;
	
	protected DisplayCallbackImpl() throws RemoteException {
		super();
	}

	public void notifyTemp(String temp) throws RemoteException {

		System.out.println("Display: "+temp);

	}

	@Override
	public boolean isExit() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setExit(boolean exit) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

}
