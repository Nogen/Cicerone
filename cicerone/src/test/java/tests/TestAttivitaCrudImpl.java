package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.gestioneattivita.attivita.IItinerario;
import com.intuition.cicerone.gestioneattivita.attivita.IStatoAttivita;
import com.intuition.cicerone.gestioneattivita.attivita.Itinerario;
import com.intuition.cicerone.persistenza.AccountCrud;
import com.intuition.cicerone.persistenza.AttivitaCrud;
import com.intuition.cicerone.persistenza.AttivitaCrudImpl;
import com.intuition.cicerone.persistenza.database.entity.AttivitaEntity;
import com.intuition.cicerone.persistenza.database.entity.ChiavePartecipanti;
import com.intuition.cicerone.persistenza.database.entity.FotoItinerarioEntity;
import com.intuition.cicerone.persistenza.database.entity.ItinerarioEntity;
import com.intuition.cicerone.persistenza.database.entity.LinguaAttivitaEntity;
import com.intuition.cicerone.persistenza.database.entity.PartecipantiEntity;
import com.intuition.cicerone.persistenza.database.repo.AttivitaRepo;
import com.intuition.cicerone.persistenza.database.repo.FotoItinerarioRepo;
import com.intuition.cicerone.persistenza.database.repo.ItinerarioRepo;
import com.intuition.cicerone.persistenza.database.repo.LinguaAttivitaRepo;
import com.intuition.cicerone.persistenza.database.repo.PartecipanteRepo;

public class TestAttivitaCrudImpl {
	private AttivitaRepo attivitaRepo;
	private ItinerarioRepo itiRepo;
	private FotoItinerarioRepo fotoRepo;
	private LinguaAttivitaRepo lingueRepo;
	private PartecipanteRepo partecipantiRepo;
	private AccountCrud accountCrud;
	private IItinerario itinerario;
	private IAttivita attivita;
	private Set<String> lingue;
	private Set<IAccount> account;
	private IAccount account1;
	private IAccount account2;
	private ItinerarioEntity itinerarioEnt;
	private AttivitaEntity attivitaEnt;
	private PartecipantiEntity partecipantiEnt;
	private FotoItinerarioEntity fotoEnt;
	private LinguaAttivitaEntity lingueEnt;
	private IAccount creatore;
	private IStatoAttivita stato;
	private AttivitaCrud attCrudImpl;
	private PartecipantiEntity part1;
	private PartecipantiEntity part2;
	private ChiavePartecipanti k1;
	private ChiavePartecipanti k2;
	private List<PartecipantiEntity> listaPartecipanti;
	private List<AttivitaEntity> listaAttEnt;

	
	
