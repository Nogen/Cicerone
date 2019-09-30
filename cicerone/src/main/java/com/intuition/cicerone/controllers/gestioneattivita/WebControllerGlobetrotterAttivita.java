package com.intuition.cicerone.controllers.gestioneattivita;

import static com.intuition.cicerone.controllers.WebConstant.HIDDEN;
import static com.intuition.cicerone.controllers.WebConstant.HIDE;
import static com.intuition.cicerone.controllers.WebConstant.LINK;
import static com.intuition.cicerone.controllers.WebConstant.LINK_MESSAGE;
import static com.intuition.cicerone.controllers.WebConstant.MESSAGE;
import static com.intuition.cicerone.controllers.WebConstant.NULL_VALUE;
import static com.intuition.cicerone.controllers.WebConstant.TYPE;
import static com.intuition.cicerone.controllers.WebConstant.TYPE_SUCCESS;
import static com.intuition.cicerone.controllers.WebConstant.USER_ACCOUNT;
import static com.intuition.cicerone.controllers.WebsiteIndice.GPANEL_TROVA_ATTIVITAJSP;
import static com.intuition.cicerone.controllers.WebsiteIndice.REDIRECT_LOGGED_HOME;
import static com.intuition.cicerone.controllers.WebsiteIndice.REDIRECT_LOGIN;
import static com.intuition.cicerone.controllers.WebsiteIndice.TROVA_ATTIVITAURL;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.controllers.mappers.AccountModelMapMapper;
import com.intuition.cicerone.controllers.mappers.IMapper;
import com.intuition.cicerone.gestioneattivita.ErrorMessage;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.persistenza.AttivitaCrud;
import com.intuition.cicerone.ricerca.IGestoreRicerca;
import com.intuition.cicerone.ricerca.RicercaByTag;
import com.intuition.cicerone.ricerca.RicercaData;
import com.intuition.cicerone.ricerca.RicercaLingua;
import com.intuition.cicerone.ricerca.RicercaLuogo;
import com.intuition.cicerone.ricerca.RicercaOdierna;

@Controller
public class WebControllerGlobetrotterAttivita {
	@Autowired
	private AttivitaCrud attivitaCrud;

	@GetMapping(TROVA_ATTIVITAURL)
	public String mostraVista(HttpServletRequest request,
			ModelMap map,
			@RequestParam(value = "p", defaultValue="0") int page,
			@RequestParam(value = "orderBy", defaultValue="dataIncontro") String orderBy,
			@RequestParam(value ="byKey", defaultValue="") String byKey,
			@RequestParam(value ="byLingua", defaultValue="") String byLingua,
			@RequestParam(value ="byLuogo", defaultValue="") String byLuogo,
			@RequestParam(value ="byDate", defaultValue="") String byDate) {
		HttpSession status = request.getSession();
		if(status.getAttribute(USER_ACCOUNT) == null) {
			return REDIRECT_LOGIN;
		}
		IAccount loggedAccount = (IAccount)status.getAttribute(USER_ACCOUNT);
		if(loggedAccount.getRuolo().equalsIgnoreCase("globetrotter")) {
			IMapper<ModelMap> mapper = new AccountModelMapMapper(loggedAccount);
			ModelMap mapAccount = mapper.converti();
			map.addAllAttributes(mapAccount);
			List<IAttivita> listaAttivita = attivitaCrud.trovaAttivitaByStato("aperto");
			listaAttivita.addAll(attivitaCrud.trovaAttivitaByStato("modificato"));

			IGestoreRicerca r = new RicercaOdierna(listaAttivita);

			if(!byKey.equals("")) {
				r = new RicercaByTag(r, byKey);
			}
			
			if (!byLingua.equals("")) {
				r = new RicercaLingua(r, byLingua);
			}

			if (!byLuogo.equals("")) {
				r = new RicercaLuogo(r, byLuogo);
			}

			if(!byDate.equals("")) {
				try {
					r = new RicercaData(r, LocalDate.parse(byDate));
				} catch (DateTimeParseException e) {
					byDate = ErrorMessage.DATAERROR3;
				}
			}

			listaAttivita = r.ricerca();
			map.addAttribute("byKey", byKey);
			map.addAttribute("byLingua", byLingua);
			map.addAttribute("byLuogo", byLuogo);
			map.addAttribute("byDate", byDate);

			PagedListHolder<IAttivita> pageHolder = new PagedListHolder<>(listaAttivita);
			pageHolder.setSort(new MutableSortDefinition(orderBy, true, true));
			pageHolder.resort();
			pageHolder.setPageSize(4);
			int totalPages = pageHolder.getPageCount();
			page = page % totalPages;
			pageHolder.setPage(page);
			map.addAttribute("pageIndex", page);
			map.addAttribute("listaAttivita", pageHolder.getPageList());
			map.addAttribute("pageSize", totalPages);
			map.addAttribute("orderBy", orderBy);
			map.addAttribute("totalSize", listaAttivita.size());
			map.addAttribute(HIDE, HIDDEN);
			map.addAttribute(MESSAGE, NULL_VALUE);
			map.addAttribute(TYPE, TYPE_SUCCESS);
			map.addAttribute(LINK, NULL_VALUE);
			map.addAttribute(LINK_MESSAGE, NULL_VALUE);
			return GPANEL_TROVA_ATTIVITAJSP;
		}
		return REDIRECT_LOGGED_HOME;
	}
}
