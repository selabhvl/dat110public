package no.hvl.dat110.crack;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class PasswordUtility {
	
	/**
	 * Low password security
	 * @param password
	 * @throws NoSuchAlgorithmException
	 */
	public static String generateHashWithoutSalt(final String password) throws NoSuchAlgorithmException {
		
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] passbytes = password.getBytes();
		md.update(passbytes);											// pass the password to the hash function
		byte[] passhash = md.digest();									// obtain the hash value of the password
		
		String hexOfHash = DatatypeConverter.printHexBinary(passhash); 	// convert into hex value
		
		return hexOfHash;
		
	}
	
	public static boolean verifyHash(String passwordtocheck, String hash) throws NoSuchAlgorithmException {
		
		String hashofpassword = generateHashWithoutSalt(passwordtocheck);
		
		boolean equal = hashofpassword.equalsIgnoreCase(hash);
		
		return equal;
	}
}

