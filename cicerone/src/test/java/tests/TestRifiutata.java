package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


import com.intuition.cicerone.gestioneattivita.richiesta.IStatoRichiesta;
import com.intuition.cicerone.gestioneattivita.richiesta.Rifiutata;

public class TestRifiutata {
	private IStatoRichiesta stato;
	private String toString;
	
	@Before
	public void setUp() {
		stato = new Rifiutata();
		toString = "rifiutata";
	}
	
    @Test(expected = UnsupportedOperationException.class)
	public void testRifiutataAccetta() {
		this.stato.accetta();
	}
    
    @Test(expected = UnsupportedOperationException.class)
  	public void testRifiutataRifiuta() {
  		this.stato.rifiuta();
  	}
    
    @Test
    public void testRifiutataToString() {
    	assertEquals(this.stato.toString(), toString);
    }
  
}
