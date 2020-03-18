/**
 * 
 */
package no.hvl.dat110.util;

/**
 * @author tdoy
 *
 */
public class ProcessConfig {

	private String processName;
	private int port;
	
	public ProcessConfig(String procName, int port) {
		this.processName = procName;
		this.port = port;
	}

	/**
	 * @return the processName
	 */
	public String getProcessName() {
		return processName;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

}
