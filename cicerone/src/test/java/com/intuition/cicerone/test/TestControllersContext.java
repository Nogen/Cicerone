package com.intuition.cicerone.test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.intuition.cicerone.autenticazione.Account;
import com.intuition.cicerone.autenticazione.Anagrafica;
import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.autenticazione.IAnagrafica;
import com.intuition.cicerone.controllers.WebConstant;
import com.intuition.cicerone.controllers.WebControllerHome;
import com.intuition.cicerone.controllers.WebsiteIndice;
import com.intuition.cicerone.controllers.gestioneaccount.WebControllerModificaProfilo;
import com.intuition.cicerone.controllers.gestioneaccount.WebControllerVisualizzaProfilo;
import com.intuition.cicerone.controllers.login.WebControllerLogin;
import com.intuition.cicerone.controllers.registrazione.WebControllerSignup;
import com.intuition.cicerone.gestioneattivita.ErrorMessage;
import com.intuition.cicerone.gestioneattivita.attivita.Attivita;
import com.intuition.cicerone.gestioneattivita.attivita.Chiuso;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.gestioneattivita.attivita.IItinerario;
import com.intuition.cicerone.gestioneattivita.attivita.Itinerario;
import com.intuition.cicerone.gestioneattivita.richiesta.IRichiesta;
import com.intuition.cicerone.gestioneattivita.richiesta.Richiesta;
import com.intuition.cicerone.persistenza.AccountCrud;
import com.intuition.cicerone.persistenza.AttivitaCrud;
import com.intuition.cicerone.persistenza.FeedbackCrud;
import com.intuition.cicerone.persistenza.RichiestaCrud;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TestControllersContext {
	private String emailEsistente;
	private String emailNonEsistente;
	private String passwordEsistente;
	private String passwordNonEsistente;
	private IAccount accountdiprova;
	
	@Autowired 
	private AccountCrud crudAccount;
	
	@Autowired
	private AttivitaCrud attivitaCrud;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private WebControllerHome controllerHome;
	
	@Autowired
	private WebControllerModificaProfilo controllerModifica;
	
	@Autowired
	private WebControllerVisualizzaProfilo controllerProfilo;
	
	@Autowired
	private WebControllerLogin controllerLogin;
	
	@Autowired
	private WebControllerSignup controllerSignup;
	
	@Autowired
	private RichiestaCrud richiestaCrud;
	
	@Autowired
	private FeedbackCrud feedbackCrud;
	
	
	private String nome;
	private String cognome;
	private String nomeNonValido;
	private String cognomeNonValido;
	private boolean flagDelete;
	private String residenzaNonValida;
	private String genere;
	private String dataNonValida;
	private String residenza;
	private String data;
	private String ntelefono;
	private String ntelefonoNonValido;
	private IAccount accountEsistente;
	private String idAttivita;
	private Double coordinata;
	private LocalTime ora;
	private IAttivita attivita;
	private IItinerario itinerario;
	private IRichiesta richiesta;
	

	@Before
	public void setUp() {
		ora = LocalTime.of(23, 12);
		coordinata = 5D;
		idAttivita = "idTestTest";
		flagDelete = false;
		emailEsistente = "aeonss@live.com";
		emailNonEsistente =  "aaaaeonss@live.com";
		passwordEsistente = "Gn12345!";
		passwordNonEsistente = "passwordSbagliata";
		nome = "Antonio";
		nomeNonValido = "antonio";
		cognome = "Rossi";
		cognomeNonValido = "rossi";
		residenza = "Bitonto, Bari, Italia";
		residenzaNonValida = "";
		genere = "0";
		data = "1994-04-30";
		dataNonValida = "1994-4-30";
		ntelefono = "0000000000";
		ntelefonoNonValido = "000";
		
		IAnagrafica a  = new Anagrafica.BuilderAnagrafica()
				.generaIdAnagrafica()
				.setNome(nome)
				.setCognome(cognome)
				.setDataDiNascita(LocalDate.parse(data))
				.setLingueParlate(new HashSet<>())
				.setNumeroDiTelefono(ntelefono)
				.setPaeseCitta(residenza)
				.setSesso(Anagrafica.Sesso.UOMO)
				.build();
		IAnagrafica a1  = new Anagrafica.BuilderAnagrafica()
				.generaIdAnagrafica()
				.setNome(nome)
				.setCognome(cognome)
				.setDataDiNascita(LocalDate.parse(data))
				.setLingueParlate(new HashSet<>())
				.setNumeroDiTelefono(ntelefono)
				.setPaeseCitta(residenza)
				.setSesso(Anagrafica.Sesso.UOMO)
				.build();
		
		if (!crudAccount.esisteAccountByEmail(emailEsistente)) {
			IAccount accountEsistente = new Account.BuilderAccount()
					.generaIdAccount()
					.setEmail(emailEsistente)
					.hashPassword(passwordEsistente)
					.setAnagrafica(a)
					.generaContatoreNotifica()
					.setRuolo("Cicerone")
					.build();
			crudAccount.salvaAccount(accountEsistente);
		} if (accountEsistente == null && crudAccount.esisteAccountByEmail(emailEsistente)) {
			accountEsistente = crudAccount.trovaAccountByEmail(emailEsistente).get();
		}
		
		accountdiprova = new Account.BuilderAccount()
				.setIdAccount("idNonValido")
				.setAnagrafica(a1)
				.generaContatoreNotifica()
				.setRuolo("cicerone")
				.setEmail("prova@prova.com")
				.setPassword(passwordEsistente)
				.build();
		crudAccount.salvaAccount(accountdiprova);
		
		itinerario = new Itinerario.BuilderItinerario()
				.setLingua(new HashSet<>())
				.setIdItinerario(idAttivita)
				.setDescrizioneAttivita(residenza)
				.setRetribuzione(Itinerario.Retribuzione.RETRIBUITO, coordinata)
				.build();
		itinerario.setCoordinateIncontro(coordinata, coordinata);
		itinerario.setDataIncontro(LocalDate.parse(data));
		itinerario.setOraIncontro(ora);
		itinerario.setLuogo(residenza);
		attivita = new Attivita.BuilderAttivita()
				.setCreatore(accountEsistente)
				.setDataApertura(LocalDate.parse(data))
				.setIdAttivita(idAttivita)
				.setItinerario(itinerario)
				.setTitolo(residenza)
				.build();
		attivita.setPartecipanti(new HashSet<>());
		attivitaCrud.salvaAttivita(attivita);
		
		richiesta = new Richiesta.BuilderRichiesta()
				.setAccount(accountdiprova)
				.setAttivita(attivita)
				.setDataRichiesta(LocalDate.parse(data))
				.setIdRichiesta(idAttivita)
				.build();
		richiestaCrud.salvaRichiesta(richiesta);
	}
		
	@After
	public void postTest() {
		Optional<IAccount> opt = crudAccount.trovaAccountByEmail(emailNonEsistente);
		if (opt.isPresent()) {
			IAccount acc = opt.get();
			crudAccount.cancellaAccount(acc);
		}
		
		
		for (IRichiesta r : richiestaCrud.trovaRichiestaByAccount(accountdiprova)) {
			richiestaCrud.eliminaRichiesta(r.getIdRichiesta());
		}
		
		feedbackCrud.eliminaByAttivita(attivita.getIdAttivita());
		
		
		for (IAttivita a : attivitaCrud.trovaAttivitaByIdCreatore(accountEsistente.getIdAccount())) {
			attivitaCrud.eliminaAttivita(a);
		}
		if (flagDelete) {
			crudAccount.cancellaAccount(accountEsistente);
			accountEsistente = null;
		}
	}
	
	
	
	@Test
	public void LoggedHomeCicerone() throws Exception {
		this.mockMvc.perform(get(WebsiteIndice.LOGGED_HOME)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente))
    	.andExpect(view().name(WebsiteIndice.HOME_CICERONE));
	}
	
	
	@Test
	public void LoggedHomeGlobetrotter() throws Exception {
		accountEsistente.modificaRuolo("globetrotter");
		this.mockMvc.perform(get(WebsiteIndice.LOGGED_HOME)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente))
    	.andExpect(view().name(WebsiteIndice.HOME_GLOBETROTTER));
	}
	
	@Test
	public void LoggedHomeFail() throws Exception {
		this.mockMvc.perform(get(WebsiteIndice.LOGGED_HOME))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_LOGIN));
	}
	
	@Test
	public void modificaAttivitaPostSuccess() throws Exception {
		this.mockMvc.perform(post(WebsiteIndice.MODIFICA_ATTIVITAURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
    			.param("id", idAttivita)
    			.param("lat", "5")
    			.param("lon", "5")
    			.param("luogo", "bari")
    			.param("ore", "23" )
    			.param("minuti", "12")
    			.param("descrizioneAttivita", "Descrizione di prova"))
    	.andExpect(model().attribute(WebConstant.MESSAGE, ErrorMessage.ATTIVITA_MODIFICATA_CON_SUCCESSO));
	}
	
	@Test
	public void modificaAttivitaPostFail() throws Exception {
		accountEsistente.modificaRuolo("Globetrotter");
		this.mockMvc.perform(post(WebsiteIndice.MODIFICA_ATTIVITAURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
    			.param("id", idAttivita)
    			.param("lat", "5")
    			.param("lon", "5")
    			.param("luogo", "bari")
    			.param("ore", "23" )
    			.param("minuti", "12")
    			.param("descrizioneAttivita", "Descrizione di prova"))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_LOGGED_HOME));
	}
	
	@Test
	public void modificaAttivitaPostFail2() throws Exception {
		this.mockMvc.perform(post(WebsiteIndice.MODIFICA_ATTIVITAURL)
    			.param("id", idAttivita)
    			.param("lat", "5")
    			.param("lon", "5")
    			.param("luogo", "bari")
    			.param("ore", "23" )
    			.param("minuti", "12")
    			.param("descrizioneAttivita", "Descrizione di prova"))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_LOGIN));
	}
	
	
	@Test
	public void modificaAttivitaPostFailArgs() throws Exception {
		this.mockMvc.perform(post(WebsiteIndice.MODIFICA_ATTIVITAURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
    			.param("id", idAttivita)
    			.param("lat", "5")
    			.param("lon", "5")
    			.param("luogo", "bari%")
    			.param("ore", "23" )
    			.param("minuti", "12")
    			.param("descrizioneAttivita", "Descrizione di prova"))
    	.andExpect(model().attribute(WebConstant.MESSAGE, ErrorMessage.LUOGOERROR));
	}
	
	
	@Test
	public void modificaAttivitaPostFailArgs2() throws Exception {
		this.mockMvc.perform(post(WebsiteIndice.MODIFICA_ATTIVITAURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
    			.param("id", idAttivita)
    			.param("lat", "5")
    			.param("lon", "5")
    			.param("luogo", "bari%")
    			.param("ore", "23" )
    			.param("minuti", "600")
    			.param("descrizioneAttivita", "Descrizione di prova")
    			)
    	.andExpect(model().attribute(WebConstant.MESSAGE, containsString("MinuteOfHour")));
	}
	
	@Test
	public void modificaAttivitaPostFailArgs3() throws Exception {
		this.mockMvc.perform(post(WebsiteIndice.MODIFICA_ATTIVITAURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
    			.param("id", idAttivita)
    			.param("lat", "5")
    			.param("lon", "5")
    			.param("luogo", "bari%")
    			.param("ore", "233" )
    			.param("minuti", "12")
    			.param("descrizioneAttivita", "Descrizione di prova"))
    	.andExpect(model().attribute(WebConstant.MESSAGE, containsString("HourOfDay")));
	}
	
	@Test
	public void modificaAttivitaPostFailUnsupported() throws Exception {
		attivita.setStatoAttivita(new Chiuso());
		attivitaCrud.salvaAttivita(attivita);
		this.mockMvc.perform(post(WebsiteIndice.MODIFICA_ATTIVITAURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
    			.param("id", idAttivita)
    			.param("lat", "5")
    			.param("lon", "5")
    			.param("luogo", "bari%")
    			.param("ore", "23" )
    			.param("minuti", "12")
    			.param("descrizioneAttivita", "Descrizione di prova"))
    	.andExpect(model().attribute(WebConstant.MESSAGE, ErrorMessage.ATTIVITACHIUSA));
	}
	
	
	@Test
	public void modificaAttivitaPostFailCreatore() throws Exception {
		this.mockMvc.perform(post(WebsiteIndice.MODIFICA_ATTIVITAURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountdiprova)
    			.param("id", idAttivita)
    			.param("lat", "5")
    			.param("lon", "5")
    			.param("luogo", "bari")
    			.param("ore", "23" )
    			.param("minuti", "12")
    			.param("descrizioneAttivita", "Descrizione di prova"))
    	.andExpect(model().attribute(WebConstant.MESSAGE, ErrorMessage.ERROREMODIFICAREATTIVITA));
	}
	
	
	@Test
	public void modificaAttivitaPostFailAttivita() throws Exception {
		this.mockMvc.perform(post(WebsiteIndice.MODIFICA_ATTIVITAURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountdiprova)
    			.param("id", "idAttivitaErrata")
    			.param("lat", "5")
    			.param("lon", "5")
    			.param("luogo", "bari")
    			.param("ore", "23" )
    			.param("minuti", "12")
    			.param("descrizioneAttivita", "Descrizione di prova"))
    	.andExpect(model().attribute(WebConstant.MESSAGE, ErrorMessage.ERROREMODIFICAREATTIVITA_NON_ESISTENTE));
	}
	
	
	
	@Test
	public void modificaAttivitaGetSuccess() throws Exception {
		this.mockMvc.perform(get(WebsiteIndice.MODIFICA_ATTIVITAURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
    			.param("id", idAttivita))
    	.andExpect(model().attribute(WebConstant.MESSAGE, WebConstant.NULL_VALUE));
	}
	
	@Test
	public void modificaAttivitaGetFail() throws Exception {
		this.mockMvc.perform(get(WebsiteIndice.MODIFICA_ATTIVITAURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountdiprova)
    			.param("id", idAttivita))
    	.andExpect(model().attribute(WebConstant.MESSAGE,  ErrorMessage.ERROREMODIFICAREATTIVITA));
	}
	
	
	@Test
	public void modificaAttivitaGetFail1() throws Exception {
		this.mockMvc.perform(get(WebsiteIndice.MODIFICA_ATTIVITAURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
    			.param("id", "idAttivitaErrata"))
    	.andExpect(model().attribute(WebConstant.MESSAGE,  ErrorMessage.ERROREMODIFICAREATTIVITA_NON_ESISTENTE));
	}
	
	@Test
	public void modificaAttivitaGetFail2() throws Exception {
		accountEsistente.modificaRuolo("globetrotter");
		this.mockMvc.perform(get(WebsiteIndice.MODIFICA_ATTIVITAURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
    			.param("id", "idAttivitaErrata"))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_LOGGED_HOME));
	}
	
	@Test
	public void modificaAttivitaGetFail3() throws Exception {
		this.mockMvc.perform(get(WebsiteIndice.MODIFICA_ATTIVITAURL)
    			.param("id", "idAttivitaErrata"))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_LOGIN));
	}
	
	@Test
	public void IscrivitiSuccess() throws Exception{
		richiesta.rifiuta();
		richiestaCrud.salvaRichiesta(richiesta);
		accountdiprova.modificaRuolo("globetrotter");
		this.mockMvc.perform(get(WebsiteIndice.ISCRIVITIURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountdiprova)
    			.param("id", idAttivita))
    	.andExpect(model().attribute(WebConstant.MESSAGE, ErrorMessage.ISCRITTO_CON_SUCESSO));
	}
	
	@Test
	public void IscrivitiFailGiaIscritto() throws Exception{
		accountdiprova.modificaRuolo("globetrotter");
		this.mockMvc.perform(get(WebsiteIndice.ISCRIVITIURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountdiprova)
    			.param("id", idAttivita))
    	.andExpect(model().attribute(WebConstant.MESSAGE, ErrorMessage.ERROREATTIVITAISCRITTO));
	}
	
	@Test
	public void IscrivitiFailidAttivitaSbaglato() throws Exception{
		accountdiprova.modificaRuolo("globetrotter");
		this.mockMvc.perform(get(WebsiteIndice.ISCRIVITIURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountdiprova)
    			.param("id", "idAttivitaErrato"))
    	.andExpect(model().attribute(WebConstant.MESSAGE, ErrorMessage.ERROREATTIVITANONESISTENTE));
	}
	
	
	
	@Test
	public void IscrivitiFail1() throws Exception{
		accountEsistente.modificaRuolo("globetrotter");
		this.mockMvc.perform(get(WebsiteIndice.ISCRIVITIURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
    			.param("id", idAttivita))
    	.andExpect(model().attribute(WebConstant.MESSAGE, ErrorMessage.ERROREATTIVITAISCRITTO));
	}
	
	@Test
	public void IscrivitiFail2() throws Exception{
		this.mockMvc.perform(get(WebsiteIndice.ISCRIVITIURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
    			.param("id", idAttivita))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_LOGGED_HOME));
	}
	
	@Test
	public void IscrivitiFail3() throws Exception{
		this.mockMvc.perform(get(WebsiteIndice.ISCRIVITIURL)
    			.param("id", idAttivita))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_LOGIN));
	}
	
	
	@Test
	public void PartecipantiSuccess() throws Exception{
		this.mockMvc.perform(get(WebsiteIndice.PARTECIPANTIURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
    			.param("id", idAttivita))
    	.andExpect(view().name(WebsiteIndice.CPANEL_PARTECIPANTIJSP));
	}
	
	@Test
	public void PartecipantiFail1() throws Exception{
		this.mockMvc.perform(get(WebsiteIndice.PARTECIPANTIURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountdiprova)
    			.param("id", idAttivita))
    	.andExpect(model().attribute(WebConstant.MESSAGE, ErrorMessage.ERROREVISUALIZZAREATTIVITA));
	}
	
	@Test
	public void PartecipantiFail2() throws Exception{
		this.mockMvc.perform(get(WebsiteIndice.PARTECIPANTIURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
    			.param("id", "sbagliata"))
    	.andExpect(model().attribute(WebConstant.MESSAGE, ErrorMessage.ERROREVISUALIZZAREPARTECIPANTIATTIVITA_NON_ESISTENTE));
	}
	
	@Test
	public void PartecipantiSFail3() throws Exception{
		accountEsistente.modificaRuolo("Globetrotter");
		this.mockMvc.perform(get(WebsiteIndice.PARTECIPANTIURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
    			.param("id", idAttivita))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_LOGGED_HOME));
	}
	
	@Test
	public void PartecipantiSFail4() throws Exception{
		this.mockMvc.perform(get(WebsiteIndice.PARTECIPANTIURL)
    			.param("id", idAttivita))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_LOGIN));
	}
	
	
	@Test
	public void rifiutaRichiesta() throws Exception{
		this.mockMvc.perform(get(WebsiteIndice.RIFIUTAURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
    			.param("id", idAttivita))
    	.andExpect(view().name("redirect:/partecipanti?id=" + idAttivita));
	}
	
	@Test
	public void rifiutaRichiestaFail1() throws Exception{
		this.mockMvc.perform(get(WebsiteIndice.RIFIUTAURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountdiprova)
    			.param("id", idAttivita))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_ATTIVITA_CREATE));
	}
	
	@Test
	public void rifiutaRichiestaFail2() throws Exception{
		this.mockMvc.perform(get(WebsiteIndice.RIFIUTAURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
    			.param("id", "sbagliata"))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_ATTIVITA_CREATE));
	}
	
	@Test
	public void rifiutaRichiestaFail3() throws Exception{
		accountEsistente.modificaRuolo("Globetrotter");
		this.mockMvc.perform(get(WebsiteIndice.RIFIUTAURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
    			.param("id", idAttivita))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_ATTIVITA_CREATE));
	}
	
	
	@Test
	public void rifiutaRichiestaFail4() throws Exception{
		
		this.mockMvc.perform(get(WebsiteIndice.RIFIUTAURL)
    			.param("id", idAttivita))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_LOGIN));
	}
	
	@Test
	public void accettaRichiesta() throws Exception{
		this.mockMvc.perform(get(WebsiteIndice.ACCETTAURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
    			.param("id", idAttivita))
    	.andExpect(view().name("redirect:/partecipanti?id=" + idAttivita));
	}
	
	@Test
	public void accettaRichiestaFail1() throws Exception{
		this.mockMvc.perform(get(WebsiteIndice.ACCETTAURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountdiprova)
    			.param("id", idAttivita))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_ATTIVITA_CREATE));
	}
	
	@Test
	public void accettaRichiestaFail2() throws Exception{
		this.mockMvc.perform(get(WebsiteIndice.ACCETTAURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
    			.param("id", "sbagliata"))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_ATTIVITA_CREATE));
	}
	
	@Test
	public void accettaRichiestaFail3() throws Exception{
		accountEsistente.modificaRuolo("Globetrotter");
		this.mockMvc.perform(get(WebsiteIndice.ACCETTAURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
    			.param("id", idAttivita))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_ATTIVITA_CREATE));
	}
	
	
	@Test
	public void accettaRichiestaFail4() throws Exception{
		
		this.mockMvc.perform(get(WebsiteIndice.ACCETTAURL)
    			.param("id", idAttivita))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_LOGIN));
	}
	
	@Test
	public void CancellaAttivita() throws Exception{
		this.mockMvc.perform(get(WebsiteIndice.CANCELLA_ATTIVITAURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
    			.param("id", idAttivita))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_ATTIVITA_CREATE));
	}
	
	@Test
	public void CancellaAttivitaAlternativoProprietario() throws Exception{
		this.mockMvc.perform(get(WebsiteIndice.CANCELLA_ATTIVITAURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountdiprova)
    			.param("id", idAttivita))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_ATTIVITA_CREATE));
	}
	
	@Test
	public void CancellaAttivitaAlternativoNoAttivita() throws Exception{
		this.mockMvc.perform(get(WebsiteIndice.CANCELLA_ATTIVITAURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
    			.param("id", "errato"))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_ATTIVITA_CREATE));
	}
	
	@Test
	public void CancellaAttivita1() throws Exception{
		accountEsistente.modificaRuolo("Globetrotter");
		this.mockMvc.perform(get(WebsiteIndice.CANCELLA_ATTIVITAURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
    			.param("id", idAttivita))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_LOGGED_HOME));
	}
	
	@Test
	public void CancellaAttivita2() throws Exception{
		accountEsistente.modificaRuolo("Globetrotter");
		this.mockMvc.perform(get(WebsiteIndice.CANCELLA_ATTIVITAURL)
    			.param("id", idAttivita))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_LOGIN));
	}
	
	
	@Test
	public void VisualizzaDettagli() throws Exception{
		this.mockMvc.perform(get(WebsiteIndice.VISUALIZZA_ATTIVITAURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
    			.param("id", idAttivita))
    	.andExpect(view().name(WebsiteIndice.VISUALIZZA_ATTIVITAJSP));
	}
	
	@Test
	public void VisualizzaDettagli1() throws Exception{
		this.mockMvc.perform(get(WebsiteIndice.VISUALIZZA_ATTIVITAURL)
    			.param("id", idAttivita))
    	.andExpect(view().name(WebsiteIndice.VISUALIZZA_ATTIVITAJSP));
	}
	
	
	@Test
	public void VisualizzaDettagli2() throws Exception{
		this.mockMvc.perform(get(WebsiteIndice.VISUALIZZA_ATTIVITAURL)
    			.param("id", "sbagliato"))
    	.andExpect(model().attribute(WebConstant.MESSAGE, ErrorMessage.ERRORE_L_ATTIVITA_NON_ESISTE));
	}
	
	@Test
	public void trovaAttivitaSuccess() throws Exception{
		accountEsistente.modificaRuolo("Globetrotter");
		this.mockMvc.perform(get(WebsiteIndice.TROVA_ATTIVITAURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente))
    	.andExpect(view().name(WebsiteIndice.GPANEL_TROVA_ATTIVITAJSP));
	}
	
	
	@Test
	public void trovaAttivitaSuccess1() throws Exception{
		accountEsistente.modificaRuolo("Globetrotter");
		this.mockMvc.perform(get(WebsiteIndice.TROVA_ATTIVITAURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
    			.param("byKey", "prova parola")
    			.param("byLuogo", "Bari")
    			.param("byLingua", "italiano")
    			.param("byDate", data)
    			.param("orderBy", "titolo"))
    	.andExpect(view().name(WebsiteIndice.GPANEL_TROVA_ATTIVITAJSP));
	}
	
	@Test
	public void trovaAttivitaInvalidData() throws Exception{
		accountEsistente.modificaRuolo("Globetrotter");
		this.mockMvc.perform(get(WebsiteIndice.TROVA_ATTIVITAURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
    			.param("byKey", "prova parola")
    			.param("byLuogo", "Bari")
    			.param("byLingua", "italiano")
    			.param("byDate", dataNonValida)
    			.param("orderBy", "titolo"))
    	.andExpect(model().attribute("byDate", ErrorMessage.DATAERROR3));
	}
	
	@Test
	public void trovaAttivitaFail1() throws Exception{
		this.mockMvc.perform(get(WebsiteIndice.TROVA_ATTIVITAURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_LOGGED_HOME));
	}
	
	@Test
	public void trovaAttivitaFail2() throws Exception{
		this.mockMvc.perform(get(WebsiteIndice.TROVA_ATTIVITAURL))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_LOGIN));
	}
	
	
	@Test
	public void gestioneRichieste() throws Exception{
		accountEsistente.modificaRuolo("Globetrotter");
		this.mockMvc.perform(get(WebsiteIndice.GESTIONE_RICHIESTEURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente))
    	.andExpect(view().name(WebsiteIndice.GPANEL_GESTIONE_RICHIESTEJSP));
	}
	
	@Test
	public void gestioneRichiestePost() throws Exception{
		accountEsistente.modificaRuolo("Globetrotter");
		this.mockMvc.perform(post(WebsiteIndice.GESTIONE_RICHIESTEURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
    			.param("commento", "commento") 
    			.param("valutazione", "5") 
    			.param("idAttivita", attivita.getIdAttivita()))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_GESTIONE_RICHIESTE));
	}
	
	@Test
	public void gestioneRichiestePostFail2() throws Exception{
		accountEsistente.modificaRuolo("Globetrotter");
		this.mockMvc.perform(post(WebsiteIndice.GESTIONE_RICHIESTEURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
    			.param("commento", "commento") 
    			.param("valutazione", "5") 
    			.param("idAttivita", "idAttivita"))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_LOGGED_HOME));
	}
	
	@Test
	public void gestioneRichiestePostFail() throws Exception{
		accountEsistente.modificaRuolo("Globetrotter");
		this.mockMvc.perform(post(WebsiteIndice.GESTIONE_RICHIESTEURL)
    			
    			.param("commento", "commento") 
    			.param("valutazione", "5") 
    			.param("idAttivita", "idAttivita"))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_LOGIN));
	}
	
	@Test
	public void gestioneRichieste2() throws Exception{
		this.mockMvc.perform(get(WebsiteIndice.GESTIONE_RICHIESTEURL)
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_LOGGED_HOME));
	}
	
	@Test
	public void gestioneRichieste3() throws Exception{
		this.mockMvc.perform(get(WebsiteIndice.GESTIONE_RICHIESTEURL))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_LOGIN));
	}
	
	@Test
	public void creaAttivitaVista() throws Exception{
		this.mockMvc.perform(get("/creaAttivita")
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente))
    	.andExpect(view().name(WebsiteIndice.CREA_ATTIVITAJSP));
	}
	
	@Test
	public void creaAttivitaVistaFail1() throws Exception{
		this.mockMvc.perform(get("/creaAttivita"))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_LOGIN));
	}
	
	@Test
	public void creaAttivitaVistaFail2() throws Exception{
		accountEsistente.modificaRuolo("Globetrotter");
		this.mockMvc.perform(get("/creaAttivita")
				.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_LOGGED_HOME));
	}
	
	@Test
	public void visualizzaAttivitaCreate() throws Exception{
		this.mockMvc.perform(get(WebsiteIndice.ATTIVITA_CREATEURL)
				.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente))
    	.andExpect(view().name(WebsiteIndice.ATTIVITA_CREATEJSP));
	}
	
	@Test
	public void visualizzaAttivitaCreate2() throws Exception{
		accountEsistente.modificaRuolo("Globetrotter");
		this.mockMvc.perform(get(WebsiteIndice.ATTIVITA_CREATEURL)
				.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_LOGGED_HOME));
	}
	
	@Test
	public void visualizzaAttivitaCreate3() throws Exception{
		this.mockMvc.perform(get(WebsiteIndice.ATTIVITA_CREATEURL))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_LOGIN));
	}
	
	
	@Test
	public void visualizzaAttivitaCreateSuccess() throws Exception{
		this.mockMvc.perform(get(WebsiteIndice.ATTIVITA_CREATEURL)
				.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
				.param("byKey", "prova parola")
				.param("byLuogo", "Bari")
				.param("byLingua", "italiano")
				.param("byDate", data)
				.param("orderBy", "titolo"))
    	.andExpect(view().name(WebsiteIndice.ATTIVITA_CREATEJSP));
	}
	
	@Test
	public void visualizzaAttivitaCreateFail() throws Exception{
		this.mockMvc.perform(get(WebsiteIndice.ATTIVITA_CREATEURL)
				.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
				.param("byKey", "prova parola")
				.param("byLuogo", "Bari")
				.param("byLingua", "italiano")
				.param("byDate", dataNonValida)
				.param("orderBy", "titolo"))
    	.andExpect(model().attribute("byDate", ErrorMessage.DATAERROR3));
	}
	
	
	
	@Test
	public void creaAttivitaPost()  throws Exception {
		this.mockMvc.perform(post("/creaAttivita")
				.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
				.param("descrizione", attivita.getDescrizioneAttivita())
				.param("retribuzione", attivita.getRetribuzione().toString()) 
				.param("valoreRetribuzione", attivita.getValoreRetribuzione().toString())
				.param("lingue", attivita.getLingua().toString())
				.param("lat", attivita.getxIncontro().toString())
				.param("lon", attivita.getyIncontro().toString())
				.param("luogo", attivita.getLuogo())
				.param("data", attivita.getDataIncontro().toString())
				.param("ore", attivita.getOraIncontro().getHour() + "")
				.param("minuti", attivita.getOraIncontro().getMinute() + "")
				.param("titolo", attivita.getTitolo()))
    	.andExpect(model().attribute(WebConstant.MESSAGE, ErrorMessage.ATTIVITA_CREATA_CON_SUCCESSO));
	}
	
	@Test
	public void creaAttivitaPostFail()  throws Exception {
		this.mockMvc.perform(post("/creaAttivita")
				.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
				.param("descrizione", attivita.getDescrizioneAttivita())
				.param("retribuzione", attivita.getRetribuzione().toString()) 
				.param("valoreRetribuzione", attivita.getValoreRetribuzione().toString())
				.param("lingue", attivita.getLingua().toString())
				.param("lat", attivita.getxIncontro().toString())
				.param("lon", attivita.getyIncontro().toString())
				.param("luogo", attivita.getLuogo())
				.param("data", dataNonValida)
				.param("ore", attivita.getOraIncontro().getHour() + "")
				.param("minuti", attivita.getOraIncontro().getMinute() + "")
				.param("titolo", attivita.getTitolo()))
    	.andExpect(model().attribute(WebConstant.TYPE, WebConstant.TYPE_DANGER));
	}
	
	@Test
	public void creaAttivitaPostFail2()  throws Exception {
		this.mockMvc.perform(post("/creaAttivita")
				.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
				.param("descrizione", attivita.getDescrizioneAttivita())
				.param("retribuzione", attivita.getRetribuzione().toString()) 
				.param("valoreRetribuzione", -5 + "")
				.param("lingue", attivita.getLingua().toString())
				.param("lat", attivita.getxIncontro().toString())
				.param("lon", attivita.getyIncontro().toString())
				.param("luogo", attivita.getLuogo())
				.param("data", attivita.getDataIncontro().toString())
				.param("ore", attivita.getOraIncontro().getHour() + "")
				.param("minuti", attivita.getOraIncontro().getMinute() + "")
				.param("titolo", attivita.getTitolo()))
    	.andExpect(model().attribute(WebConstant.TYPE, WebConstant.TYPE_DANGER));
	}
	
	@Test
	public void creaAttivitaPostNoLogin()  throws Exception {
		this.mockMvc.perform(post("/creaAttivita")
				.param("descrizione", attivita.getDescrizioneAttivita())
				.param("retribuzione", attivita.getRetribuzione().toString()) 
				.param("valoreRetribuzione", attivita.getValoreRetribuzione().toString())
				.param("lingue", attivita.getLingua().toString())
				.param("lat", attivita.getxIncontro().toString())
				.param("lon", attivita.getyIncontro().toString())
				.param("luogo", attivita.getLuogo())
				.param("data", attivita.getDataIncontro().toString())
				.param("ore", attivita.getOraIncontro().getHour() + "")
				.param("minuti", attivita.getOraIncontro().getMinute() + "")
				.param("titolo", attivita.getTitolo()))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_LOGIN));
	}
	
	@Test
	public void creaAttivitaPostWrongRole()  throws Exception {
		accountEsistente.modificaRuolo("Globetrotter");
		this.mockMvc.perform(post("/creaAttivita")
				.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente)
				.param("descrizione", attivita.getDescrizioneAttivita())
				.param("retribuzione", attivita.getRetribuzione().toString()) 
				.param("valoreRetribuzione", attivita.getValoreRetribuzione().toString())
				.param("lingue", attivita.getLingua().toString())
				.param("lat", attivita.getxIncontro().toString())
				.param("lon", attivita.getyIncontro().toString())
				.param("luogo", attivita.getLuogo())
				.param("data", attivita.getDataIncontro().toString())
				.param("ore", attivita.getOraIncontro().getHour() + "")
				.param("minuti", attivita.getOraIncontro().getMinute() + "")
				.param("titolo", attivita.getTitolo()))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_LOGGED_HOME));
	}
	
	
	
	
    @Test
    public void contexLoadsHome() throws Exception {
    	assertThat(controllerHome).isNotNull();
    }
    
    @Test
    public void contexLoadsModifica() throws Exception {
    	assertThat(controllerModifica).isNotNull();
    }
    
    @Test
    public void contexLoadsProfilo() throws Exception {
    	assertThat(controllerProfilo).isNotNull();
    }
    
    @Test
    public void contexLoadsLogin() throws Exception {
    	assertThat(controllerLogin).isNotNull();
    }
    
    @Test
    public void contexLoadsSignup() throws Exception {
    	assertThat(controllerSignup).isNotNull();
    }
    
    
    @Test
    public void testHomeControllerHome()  throws Exception {
    	this.mockMvc.perform(get("/"))
    	.andExpect(status().isOk());
    }
    
    @Test
    public void testHomeControllerHomeEmail()  throws Exception {
    	this.mockMvc.perform(post("/")
    			.param("nome", nome)
    			.param("cognome", cognome)
    			.param("email", emailEsistente)
    			.param("descrizione", "Email di test"))
    	.andExpect(view().name("redirect:/"));
    }
    
    
    @Test
    public void testHomeControllerChooseCicerone()  throws Exception {
    	this.mockMvc.perform(get("/chooseCicerone"))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_LOGIN));
    }
    
    @Test
    public void testHomeControllerGlobetrotter()  throws Exception {
    	this.mockMvc.perform(get("/chooseGlobetrotter"))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_LOGIN));
    }
    
    @Test
    public void testHomeControllerChooseCiceroneLoggato()  throws Exception {
    	this.mockMvc.perform(get("/chooseCicerone")
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_LOGGED_HOME));
    }
    
    @Test
    public void testHomeControllerChooseGlobetrotterLoggato()  throws Exception {
    	this.mockMvc.perform(get("/chooseGlobetrotter")
    			.sessionAttr(WebConstant.USER_ACCOUNT, accountEsistente))
    	.andExpect(view().name(WebsiteIndice.REDIRECT_LOGGED_HOME));
    }
    
    @Test
    public void testHomeControllerProfiloErrore()  throws Exception {
    	this.mockMvc.perform(get("/profilo"))
    	.andExpect(model().attribute("message", ErrorMessage.UTENTEERROR));
    }
    
    @Test
    public void testHomeControllerIdProfilo()  throws Exception {
    	this.mockMvc.perform(get("/profilo?id=" + accountEsistente.getIdAccount())).
    	andExpect(model().attribute("email", accountEsistente.getEmail()));
    }
    
    @Test
    public void testHomeControllerLoginGet()  throws Exception {
    	this.mockMvc.perform(get("/login"))
    	.andExpect(model().attribute("hide", "hidden"));
    }
    
    @Test
    public void testHomeControllerLoginGetLogged()  throws Exception {
    	this.mockMvc.perform(get("/login")
    			.sessionAttr("userAccount", accountEsistente))
    	.andExpect(view().name("choose"));
    }
    
    @Test
    public void testHomeControllerLogout()  throws Exception {
    	this.mockMvc.perform(get("/logout"))
    	.andExpect(view().name("login"));
    }
    
    @Test
    public void testHomeControllerLoginPost()  throws Exception {
    	this.mockMvc.perform(post("/login")
    	.param("email", emailEsistente)
    	.param("password", passwordEsistente))
    	.andExpect(view().name("choose"));
    }
    
    @Test
    public void testHomeControllerLoginPostErroreEmail()  throws Exception {
    	this.mockMvc.perform(post("/login")
    	.param("email", emailNonEsistente)
    	.param("password", passwordEsistente))
    	.andExpect(model().attribute("message", ErrorMessage.ACCOUNTERROR4));
    }
    
    @Test
    public void testHomeControllerLoginPostErrorePassword()  throws Exception {
    	this.mockMvc.perform(post("/login")
    	.param("email", emailEsistente)
    	.param("password", passwordNonEsistente))
    	.andExpect(model().attribute("message", ErrorMessage.PSWERROR));
    }
    
    @Test
    public void testHomeControllerSignupGet()  throws Exception {
    	this.mockMvc.perform(get("/signup"))
    	.andExpect(view().name("signup"));
    }
    
    @Test
    public void testHomeControllerSignupGetLogged()  throws Exception {
    	this.mockMvc.perform(get("/signup")
    			.sessionAttr("userAccount", accountEsistente))
    	.andExpect(view().name("choose"));
    }
    
    @Test
    public void testHomeControllerSignupPostLogged()  throws Exception {
    	this.mockMvc.perform(post("/signup")
    			.sessionAttr("userAccount", accountEsistente)
    			.param("email", emailEsistente)
    	    	.param("password", passwordEsistente)
    	    	.param("nome", nome)
    	    	.param("cognome", cognome)
    	    	.param("residenza", residenza)
    	    	.param("genere", genere)
    	    	.param("bday", data)
    	    	.param("ntelefono", ntelefono))
    	.andExpect(view().name("choose"));
    }
    
    
    @Test
    public void testHomeControllerSignupPostSuccess()  throws Exception {
    	this.mockMvc.perform(post("/signup")
    	.param("email", emailNonEsistente)
    	.param("password", passwordEsistente)
    	.param("nome", nome)
    	.param("cognome", cognome)
    	.param("residenza", residenza)
    	.param("genere", genere)
    	.param("bday", data)
    	.param("ntelefono", ntelefono))
    	.andExpect(model().attribute("message", ErrorMessage.MESSAGGIOWELCOME));
    }
    
    @Test
    public void testHomeControllerSignupPostEmail()  throws Exception {
    	this.mockMvc.perform(post("/signup")
    	.param("email", emailEsistente)
    	.param("password", passwordEsistente)
    	.param("nome", nome)
    	.param("cognome", cognome)
    	.param("residenza", residenza)
    	.param("genere", genere)
    	.param("bday", data)
    	.param("ntelefono", ntelefono))
    	.andExpect(model().attribute("message", ErrorMessage.EMAILERROR2));
    }
    
    @Test
    public void testHomeControllerSignupPostNome()  throws Exception {
    	this.mockMvc.perform(post("/signup")
    	.param("email", emailNonEsistente)
    	.param("password", passwordEsistente)
    	.param("nome", nomeNonValido)
    	.param("cognome", cognome)
    	.param("residenza", residenza)
    	.param("genere", genere)
    	.param("bday", data)
    	.param("ntelefono", ntelefono))
    	.andExpect(model().attribute("message", ErrorMessage.NOMEERROR));
    }
    
    
    @Test
    public void testHomeControllerSignupPostCognome()  throws Exception {
    	this.mockMvc.perform(post("/signup")
    	.param("email", emailNonEsistente)
    	.param("password", passwordEsistente)
    	.param("nome", nome)
    	.param("cognome", cognomeNonValido)
    	.param("residenza", residenza)
    	.param("genere", genere)
    	.param("bday", data)
    	.param("ntelefono", ntelefono))
    	.andExpect(model().attribute("message", ErrorMessage.COGNOMEERROR));
    }
    
    
    @Test
    public void testHomeControllerSignupPostResidenza()  throws Exception {
    	this.mockMvc.perform(post("/signup")
    	.param("email", emailNonEsistente)
    	.param("password", passwordEsistente)
    	.param("nome", nome)
    	.param("cognome", cognome)
    	.param("residenza", residenzaNonValida)
    	.param("genere", genere)
    	.param("bday", data)
    	.param("ntelefono", ntelefono))
    	.andExpect(model().attribute("message", ErrorMessage.RESIDENZAERROR));
    }
    
    
    @Test
    public void testHomeControllerSignupPosDataFormat()  throws Exception {
    	this.mockMvc.perform(post("/signup")
    	.param("email", emailNonEsistente)
    	.param("password", passwordEsistente)
    	.param("nome", nome)
    	.param("cognome", cognome)
    	.param("residenza", residenza)
    	.param("genere", genere)
    	.param("bday", dataNonValida)
    	.param("ntelefono", ntelefono))
    	.andExpect(model().attributeExists("message"));
    }
    
    @Test
    public void testHomeControllerSignupPosData()  throws Exception {
    	this.mockMvc.perform(post("/signup")
    	.param("email", emailNonEsistente)
    	.param("password", passwordEsistente)
    	.param("nome", nome)
    	.param("cognome", cognome)
    	.param("residenza", residenza)
    	.param("genere", genere)
    	.param("bday", LocalDate.of(2100, 3, 24).toString())
    	.param("ntelefono", ntelefono))
    	.andExpect(model().attribute("message", ErrorMessage.DATAERROR));
    }
    
    
    @Test
    public void testHomeControllerSignupPosTelefono()  throws Exception {
    	this.mockMvc.perform(post("/signup")
    	.param("email", emailNonEsistente)
    	.param("password", passwordEsistente)
    	.param("nome", nome)
    	.param("cognome", cognome)
    	.param("residenza", residenza)
    	.param("genere", genere)
    	.param("bday", data)
    	.param("ntelefono", ntelefonoNonValido))
    	.andExpect(model().attribute("message", ErrorMessage.TELEFONOERROR));
    }
    
    @Test
    public void testHomeControllerModificaGetNotLogged()  throws Exception {
    	this.mockMvc.perform(get("/modificaProfilo"))
    	.andExpect(model().attribute("showOnLogin", "hidden"));
    }
    
    @Test
    public void testHomeControllerModificaGetLogged()  throws Exception {
    	this.mockMvc.perform(get("/modificaProfilo")
    			.sessionAttr("userAccount", accountEsistente))
    	.andExpect(model().attribute("email", containsString(accountEsistente.getEmail())));
    }
    
    
    @Test
    public void testHomeControllerModificaPostNonLoggato()  throws Exception {
    	this.mockMvc.perform(post("/modificaProfilo")
    	.param("password", "")
    	.param("nome", nome)
    	.param("cognome", cognome)
    	.param("residenza", residenza)
    	.param("bday", data)
    	.param("ntelefono", ntelefono)
    	.param("lingue", "#Italiano"))
    	.andExpect(model().attribute("message", ErrorMessage.PROFILOERROR));
    }
    
    @Test
    public void testHomeControllerModificaPostNome()  throws Exception {
    	this.mockMvc.perform(post("/modificaProfilo")
    	.sessionAttr("userAccount", accountEsistente)
    	.param("password", "")
    	.param("nome", nomeNonValido)
    	.param("cognome", cognome)
    	.param("residenza", residenza)
    	.param("bday", data)
    	.param("ntelefono", ntelefono)
    	.param("lingue", "#Italiano"))
    	.andExpect(model().attribute("message", ErrorMessage.NOMEERROR));
    }
    
    @Test
    public void testHomeControllerModificaPostCognome()  throws Exception {
    	this.mockMvc.perform(post("/modificaProfilo")
    	.sessionAttr("userAccount", accountEsistente)
    	.param("password", "")
    	.param("nome", nome)
    	.param("cognome", cognomeNonValido)
    	.param("residenza", residenza)
    	.param("bday", data)
    	.param("ntelefono", ntelefono)
    	.param("lingue", "#Italiano"))
    	.andExpect(model().attribute("message", ErrorMessage.COGNOMEERROR));
    }
    
    @Test
    public void testHomeControllerModificaPostResidenza()  throws Exception {
    	this.mockMvc.perform(post("/modificaProfilo")
    	.sessionAttr("userAccount", accountEsistente)
    	.param("password", "")
    	.param("nome", nome)
    	.param("cognome", cognome)
    	.param("residenza", residenzaNonValida)
    	.param("bday", data)
    	.param("ntelefono", ntelefono)
    	.param("lingue", "#Italiano"))
    	.andExpect(model().attribute("message", ErrorMessage.RESIDENZAERROR));
    }
    
    @Test
    public void testHomeControllerModificaPostDataForm()  throws Exception {
    	this.mockMvc.perform(post("/modificaProfilo")
    	.sessionAttr("userAccount", accountEsistente)
    	.param("password", "")
    	.param("nome", nome)
    	.param("cognome", cognome)
    	.param("residenza", residenza)
    	.param("bday", dataNonValida)
    	.param("ntelefono", ntelefono)
    	.param("lingue", "#Italiano"))
    	.andExpect(model().attributeExists("message"));
    }
    
    @Test
    public void testHomeControllerModificaPostData()  throws Exception {
    	this.mockMvc.perform(post("/modificaProfilo")
    	.sessionAttr("userAccount", accountEsistente)
    	.param("password", "")
    	.param("nome", nome)
    	.param("cognome", cognome)
    	.param("residenza", residenza)
    	.param("bday", LocalDate.of(2100, 3, 24).toString())
    	.param("ntelefono", ntelefono)
    	.param("lingue", "#Italiano"))
    	.andExpect(model().attribute("message", ErrorMessage.DATAERROR));
    }
    
    @Test
    public void testHomeControllerModificaPassword()  throws Exception {
    	this.mockMvc.perform(post("/modificaProfilo")
    	.sessionAttr("userAccount", accountEsistente)
    	.param("password", passwordEsistente)
    	.param("nome", nome)
    	.param("cognome", cognome)
    	.param("residenza", residenza)
    	.param("bday", data)
    	.param("ntelefono", ntelefono)
    	.param("lingue", "#Italiano"))
    	.andExpect(model().attribute("message", ErrorMessage.PSWERROR2));
    }
    
    
    @Test
    public void testHomeControllerModificaPasswordSuccess()  throws Exception {
    	flagDelete = true;
    	this.mockMvc.perform(post("/modificaProfilo")
    	.sessionAttr("userAccount", accountEsistente)
    	.param("password", "10000Days@")
    	.param("nome", nome)
    	.param("cognome", cognome)
    	.param("residenza", residenza)
    	.param("bday", data)
    	.param("ntelefono", ntelefono)
    	.param("lingue", "#Italiano"))
    	.andExpect(model().attribute("message",  ErrorMessage.PROFILOERROR2));
    }
    
    
    
      
    @Test
    public void testHomeControllerModificaPostTelefono()  throws Exception {
    	this.mockMvc.perform(post("/modificaProfilo")
    	.sessionAttr("userAccount", accountEsistente)
    	.param("password", "")
    	.param("nome", nome)
    	.param("cognome", cognome)
    	.param("residenza", residenza)
    	.param("bday", data)
    	.param("ntelefono", ntelefonoNonValido)
    	.param("lingue", "#Italiano"))
    	.andExpect(model().attribute("message", ErrorMessage.TELEFONOERROR));
    }
    
    @Test
    public void testHomeControllerModificaPostSUCCESS()  throws Exception {
    	this.mockMvc.perform(post("/modificaProfilo")
    	.sessionAttr("userAccount", accountEsistente)
    	.param("password", "")
    	.param("nome", nome)
    	.param("cognome", cognome)
    	.param("residenza", residenza)
    	.param("bday", data)
    	.param("ntelefono", ntelefono)
    	.param("lingue", "#Italiano"))
    	.andExpect(model().attribute("message", ErrorMessage.PROFILOERROR2));
    }
}

