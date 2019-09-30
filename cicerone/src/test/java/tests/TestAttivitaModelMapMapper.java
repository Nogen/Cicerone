package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.autenticazione.IAnagrafica;
import com.intuition.cicerone.controllers.mappers.AttivitaModelMapMapper;
import com.intuition.cicerone.gestioneattivita.attivita.Attivita;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.gestioneattivita.attivita.IItinerario;
import com.intuition.cicerone.gestioneattivita.attivita.Itinerario;

public class TestAttivitaModelMapMapper {
	private IAttivita attivita;
	private IItinerario itinerario;
	private Set<String> lingue;
	private IAccount account;
	private AttivitaModelMapMapper mapper;
	private IAnagrafica anagrafica;
	
	@Before
	public void SetUp() {
		anagrafica = mock(IAnagrafica.class);
		when(anagrafica.getNome()).thenReturn("Davide");
		when(anagrafica.getCognome()).thenReturn("Rossi");
		account = mock(IAccount.class);
		when(account.getAnagrafica()).thenReturn(anagrafica);
		when(account.getIdAccount()).thenReturn("idAccount");
		lingue = new HashSet<>();
		lingue.add("italiano");
		itinerario = new Itinerario.BuilderItinerario()
				.setIdItinerario("idItinerario")
				.setDescrizioneAttivita("descrizione")
				.setRetribuzione(Itinerario.Retribuzione.RETRIBUITO, 5.5D)
				.setLingua(lingue)
				.build();
		itinerario.setCoordinateIncontro(5D, 5D);
		itinerario.setDataIncontro(LocalDate.of(1994, 4, 4));
		itinerario.setLuogo("Bari");
		itinerario.setOraIncontro(LocalTime.of(12, 0));
		attivita = new Attivita.BuilderAttivita()
				.setIdAttivita("idAttivita")
				.setDataApertura(LocalDate.of(1994, 4, 4))
				.setItinerario(itinerario)
				.setTitolo("titolo")
				.setCreatore(account)
				.build();
		mapper = new AttivitaModelMapMapper(attivita);
	}
	
	@Test
	public void testDataApertura() {
		assertEquals(LocalDate.of(1994, 4, 4).toString(), mapper.converti().get("dataApertura"));
	}
	
	@Test
	public void testDataModifica() {
		assertEquals(LocalDate.of(1994, 4, 4).toString(), mapper.converti().get("dataModifica"));
	}
	
	@Test
	public void testId() {
		assertEquals("idAttivita", mapper.converti().get("idAttivita"));
	}
	
	@Test
	public void testTitolo() {
		assertEquals("titolo", mapper.converti().get("titoloAttivita"));
	}
	
	@Test
	public void testDescrizione() {
		assertEquals("descrizione", mapper.converti().get("descrizioneAttivita"));
	}
	
	
	
	@Test
	public void testLingueEmpty() {
		lingue.remove("italiano");
		assertEquals("", mapper.converti().get("lingue"));
	}
	
	
	@Test
	public void testLingue() {
		assertTrue(((String)mapper.converti().get("lingue")).contains("italiano"));
	}
		
	
	@Test
	public void testRetribuzione() {
		assertEquals("retribuito", ((String)mapper.converti().get("retribuzione")).toLowerCase());
	}
	
	
	@Test
	public void testValoreRetribuzione() {
		assertEquals("5.5", mapper.converti().get("valoreRetribuzione"));
	}
	
	@Test
	public void testStato() {
		assertEquals("aperto", ((String)mapper.converti().get("statoAttivita")).toLowerCase());
	}
	
	@Test
	public void testNome() {
		assertEquals("Davide", mapper.converti().get("nomeCreatore"));
	}
	
	@Test
	public void testCognome() {
		assertEquals("Rossi", mapper.converti().get("cognomeCreatore"));
	}
	
	@Test
	public void testIdCreatore() {
		assertEquals("idAccount", mapper.converti().get("idCreatore"));
	}
	
	@Test
	public void testLuogo() {
		assertEquals("Bari", mapper.converti().get("luogo"));
	}
	
	@Test
	public void testxIncontro() {
		assertEquals(5D, mapper.converti().get("xIncontro"));
	}
	
	@Test
	public void testyIncontro() {
		assertEquals(5D, mapper.converti().get("yIncontro"));
	}
	
	@Test
	public void testDataIncontro() {
		assertEquals(LocalDate.of(1994, 4, 4).toString(), mapper.converti().get("dataIncontro"));
	}
	
	@Test
	public void testOraIncontro() {
		assertEquals(LocalTime.of(12, 0).toString(), mapper.converti().get("oraIncontro"));
	}
}
