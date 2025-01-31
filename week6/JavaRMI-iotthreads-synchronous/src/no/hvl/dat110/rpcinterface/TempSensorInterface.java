package no.hvl.dat110.rpcinterface;

/**
 * dat110: DS Lab2
 */

import java.rmi.Remote;

public interface TempSensorInterface extends Remote {
	
	public static final int SERVER_PORT = 9091;
	
	public static final String REMOTE_IFACE_NAME = "TempSensorInterface";
	
	// TODO
	// define the methods that should be remotely invoked here

}
