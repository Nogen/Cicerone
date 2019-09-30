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
import com.intuition.cicerone.feedback.IFeedback;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.persistenza.AccountCrud;
import com.intuition.cicerone.persistenza.AttivitaCrud;
import com.intuition.cicerone.persistenza.FeedbackCrud;
import com.intuition.cicerone.persistenza.FeedbackCrudImpl;
import com.intuition.cicerone.persistenza.database.entity.FeedbackEntity;
import com.intuition.cicerone.persistenza.database.repo.FeedbackRepo;

public class TestFeedbackCrudImpl {
	private AccountCrud accountCrud;
	private AttivitaCrud attivitaCrud;
	private FeedbackRepo feedbackRepo;
	private IAttivita attivita;
	private IAccount account;
	private FeedbackEntity feedEnt;
	private IFeedback feedback;
	private FeedbackCrud feedbackCrud;
	
	@Before
	public void setUp() {
		
		
		
		
		
		attivita = mock(IAttivita.class);
		when(attivita.getIdAttivita()).thenReturn("idAttivita");
		
		account = mock(IAccount.class);
		when(account.getIdAccount()).thenReturn("idAccount");
		
		feedEnt = mock(FeedbackEntity.class);
		when(feedEnt.getIdAccount()).thenReturn("idAccount");
		when(feedEnt.getIdFeedback()).thenReturn("idfeedback");
		when(feedEnt.getCommento()).thenReturn("commento");
		when(feedEnt.getDataRilascio()).thenReturn(LocalDate.of(1994, 4, 4));
		when(feedEnt.getValutazione()).thenReturn(5);
		
		feedback = mock(IFeedback.class);
		when(feedback.getCommento()).thenReturn("Commento");
		when(feedback.getAttivita()).thenReturn(attivita);
		when(feedback.getAutore()).thenReturn(account);
		when(feedback.getData()).thenReturn(LocalDate.of(1994, 4, 14));
		when(feedback.getIdFeedback()).thenReturn("idFeedback");
		when(feedback.getValutazione()).thenReturn(4);
		
		List<FeedbackEntity> listFeedback = new ArrayList<>();
		listFeedback.add(feedEnt);
		
		feedbackRepo = mock(FeedbackRepo.class);
		when(feedbackRepo.findByIdAttivitaAndIdAccount(any(String.class), any(String.class))).thenReturn(Optional.of(feedEnt));
		when(feedbackRepo.findByIdAttivita(any(String.class))).thenReturn(listFeedback);
		doNothing().when(feedbackRepo).deleteByIdAttivita(any(String.class));
		attivitaCrud = mock(AttivitaCrud.class);
		when(attivitaCrud.trovaAttivita(any(String.class), isNull())).thenReturn(Optional.of(attivita));
		
		accountCrud = mock(AccountCrud.class);
		when(accountCrud.trovaAccountById(any(String.class))).thenReturn(Optional.of(account));
				
		feedbackCrud = new FeedbackCrudImpl(feedbackRepo, accountCrud, attivitaCrud);
	}
	
	
	@Test
	public void testaSalvaFeedback() {
		assertTrue(feedbackCrud.salvaFeedback(feedback));
	}

	
	@Test
	public void testaTrovaFeedbackbyidCreatore() {
		assertTrue(feedbackCrud.trovaFeedbackByIdCreatore("idCreatore", "idAttivita").isPresent());
	}
	
	@Test
	public void testaTrovaFeedbackbyidCreatoreFail() {
		when(accountCrud.trovaAccountById(any(String.class))).thenReturn(Optional.empty());
		assertFalse(feedbackCrud.trovaFeedbackByIdCreatore("idCreatore", "idAttivita").isPresent());
	}
	
	@Test
	public void testaTrovaFeedbackbyidCreatoreFail2() {
		when(attivitaCrud.trovaAttivita(any(String.class), isNull())).thenReturn(Optional.empty());
		assertFalse(feedbackCrud.trovaFeedbackByIdCreatore("idCreatore", "idAttivita").isPresent());
	}
	
	@Test
	public void testaTrovaFeedbackbyidCreatoreFail3() {
		when(feedbackRepo.findByIdAttivitaAndIdAccount(any(String.class), any(String.class))).thenReturn(Optional.empty());
		assertFalse(feedbackCrud.trovaFeedbackByIdCreatore("idCreatore", "idAttivita").isPresent());
	}
	
	@Test
	public void testaTrovaFeedbackbyidCreatoreFail4() {
		when(attivitaCrud.trovaAttivita(any(String.class), isNull())).thenReturn(Optional.empty());
		when(feedbackRepo.findByIdAttivitaAndIdAccount(any(String.class), any(String.class))).thenReturn(Optional.empty());
		assertFalse(feedbackCrud.trovaFeedbackByIdCreatore("idCreatore", "idAttivita").isPresent());
	}
	
	@Test
	public void testaTrovaFeedbackbyidCreatoreFail5() {
		when(accountCrud.trovaAccountById(any(String.class))).thenReturn(Optional.empty());
		when(feedbackRepo.findByIdAttivitaAndIdAccount(any(String.class), any(String.class))).thenReturn(Optional.empty());
		assertFalse(feedbackCrud.trovaFeedbackByIdCreatore("idCreatore", "idAttivita").isPresent());
	}
	
	@Test
	public void testaTrovaFeedbackbyidCreatoreFail6() {
		when(accountCrud.trovaAccountById(any(String.class))).thenReturn(Optional.empty());
		when(attivitaCrud.trovaAttivita(any(String.class), isNull())).thenReturn(Optional.empty());
		assertFalse(feedbackCrud.trovaFeedbackByIdCreatore("idCreatore", "idAttivita").isPresent());
	}
	
	@Test
	public void testaTrovabyAttivita() {
		assertTrue(!feedbackCrud.trovaByAttivita(attivita).isEmpty());
	}
	
	@Test
	public void testaTrovabyAttivitaFail1() {
		when(accountCrud.trovaAccountById(any(String.class))).thenReturn(Optional.empty());
		assertTrue(feedbackCrud.trovaByAttivita(attivita).isEmpty());
	}
	
	@Test
	public void testEliminaFeedback() {
		assertTrue(feedbackCrud.eliminaByAttivita("idAttivita"));
	}
	
}
