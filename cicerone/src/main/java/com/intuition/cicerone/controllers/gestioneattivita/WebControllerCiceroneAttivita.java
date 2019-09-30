package com.intuition.cicerone.controllers.gestioneattivita;

import static com.intuition.cicerone.controllers.WebConstant.USER_ACCOUNT;
import static com.intuition.cicerone.controllers.WebsiteIndice.ATTIVITA_CREATEJSP;
import static com.intuition.cicerone.controllers.WebsiteIndice.ATTIVITA_CREATEURL;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
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
import com.intuition.cicerone.controllers.RequestsHandler;
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
public class WebControllerCiceroneAttivita {

	@Autowired
	private AttivitaCrud attivitaCrud;

	@GetMapping(ATTIVITA_CREATEURL)
	public String processaVista(ModelMap map, 
			HttpServletRequest request,
			@RequestParam(value = "p", defaultValue="0") int page,
			@RequestParam(value = "orderBy", defaultValue="dataIncontro") String orderBy,
			@RequestParam(value ="byKey", defaultValue="") String byKey,
			@RequestParam(value ="byLingua", defaultValue="") String byLingua,
			@RequestParam(value ="byLuogo", defaultValue="") String byLuogo,
			@RequestParam(value ="byDate", defaultValue="") String byDate) {
		List<IAttivita> listaAttivita = new ArrayList<>();
		HttpSession status = request.getSession();
		IAccount loggedAccount = (IAccount)status.getAttribute(USER_ACCOUNT);
		if(loggedAccount != null) {
			listaAttivita = attivitaCrud.trovaAttivitaByIdCreatore(loggedAccount.getIdAccount());
		}
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
		RequestsHandler handler = new RequestsHandler(loggedAccount);
		return handler.setModelMapAccount(map)
				.roleEq("cicerone")
				.ifCorrectReturn(visualizzaAttivitaCreate(listaAttivita, map, page, orderBy))
				.build();
	}

	private String visualizzaAttivitaCreate(List<IAttivita> listaAttivita, ModelMap map, int page, String orderBy) {
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
			return ATTIVITA_CREATEJSP;
	}
}
