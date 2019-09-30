package com.intuition.cicerone.autenticazione.utils;

import java.util.UUID;

public class IdGenerator {
	
	private IdGenerator() {
	}

	public static String generateId() {
		String id = UUID.randomUUID().toString();
		return id.replace("-", "");
	}
	
}
