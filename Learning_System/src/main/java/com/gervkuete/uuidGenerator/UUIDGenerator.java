package com.gervkuete.uuidGenerator;

import java.security.SecureRandom;
import java.util.UUID;

public class UUIDGenerator {
	
	public static final int LENGTH = 8;
	
	// Generate UUID code
	public static String generateUUIDCode() {
		
		UUID uuidCode = UUID.nameUUIDFromBytes(getBytes(LENGTH));
		String uniqueCode = uuidCode.toString();
		
		return uniqueCode;
	}
	
	// Generate a random array of bytes
	private static byte[] getBytes(int length) {
		
		SecureRandom random = new SecureRandom();
		byte [] bytes = new byte[length];
		random.nextBytes(bytes);
	
		return bytes;
	}

}
