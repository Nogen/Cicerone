package com.intuition.cicerone.controllers.registrazione;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.intuition.cicerone.autenticazione.Account;
import com.intuition.cicerone.autenticazione.Anagrafica;
import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.autenticazione.IAnagrafica;
import com.intuition.cicerone.autenticazione.utils.IdGenerator;
import com.intuition.cicerone.controllers.WebConstant;
import com.intuition.cicerone.controllers.WebsiteIndice;
import com.intuition.cicerone.controllers.mappers.AccountModelMapMapper;
import com.intuition.cicerone.controllers.mappers.IMapper;
import com.intuition.cicerone.gestioneattivita.ErrorMessage;
import com.intuition.cicerone.persistenza.AccountCrud;

@Controller
public class WebControllerSignup {
	@Autowired
	private AccountCrud accountService;

	@GetMapping(WebsiteIndice.SIGNUPURL)
	public String mostraVista(ModelMap map, 
			HttpServletRequest request, 
			RedirectAttributes redirectAttributes) {
		
		map.addAttribute(WebConstant.HIDE, WebConstant.HIDDEN);
		HttpSession status = request.getSession();
		if (status.getAttribute(WebConstant.USER_ACCOUNT) != null) {
			IAccount account = (IAccount)status.getAttribute(WebConstant.USER_ACCOUNT);
			IMapper<ModelMap> mapper = new AccountModelMapMapper(account);
			map.addAllAttributes(mapper.converti());
			return WebsiteIndice.CHOOSEJSP;
		}
		return WebsiteIndice.SIGNUPJSP;
	}

	@PostMapping(WebsiteIndice.SIGNUPURL)
	public String validaSignup(@RequestParam("email") String email, 
			@RequestParam("nome") String nome,
			@RequestParam("cognome") String cognome,
			@RequestParam("ntelefono") String numeroDiTelefono,
			@RequestParam("residenza") String residenza,
			@RequestParam("password") String password,
			@RequestParam("genere") String genere,
			@RequestParam("bday") String dataDiNascita,
			ModelMap map,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {

		HttpSession status = request.getSession();
		if (status.getAttribute(WebConstant.USER_ACCOUNT) != null) {
			IAccount account = (IAccount)status.getAttribute(WebConstant.USER_ACCOUNT);
			IMapper<ModelMap> mapper = new AccountModelMapMapper(account);
			map.addAllAttributes(mapper.converti());
			return WebsiteIndice.CHOOSEJSP;
		}
		map.addAttribute(WebConstant.HIDE, WebConstant.HIDDEN);
		if (accountService.esisteAccountByEmail(email)) {
			map.addAttribute(WebConstant.HIDE, WebConstant.NULL_VALUE);
			map.addAttribute(WebConstant.TYPE, WebConstant.TYPE_DANGER);
			map.addAttribute(WebConstant.LINK, WebsiteIndice.LOGINJSP);
			map.addAttribute(WebConstant.LINK_MESSAGE, "Clicca qui se sei già registrato");
			map.addAttribute(WebConstant.MESSAGE, ErrorMessage.EMAILERROR2);
			return WebsiteIndice.SIGNUPJSP;
		} else if (accountService.esisteByTelefono(numeroDiTelefono)){
			map.addAttribute(WebConstant.HIDE, WebConstant.NULL_VALUE);
			map.addAttribute(WebConstant.TYPE, WebConstant.TYPE_DANGER);
			map.addAttribute(WebConstant.LINK, WebsiteIndice.LOGINJSP);
			map.addAttribute(WebConstant.LINK_MESSAGE, "Clicca qui se sei già registrato");
			map.addAttribute(WebConstant.MESSAGE, "Numero di telefono già esistente.");
			return WebsiteIndice.SIGNUPJSP;
		}else {
			try {
				IAnagrafica anagrafica = new Anagrafica.BuilderAnagrafica().setNome(nome).setCognome(cognome)
						.setDataDiNascita(LocalDate.parse(dataDiNascita)).setNumeroDiTelefono(numeroDiTelefono).setPaeseCitta(residenza)
						.setSesso(genere.toLowerCase().equalsIgnoreCase("uomo") ? Anagrafica.Sesso.UOMO : Anagrafica.Sesso.DONNA)
						.setIdAnagrafica(IdGenerator.generateId())
						.build();

				IAccount account = new Account.BuilderAccount().setIdAccount(IdGenerator.generateId()).setAnagrafica(anagrafica)
						.generaContatoreNotifica().setEmail(email).setRuolo("Globetrotter").hashPassword(password)
						.build();
				accountService.salvaAccount(account);
				map.addAttribute(WebConstant.HIDE,  WebConstant.NULL_VALUE);
				map.addAttribute(WebConstant.TYPE, WebConstant.TYPE_SUCCESS);
				map.addAttribute(WebConstant.LINK, WebsiteIndice.LOGINJSP);
				map.addAttribute(WebConstant.LINK_MESSAGE, "Clicca qui per fare il log-in");
				map.addAttribute(WebConstant.MESSAGE, "Grazie per esserti iscritto!");
				return WebsiteIndice.SIGNUPJSP; 
			} catch (IllegalArgumentException e) {
				map.addAttribute(WebConstant.HIDE,  WebConstant.NULL_VALUE);
				map.addAttribute(WebConstant.TYPE, WebConstant.TYPE_DANGER);
				map.addAttribute(WebConstant.LINK,  WebConstant.NULL_VALUE);
				map.addAttribute(WebConstant.LINK_MESSAGE,  WebConstant.NULL_VALUE);
				map.addAttribute(WebConstant.MESSAGE, e.getMessage());
			} catch (DateTimeParseException e) {
				map.addAttribute(WebConstant.HIDE,  WebConstant.NULL_VALUE);
				map.addAttribute(WebConstant.TYPE, WebConstant.TYPE_DANGER);
				map.addAttribute(WebConstant.LINK,  WebConstant.NULL_VALUE);
				map.addAttribute(WebConstant.LINK_MESSAGE,  WebConstant.NULL_VALUE);
				map.addAttribute(WebConstant.MESSAGE, ErrorMessage.DATAERROR3);
			}
		}
		return WebsiteIndice.SIGNUPJSP;
	}	
}
