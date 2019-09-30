package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.intuition.cicerone.autenticazione.utils.HashnSalt;

public class TestHashnSalt {
	
	private String hashedpassword;
	private String salt;
	private String password;
	
	@Before
	public void setUp() {
		hashedpassword = "$2a$10$vZUWAjym5csOUNrOkEt3bOYr5iWPuT1oq9kbPjojerNj2K3QVxUFm";
		salt = "$2a$10$vZUWAjym5csOUNrOkEt3bO";
		password = "10000Days@";
	}
	
	@Test
	public void testExtractSalt() {
		assertEquals(salt, HashnSalt.extractSalt(hashedpassword));
	}
	
	@Test
	public void testGenerateSalt() {
		assertTrue(HashnSalt.generateSalt().length() == salt.length());
	}
	
	
	@Test
	public void testHash() {
		assertEquals(hashedpassword, HashnSalt.encode(password, salt));
	}
	

}
