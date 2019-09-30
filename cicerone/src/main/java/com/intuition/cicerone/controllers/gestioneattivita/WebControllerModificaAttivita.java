package com.intuition.cicerone.controllers.gestioneattivita;

import static com.intuition.cicerone.controllers.WebConstant.HIDDEN;
import static com.intuition.cicerone.controllers.WebConstant.HIDE;
import static com.intuition.cicerone.controllers.WebConstant.LINK;
import static com.intuition.cicerone.controllers.WebConstant.LINK_MESSAGE;
import static com.intuition.cicerone.controllers.WebConstant.MESSAGE;
import static com.intuition.cicerone.controllers.WebConstant.NULL_VALUE;
import static com.intuition.cicerone.controllers.WebConstant.TYPE;
import static com.intuition.cicerone.controllers.WebConstant.TYPE_DANGER;
import static com.intuition.cicerone.controllers.WebConstant.TYPE_SUCCESS;
import static com.intuition.cicerone.controllers.WebConstant.USER_ACCOUNT;
import static com.intuition.cicerone.controllers.WebsiteIndice.ATTIVITA_CREATEURL;
import static com.intuition.cicerone.controllers.WebsiteIndice.CPANEL_MODIFICA_ATTIVITAJSP;
import static com.intuition.cicerone.controllers.WebsiteIndice.MODIFICA_ATTIVITAURL;
import static com.intuition.cicerone.controllers.WebsiteIndice.REDIRECT_LOGGED_HOME;
import static com.intuition.cicerone.controllers.WebsiteIndice.REDIRECT_LOGIN;
import static com.intuition.cicerone.gestioneattivita.ErrorMessage.ATTIVITA_MODIFICATA_CON_SUCCESSO;
import static com.intuition.cicerone.gestioneattivita.ErrorMessage.ERROREMODIFICAREATTIVITA;
import static com.intuition.cicerone.gestioneattivita.ErrorMessage.ERROREMODIFICAREATTIVITA_NON_ESISTENTE;
import static com.intuition.cicerone.gestioneattivita.ErrorMessage.VISUALIZZA_LE_ATTIVITA_DA_TE_CREATE;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.controllers.mappers.AccountModelMapMapper;
import com.intuition.cicerone.controllers.mappers.AttivitaModelMapMapper;
import com.intuition.cicerone.controllers.mappers.IMapper;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.gestioneattivita.attivita.ProxyAttivita;
import com.intuition.cicerone.persistenza.AttivitaCrud;

@Controller
public class WebControllerModificaAttivita {	
	@Autowired
	private AttivitaCrud attivitaCrud;
	
	@GetMapping(MODIFICA_ATTIVITAURL)
	public String mostraVista(ModelMap map,
			HttpServletRequest request,
			@RequestParam(value = "id", defaultValue="errore") String idAttivita) {
		
		HttpSession status = request.getSession();
		if(status.getAttribute(USER_ACCOUNT) == null) {
			return REDIRECT_LOGIN;
		}
		IAccount loggedAccount = (IAccount)status.getAttribute(USER_ACCOUNT);
		if(loggedAccount.getRuolo().equalsIgnoreCase("cicerone")) {
			IMapper<ModelMap> mapper = new AccountModelMapMapper(loggedAccount);
			ModelMap mapAccount = mapper.converti();
			Optional<IAttivita> optAttivita = attivitaCrud.trovaAttivita(idAttivita, null);
			if(optAttivita.isPresent()) {
				IAttivita attivita = optAttivita.get();
				if (attivita.getCreatore().equals(loggedAccount)) {
					IMapper<ModelMap> mapperAttivita = new AttivitaModelMapMapper(attivita);
					map.addAllAttributes(mapperAttivita.converti());
					map.addAllAttributes(mapAccount);
					map.addAttribute(HIDE, HIDDEN);
					map.addAttribute(MESSAGE, NULL_VALUE);
					map.addAttribute(TYPE, TYPE_DANGER);
					map.addAttribute(LINK, NULL_VALUE);
					map.addAttribute(LINK_MESSAGE, NULL_VALUE);
					return CPANEL_MODIFICA_ATTIVITAJSP;
				}
				map.addAllAttributes(mapAccount);
				map.addAttribute(HIDE, NULL_VALUE);
				map.addAttribute(MESSAGE, ERROREMODIFICAREATTIVITA);
				map.addAttribute(TYPE, TYPE_DANGER);
				map.addAttribute(LINK, ATTIVITA_CREATEURL);
				map.addAttribute(LINK_MESSAGE, VISUALIZZA_LE_ATTIVITA_DA_TE_CREATE);
				return CPANEL_MODIFICA_ATTIVITAJSP;
			}
			map.addAllAttributes(mapAccount);
			map.addAttribute(HIDE, NULL_VALUE);
			map.addAttribute(MESSAGE, ERROREMODIFICAREATTIVITA_NON_ESISTENTE);
			map.addAttribute(TYPE, TYPE_DANGER);
			map.addAttribute(LINK, ATTIVITA_CREATEURL);
			map.addAttribute(LINK_MESSAGE, VISUALIZZA_LE_ATTIVITA_DA_TE_CREATE);
			return CPANEL_MODIFICA_ATTIVITAJSP;
		}
		return REDIRECT_LOGGED_HOME;
	}	
	