	@Before
	public void setUp() {
		k1 = mock(ChiavePartecipanti.class);
		when(k1.getIdAccount()).thenReturn("idAccount1");
		k2 = mock(ChiavePartecipanti.class);
		when(k2.getIdAccount()).thenReturn("idAccount2");
		part1 = mock(PartecipantiEntity.class);
		when(part1.getChiavePartecipanti()).thenReturn(k1);
		
		part2 = mock(PartecipantiEntity.class);
		when(part2.getChiavePartecipanti()).thenReturn(k2);
		
		listaPartecipanti = new ArrayList<>();
		listaPartecipanti.add(part1);
		listaPartecipanti.add(part2);
		
	
		
		stato = mock(IStatoAttivita.class);
		when(stato.toString()).thenReturn("aperto");
		creatore = mock(IAccount.class);
		when(creatore.getIdAccount()).thenReturn("idCreatore");
		lingue = new HashSet<>();
		lingue.add("Italiano");
		lingue.add("Inglese");
		
		account1 = mock(IAccount.class);
		when(account1.getIdAccount()).thenReturn("idAccount1");
		account2 = mock(IAccount.class);
		when(account2.getIdAccount()).thenReturn("idAccount2");
		
		account = new HashSet<>();
		account.add(account1);
		account.add(account2);
		
		itinerarioEnt = mock(ItinerarioEntity.class);
		when(itinerarioEnt.getIdItinerario()).thenReturn("IdItinerario");
		when(itinerarioEnt.getDataIncontro()).thenReturn(LocalDate.of(1994, 04, 30));
		when(itinerarioEnt.getDescrizioneAttivita()).thenReturn("Prova Descrizione");
		when(itinerarioEnt.getNomeLuogo()).thenReturn("Bari");
		when(itinerarioEnt.getOraIncontro()).thenReturn(LocalTime.of(23, 12));
		when(itinerarioEnt.getRetribuzione()).thenReturn(true);
		when(itinerarioEnt.getValoreRetribuzione()).thenReturn(5.5D);
		when(itinerarioEnt.getxIncontro()).thenReturn(5D);
		when(itinerarioEnt.getyIncontro()).thenReturn(5D);
		
		attivitaEnt = mock(AttivitaEntity.class);
		when(attivitaEnt.getIdAttivita()).thenReturn("idAttivita");
		when(attivitaEnt.getDataApertura()).thenReturn(LocalDate.of(1994, 04, 30));
		when(attivitaEnt.getDataModifica()).thenReturn(LocalDate.of(1994, 04, 30));
		when(attivitaEnt.getIdCreatore()).thenReturn("idCreatore");
		when(attivitaEnt.getIdItinerario()).thenReturn("idItinerario");
		when(attivitaEnt.getStatoAttivita()).thenReturn("aperto");
		when(attivitaEnt.getTitoloAttivita()).thenReturn("Titolo");
		partecipantiEnt = mock(PartecipantiEntity.class);
		fotoEnt = mock(FotoItinerarioEntity.class);
		lingueEnt = mock(LinguaAttivitaEntity.class);
		
		listaAttEnt = new ArrayList<>();
		listaAttEnt.add(attivitaEnt);
		
		itinerario = mock(IItinerario.class);
		when(itinerario.getIdItinerario()).thenReturn("idItinerario");
		when(itinerario.getDataIncontro()).thenReturn(LocalDate.of(1994, 04, 30));
		when(itinerario.getDescrizioneAttivita()).thenReturn("Prova Descrizione");
		when(itinerario.getLingua()).thenReturn(lingue);
		when(itinerario.getLuogo()).thenReturn("Bari");
		when(itinerario.getOraIncontro()).thenReturn(LocalTime.of(23, 12));
		when(itinerario.getRetribuzione()).thenReturn(Itinerario.Retribuzione.RETRIBUITO);
		when(itinerario.getValoreRetribuzione()).thenReturn(5.5D);
		when(itinerario.getxIncontro()).thenReturn(5D);
		when(itinerario.getyIncontro()).thenReturn(5D);
		
		attivita = mock(IAttivita.class);
		when(attivita.getCreatore()).thenReturn(creatore);
		when(attivita.getDataApertura()).thenReturn(LocalDate.of(1994, 04, 30));
		when(attivita.getDataIncontro()).thenReturn(LocalDate.of(1994, 04, 30));
		when(attivita.getDataModifica()).thenReturn(LocalDate.of(1994, 04, 30));
		when(attivita.getIdAttivita()).thenReturn("idAttivita");
		when(attivita.getItinerario()).thenReturn(itinerario);
		when(attivita.getStatoAttivita()).thenReturn(stato);
		when(attivita.getTitolo()).thenReturn("Titolo");
		when(attivita.getPartecipanti()).thenReturn(account);
		
		
		
		attivitaRepo = mock(AttivitaRepo.class);
		doReturn(attivitaEnt).when(attivitaRepo).save(any(AttivitaEntity.class));
		when(attivitaRepo.findById(any(String.class))).thenReturn(Optional.of(attivitaEnt));
		doNothing().when(attivitaRepo).deleteById(any(String.class));
		when(attivitaRepo.findByStatoAttivita(any(String.class))).thenReturn(listaAttEnt);
		when(attivitaRepo.findByIdCreatore(any(String.class))).thenReturn(listaAttEnt);
		
		itiRepo = mock(ItinerarioRepo.class);
		doReturn(itinerarioEnt).when(itiRepo).save(any(ItinerarioEntity.class));
		when(itiRepo.findById(any(String.class))).thenReturn(Optional.of(itinerarioEnt));
		doNothing().when(itiRepo).deleteById(any(String.class));
		
		fotoRepo = mock(FotoItinerarioRepo.class);
		doReturn(fotoEnt).when(fotoRepo).save(any(FotoItinerarioEntity.class));
		doReturn(new ArrayList<>()).when(fotoRepo).findByChiaveFotoIdItinerario(any(String.class));
		doNothing().when(fotoRepo).deleteByChiaveFotoIdItinerario(any(String.class));
		
		lingueRepo = mock(LinguaAttivitaRepo.class);
		doReturn(lingueEnt).when(lingueRepo).save(any(LinguaAttivitaEntity.class));
		doReturn(new ArrayList<>()).when(lingueRepo).findByChiaveLinguaAttivitaIdItinerario(any(String.class));
		doNothing().when(lingueRepo).deleteByChiaveLinguaAttivitaIdItinerario(any(String.class));
		
		partecipantiRepo = mock(PartecipanteRepo.class);
		doReturn(partecipantiEnt).when(partecipantiRepo).save(any(PartecipantiEntity.class));
		when(partecipantiRepo.findByChiavePartecipantiIdAttivita(any(String.class))).thenReturn(listaPartecipanti);
		doNothing().when(partecipantiRepo).deleteByChiavePartecipantiIdAttivita(any(String.class));
		doNothing().when(partecipantiRepo).deleteByChiavePartecipantiIdAccount(any(String.class));
		
		accountCrud = mock(AccountCrud.class);
		when(accountCrud.trovaAccountById(any(String.class))).thenReturn(Optional.of(account1));
		
		attCrudImpl = new AttivitaCrudImpl(attivitaRepo, itiRepo, lingueRepo, accountCrud, partecipantiRepo);
		
	}
	
	
	@Test
	public void testSalva() {
		assertTrue(attCrudImpl.salvaAttivita(attivita));
	}
	
