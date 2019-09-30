package tests;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.autenticazione.utils.IdGenerator;
import com.intuition.cicerone.gestioneattivita.attivita.Attivita;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.gestioneattivita.attivita.IItinerario;
import com.intuition.cicerone.gestioneattivita.attivita.IStatoAttivita;
import com.intuition.cicerone.gestioneattivita.attivita.Itinerario;


@RunWith(Parameterized.class)
public class TestAttivita {
	private LocalDate date;
	private LocalTime time;
	private String titoloP;
	private IItinerario itinerario;
	private String id;
	private Set<String> lingue;
	private Set<IAccount> partecipanti;
	private IAccount account;
	private IAttivita attivita;
	private IStatoAttivita stato;
	private static final String troppoLunga = "L5wmPayBihJ0MA3Vg5nQT13HpuEkV3sbDNt5r9qgdyDwqOUUdtZAsTFZKylw84HrZe05R1KYAqwAcCY2s9XoIHM5S5ES1TmgNvRLtyWlmpk2HekYtvsft7ifozePW5RKanXHgl7HuFIq035JV5FT6eqIXcZe0yTaY0li2bE5uw4IMgEtQLkOCZNxlLiiTjMQeg7VaXHYhGFObdcy1w5d9jSHgErPYbKkLLeR4jxCimoVzudZQHLCBGb1GT5AMwwy";

	@Before
	public void setUp() {
		titoloP = "Prova titolo attivita";
		date = LocalDate.now();
		time = LocalTime.now();
		lingue = new HashSet<>();
		lingue.add("Francese");
		lingue.add("Spagnolo");
		id = IdGenerator.generateId();
		account = mock(IAccount.class);
		itinerario = mock(IItinerario.class);
		stato = mock(IStatoAttivita.class);
		partecipanti = (Set<IAccount>)mock(Set.class);
		attivita = new Attivita.BuilderAttivita()
				.setCreatore(account)
				.setItinerario(itinerario)
				.setIdAttivita(id)
				.setDataApertura(LocalDate.now())
				.setTitolo(titoloP)
				.build();
		attivita.setPartecipanti(partecipanti);
		attivita.setStatoAttivita(stato);
		attivita.setDataModifica(date);
	}

	@Parameter(0)	
	public String idCasuale;

	@Parameter(1)
	public String titoloTest;

	@Parameter(2)
	public IItinerario itinerarioTest;

	@Parameter(3)	
	public IAccount accountTest;

	@Parameter(4)
	public LocalDate dataTest;





