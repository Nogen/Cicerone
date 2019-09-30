package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.gestioneattivita.richiesta.IRichiesta;
import com.intuition.cicerone.gestioneattivita.richiesta.IStatoRichiesta;
import com.intuition.cicerone.persistenza.AccountCrud;
import com.intuition.cicerone.persistenza.AttivitaCrud;
import com.intuition.cicerone.persistenza.RichiestaCrud;
import com.intuition.cicerone.persistenza.RichiestaCrudImpl;
import com.intuition.cicerone.persistenza.database.entity.RichiestaEntity;
import com.intuition.cicerone.persistenza.database.repo.RichiestaRepo;

public class TestRichiestaCrudImpl {
	private RichiestaRepo richiestaRepo;
	private AttivitaCrud attivitaCrud;
	private AccountCrud accountCrud;
	private RichiestaCrud richiestaCrud;
	private RichiestaEntity richiestaEnt;
	private IRichiesta richiesta;
	private IAttivita attivita;
	private IAccount richiedente;
	private IStatoRichiesta stato;
	private List<RichiestaEntity> listaRichieste;
	
	@Before
	public void setUp() {
		stato = mock(IStatoRichiesta.class);
		when(stato.toString()).thenReturn("accettata");
		
		richiestaEnt = mock(RichiestaEntity.class);
		when(richiestaEnt.getIdRichiesta()).thenReturn("idRichiesta");
		when(richiestaEnt.getDataRichiesta()).thenReturn(LocalDate.of(2019, 4, 4));
		when(richiestaEnt.getIdAttivita()).thenReturn("idAttivita");
		when(richiestaEnt.getIdRichiedente()).thenReturn("idAccount");
		when(richiestaEnt.getStatoRichiesta()).thenReturn("accettata");
		
		listaRichieste = new ArrayList<>();
		listaRichieste.add(richiestaEnt);
		
		
		attivita = mock(IAttivita.class);
		when(attivita.getIdAttivita()).thenReturn("idAttivita");
		
		richiedente = mock(IAccount.class);
		when(richiedente.getIdAccount()).thenReturn("idAccount");
		
		
		richiesta = mock(IRichiesta.class);
		when(richiesta.getAttivita()).thenReturn(attivita);
		when(richiesta.getMittente()).thenReturn(richiedente);
		when(richiesta.getDataRichiesta()).thenReturn(LocalDate.of(2019, 4, 4));
		when(richiesta.getIdRichiesta()).thenReturn("idRichiesta");
		when(richiesta.getStatoRichiesta()).thenReturn(stato);
		
		
		richiestaRepo = mock(RichiestaRepo.class);
		when(richiestaRepo.save(any(RichiestaEntity.class))).thenReturn(richiestaEnt);
		when(richiestaRepo.findById(any(String.class))).thenReturn(Optional.of(richiestaEnt));
		when(richiestaRepo.findByIdRichiedente(any(String.class))).thenReturn(listaRichieste);
		when(richiestaRepo.findByIdAttivita(any(String.class))).thenReturn(listaRichieste);
		doNothing().when(richiestaRepo).deleteByIdAttivita(any(String.class));
		
		attivitaCrud = mock(AttivitaCrud.class);
		when(attivitaCrud.trovaAttivita(any(String.class), isNull())).thenReturn(Optional.of(attivita));
		
		accountCrud = mock(AccountCrud.class);
		when(accountCrud.trovaAccountById(any(String.class))).thenReturn(Optional.of(richiedente));
		
		richiestaCrud = new RichiestaCrudImpl(richiestaRepo, attivitaCrud, accountCrud);
		
	}
	
	@Test
	public void testSalva() {
		assertTrue(richiestaCrud.salvaRichiesta(richiesta));
	}
	
	@Test 
	public void trovaRichiestaById() {
		assertTrue(richiestaCrud.trovaRichiestaById("idRichiesta").isPresent());
	}
	
	@Test 
	public void trovaRichiestaByIdNull() {
		when(richiestaRepo.findById(any(String.class))).thenReturn(Optional.empty());
		assertFalse(richiestaCrud.trovaRichiestaById("idRichiesta").isPresent());
	}
	
	@Test 
	public void trovaRichiestaByIdNullAttivita() {
		when(attivitaCrud.trovaAttivita(any(String.class), isNull())).thenReturn(Optional.empty());
		assertFalse(richiestaCrud.trovaRichiestaById("idRichiesta").isPresent());
	}
	
	@Test
	public void trovaRichiestaByIdNullAccount() {
		when(accountCrud.trovaAccountById(any(String.class))).thenReturn(Optional.empty());
		assertFalse(richiestaCrud.trovaRichiestaById("idRichiesta").isPresent());
	}
	
	
	@Test
	public void trovaRichiestaByAccount() {
		assertTrue(!richiestaCrud.trovaRichiestaByAccount(richiedente).isEmpty());
	}
	
	
	@Test
	public void trovaRichiestaByAccountEmpty() {
		when(richiestaRepo.findByIdRichiedente(any(String.class))).thenReturn(new ArrayList<>());
		assertTrue(richiestaCrud.trovaRichiestaByAccount(richiedente).isEmpty());
	}
	
	
	@Test
	public void trovaRichiestaByAccountNoAttivita() {
		when(attivitaCrud.trovaAttivita(any(String.class), isNull())).thenReturn(Optional.empty());
		assertTrue(richiestaCrud.trovaRichiestaByAccount(richiedente).isEmpty());
	}
	
	
	@Test
	public void trovaRichiestaByAttivita() {
		assertTrue(!richiestaCrud.trovaRichiestaByAttivita(attivita).isEmpty());
	}
	
	@Test
	public void trovaRichiestaByAttivitaEmpty() {
		when(richiestaRepo.findByIdAttivita(any(String.class))).thenReturn(new ArrayList<>());
		assertTrue(richiestaCrud.trovaRichiestaByAttivita(attivita).isEmpty());
	}
	
	@Test
	public void trovaRichiestaByAttivitaNull() {
		when(accountCrud.trovaAccountById(any(String.class))).thenReturn(Optional.empty());
		assertTrue(richiestaCrud.trovaRichiestaByAttivita(attivita).isEmpty());
	}
	
	@Test
	public void cancellaRichiesta() {
		assertTrue(richiestaCrud.eliminaRichiesta("idAttivita"));
	}

}
