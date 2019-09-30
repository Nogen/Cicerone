package com.intuition.cicerone.controllers.gestioneaccount;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.intuition.cicerone.autenticazione.Account;
import com.intuition.cicerone.autenticazione.Anagrafica;
import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.autenticazione.IAnagrafica;
import com.intuition.cicerone.autenticazione.utils.HashnSalt;
import com.intuition.cicerone.controllers.WebConstant;
import com.intuition.cicerone.controllers.WebsiteIndice;
import com.intuition.cicerone.controllers.mappers.AccountModelMapMapper;
import com.intuition.cicerone.controllers.mappers.IMapper;
import com.intuition.cicerone.gestioneattivita.ErrorMessage;
import com.intuition.cicerone.persistenza.AccountCrud;

@Controller
public class WebControllerModificaProfilo {
	@Autowired
	private AccountCrud accountService;
	
	@GetMapping(WebsiteIndice.MODIFICAPROFILOURL)
	public String mostraVista(HttpServletRequest request, ModelMap map) {
		map.addAttribute(WebConstant.SHOW_ON_LOGIN, WebConstant.NULL_VALUE);
		map.addAttribute(WebConstant.HIDE, WebConstant.HIDDEN);
		map.addAttribute(WebConstant.MESSAGE, WebConstant.NULL_VALUE);
		map.addAttribute(WebConstant.TYPE, WebConstant.TYPE_DANGER);
		map.addAttribute(WebConstant.LINK, WebsiteIndice.LOGINJSP);
		map.addAttribute(WebConstant.LINK_MESSAGE, ErrorMessage.PROFILOERROR3);
		HttpSession status = request.getSession();
		if (status.getAttribute(WebConstant.USER_ACCOUNT) != null) {
			IAccount account = (IAccount)status.getAttribute(WebConstant.USER_ACCOUNT);
			IMapper<ModelMap> mapper = new AccountModelMapMapper(account);
			map.addAllAttributes(mapper.converti());
		} else {
			map.addAttribute(WebConstant.SHOW_ON_LOGIN, WebConstant.HIDDEN);
			map.addAttribute(WebConstant.CONTENT, WebConstant.NULL_VALUE);
			map.addAttribute(WebConstant.HIDE, WebConstant.NULL_VALUE);
			map.addAttribute(WebConstant.MESSAGE, ErrorMessage.PROFILOERROR);
		}
		return WebsiteIndice.MODIFICAPROFILOJSP;
	}

	@PostMapping(WebsiteIndice.MODIFICAPROFILOURL)
	public String validaProfiloModificato(@RequestParam("nome") String nome,
			@RequestParam("cognome") String cognome,
			@RequestParam("residenza") String residenza,
			@RequestParam("ntelefono") String numeroTelefono,
			@RequestParam("bday") String dataDiNascita,
			@RequestParam("lingue") String lingue,
			@RequestParam("password") String password,
			HttpServletRequest request, ModelMap map) {
		map.addAttribute(WebConstant.SHOW_ON_LOGIN, WebConstant.NULL_VALUE);
		map.addAttribute(WebConstant.HIDE, WebConstant.HIDDEN);
		map.addAttribute(WebConstant.MESSAGE, WebConstant.NULL_VALUE);
		map.addAttribute(WebConstant.TYPE, WebConstant.TYPE_DANGER);
		map.addAttribute(WebConstant.LINK, WebConstant.NULL_VALUE);
		map.addAttribute(WebConstant.LINK_MESSAGE, WebConstant.NULL_VALUE);
		HttpSession status = request.getSession();
		if (status.getAttribute(WebConstant.USER_ACCOUNT) != null) {
			IAccount accountOld = (IAccount)status.getAttribute(WebConstant.USER_ACCOUNT);
			IAnagrafica anagraficaOld = accountOld.getAnagrafica();
			
			try {
				String tmpSalt = HashnSalt.extractSalt(accountOld.getPassword());
				String tmpHash = HashnSalt.encode(password, tmpSalt);
				if (password.isEmpty()) {
					tmpHash = accountOld.getPassword();
				} else {
					if (tmpHash.equals(accountOld.getPassword())) {
						throw new IllegalArgumentException(ErrorMessage.PSWERROR2);
					} else if (!Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$", password)){
						throw new IllegalArgumentException(ErrorMessage.PSWERROR);
					}
				}
				Set<String> setLingue = new HashSet<>(Arrays.asList(lingue.replace(" ", "").split("#")));
				setLingue.remove("");
				IAnagrafica anagrafica = new Anagrafica.BuilderAnagrafica()
						.setIdAnagrafica(anagraficaOld.getIdAnagrafica())
						.setNome(nome)
						.setCognome(cognome)
						.setNumeroDiTelefono(numeroTelefono)
						.setDataDiNascita(LocalDate.parse(dataDiNascita))
						.setPaeseCitta(residenza)
						.setSesso(anagraficaOld.getSesso())
						.setLingueParlate(setLingue)
						.build();

				IAccount account = new Account.BuilderAccount()
				.setIdAccount(accountOld.getIdAccount())
				.setContatoreNotifica(accountOld.getContatoreNotifiche())
				.setEmail(accountOld.getEmail())
				.setPassword(tmpHash)
				.setRuolo(accountOld.getRuolo())
				.setAnagrafica(anagrafica)
				.build();
				accountService.salvaAccount(account);
				status.setAttribute(WebConstant.USER_ACCOUNT, account);
				IMapper<ModelMap> mapper = new AccountModelMapMapper(account);
				map.addAllAttributes(mapper.converti());
				map.addAttribute(WebConstant.HIDE, WebConstant.NULL_VALUE);
				map.addAttribute(WebConstant.MESSAGE, ErrorMessage.PROFILOERROR2);
				map.addAttribute(WebConstant.TYPE, WebConstant.TYPE_SUCCESS);
				return WebsiteIndice.MODIFICAPROFILOJSP;
			} catch (IllegalArgumentException|DateTimeParseException e) {
				map.addAttribute(WebConstant.HIDE, WebConstant.NULL_VALUE);
				map.addAttribute(WebConstant.MESSAGE, e.getMessage());
				map.addAttribute(WebConstant.TYPE, WebConstant.TYPE_DANGER);
			}
			IMapper<ModelMap> mapper = new AccountModelMapMapper(accountOld);
			map.addAllAttributes(mapper.converti());
		} else {
			map.addAttribute(WebConstant.SHOW_ON_LOGIN, WebConstant.HIDDEN);
			map.addAttribute(WebConstant.CONTENT, WebConstant.NULL_VALUE);
			map.addAttribute(WebConstant.HIDE, WebConstant.NULL_VALUE);
			map.addAttribute(WebConstant.MESSAGE, ErrorMessage.PROFILOERROR);
			map.addAttribute(WebConstant.LINK, WebsiteIndice.LOGINJSP);
			map.addAttribute(WebConstant.LINK_MESSAGE, ErrorMessage.PROFILOERROR3);
		}
		return WebsiteIndice.MODIFICAPROFILOJSP;
	}	
}
