package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.intuition.cicerone.autenticazione.Account;
import com.intuition.cicerone.autenticazione.Anagrafica;
import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.autenticazione.IAnagrafica;
import com.intuition.cicerone.autenticazione.utils.IdGenerator;
import com.intuition.cicerone.persistenza.AccountCrud;
import com.intuition.cicerone.persistenza.AccountCrudImpl;
import com.intuition.cicerone.persistenza.database.entity.AccountEntity;
import com.intuition.cicerone.persistenza.database.entity.AnagraficaEntity;
import com.intuition.cicerone.persistenza.database.entity.ChiaveLinguaAccount;
import com.intuition.cicerone.persistenza.database.entity.LinguaAccountEntity;
import com.intuition.cicerone.persistenza.database.repo.AccountRepo;
import com.intuition.cicerone.persistenza.database.repo.AnagraficaRepo;
import com.intuition.cicerone.persistenza.database.repo.LinguaAccountRepo;


public class TestAccountCrudImpl {
	private IAccount account;
	private IAnagrafica anagrafica;
	private String ida;
	private String id;
	private String nome;
	private String cognome;
	private String email;
	private String password;
	private String residenza;
	private LocalDate bday;
	private String numeroTelefono;
	private Anagrafica.Sesso genere;
	private Set<String> lingue;
	private String ruolo;
	private AccountEntity entityacc;
	private AnagraficaEntity entityana;
	private LinguaAccountEntity entityling1;
	private LinguaAccountEntity entityling2;
	private AccountRepo accountrepo;
	private AnagraficaRepo anagraficarepo;
	private LinguaAccountRepo linguerepo;
	private AccountCrud crud;

	@Before
	public void setUp() {
		accountrepo = mock(AccountRepo.class);
		anagraficarepo = mock(AnagraficaRepo.class);
		linguerepo = mock(LinguaAccountRepo.class);
		ida = IdGenerator.generateId();
		id = IdGenerator.generateId();
		nome = "Gaetano";
		cognome = "Rossi";
		email = "g.rossi@gmail.com";
		password = "password";
		residenza ="Bari";
		bday = LocalDate.of(1994, 04, 30);
		numeroTelefono = "0000000000";
		genere = Anagrafica.Sesso.UOMO;
		lingue = new HashSet<>();
		lingue.add("Italiano");
		lingue.add("Inglese");
		ruolo = "Cicerone";
		anagrafica = new Anagrafica.BuilderAnagrafica()
				.setIdAnagrafica(id)
				.setNome(nome)
				.setCognome(cognome)
				.setDataDiNascita(bday)
				.setPaeseCitta(residenza)
				.setNumeroDiTelefono(numeroTelefono)
				.setSesso(genere)
				.setLingueParlate(lingue)
				.build();
		account = new Account.BuilderAccount()
				.setIdAccount(ida)
				.setAnagrafica(anagrafica)
				.generaContatoreNotifica()
				.setEmail(email)
				.setPassword(password)
				.setRuolo(ruolo)
				.build();
		
		entityacc = new AccountEntity();
		entityacc.setEmail(email);
		entityacc.setIdAccount(ida);
		entityacc.setIdAnagrafica(id);
		entityacc.setNumeroNotifiche(0);
		entityacc.setPassword(password);
		entityacc.setRuolo(ruolo);
		
		entityana = new AnagraficaEntity();
		entityana.setIdAnagrafica(id);
		entityana.setNome(nome);
		entityana.setCognome(cognome);
		entityana.setDataDiNascita(bday);
		entityana.setCitta(residenza);
		entityana.setNumeroDiTelefono(numeroTelefono);
		entityana.setSesso(false);
		
		ChiaveLinguaAccount k1 = new ChiaveLinguaAccount();
		k1.setIdAnagrafica(id);
		k1.setLingua("Italiano");
		
		entityling1 = new LinguaAccountEntity();
		entityling1.setChiaveComposta(k1);
		
		ChiaveLinguaAccount k2 = new ChiaveLinguaAccount();
		k1.setIdAnagrafica(id);
		k1.setLingua("Inglese");
		
		entityling2 = new LinguaAccountEntity();
		entityling2.setChiaveComposta(k2);
		
		crud = new AccountCrudImpl(accountrepo, anagraficarepo, linguerepo);
	}
	
	
	@Test
	public void cancellaAccount() {
		List<LinguaAccountEntity> a = new ArrayList<>();
		a.add(entityling1);
		doNothing().when(accountrepo).deleteById(any(String.class));
		doNothing().when(anagraficarepo).deleteById(any(String.class));
		when(linguerepo.findByChiaveCompostaIdAnagrafica(any(String.class))).thenReturn(a);
		doNothing().when(linguerepo).deleteAll();
		this.crud.cancellaAccount(account);
		assertTrue(true);
	}
	
	
	@Test 
	public void esisteAccountByEmail() {
		when(accountrepo.findById(any(String.class))).thenReturn(Optional.of(entityacc));
		assertTrue(this.crud.esisteAccountById(ida));
	}
	
	
	@Test 
	public void esisteAccountByEmailFalse() {
		when(accountrepo.findById(any(String.class))).thenReturn(Optional.empty());
		assertFalse(this.crud.esisteAccountById(ida));
	}
	
