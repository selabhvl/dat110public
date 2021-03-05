/**
 * 
 */
package no.hvl.dat110.ds.middleware;

import java.io.Serializable;

/**
 * @author tdoy
 *
 */
public class Token implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int tokenId;
	
	public Token(int tokenId) {
		this.tokenId = tokenId;
	}

	/**
	 * @return the tokenId
	 */
	public int getTokenId() {
		return tokenId;
	}

}