	@PostMapping(MODIFICA_ATTIVITAURL)
	public String processaPost(ModelMap map,
			HttpServletRequest request,
			@RequestParam("id") String idAttivita,
			@RequestParam("lat") Double latitudine,
			@RequestParam("lon") Double longitudine,
			@RequestParam("luogo") String luogo,
			@RequestParam("ore") Integer ore,
			@RequestParam("minuti") Integer minuti,
			@RequestParam("descrizioneAttivita") String descrizione
			) {
		
		HttpSession status = request.getSession();
		if(status.getAttribute(USER_ACCOUNT) == null) {
			return REDIRECT_LOGIN;
		}
		IAccount loggedAccount = (IAccount)status.getAttribute(USER_ACCOUNT);
		if(loggedAccount.getRuolo().equalsIgnoreCase("cicerone")) {
			IMapper<ModelMap> mapper = new AccountModelMapMapper(loggedAccount);
			ModelMap mapAccount = mapper.converti();
			map.addAllAttributes(mapAccount);
			Optional<IAttivita> optAttivita = attivitaCrud.trovaAttivita(idAttivita, null);
			if(optAttivita.isPresent()) {
				IAttivita attivita = new ProxyAttivita(loggedAccount.getRuolo(), optAttivita.get());
				if (attivita.getCreatore().equals(loggedAccount)) {
					try {
						attivita.modificaDataEOraIncontro(attivita.getDataIncontro(), (ore != null && minuti != null) ?  LocalTime.of(ore, minuti) : attivita.getOraIncontro());
						attivita.modificaPuntoIncontro(latitudine, longitudine, luogo);
						attivita.modificaDescrizione(descrizione);
						attivitaCrud.salvaAttivita(attivita);
						IMapper<ModelMap> mapperAttivita = new AttivitaModelMapMapper(attivita);
						map.addAllAttributes(mapperAttivita.converti());
						map.addAttribute(HIDE, NULL_VALUE);
						map.addAttribute(MESSAGE, ATTIVITA_MODIFICATA_CON_SUCCESSO);
						map.addAttribute(TYPE, TYPE_SUCCESS);
						map.addAttribute(LINK, ATTIVITA_CREATEURL);
						map.addAttribute(LINK_MESSAGE, VISUALIZZA_LE_ATTIVITA_DA_TE_CREATE);
						return CPANEL_MODIFICA_ATTIVITAJSP;
					} catch(IllegalArgumentException|UnsupportedOperationException|DateTimeException e) {
						map.addAttribute(HIDE, NULL_VALUE);
						map.addAttribute(MESSAGE, e.getMessage());
						map.addAttribute(TYPE, TYPE_DANGER);
						map.addAttribute(LINK, ATTIVITA_CREATEURL);
						map.addAttribute(LINK_MESSAGE, VISUALIZZA_LE_ATTIVITA_DA_TE_CREATE);
					}
					IMapper<ModelMap> mapperAttivita = new AttivitaModelMapMapper(attivita);
					map.addAllAttributes(mapperAttivita.converti());
					return CPANEL_MODIFICA_ATTIVITAJSP;
				}
				map.addAllAttributes(mapAccount);
				map.addAttribute(HIDE, NULL_VALUE);
				map.addAttribute(MESSAGE, ERROREMODIFICAREATTIVITA);
				map.addAttribute(TYPE, TYPE_DANGER);
				map.addAttribute(LINK, ATTIVITA_CREATEURL);
				map.addAttribute(LINK_MESSAGE, VISUALIZZA_LE_ATTIVITA_DA_TE_CREATE);
				return CPANEL_MODIFICA_ATTIVITAJSP;
			}
			map.addAllAttributes(mapAccount);
			map.addAttribute(HIDE, NULL_VALUE);
			map.addAttribute(MESSAGE, ERROREMODIFICAREATTIVITA_NON_ESISTENTE);
			map.addAttribute(TYPE, TYPE_DANGER);
			map.addAttribute(LINK, ATTIVITA_CREATEURL);
			map.addAttribute(LINK_MESSAGE, VISUALIZZA_LE_ATTIVITA_DA_TE_CREATE);
			return CPANEL_MODIFICA_ATTIVITAJSP;
		}
		return REDIRECT_LOGGED_HOME;		
	}
}
