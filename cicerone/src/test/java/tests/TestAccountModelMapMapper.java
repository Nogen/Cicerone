package tests;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import com.intuition.cicerone.autenticazione.Account;
import com.intuition.cicerone.autenticazione.Anagrafica;
import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.autenticazione.IAnagrafica;
import com.intuition.cicerone.controllers.mappers.AccountModelMapMapper;
import com.intuition.cicerone.controllers.mappers.IMapper;

public class TestAccountModelMapMapper {
	private String nome;
	private String cognome;
	private String residenza;
	private String data;
	private String ntelefono;
	private String email;
	private String password;
	private IAnagrafica anagrafica;
	private IAccount account;
	private IMapper<ModelMap> mapper;
	
	@Before
	public void setUp() {
		email = "aeonss@live.com";
		password = "10000Days@";
		nome = "Antonio";
		cognome = "Rossi";
		residenza = "Bitonto, Bari, Italia";
		data = "1994-04-30";
		ntelefono = "0000000000";
		
		anagrafica = new Anagrafica.BuilderAnagrafica()
				.setCognome(cognome)
				.setNome(nome)
				.generaIdAnagrafica()
				.setPaeseCitta(residenza)
				.setSesso(Anagrafica.Sesso.UOMO)
				.setDataDiNascita(LocalDate.parse(data))
				.setNumeroDiTelefono(ntelefono)
				.build();
		
		account = new Account.BuilderAccount()
				.generaIdAccount()
				.setAnagrafica(anagrafica)
				.setEmail(email)
				.hashPassword(password)
				.build();
		
		mapper = new AccountModelMapMapper(account);
	}
	
	
	@Test
	public void noRuolo() {
		assertEquals("cicerone", mapper.converti().get("ruolo"));
	}
	
	@Test
	public void noLingue() {
		assertEquals("", mapper.converti().get("lingue"));
	}
	
	
	

}