	// creates the test data
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] {  
			{null, null, null, null, null},
			{null, "", null, null, null},
			{null, troppoLunga, null, null, null},
		};
		return Arrays.asList(data);
	}
	
	@Test
	public void testBuilderGeneraid() {
		new Attivita.BuilderAttivita().generaIdAttivta();
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBuilderid() {
		new Attivita.BuilderAttivita().setIdAttivita(idCasuale);
	}


	@Test(expected = IllegalArgumentException.class)
	public void testBuilderTitolo() {
		new Attivita.BuilderAttivita().setTitolo(titoloTest);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBuilderItinerario() {
		new Attivita.BuilderAttivita().setItinerario(itinerarioTest);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBuilderCreatore() {
		new Attivita.BuilderAttivita().setCreatore(accountTest);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBuilderData() {
		new Attivita.BuilderAttivita().setDataApertura(dataTest);
	}


	@Test
	public void getCreatore() {
		assertEquals(account, this.attivita.getCreatore());
	}

	@Test
	public void getTitolo() {
		assertEquals(titoloP, this.attivita.getTitolo());
	}

	@Test
	public void getStatoAttivita() {
		assertEquals(stato, this.attivita.getStatoAttivita());
	}

	@Test
	public void getPartecipanti() {
		assertEquals(partecipanti, this.attivita.getPartecipanti());
	}

	@Test
	public void getIdAttivita() {
		assertEquals(id, this.attivita.getIdAttivita());
	}

	@Test
	public void getDataApertura() {
		assertEquals(date, this.attivita.getDataApertura());
	}

	@Test
	public void getDataModifica() {
		assertEquals(date, this.attivita.getDataModifica());
	}

	@Test
	public void getItinerario() {
		assertEquals(itinerario, this.attivita.getItinerario());
	}





	@Test
	public void getDataIncontro() {
		when(itinerario.getDataIncontro()).thenReturn(date);
		assertEquals(date, this.attivita.getDataIncontro());
	}

	@Test
	public void getOraIncontro() {
		when(itinerario.getOraIncontro()).thenReturn(time);
		assertEquals(time, this.attivita.getOraIncontro());
	}

	@Test
	public void getLingua() {
		when(itinerario.getLingua()).thenReturn(lingue);
		assertEquals(lingue, this.attivita.getLingua());
	}

	@Test
	public void getRetribuzione() {
		when(itinerario.getRetribuzione()).thenReturn(Itinerario.Retribuzione.RETRIBUITO);
		assertEquals(Itinerario.Retribuzione.RETRIBUITO, this.attivita.getRetribuzione());
	}

	@Test
	public void getValoreRetribuzione() {
		when(itinerario.getValoreRetribuzione()).thenReturn(5D);
		assertEquals(Double.valueOf(5D), this.attivita.getValoreRetribuzione());
	}

	@Test
	public void getLuogo() {
		when(itinerario.getLuogo()).thenReturn("Bari");
		assertEquals("Bari", this.attivita.getLuogo());
	}	

	@Test
	public void getDescrizioneAttivita() {
		when(itinerario.getDescrizioneAttivita()).thenReturn("descrizione di prova");
		assertEquals("descrizione di prova", this.attivita.getDescrizioneAttivita());
	}

	@Test
	public void getxIncontro() {
		when(itinerario.getxIncontro()).thenReturn(5D);
		assertEquals(Double.valueOf(5D),this.attivita.getxIncontro());
	}

	@Test
	public void getyIncontro() {
		when(itinerario.getyIncontro()).thenReturn(5D);
		assertEquals(Double.valueOf(5D), this.attivita.getyIncontro());
	}


	@Test(expected = IllegalArgumentException.class)
	public void testattivitaSetDataModifica() {
		this.attivita.setDataModifica(null);
	}
	
	@Test
	public void testattivitaSetDataModificaSuccess() {
		this.attivita.setDataModifica(date);
		assertEquals(date, this.attivita.getDataModifica());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testattivitaSetPartecipanti() {
		this.attivita.setPartecipanti(null);
	}
	
	@Test
	public void testattivitaSetPartecipantiSuccess() {
		Set<IAccount> a = new HashSet<>();
		this.attivita.setPartecipanti(a);
		assertEquals(a, this.attivita.getPartecipanti());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testattivitaSetStato() {
		this.attivita.setStatoAttivita(null);
	}
	
	@Test
	public void testattivitaSetStatoSuccess() {
		this.attivita.setStatoAttivita(stato);
		assertEquals(stato, this.attivita.getStatoAttivita());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testattivitaModificaItinerario() {
		this.attivita.modificaItinerario(null);
	}
	
	@Test
	public void testattivitaModificaItinerarioSuccess() {
		this.attivita.modificaItinerario(itinerario);
		assertEquals(itinerario, this.attivita.getItinerario());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testattivitaAggiungiPartecipantiFail1() {
		this.attivita.aggiungiPartecipante(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testattivitaAggiungiPartecipantiFail2() {
		when(partecipanti.contains(any(IAccount.class))).thenReturn(true);
		this.attivita.aggiungiPartecipante(account);
	}
	
	@Test
	public void testattivitaAggiungiPartecipantiSuccess() {
		when(partecipanti.contains(any(IAccount.class))).thenReturn(false);
		doNothing().when(stato).aggiungi(account);
		assertTrue(this.attivita.aggiungiPartecipante(account));
	}
	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testattivitaEliminaPartecipantiFail1() {
		this.attivita.eliminaPartecipante(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testattivitaEliminaPartecipantiFail2() {
		when(partecipanti.contains(any(IAccount.class))).thenReturn(false);
		this.attivita.eliminaPartecipante(account);
	}
	
	@Test
	public void testattivitaEliminaPartecipantiSuccess() {
		when(partecipanti.contains(any(IAccount.class))).thenReturn(true);
		doNothing().when(stato).elimina(account);
		assertTrue(this.attivita.eliminaPartecipante(account));
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testattivitaIscrivibileFail1() {
		this.attivita.isIscrivibile(null);
	}
	
	
	@Test
	public void testattivitaIscrivibileSuccess1() {
		when(stato.isIscrivibile(any(IAccount.class))).thenReturn(true);
		assertTrue(this.attivita.isIscrivibile(account));
	}
	
	@Test
	public void testattivitaIscrivibileSuccess2() {
		when(stato.isIscrivibile(any(IAccount.class))).thenReturn(false);
		assertFalse(this.attivita.isIscrivibile(account));
	}
	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testattivitaPuntoIncontroFail1() {
		this.attivita.modificaPuntoIncontro(null, null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testattivitaPuntoIncontroFail2() {
		this.attivita.modificaPuntoIncontro(null, null, "Bitonto");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testattivitaPuntoIncontroFail3() {
		this.attivita.modificaPuntoIncontro(null, 5D, "Bitonto");
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testattivitaPuntoIncontroFail4() {
		this.attivita.modificaPuntoIncontro(5D, null, "Bitonto");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testattivitaPuntoIncontroFail5() {
		this.attivita.modificaPuntoIncontro(5D, 5D, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testattivitaPuntoIncontroFail6() {
		this.attivita.modificaPuntoIncontro(5D, null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testattivitaPuntoIncontroFail7() {
		this.attivita.modificaPuntoIncontro(null, 5D, null);
	}
	
	@Test
	public void testattivitaPuntoIncontroSuccess() {
		when(stato.modificaPunto(any(Double.class), any(Double.class), any(String.class))).thenReturn(true);
		assertTrue(this.attivita.modificaPuntoIncontro(5D, 5D, "Bitonto"));
	}
	
	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testattivitaDataEOraIncontroFail1() {
		this.attivita.modificaDataEOraIncontro(null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testattivitaDataEOraIncontroFail2() {
		this.attivita.modificaDataEOraIncontro(date, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testattivitaDataEOraIncontroFail3() {
		this.attivita.modificaDataEOraIncontro(null, time);
	}
	
	@Test
	public void testattivitaDataEOraIncontroSuccess() {
		when(stato.modificaDataEOra(any(LocalDate.class), any(LocalTime.class))).thenReturn(true);
		assertTrue(this.attivita.modificaDataEOraIncontro(date, time));
	}
	
	
	@Test
	public void testattivitaChiudiAttivita() {
		assertTrue(this.attivita.chiudiAttivita());
	}
	
	
	@Test
	public void testModificaDescrizione() {
		when(stato.modificaDescrizione(any(String.class))).thenReturn(true);
		assertTrue(this.attivita.modificaDescrizione("Descrizione"));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testModificaDescrizioneFail() {
		assertTrue(this.attivita.modificaDescrizione(null));
	}
	
	
	
	
	

}