	@Test
	public void testSalvaEmptyDatamodifica() {
		when(attivita.getDataModifica()).thenReturn(null);
		assertTrue(attCrudImpl.salvaAttivita(attivita));
	}
	
	@Test
	public void testSalvaOtherRetribuzione() {
		when(itinerario.getRetribuzione()).thenReturn(Itinerario.Retribuzione.NONRETRIBUITO);
		assertTrue(attCrudImpl.salvaAttivita(attivita));
	}
	
	@Test
	public void testSalvaNullLingue() {
		when(itinerario.getLingua()).thenReturn(null);
		assertTrue(attCrudImpl.salvaAttivita(attivita));
	}
	
	@Test
	public void testSalvaNullPartecipanti() {
		when(attivita.getPartecipanti()).thenReturn(null);
		assertTrue(attCrudImpl.salvaAttivita(attivita));
	}
	
	@Test
	public void testSalvaEmptyPartecipanti() {
		when(attivita.getPartecipanti()).thenReturn(new HashSet<>());
		assertTrue(attCrudImpl.salvaAttivita(attivita));
	}
	
	
	@Test
	public void testSalvaEmptyLingue() {
		when(itinerario.getLingua()).thenReturn(new HashSet<>());
		assertTrue(attCrudImpl.salvaAttivita(attivita));
	}
	
	@Test
	public void trovaLingueEmpty() {
		assertTrue(attCrudImpl.trovaLingue("idItinerario").isEmpty());
	}
	
	@Test
	public void trovaPartecipanti() {
		assertFalse(attCrudImpl.trovaPartecipanti("idAttivita").isEmpty());
	}
	
	@Test
	public void trovaPartecipantiEmpty() {
		when(partecipantiRepo.findByChiavePartecipantiIdAttivita("idAttivita")).thenReturn(new ArrayList<>());
		when(accountCrud.trovaAccountById(any(String.class))).thenReturn(Optional.empty());
		assertTrue(attCrudImpl.trovaPartecipanti("idAttivita").isEmpty());
	}
	
	@Test
	public void trovaPartecipantiEmpty2() {
		when(accountCrud.trovaAccountById(any(String.class))).thenReturn(Optional.empty());
		assertTrue(attCrudImpl.trovaPartecipanti("idAttivita").isEmpty());
	}
	
	@Test
	public void trovaItinerario() {
		assertTrue(attCrudImpl.trovaItinerario("idItinerario").isPresent());
	}
	
	@Test
	public void trovaItinerarioEmpty() {
		when(itiRepo.findById(any(String.class))).thenReturn(Optional.empty());
		assertTrue(attCrudImpl.trovaItinerario("idItinerario").isEmpty());
	}
	
	@Test
	public void trovaAttivita() {
		assertTrue(attCrudImpl.trovaAttivita("idAttivita", account).isPresent());
	}
	
	@Test
	public void trovaAttivitaEmpty1() {
		when(attivitaRepo.findById(any(String.class))).thenReturn(Optional.empty());
		assertTrue(attCrudImpl.trovaAttivita("idAttivita", account).isEmpty());
	}
	
	@Test
	public void trovaAttivitaEmpty2() {
		when(itiRepo.findById(any(String.class))).thenReturn(Optional.empty());
		assertTrue(attCrudImpl.trovaAttivita("idAttivita", account).isEmpty());
	}
	
	@Test
	public void trovaAttivitaEmpty3() {
		when(accountCrud.trovaAccountById(any(String.class))).thenReturn(Optional.empty());
		assertTrue(attCrudImpl.trovaAttivita("idAttivita", account).isEmpty());
	}
	
	
	@Test
	public void eliminaAttivita() {
		assertTrue(attCrudImpl.eliminaAttivita(attivita));
	}
	
	@Test
	public void eliminaAttivitaById() {
		assertTrue(attCrudImpl.eliminaAttivitaById("idAttivita"));
	}
	
	@Test
	public void eliminaAttivitaByIdFail() {
		when(itiRepo.findById(any(String.class))).thenReturn(Optional.empty());
		assertFalse(attCrudImpl.eliminaAttivitaById("idAttivita"));
	}
	
	@Test
	public void eliminaPartecipante() {
		assertTrue(attCrudImpl.eliminaPartecipante("idAccount"));
	}
	
	@Test
	public void trovaAttivitaByCreatore() {
		assertTrue(!attCrudImpl.trovaAttivitaByIdCreatore("idCreatore").isEmpty());
	}
	
	@Test
	public void trovaAttivitaByStato() {
		assertTrue(!attCrudImpl.trovaAttivitaByStato("aperto").isEmpty());
	}
	
	@Test
	public void trovaAttivitaByStatoFail() {
		when(attivitaRepo.findById(any(String.class))).thenReturn(Optional.empty());
		assertTrue(attCrudImpl.trovaAttivitaByStato("aperto").isEmpty());
	}

}
