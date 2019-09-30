package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.autenticazione.utils.IdGenerator;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.gestioneattivita.attivita.IItinerario;
import com.intuition.cicerone.gestioneattivita.attivita.IStatoAttivita;
import com.intuition.cicerone.gestioneattivita.attivita.Itinerario;
import com.intuition.cicerone.gestioneattivita.attivita.ProxyAttivita;


public class TestProxyAttivita {
	private ProxyAttivita proxy;
	private IAttivita attivita;
	private LocalDate date;
	private LocalTime time;
	private IAccount account;
	private String id;
	private String cicerone;
	private String globetrotter;
	private String admin;
	private IItinerario itinerario;
	private Set<String> lingue;
	private Set<IAccount> partecipanti;
	private IStatoAttivita stato;

	
	@Before
	public void setUp() {
		lingue = new HashSet<>();
		lingue.add("Francese");
		lingue.add("Spagnolo");
		itinerario = mock(IItinerario.class);
		partecipanti = mock(Set.class);
		cicerone = "Cicerone";
		globetrotter = "Globetrotter";
		admin = "Admin";
		id = IdGenerator.generateId();
		date = LocalDate.now();
		time = LocalTime.now();
		attivita = mock(IAttivita.class);
		account = mock(IAccount.class);
		stato = mock(IStatoAttivita.class);
	}
	
	
	@Test
	public void getCreatore() {
		proxy = new ProxyAttivita(admin, attivita);
		when(attivita.getCreatore()).thenReturn(account);
		assertEquals(account, this.proxy.getCreatore());
	}

	@Test
	public void getTitolo() {
		proxy = new ProxyAttivita(admin, attivita);
		when(attivita.getTitolo()).thenReturn("Titolo prova");
		assertEquals("Titolo prova", this.proxy.getTitolo());
	}

	@Test
	public void getStatoAttivita() {
		proxy = new ProxyAttivita(admin, attivita);
		when(attivita.getStatoAttivita()).thenReturn(stato);
		assertEquals(stato, this.proxy.getStatoAttivita());
	}

	@Test
	public void getPartecipanti() {
		proxy = new ProxyAttivita(admin, attivita);
		when(attivita.getPartecipanti()).thenReturn(partecipanti);
		assertEquals(partecipanti, this.proxy.getPartecipanti());
	}

	@Test
	public void getIdAttivita() {
		proxy = new ProxyAttivita(admin, attivita);
		when(attivita.getIdAttivita()).thenReturn(id);
		assertEquals(id, this.proxy.getIdAttivita());
	}

	@Test
	public void getDataApertura() {
		proxy = new ProxyAttivita(admin, attivita);
		when(attivita.getDataApertura()).thenReturn(date);
		assertEquals(date, this.proxy.getDataApertura());
	}

	@Test
	public void getDataModifica() {
		proxy = new ProxyAttivita(admin, attivita);
		when(attivita.getDataModifica()).thenReturn(date);
		assertEquals(date, this.proxy.getDataModifica());
	}

	@Test
	public void getItinerario() {
		proxy = new ProxyAttivita(admin, attivita);
		when(attivita.getItinerario()).thenReturn(itinerario);
		assertEquals(itinerario, this.proxy.getItinerario());
	}





	@Test
	public void getDataIncontro() {
		proxy = new ProxyAttivita(admin, attivita);
		when(attivita.getDataIncontro()).thenReturn(date);
		assertEquals(date, this.proxy.getDataIncontro());
	}

	@Test
	public void getOraIncontro() {
		proxy = new ProxyAttivita(admin, attivita);
		when(attivita.getOraIncontro()).thenReturn(time);
		assertEquals(time, this.proxy.getOraIncontro());
	}

	@Test
	public void getLingua() {
		proxy = new ProxyAttivita(admin, attivita);
		when(attivita.getLingua()).thenReturn(lingue);
		assertEquals(lingue, this.proxy.getLingua());
	}

	@Test
	public void getRetribuzione() {
		proxy = new ProxyAttivita(admin, attivita);
		when(attivita.getRetribuzione()).thenReturn(Itinerario.Retribuzione.RETRIBUITO);
		assertEquals(Itinerario.Retribuzione.RETRIBUITO, this.proxy.getRetribuzione());
	}

	@Test
	public void getValoreRetribuzione() {
		proxy = new ProxyAttivita(admin, attivita);
		when(attivita.getValoreRetribuzione()).thenReturn(5D);
		assertEquals(Double.valueOf(5D), this.proxy.getValoreRetribuzione());
	}

