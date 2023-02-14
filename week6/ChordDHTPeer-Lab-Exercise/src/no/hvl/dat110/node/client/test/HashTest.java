/**
 * 
 */
package no.hvl.dat110.node.client.test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;

import no.hvl.dat110.util.Hash;

/**
 * @author tdoy
 *
 */
class HashTest {

	/**
	 * Test method for {@link no.hvl.dat110.util.Hash#hashOf(java.lang.String)}.
	 */
	@Test
	void testHashOf() {
		
		BigInteger address_process1_expected = new BigInteger("53937554629190552131995290006614509577");
		
		BigInteger address_process1_actual = Hash.hashOf("process1");
		
		assertTrue(address_process1_expected.compareTo(address_process1_actual) == 0);
	}

	/**
	 * Test method for {@link no.hvl.dat110.util.Hash#addressSize()}.
	 */
	@Test
	void testAddressSize() {
		BigInteger address_space = new BigInteger("340282366920938463463374607431768211456");
		
		assertTrue(Hash.addressSize().compareTo(address_space) == 0);

	}

}
