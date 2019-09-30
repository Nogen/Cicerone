package tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.gestioneattivita.attivita.Aperto;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.gestioneattivita.attivita.IItinerario;
import com.intuition.cicerone.gestioneattivita.attivita.IStatoAttivita;




public class TestAperto {
	private LocalDate date;
	private LocalTime time;
	private IItinerario itinerario;
	private Set<IAccount> partecipanti;
	private IAccount account;
	private IAttivita attivita;
	private IStatoAttivita stato;
	
	@Before
	public void setUp() {
		date = LocalDate.now();
		time = LocalTime.now();
		account = mock(IAccount.class);
		itinerario = mock(IItinerario.class);
		partecipanti = mock(Set.class);
		attivita = mock(IAttivita.class);
		stato = new Aperto(attivita);
	}

	
	@Test
	public void testApertoAggiungiPartecipanti() {
		when(attivita.getPartecipanti()).thenReturn(partecipanti);
		when(partecipanti.add(any(IAccount.class))).thenReturn(true);
		doNothing().when(attivita).setPartecipanti(anySet());
		this.stato.aggiungi(account);
		assertTrue(true);
	}
	
	
	@Test
	public void testApertoEliminaPartecipanti() {
		when(attivita.getPartecipanti()).thenReturn(partecipanti);
		when(partecipanti.remove(any(IAccount.class))).thenReturn(true);
		doNothing().when(attivita).setPartecipanti(anySet());
		this.stato.elimina(account);
		assertTrue(true);
	}
	
	
	@Test
	public void testApertoIscribibileTrue() {
		when(attivita.getPartecipanti()).thenReturn(partecipanti);
		when(partecipanti.contains(any(IAccount.class))).thenReturn(true);
		assertTrue(this.stato.isIscrivibile(account));
	}
	
	@Test
	public void testApertoIscribibileFalse() {
		when(attivita.getPartecipanti()).thenReturn(partecipanti);
		when(partecipanti.contains(any(IAccount.class))).thenReturn(false);
		assertFalse(this.stato.isIscrivibile(account));
	}
	
	@Test
	public void testApertoModificaPunto() {
		when(attivita.getItinerario()).thenReturn(itinerario);
		doNothing().when(itinerario).setCoordinateIncontro(any(Double.class), any(Double.class));
		doNothing().when(itinerario).setLuogo(any(String.class));
		doNothing().when(attivita).modificaItinerario(itinerario);
		doNothing().when(attivita).setStatoAttivita(any(IStatoAttivita.class));
		doNothing().when(attivita).setDataModifica(any(LocalDate.class));
		assertTrue(this.stato.modificaPunto(5D, 5D, "bitonto"));
	}
	
	
	@Test
	public void testApertoModificaOraEData() {
		when(attivita.getItinerario()).thenReturn(itinerario);
		doNothing().when(itinerario).setDataIncontro(any(LocalDate.class));
		doNothing().when(itinerario).setOraIncontro(any(LocalTime.class));
		doNothing().when(attivita).modificaItinerario(itinerario);
		doNothing().when(attivita).setStatoAttivita(any(IStatoAttivita.class));
		doNothing().when(attivita).setDataModifica(any(LocalDate.class));
		assertTrue(this.stato.modificaDataEOra(date, time));
	}
	
	
	@Test
	public void testApertoModificaDescrizione() {
		when(attivita.getItinerario()).thenReturn(itinerario);
		doNothing().when(itinerario).setDescrizioneAttivita(any(String.class));
		doNothing().when(attivita).modificaItinerario(itinerario);
		doNothing().when(attivita).setStatoAttivita(any(IStatoAttivita.class));
		doNothing().when(attivita).setDataModifica(any(LocalDate.class));
		assertTrue(this.stato.modificaDescrizione("descrizione"));
	}
	
	@Test
	public void testtoString() {
		assertEquals("aperto", this.stato.toString());
	}

}
