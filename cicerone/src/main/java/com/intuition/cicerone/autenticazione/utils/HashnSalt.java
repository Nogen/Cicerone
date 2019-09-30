package com.intuition.cicerone.autenticazione.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class HashnSalt {
	
	private HashnSalt() {
	}

	public static String extractSalt(String hash) {
		return hash.substring(0, 29);
	}

	public static String generateSalt() {
		return BCrypt.gensalt();
	}

	public static String encode(String password, String salt) {
		return BCrypt.hashpw(password, salt);
	}
}