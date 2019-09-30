package com.intuition.cicerone.controllers.gestioneattivita;

import static com.intuition.cicerone.controllers.WebConstant.HIDDEN;
import static com.intuition.cicerone.controllers.WebConstant.HIDE;
import static com.intuition.cicerone.controllers.WebConstant.LINK;
import static com.intuition.cicerone.controllers.WebConstant.LINK_MESSAGE;
import static com.intuition.cicerone.controllers.WebConstant.MESSAGE;
import static com.intuition.cicerone.controllers.WebConstant.NULL_VALUE;
import static com.intuition.cicerone.controllers.WebConstant.TYPE;
import static com.intuition.cicerone.controllers.WebConstant.TYPE_DANGER;
import static com.intuition.cicerone.controllers.WebConstant.USER_ACCOUNT;
import static com.intuition.cicerone.controllers.WebsiteIndice.ATTIVITA_CREATEURL;
import static com.intuition.cicerone.controllers.WebsiteIndice.CPANEL_PARTECIPANTIJSP;
import static com.intuition.cicerone.controllers.WebsiteIndice.PARTECIPANTIURL;
import static com.intuition.cicerone.controllers.WebsiteIndice.REDIRECT_LOGGED_HOME;
import static com.intuition.cicerone.controllers.WebsiteIndice.REDIRECT_LOGIN;
import static com.intuition.cicerone.gestioneattivita.ErrorMessage.ERROREVISUALIZZAREATTIVITA;
import static com.intuition.cicerone.gestioneattivita.ErrorMessage.ERROREVISUALIZZAREPARTECIPANTIATTIVITA_NON_ESISTENTE;
import static com.intuition.cicerone.gestioneattivita.ErrorMessage.VISUALIZZA_LE_ATTIVITA_DA_TE_CREATE;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.controllers.mappers.AccountModelMapMapper;
import com.intuition.cicerone.controllers.mappers.AttivitaModelMapMapper;
import com.intuition.cicerone.controllers.mappers.IMapper;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.persistenza.AttivitaCrud;
import com.intuition.cicerone.persistenza.RichiestaCrud;

@Controller
public class WebControllerGestisciPartecipanti {
	@Autowired
	private AttivitaCrud attivitaCrud;
	@Autowired
	private RichiestaCrud richiestaCrud;
	
	@GetMapping(PARTECIPANTIURL)
	public String mostraVista(HttpServletRequest request,
			ModelMap map,
			@RequestParam(value="id", defaultValue="errore") String idAttivita) {
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
					map.addAttribute("listaPartecipanti", attivitaCrud.trovaPartecipanti(attivita.getIdAttivita()));
					map.addAttribute("listaIscrizioni", richiestaCrud.trovaRichiestaByAttivita(attivita));
					IMapper<ModelMap> mapperAttivita = new AttivitaModelMapMapper(attivita);
					map.addAllAttributes(mapperAttivita.converti());
					map.addAllAttributes(mapAccount);
					map.addAttribute(HIDE, HIDDEN);
					map.addAttribute(MESSAGE, NULL_VALUE);
					map.addAttribute(TYPE, TYPE_DANGER);
					map.addAttribute(LINK, NULL_VALUE);
					map.addAttribute(LINK_MESSAGE, NULL_VALUE);
					return CPANEL_PARTECIPANTIJSP;
				}
				map.addAllAttributes(mapAccount);
				map.addAttribute(HIDE, NULL_VALUE);
				map.addAttribute(MESSAGE, ERROREVISUALIZZAREATTIVITA);
				map.addAttribute(TYPE, TYPE_DANGER);
				map.addAttribute(LINK, ATTIVITA_CREATEURL);
				map.addAttribute(LINK_MESSAGE, VISUALIZZA_LE_ATTIVITA_DA_TE_CREATE);
				return CPANEL_PARTECIPANTIJSP;
			}
			map.addAllAttributes(mapAccount);
			map.addAttribute(HIDE, NULL_VALUE);
			map.addAttribute(MESSAGE, ERROREVISUALIZZAREPARTECIPANTIATTIVITA_NON_ESISTENTE);
			map.addAttribute(TYPE, TYPE_DANGER);
			map.addAttribute(LINK, ATTIVITA_CREATEURL);
			map.addAttribute(LINK_MESSAGE, VISUALIZZA_LE_ATTIVITA_DA_TE_CREATE);
			return CPANEL_PARTECIPANTIJSP;
		}
		return REDIRECT_LOGGED_HOME;
	}
}
