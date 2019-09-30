package com.intuition.cicerone.controllers.gestioneattivita;

import static com.intuition.cicerone.controllers.WebConstant.USER_ACCOUNT;
import static com.intuition.cicerone.controllers.WebsiteIndice.CANCELLA_ATTIVITAURL;
import static com.intuition.cicerone.controllers.WebsiteIndice.REDIRECT_ATTIVITA_CREATE;
import static com.intuition.cicerone.controllers.WebsiteIndice.REDIRECT_LOGGED_HOME;
import static com.intuition.cicerone.controllers.WebsiteIndice.REDIRECT_LOGIN;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.persistenza.AttivitaCrud;
import com.intuition.cicerone.persistenza.FeedbackCrud;
import com.intuition.cicerone.persistenza.RichiestaCrud;

@Controller	
public class WebControllerEliminaAttivita {
	
	
	@Autowired
	private AttivitaCrud attivitaCrud;
	@Autowired
	private RichiestaCrud richiestaCrud;
	@Autowired
	private FeedbackCrud feedbackCrud;
	
	@GetMapping(CANCELLA_ATTIVITAURL)
	public String processaGet(HttpServletRequest request,
			ModelMap map,
			@RequestParam(value="id", defaultValue="errore") String idAttivita) {
		HttpSession status = request.getSession();
		if(status.getAttribute(USER_ACCOUNT) == null) {
			return REDIRECT_LOGIN;
		}
		IAccount loggedAccount = (IAccount)status.getAttribute(USER_ACCOUNT);
		if(loggedAccount.getRuolo().equalsIgnoreCase("cicerone")) {
			Optional<IAttivita> optAttivita = attivitaCrud.trovaAttivita(idAttivita, null);
			if(optAttivita.isPresent()) {
				IAttivita attivita = optAttivita.get();
				if (attivita.getCreatore().equals(loggedAccount)) {
					attivitaCrud.eliminaAttivita(attivita);
					richiestaCrud.eliminaRichiesta(attivita.getIdAttivita());
					feedbackCrud.eliminaByAttivita(attivita.getIdAttivita());
				}
			}
			return REDIRECT_ATTIVITA_CREATE;
		}
		return REDIRECT_LOGGED_HOME;		
	}
}
