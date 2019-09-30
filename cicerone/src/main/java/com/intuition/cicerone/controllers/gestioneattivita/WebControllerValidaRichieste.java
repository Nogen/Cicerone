package com.intuition.cicerone.controllers.gestioneattivita;

import static com.intuition.cicerone.controllers.WebConstant.USER_ACCOUNT;
import static com.intuition.cicerone.controllers.WebsiteIndice.ACCETTAURL;
import static com.intuition.cicerone.controllers.WebsiteIndice.REDIRECT_ATTIVITA_CREATE;
import static com.intuition.cicerone.controllers.WebsiteIndice.REDIRECT_LOGIN;
import static com.intuition.cicerone.controllers.WebsiteIndice.RIFIUTAURL;

import java.util.HashSet;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.gestioneattivita.richiesta.IRichiesta;
import com.intuition.cicerone.persistenza.AttivitaCrud;
import com.intuition.cicerone.persistenza.RichiestaCrud;

@Controller
public class WebControllerValidaRichieste {
	@Autowired
	private RichiestaCrud richiestaCrud;
	@Autowired
	private AttivitaCrud attivitaCrud;

	@GetMapping(ACCETTAURL)
	public String processaGetAccetta(HttpServletRequest request,
			@RequestParam(value = "id", defaultValue = "errore") String idRichiesta) {
		return validaAccettazione(request, idRichiesta, true);
	}

	@GetMapping(RIFIUTAURL)
	public String processaGetRifiuta(HttpServletRequest request,
			@RequestParam(value = "id", defaultValue = "errore") String idRichiesta) {
		return validaAccettazione(request, idRichiesta, false);
	}
	
	
	private String validaAccettazione(HttpServletRequest request, String idRichiesta, Boolean accettata) {
		HttpSession status = request.getSession();
		if(status.getAttribute(USER_ACCOUNT) == null) {
			return REDIRECT_LOGIN;
		}
		IAccount loggedAccount = (IAccount)status.getAttribute(USER_ACCOUNT);
		if(loggedAccount.getRuolo().equalsIgnoreCase("cicerone")) {
			Optional<IRichiesta> optRichiesta = richiestaCrud.trovaRichiestaById(idRichiesta);
			if(optRichiesta.isPresent()) {
				IRichiesta richiesta = optRichiesta.get();
				IAttivita attivita = richiesta.getAttivita();
				if (attivita.getCreatore().equals(loggedAccount)) {
					attivita.setPartecipanti(new HashSet<>());
					if (accettata) {
						richiesta.accetta();
						attivitaCrud.salvaAttivita(attivita);
					} else {
						richiesta.rifiuta();
					}
					richiestaCrud.salvaRichiesta(richiesta);
					return "redirect:/partecipanti?id=" + attivita.getIdAttivita();
				}
				
			}
		}
		return REDIRECT_ATTIVITA_CREATE;
	}
}