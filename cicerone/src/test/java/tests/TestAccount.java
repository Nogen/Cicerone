package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.intuition.cicerone.autenticazione.Account;
import com.intuition.cicerone.autenticazione.Anagrafica;
import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.autenticazione.IAnagrafica;
import com.intuition.cicerone.autenticazione.utils.IdGenerator;
import com.intuition.cicerone.notificazione.IEventoCommand;

@RunWith(Parameterized.class)
public class TestAccount {
	private IAccount account;
	private String emailP;
	private String id;
	private String password;
	private IAnagrafica anagrafica;
	private String ruoloP;
	private IEventoCommand cmd;
	private static final String troppoLunga = "RFikXtqcgPCUBavdXFZdITxboGrrEeyBpSNlRPhCiKsKxgCJWJuZaaEMEeDMztaotZtNnAERpcbUkvbzKzZxTlaTvQVrTctjnNXPg";
	
	@Before
	public void setUp() {
		emailP = "aeonss@live.com";
		ruoloP = "Cicerone";
		id = IdGenerator.generateId();
		password = "Gn12345!";
		anagrafica = mock(Anagrafica.class);
		cmd = mock(IEventoCommand.class);
		this.account = new Account.BuilderAccount()
				.setEmail(emailP)
				.setAnagrafica(anagrafica)
				.setIdAccount(id)
				.setRuolo(ruoloP)
				.generaContatoreNotifica()
				.setPassword(password)
				.build();
				
	}
	
	@Parameter(0)
    public String email;
	
	@Parameter(1)
	public Long contatoreNotifiche;
	
	@Parameter(2)	
	public String idCasuale;
	
	@Parameter(3)
	public String ruolo;
	
	@Parameter(4)
	public String passwordB;
	
	@Parameter(5)	
	public IAnagrafica anagraficaB;
	


    // creates the test data
    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] { {"Value,@sbagliato.com", Long.valueOf(-50L), null, "ccicerone", null, null},  
        	{troppoLunga, Long.valueOf(-1L), null, "glfobetrotter", null, null},
        	{null, null, null, null, null, null}};
        return Arrays.asList(data);
    }
	
    @Test(expected = IllegalArgumentException.class)
	public void testBuilderid() {
		new Account.BuilderAccount().setIdAccount(idCasuale);
	}
    
    @Test(expected = IllegalArgumentException.class)
   	public void testBuilderRuolo() {
   		new Account.BuilderAccount().setRuolo(ruolo);
   	}
	
    @Test
   	public void testBuilderRuoloConSuccesso() {
   		IAccount a = new Account.BuilderAccount().setRuolo("Cicerone").build();
   		assertEquals("Cicerone", a.getRuolo());
   	}
    
	@Test(expected = IllegalArgumentException.class)
	public void testBuilderEmail() {
		new Account.BuilderAccount().setEmail(email);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBuilderPassword() {
		new Account.BuilderAccount().setPassword(passwordB);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBuilderHashPassword() {
		new Account.BuilderAccount().hashPassword(passwordB);
	}
	
	@Test
	public void testBuilderHashPasswordSuccess() {
		new Account.BuilderAccount().hashPassword(password);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBuilderAnagrafica() {
		new Account.BuilderAccount().setAnagrafica(anagraficaB);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBuilderContatoreNotifica() {
		new Account.BuilderAccount().setContatoreNotifica(contatoreNotifiche);
	}
	
	@Test
	public void testBuilderContatoreNotificaSuccess() {
		IAccount a = new Account.BuilderAccount().setContatoreNotifica(Long.valueOf(5)).build();
		assertEquals(Long.valueOf(5), a.getContatoreNotifiche());
	}
	
	
	
	@Test(expected = IllegalArgumentException.class)
	public void testAccountCambioPassword() {
		this.account.cambiaPassword(passwordB);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAccountCambioPasswordVecchia() {
		this.account.cambiaPassword(password);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAccountCambioPasswordNuova() {
		String passwordNuova = "Gn12345!";
		this.account.cambiaPassword(passwordNuova);
	}
	
	@Test
	public void testAccountCambioPasswordNuovaSuccess() {
		String passwordNuova = "10000Days@";
		this.account.cambiaPassword(passwordNuova);
		assertEquals(passwordNuova, this.account.getPassword());
	}
	
	@Test
	public void testAccountAggiungiNotifica() {
		this.account.aggiungiNotifica();
		assertEquals(Long.valueOf(1), this.account.getContatoreNotifiche());
	}
	
	@Test
	public void testAccountSottraiNotifica() {
		this.account.aggiungiNotifica();
		this.account.sottraiNotifica();
		assertEquals(Long.valueOf(0), this.account.getContatoreNotifiche());
	}
	
	@Test
	public void testAccountAzzeraNotifica() {
		this.account.azzeraNotifca();
		assertEquals(Long.valueOf(0), this.account.getContatoreNotifiche());
	}
	
	@Test
	public void testNotificaMe() {
		this.account.notificaMe(this.cmd);
		assertEquals(Long.valueOf(1), this.account.getContatoreNotifiche());
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void testAccountSottraiNotificaErrore() {
		this.account.sottraiNotifica();
	}
	
	@Test
	public void testAggiungiLingua() {
		doNothing().when(anagrafica).aggiungiLingueParlate(any(String.class));
		this.account.aggiungiLingua("Spagnolo");
		assertTrue(true);
	}
	
	@Test
	public void testModificaResidenza() {
		doNothing().when(anagrafica).modificaPaeseCitta(any(String.class));
		this.account.modificaResidenza("Bari, Bari, Italia");
		assertTrue(true);
	}
	
	@Test
	public void testModificaNumeroDiTelefono() {
		doNothing().when(anagrafica).modificaNumeroDiTelefono(any(String.class));
		this.account.modificaNumeroDiTelefono("0000000000");
		assertTrue(true);
	}
	
	@Test
	public void testAccountEqualsFalso() {
		assertFalse(this.account.equals(null));
	}
	
	@Test
	public void testAccountEqualsFalso2() {
		IAccount a = new Account.BuilderAccount()
				.generaIdAccount()
				.build();
		assertFalse(this.account.equals(a));
	}
	
	@Test
	public void testAccountEqualsVero() {
		IAccount a = new Account.BuilderAccount()
				.setIdAccount(id)
				.build();
		assertTrue(this.account.equals(a));
	}
	
	@Test
	public void testHashCode() {
		assertEquals(Objects.hashCode(id), this.account.hashCode());
	}
	
	@Test
	public void testgetRuolo() {
		assertEquals(ruoloP, this.account.getRuolo());
	}
	
	@Test
	public void testgetEmail() {
		assertEquals(emailP, this.account.getEmail());
	}
	
	@Test
	public void testgetAnagrafica() {
		assertEquals(anagrafica, this.account.getAnagrafica());
	}
	
	@Test
	public void testModificaRuolo() {
		this.account.modificaRuolo("cicerone");
		assertEquals("cicerone", this.account.getRuolo());
	}
	
	@Test
	public void testModificaRuolo2() {
		this.account.modificaRuolo("globetrotter");
		assertEquals("globetrotter", this.account.getRuolo());
	}
	
	@Test
	public void testModificaRuolo3() {
		this.account.modificaRuolo("admin");
		assertEquals("admin", this.account.getRuolo());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testModificaRuoloFail() {
		this.account.modificaRuolo("globetrotterr");
		
	}
}
