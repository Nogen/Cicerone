package tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.autenticazione.IAnagrafica;
import com.intuition.cicerone.controllers.mappers.RichiestaModelMapMapper;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.gestioneattivita.richiesta.IRichiesta;

public class TestRichiestaModelMapMapper {
	private IAttivita attivita;
	private IAccount account;
	private RichiestaModelMapMapper mapper;
	private IAnagrafica anagrafica;
	private IRichiesta richiesta;
	
	@Before
	public void SetUp() {
		anagrafica = mock(IAnagrafica.class);
		when(anagrafica.getNome()).thenReturn("Davide");
		when(anagrafica.getCognome()).thenReturn("Rossi");
		when(anagrafica.getNumeroDiTelefono()).thenReturn("3333333333");
		account = mock(IAccount.class);
		when(account.getAnagrafica()).thenReturn(anagrafica);
		when(account.getIdAccount()).thenReturn("idAccount");
		when(account.getEmail()).thenReturn("prova@prova.com");
		attivita = mock(IAttivita.class);
		when(attivita.getIdAttivita()).thenReturn("idAttivita");
		when(attivita.getTitolo()).thenReturn("titolo");
		
		richiesta = mock(IRichiesta.class);
		when(richiesta.getMittente()).thenReturn(account);
		when(richiesta.getDataRichiesta()).thenReturn(LocalDate.of(2019, 4, 4));
		when(richiesta.getAttivita()).thenReturn(attivita);
		when(richiesta.getIdRichiesta()).thenReturn("idRichiesta");

		mapper = new RichiestaModelMapMapper(richiesta);
	}
	
	@Test
	public void testIdAccount() {
		assertEquals("idAccount", mapper.converti().get("idAccount"));
	}
	
	@Test
	public void testNome() {
		assertEquals("Davide", mapper.converti().get("nome"));
	}
	
	
	@Test
	public void testCognome() {
		assertEquals("Rossi", mapper.converti().get("cognome"));
	}
	
	@Test
	public void testEmail() {
		assertEquals("prova@prova.com", mapper.converti().get("email"));
	}
	
	@Test
	public void testTelefono() {
		assertEquals("3333333333", mapper.converti().get("ntelefono"));
	}
	
	@Test
	public void testData() {
		assertEquals(LocalDate.of(2019, 4, 4).toString(), mapper.converti().get("dataRichiesta"));
	}
	
	@Test
	public void testIdAttivita() {
		assertEquals("idAttivita", mapper.converti().get("idAttivita"));
	}
	
	@Test
	public void testTitolo() {
		assertEquals("titolo", mapper.converti().get("titoloAttivita"));
	}
}
