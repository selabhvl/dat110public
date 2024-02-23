/**
 * 
 */
package no.hvl.dat110.crack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;

import org.paukov.combinatorics3.Generator;

import no.hvl.dat110.workernodes.Utility;

/**
 * dat110: DS-Lab 2
 * @author tdoy
 *
 */
public class BruteForce {


	public BruteForce() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Permutation with repetition
	 * key = #alphabets^lengthOfKey
	 * @param keyspace
	 * @throws NoSuchAlgorithmException 
	 */
	public static boolean crackPassword(String[] keyspace, int keylength, String hashtocrack) throws NoSuchAlgorithmException {

		Iterator<List<String>> keys = Generator.permutation(keyspace).withRepetitions(keylength).iterator();
		
		int total = 0;
		
		while(keys.hasNext()) {
			List<String> key = keys.next();
			
			String skey = "";
			for(int i=0; i<key.size(); i++) {
				skey += key.get(i);
			}
			//System.out.println(skey);

			boolean found = PasswordUtility.verifyHash(skey, hashtocrack);
			if(found) {
				System.out.println(skey);
				return true;
			}
			//++total;
		}
		//System.out.println(total);
		return false;

	}
    
	public static void main(String[] args) throws IOException, InvalidKeyException, NoSuchAlgorithmException {

		int[] jobspasswordlen = {2, 3};
		// password = s0lbA
		String hashofpassword = PasswordUtility.generateHashWithoutSalt("s0l");
		System.out.println("This is the hash of the password we want to crack "+hashofpassword);
		byte[] pwdb = "s0l".getBytes();
		int pwdbits = pwdb.length*8;
		System.out.println("number of bytes: "+pwdb.length+" | number of bits: "+pwdbits);
		
		// start to crack - this is a compute intensive task
		System.out.println("Starting to crack password...");
		boolean found = false;
		long start = System.currentTimeMillis();
		for(int i=0; i<jobspasswordlen.length; i++) {
			found = crackPassword(Utility.getKeyspace(), jobspasswordlen[i], hashofpassword);
			if(found) {
				long end = System.currentTimeMillis();
				long diff = end - start;
				System.out.println("Password found: | search takes "+diff+" milisecond");
				return;
			}
		}
		
	}

}
