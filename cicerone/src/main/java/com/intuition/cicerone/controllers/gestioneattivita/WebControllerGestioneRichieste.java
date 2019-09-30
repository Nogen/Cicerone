package com.intuition.cicerone.controllers.gestioneattivita;

import static com.intuition.cicerone.controllers.WebConstant.USER_ACCOUNT;
import static com.intuition.cicerone.controllers.WebsiteIndice.GESTIONE_RICHIESTEURL;
import static com.intuition.cicerone.controllers.WebsiteIndice.GPANEL_GESTIONE_RICHIESTEJSP;
import static com.intuition.cicerone.controllers.WebsiteIndice.REDIRECT_GESTIONE_RICHIESTE;
import static com.intuition.cicerone.controllers.WebsiteIndice.REDIRECT_LOGGED_HOME;
import static com.intuition.cicerone.controllers.WebsiteIndice.REDIRECT_LOGIN;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.controllers.mappers.AccountModelMapMapper;
import com.intuition.cicerone.controllers.mappers.IMapper;
import com.intuition.cicerone.feedback.Feedback;
import com.intuition.cicerone.feedback.IFeedback;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.gestioneattivita.richiesta.IRichiesta;
import com.intuition.cicerone.persistenza.AttivitaCrud;
import com.intuition.cicerone.persistenza.FeedbackCrud;
import com.intuition.cicerone.persistenza.RichiestaCrud;

@Controller
public class WebControllerGestioneRichieste {
	@Autowired
	private RichiestaCrud richiestaCrud;
	@Autowired
	private FeedbackCrud feedbackCrud;
	@Autowired
	private AttivitaCrud attivitaCrud;
	
	@GetMapping(GESTIONE_RICHIESTEURL)
	public String mostraVista(HttpServletRequest request, ModelMap map,
			@RequestParam(value = "p", defaultValue="0") int page,
			@RequestParam(value = "orderBy", defaultValue="statoRichiesta") String orderBy) {
		HttpSession status = request.getSession();
		if(status.getAttribute(USER_ACCOUNT) == null) {
			return REDIRECT_LOGIN;
		}
		IAccount loggedAccount = (IAccount)status.getAttribute(USER_ACCOUNT);
		IMapper<ModelMap> mapper = new AccountModelMapMapper(loggedAccount);
		ModelMap mapAccount = mapper.converti();
		map.addAllAttributes(mapAccount);
		if(loggedAccount.getRuolo().equalsIgnoreCase("globetrotter")) {
			Map<IRichiesta, IFeedback> mappa = new LinkedHashMap<>();
			List<IRichiesta> listaRichieste = richiestaCrud.trovaRichiestaByAccount(loggedAccount);
			int totalSize = listaRichieste.size();
			ComparatorFactory com = new ComparatorFactory();
			listaRichieste.sort(com.create(orderBy));
			PagedListHolder<IRichiesta> pageHolder = new PagedListHolder<>(listaRichieste);
			pageHolder.setPageSize(4);
			int totalPages = pageHolder.getPageCount();
			page = page % totalPages;
			pageHolder.setPage(page);
			listaRichieste = pageHolder.getPageList();
			for (IRichiesta richiesta : listaRichieste) {
				Optional<IFeedback> feedbackOpt = feedbackCrud.trovaFeedbackByIdCreatore(loggedAccount.getIdAccount(), richiesta.getAttivita().getIdAttivita());
				if (feedbackOpt.isPresent()) {
					mappa.put(richiesta, feedbackOpt.get());
				} else {
					mappa.put(richiesta, null);
				}
			}
			map.addAttribute("pageIndex", page);
			map.addAttribute("listaRichieste",
			mappa);
			map.addAttribute("pageSize", totalPages);
			map.addAttribute("orderBy", orderBy);
			map.addAttribute("totalSize", totalSize);
			return GPANEL_GESTIONE_RICHIESTEJSP;
		}
		return REDIRECT_LOGGED_HOME;
	}
	
	@PostMapping(GESTIONE_RICHIESTEURL)
	public String porcessaPost(HttpServletRequest request, ModelMap map,
			@RequestParam("commento") String commento,
			@RequestParam("valutazione") int valutazione,
			@RequestParam("idAttivita") String idAttivita) {
		HttpSession status = request.getSession();
		if(status.getAttribute(USER_ACCOUNT) == null) {
			return REDIRECT_LOGIN;
		}
		IAccount loggedAccount = (IAccount)status.getAttribute(USER_ACCOUNT);
		IMapper<ModelMap> mapper = new AccountModelMapMapper(loggedAccount);
		ModelMap mapAccount = mapper.converti();
		map.addAllAttributes(mapAccount);
		Optional<IFeedback> optcheck = feedbackCrud.trovaFeedbackByIdCreatore(loggedAccount.getIdAccount(), idAttivita);
		if(loggedAccount.getRuolo().equalsIgnoreCase("globetrotter") && !optcheck.isPresent()) {
			Optional<IAttivita> attivitaOpt = attivitaCrud.trovaAttivita(idAttivita, null);
			if (attivitaOpt.isPresent()) {
				IAttivita attivita = attivitaOpt.get();
				IFeedback feedback = new Feedback.BuilderFeedback()
						.generaIdFeedback()
						.setAccount(loggedAccount)
						.setData(LocalDate.now())
						.setTempAtt(attivita)
						.setValutazione(valutazione)
						.setCommento(commento)
					.build();
				feedbackCrud.salvaFeedback(feedback);
				return REDIRECT_GESTIONE_RICHIESTE;
			}
		}
		return REDIRECT_LOGGED_HOME;
	}

		
}


class ComparatorFactory {
	public Comparator<IRichiesta> create(String orderBy) {
		switch (orderBy) {
		case "titolo":
			return new ByTitoloAttivita();
		case "dataRichiesta":
			return new ByDataIncontro();
		default:
			return new ByStatoRichiesta();
		}
	}
}


class ByTitoloAttivita implements Comparator<IRichiesta> {

	@Override
	public int compare(IRichiesta o1, IRichiesta o2) {
		return o1.getAttivita().getTitolo().compareTo(o2.getAttivita().getTitolo());
	}
	
}


class ByStatoRichiesta implements Comparator<IRichiesta> {

	@Override
	public int compare(IRichiesta o1, IRichiesta o2) {
		return o1.getStatoRichiesta().toString().compareTo(o2.getStatoRichiesta().toString());
	}
	
}


class ByDataIncontro implements Comparator<IRichiesta> {

	@Override
	public int compare(IRichiesta o1, IRichiesta o2) {
		return -1*o1.getDataRichiesta().compareTo(o2.getDataRichiesta());
	}
	
}