	@Test
	public void getLuogo() {
		proxy = new ProxyAttivita(admin, attivita);
		when(attivita.getLuogo()).thenReturn("Bari");
		assertEquals("Bari", this.proxy.getLuogo());
	}	

	@Test
	public void getDescrizioneAttivita() {
		proxy = new ProxyAttivita(admin, attivita);
		when(attivita.getDescrizioneAttivita()).thenReturn("descrizione di prova");
		assertEquals("descrizione di prova", this.proxy.getDescrizioneAttivita());
	}

	@Test
	public void getxIncontro() {
		proxy = new ProxyAttivita(admin, attivita);
		when(attivita.getxIncontro()).thenReturn(5D);
		assertEquals(Double.valueOf(5D), this.proxy.getxIncontro());
	}

	@Test
	public void getyIncontro() {
		proxy = new ProxyAttivita(admin, attivita);
		when(attivita.getyIncontro()).thenReturn(5D);
		assertEquals(Double.valueOf(5D), this.proxy.getyIncontro());
	}


	@Test
	public void testattivitaSetDataModifica() {
		proxy = new ProxyAttivita(admin, attivita);
		doNothing().when(attivita).setDataModifica(any(LocalDate.class));
		this.proxy.setDataModifica(date);
		assertTrue(true);
	}
	
	
	@Test
	public void testattivitaSetPartecipanti() {
		proxy = new ProxyAttivita(admin, attivita);
		doNothing().when(attivita).setPartecipanti(anySet());
		this.proxy.setPartecipanti(partecipanti);
		assertTrue(true);
	}
	
	
	@Test
	public void testattivitaSetStato() {
		proxy = new ProxyAttivita(admin, attivita);
		doNothing().when(attivita).setStatoAttivita(any(IStatoAttivita.class));
		this.proxy.setStatoAttivita(stato);
		assertTrue(true);
	}
	
	
	@Test
	public void testattivitaModificaItinerario() {
		proxy = new ProxyAttivita(admin, attivita);
		doNothing().when(attivita).modificaItinerario(any(Itinerario.class));
		this.proxy.modificaItinerario(itinerario);
		assertTrue(true);
	}
	

	
	@Test
	public void testattivitaAggiungiPartecipantiSuccess() {
		proxy = new ProxyAttivita(admin, attivita);
		when(attivita.aggiungiPartecipante(any(IAccount.class))).thenReturn(true);
		assertTrue(this.proxy.aggiungiPartecipante(account));
	}
	