	@Test 
	public void esisteAccountById() {
		List<AccountEntity> n = new ArrayList<>();
		n.add(entityacc);
		when(accountrepo.findByEmail(any(String.class))).thenReturn(n);
		assertTrue(this.crud.esisteAccountByEmail(email));
	}
	
	@Test 
	public void esisteAccountByIdFalse() {
		List<AccountEntity> n = new ArrayList<>();
		when(accountrepo.findByEmail(any(String.class))).thenReturn(n);
		assertFalse(this.crud.esisteAccountByEmail(email));
	}
	
	
	@Test
	public void salvaAccountIAccount() {
		when(accountrepo.save(any(AccountEntity.class))).thenAnswer((Answer<?>) new Answer() {
	         public Object answer(InvocationOnMock invocation) {
	             Object[] args = invocation.getArguments();
	             AccountEntity a = (AccountEntity)args[0];
	             assertTrue(entityacc.equals(a));
	             return null;
	         }
		});
		this.crud.salvaAccount(account);
	}
	
	@Test
	public void salvaAccountIAccountAlternativo() {
		anagrafica = new Anagrafica.BuilderAnagrafica()
				.setIdAnagrafica(id)
				.setNome(nome)
				.setCognome(cognome)
				.setDataDiNascita(bday)
				.setPaeseCitta(residenza)
				.setNumeroDiTelefono(numeroTelefono)
				.setSesso(Anagrafica.Sesso.DONNA)
				.build();
		account = new Account.BuilderAccount()
				.setIdAccount(ida)
				.setAnagrafica(anagrafica)
				.generaContatoreNotifica()
				.setEmail(email)
				.setPassword(password)
				.build();
		when(accountrepo.save(any(AccountEntity.class))).thenReturn(entityacc);
		this.crud.salvaAccount(account);
		assertTrue(true);
	}
	
	
	@Test
	public void salvaAccountIAnagrafica() {
		when(accountrepo.save(any(AccountEntity.class))).thenReturn(entityacc);
		when(anagraficarepo.save(any(AnagraficaEntity.class))).thenAnswer((Answer<?>)new Answer() {
	         public Object answer(InvocationOnMock invocation) {
	             Object[] args = invocation.getArguments();
	             AnagraficaEntity a = (AnagraficaEntity)args[0];
	             assertTrue(entityana.equals(a));
	             return null;
	         }
		});
		this.crud.salvaAccount(account);
	}
	
	
	@Test
	public void trovaAccountByEmail() {
		List<LinguaAccountEntity> ln = new ArrayList<>();
		ln.add(entityling1);
		ln.add(entityling2);
		List<AccountEntity> n = new ArrayList<>();
		n.add(entityacc);
		when(accountrepo.findByEmail(any(String.class))).thenReturn(n);
		when(anagraficarepo.findById(any(String.class))).thenReturn(Optional.of(entityana));
		when(linguerepo.findByChiaveCompostaIdAnagrafica(any(String.class))).thenReturn(ln);
		
		assertTrue(this.crud.trovaAccountByEmail(email).isPresent());
	}
	
