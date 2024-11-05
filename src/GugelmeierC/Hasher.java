package GugelmeierC;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher
{
	public static String HashPassword(String passwordToHash) {
		String generatedPassword = null;
		try 
		{
		  // Create MessageDigest instance for SHA1
		  MessageDigest sha1 = MessageDigest.getInstance("SHA1");
		
		  // Add password bytes to digest
		  sha1.update(passwordToHash.getBytes());
		
		  // Get the hash's bytes
		  byte[] bytes = sha1.digest();
		
		  // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
		  StringBuilder sb = new StringBuilder();
		  for (int i = 0; i < bytes.length; i++) {
		    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
		  }
		
		  // Get complete hashed password in hex format
		  generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
		  e.printStackTrace();
		}
		return generatedPassword;
	}
}