	@Test
	public void testattivitaAggiungiPartecipantiSuccess1() {
		proxy = new ProxyAttivita(cicerone, attivita);
		when(attivita.aggiungiPartecipante(any(IAccount.class))).thenReturn(true);
		assertTrue(this.proxy.aggiungiPartecipante(account));
	}
	
	
	@Test(expected = UnsupportedOperationException.class)
	public void testattivitaAggiungiPartecipantiFail1() {
		proxy = new ProxyAttivita("ciceronee", attivita);
		this.proxy.aggiungiPartecipante(account);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testattivitaAggiungiPartecipantiFail2() {
		proxy = new ProxyAttivita("globetrotter", attivita);
		this.proxy.aggiungiPartecipante(account);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testattivitaAggiungiPartecipantiFail3() {
		proxy = new ProxyAttivita("adminn", attivita);
		this.proxy.aggiungiPartecipante(account);
	}
	
	
	
	
	@Test
	public void testattivitaEliminaPartecipantiSuccess() {
		proxy = new ProxyAttivita(admin, attivita);
		when(attivita.eliminaPartecipante(any(IAccount.class))).thenReturn(true);
		assertTrue(this.proxy.eliminaPartecipante(account));
	}
	
	@Test
	public void testattivitaEliminaPartecipantiSuccess1() {
		proxy = new ProxyAttivita(cicerone, attivita);
		when(attivita.eliminaPartecipante(any(IAccount.class))).thenReturn(true);
		assertTrue(this.proxy.eliminaPartecipante(account));
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testattivitaEliminaPartecipantiFail2() {
		proxy = new ProxyAttivita("globetrotter", attivita);
		this.proxy.eliminaPartecipante(account);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testattivitaEliminaPartecipantiFail3() {
		proxy = new ProxyAttivita("adminn", attivita);
		this.proxy.eliminaPartecipante(account);
	}
	
	
	
	
	@Test
	public void testattivitaModificaDataEOraSuccess() {
		proxy = new ProxyAttivita(admin, attivita);
		when(attivita.modificaDataEOraIncontro(any(LocalDate.class), any(LocalTime.class))).thenReturn(true);
		assertTrue(this.proxy.modificaDataEOraIncontro(date, time));
	}
	
	@Test
	public void testattivitaModificaDataEOraSuccess1() {
		proxy = new ProxyAttivita(cicerone, attivita);
		when(attivita.modificaDataEOraIncontro(any(LocalDate.class), any(LocalTime.class))).thenReturn(true);
		assertTrue(this.proxy.modificaDataEOraIncontro(date, time));
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testattivitaEModificaDataEOraFail2() {
		proxy = new ProxyAttivita("globetrotter", attivita);
		this.proxy.modificaDataEOraIncontro(date, time);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testattivitaModificaDataEOraFail3() {
		proxy = new ProxyAttivita("adminn", attivita);
		this.proxy.modificaDataEOraIncontro(date, time);
	}
	
	
	
	
	
	
	@Test
	public void testattivitaModificaPuntoSuccess() {
		proxy = new ProxyAttivita(admin, attivita);
		when(attivita.modificaPuntoIncontro(any(Double.class), any(Double.class), any(String.class))).thenReturn(true);
		assertTrue(this.proxy.modificaPuntoIncontro(5D, 5d, "bitonto"));
	}
	
	@Test
	public void testattivitaModificaPuntoSuccess1() {
		proxy = new ProxyAttivita(cicerone, attivita);
		when(attivita.modificaPuntoIncontro(any(Double.class), any(Double.class), any(String.class))).thenReturn(true);
		assertTrue(this.proxy.modificaPuntoIncontro(5D, 5D, "bitonto"));
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testattivitaEModificaPuntoFail2() {
		proxy = new ProxyAttivita("globetrotter", attivita);
		this.proxy.modificaPuntoIncontro(5D, 5D, "bitonto");
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testattivitaModificaPuntoFail3() {
		proxy = new ProxyAttivita("adminn", attivita);
		this.proxy.modificaPuntoIncontro(5D, 5D, "bitonto");
	}
	
	
	
	
	
	
	
	@Test
	public void testattivitaChiudiAttivitaSuccess() {
		proxy = new ProxyAttivita(admin, attivita);
		when(attivita.chiudiAttivita()).thenReturn(true);
		assertTrue(this.proxy.chiudiAttivita());
	}
	
	@Test
	public void testattivitaChiudiAttivitaSuccess1() {
		proxy = new ProxyAttivita(cicerone, attivita);
		when(attivita.chiudiAttivita()).thenReturn(true);
		assertTrue(this.proxy.chiudiAttivita());
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testattivitaChiudiAttivitaFail2() {
		proxy = new ProxyAttivita("globetrotter", attivita);
		this.proxy.chiudiAttivita();
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testattivitaChiudiAttivitaFail3() {
		proxy = new ProxyAttivita("adminn", attivita);
		this.proxy.chiudiAttivita();
	}
	
	
	
	
	
	
	
	
	@Test
	public void testattivitaIscrivibileSuccess() {
		proxy = new ProxyAttivita(admin, attivita);
		when(attivita.isIscrivibile(any(IAccount.class))).thenReturn(true);
		assertTrue(this.proxy.isIscrivibile(account));
	}
	
	@Test
	public void testattivitaIscrivibileSuccess1() {
		proxy = new ProxyAttivita(globetrotter, attivita);
		when(attivita.isIscrivibile(any(IAccount.class))).thenReturn(true);
		assertTrue(this.proxy.isIscrivibile(account));
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testattivitaIscrivibileFail2() {
		proxy = new ProxyAttivita("globetrotteer", attivita);
		this.proxy.isIscrivibile(account);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testattivitaIscrivibileFail3() {
		proxy = new ProxyAttivita("adminn", attivita);
		this.proxy.isIscrivibile(account);
	}
	
	
	@Test
	public void testattivitaModifcaDescrizioneSuccess() {
		proxy = new ProxyAttivita(admin, attivita);
		when(attivita.modificaDescrizione(any(String.class))).thenReturn(true);
		assertTrue(this.proxy.modificaDescrizione("descrizione"));
	}
	
	@Test
	public void testattivitaModifcaDescrizioneSuccess1() {
		proxy = new ProxyAttivita(cicerone, attivita);
		when(attivita.modificaDescrizione(any(String.class))).thenReturn(true);
		assertTrue(this.proxy.modificaDescrizione("descrizione"));
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testattivitaModifcaDescrizioneFail2() {
		proxy = new ProxyAttivita("globetrotteer", attivita);
		this.proxy.modificaDescrizione("Descrizione");
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testattivitaModifcaDescrizioneFail3() {
		proxy = new ProxyAttivita("adminn", attivita);
		this.proxy.modificaDescrizione("Descrizione");
	}
	
	
}
