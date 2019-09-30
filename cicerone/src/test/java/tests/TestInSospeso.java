package tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.gestioneattivita.attivita.Attivita;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.gestioneattivita.richiesta.IRichiesta;
import com.intuition.cicerone.gestioneattivita.richiesta.IStatoRichiesta;
import com.intuition.cicerone.gestioneattivita.richiesta.InSospeso;
import com.intuition.cicerone.gestioneattivita.richiesta.Richiesta;

public class TestInSospeso {
	private IStatoRichiesta stato;
	private String toString;
	private IRichiesta richiesta;
	private IAttivita attivita;
	
	@Before
	public void setUp() {
		richiesta = mock(Richiesta.class);
		attivita = mock(Attivita.class);
		stato = new InSospeso(richiesta);
		toString = "in sospeso";
	}
	
    @Test
	public void testInSospesoAccetta() {
		when(richiesta.getAttivita()).thenReturn(attivita);
		when(attivita.aggiungiPartecipante(any(IAccount.class))).thenReturn(true);
		assertEquals("accettata", stato.accetta().toString());
	}
    
    @Test
  	public void testInSospesoRifiuta() {
    	IStatoRichiesta r = this.stato.rifiuta();
    	assertEquals("rifiutata", r.toString());
  	}
    
    @Test
    public void testInSospesoToString() {
    	assertEquals(toString, this.stato.toString());
    }
  
}