	@Test
	public void trovaAccountByEmailfail() {
		List<LinguaAccountEntity> ln = new ArrayList<>();
		ln.add(entityling1);
		ln.add(entityling2);
		List<AccountEntity> n = new ArrayList<>();
		when(accountrepo.findByEmail(any(String.class))).thenReturn(n);
		when(anagraficarepo.findById(any(String.class))).thenReturn(Optional.of(entityana));
		when(linguerepo.findByChiaveCompostaIdAnagrafica(any(String.class))).thenReturn(ln);
		
		assertFalse(this.crud.trovaAccountByEmail(email).isPresent());
	}
	
	@Test
	public void trovaAccountByEmailfail2() {
		List<LinguaAccountEntity> ln = new ArrayList<>();
		ln.add(entityling1);
		ln.add(entityling2);
		List<AccountEntity> n = new ArrayList<>();
		n.add(entityacc);
		when(accountrepo.findByEmail(any(String.class))).thenReturn(n);
		when(anagraficarepo.findById(any(String.class))).thenReturn(Optional.empty());
		when(linguerepo.findByChiaveCompostaIdAnagrafica(any(String.class))).thenReturn(ln);
		
		assertFalse(this.crud.trovaAccountByEmail(email).isPresent());
	}
	
	@Test
	public void trovaAccountByEmail3() {
		List<LinguaAccountEntity> ln = new ArrayList<>();

		List<AccountEntity> n = new ArrayList<>();
		n.add(entityacc);
		when(accountrepo.findByEmail(any(String.class))).thenReturn(n);
		when(anagraficarepo.findById(any(String.class))).thenReturn(Optional.of(entityana));
		when(linguerepo.findByChiaveCompostaIdAnagrafica(any(String.class))).thenReturn(ln);
		
		assertTrue(this.crud.trovaAccountByEmail(email).isPresent());
	}
	
	@Test
	public void trovaAccountById() {
		List<LinguaAccountEntity> ln = new ArrayList<>();
		ln.add(entityling1);
		ln.add(entityling2);
		when(accountrepo.findById(any(String.class))).thenReturn(Optional.of(entityacc));
		when(anagraficarepo.findById(any(String.class))).thenReturn(Optional.of(entityana));
		when(linguerepo.findByChiaveCompostaIdAnagrafica(any(String.class))).thenReturn(ln);
		
		assertTrue(this.crud.trovaAccountById(ida).isPresent());
	}
	
	@Test
	public void trovaAccountByIdFail1() {
		List<LinguaAccountEntity> ln = new ArrayList<>();
		ln.add(entityling1);
		ln.add(entityling2);
		when(accountrepo.findById(any(String.class))).thenReturn(Optional.empty());
		when(anagraficarepo.findById(any(String.class))).thenReturn(Optional.of(entityana));
		when(linguerepo.findByChiaveCompostaIdAnagrafica(any(String.class))).thenReturn(ln);
		
		assertFalse(this.crud.trovaAccountById(ida).isPresent());
	}
	
	@Test
	public void trovaAccountByIdFail2() {
		List<LinguaAccountEntity> ln = new ArrayList<>();
		ln.add(entityling1);
		ln.add(entityling2);
		when(accountrepo.findById(any(String.class))).thenReturn(Optional.of(entityacc));
		when(anagraficarepo.findById(any(String.class))).thenReturn(Optional.empty());
		when(linguerepo.findByChiaveCompostaIdAnagrafica(any(String.class))).thenReturn(ln);
		
		assertFalse(this.crud.trovaAccountById(ida).isPresent());
	}

}
