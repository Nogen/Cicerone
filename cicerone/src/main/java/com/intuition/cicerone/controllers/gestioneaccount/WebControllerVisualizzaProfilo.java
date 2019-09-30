package com.intuition.cicerone.controllers.gestioneaccount;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.controllers.WebConstant;
import com.intuition.cicerone.controllers.WebsiteIndice;
import com.intuition.cicerone.controllers.mappers.AccountModelMapMapper;
import com.intuition.cicerone.controllers.mappers.IMapper;
import com.intuition.cicerone.gestioneattivita.ErrorMessage;
import com.intuition.cicerone.persistenza.AccountCrud;

@Controller
public class WebControllerVisualizzaProfilo  {	
	@Autowired
	private AccountCrud accountService;
	
	@GetMapping(WebsiteIndice.PROFILOURL)
	public String mostraVista(@RequestParam(name="id", defaultValue="errore") String id, ModelMap map) {

		map.addAttribute(WebConstant.SHOW_ON_LOGIN, WebConstant.HIDDEN);
		map.addAttribute(WebConstant.HIDE, WebConstant.NULL_VALUE);
		map.addAttribute(WebConstant.TYPE, WebConstant.TYPE_DANGER);
		map.addAttribute(WebConstant.LINK, WebConstant.NULL_VALUE);
		map.addAttribute(WebConstant.LINK_MESSAGE, WebConstant.NULL_VALUE);
		map.addAttribute(WebConstant.MESSAGE, ErrorMessage.UTENTEERROR);
		Optional<IAccount> opt = accountService.trovaAccountById(id);
		if (opt.isPresent()) {
			IAccount account = opt.get();
			IMapper<ModelMap> mapper = new AccountModelMapMapper(account);
			map.addAllAttributes(mapper.converti());
			map.addAttribute(WebConstant.HIDE, WebConstant.HIDDEN);
			map.addAttribute(WebConstant.SHOW_ON_LOGIN, WebConstant.NULL_VALUE);
		}
		return WebsiteIndice.VISUALIZZAPROFILOJSP; 
	}
}
