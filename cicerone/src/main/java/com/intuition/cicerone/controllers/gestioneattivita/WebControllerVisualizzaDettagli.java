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
import static com.intuition.cicerone.controllers.WebsiteIndice.REDIRECT_LOGIN;
import static com.intuition.cicerone.controllers.WebsiteIndice.VISUALIZZA_ATTIVITAJSP;
import static com.intuition.cicerone.controllers.WebsiteIndice.VISUALIZZA_ATTIVITAURL;
import static com.intuition.cicerone.gestioneattivita.ErrorMessage.ERRORE_L_ATTIVITA_NON_ESISTE;
import static com.intuition.cicerone.gestioneattivita.ErrorMessage.RITORNA_ALLA_HOME;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.controllers.mappers.AccountModelMapMapper;
import com.intuition.cicerone.controllers.mappers.AttivitaModelMapMapper;
import com.intuition.cicerone.controllers.mappers.IMapper;
import com.intuition.cicerone.feedback.IFeedback;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.persistenza.AttivitaCrud;
import com.intuition.cicerone.persistenza.FeedbackCrud;

@Controller
public class WebControllerVisualizzaDettagli {
	
	
	@Autowired
	private AttivitaCrud attivitaCrud;
	@Autowired
	private FeedbackCrud feedbackCrud;

	@GetMapping(VISUALIZZA_ATTIVITAURL)
	public String mostraVista(HttpServletRequest request, 
			ModelMap map,
			@RequestParam(value="id", defaultValue="errore") String idAttivita,
			@RequestParam(value = "p", defaultValue="0") int page) {
		HttpSession status = request.getSession();
		if(status.getAttribute(USER_ACCOUNT) != null) {
			IAccount loggedAccount = (IAccount)status.getAttribute(USER_ACCOUNT);
			IMapper<ModelMap> accountMapper = new AccountModelMapMapper(loggedAccount);
			map.addAllAttributes(accountMapper.converti());
		}
		Optional<IAttivita> optAttivita = attivitaCrud.trovaAttivita(idAttivita, null);
		if (optAttivita.isPresent()) {
			IAttivita attivita = optAttivita.get();
			IMapper<ModelMap> attivitaMapper = new AttivitaModelMapMapper(attivita);
			map.addAllAttributes(attivitaMapper.converti());
			List<IFeedback> listaFeedback = feedbackCrud.trovaByAttivita(attivita);
			PagedListHolder<IFeedback> pageHolder = new PagedListHolder<>(listaFeedback);
			pageHolder.resort();
			pageHolder.setPageSize(4);
			int totalPages = pageHolder.getPageCount();
			page = page % totalPages;
			pageHolder.setPage(page);
			map.addAttribute("pageIndex", page);
			map.addAttribute("listaFeedback", pageHolder.getPageList());
			map.addAttribute("pageSize", totalPages);
			map.addAttribute(HIDE, HIDDEN);
			return VISUALIZZA_ATTIVITAJSP;
		}
		map.addAttribute(HIDE, NULL_VALUE);
		map.addAttribute(TYPE, TYPE_DANGER);
		map.addAttribute(MESSAGE, ERRORE_L_ATTIVITA_NON_ESISTE);
		map.addAttribute(LINK, REDIRECT_LOGIN);
		map.addAttribute(LINK_MESSAGE, RITORNA_ALLA_HOME);
		return VISUALIZZA_ATTIVITAJSP;
	}
}
