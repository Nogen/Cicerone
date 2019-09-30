package tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.intuition.cicerone.gestioneattivita.richiesta.Accettata;
import com.intuition.cicerone.gestioneattivita.richiesta.IStatoRichiesta;

public class TestAccettata {
	private IStatoRichiesta stato;
	private String toString;
	
	@Before
	public void setUp() {
		stato = new Accettata();
		toString = "accettata";
	}
	
    @Test(expected = UnsupportedOperationException.class)
	public void testAccettataAccetta() {
		this.stato.accetta();
	}
    
    @Test(expected = UnsupportedOperationException.class)
  	public void testAccettataRifiuta() {
  		this.stato.rifiuta();
  	}
    
    @Test
    public void testAccettataToString() {
    	assertEquals(this.stato.toString(), toString);
    }
  
}
