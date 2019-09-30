package tests;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.gestioneattivita.attivita.Chiuso;
import com.intuition.cicerone.gestioneattivita.attivita.IStatoAttivita;




public class TestChiuso {
	private LocalDate date;
	private LocalTime time;
	private IAccount account;
	private IStatoAttivita stato;
	
	@Before
	public void setUp() {
		date = LocalDate.now();
		time = LocalTime.now();
		account = mock(IAccount.class);
		stato = new Chiuso();
	}

	
	@Test(expected = UnsupportedOperationException.class)
	public void testChiusoAggiungiPartecipanti() {
		this.stato.aggiungi(account);
	}
	
	
	@Test(expected = UnsupportedOperationException.class)
	public void testChiusoEliminaPartecipanti() {
		this.stato.elimina(account);
	}
	
	
	@Test(expected = UnsupportedOperationException.class)
	public void testChiusoIscribibile() {
		this.stato.isIscrivibile(account);
	}

	
	@Test(expected = UnsupportedOperationException.class)
	public void testChiusoModificaPunto() {
		this.stato.modificaPunto(5D, 5D, "bitonto");
	}
	
	
	@Test(expected = UnsupportedOperationException.class)
	public void testChiusoModificaOraEData() {
		this.stato.modificaDataEOra(date, time);
	}
	
	
	@Test(expected = UnsupportedOperationException.class)
	public void testChiusoModificaDescrizione() {
		this.stato.modificaDescrizione("descrizione");
	}
	
	@Test
	public void testtoString() {
		assertEquals("chiuso", this.stato.toString());
	}

}

