package com.intuition.cicerone.controllers;


import static com.intuition.cicerone.controllers.WebConstant.USER_ACCOUNT;
import static com.intuition.cicerone.controllers.WebsiteIndice.HOME_CICERONE;
import static com.intuition.cicerone.controllers.WebsiteIndice.HOME_GLOBETROTTER;
import static com.intuition.cicerone.controllers.WebsiteIndice.LOGGED_HOME;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.intuition.cicerone.autenticazione.IAccount;


@Controller
public class WebControllerHome {
	

	@Autowired
	private EmailServiceImpl emailService;
	
	@GetMapping(WebsiteIndice.INDIRIZZOBASEURL)
	public String mostraVista() {
		return WebsiteIndice.HOMEHTML;
	}
	
	@PostMapping(WebsiteIndice.INDIRIZZOBASEURL)
	public String validaEmail(@RequestParam("email") String email,
			@RequestParam("nome") String nome,
			@RequestParam("cognome") String cognome,
			@RequestParam("descrizione") String descrizione) {
		emailService.sendSimpleMessage(WebConstant.CICER1NOREPLY_GMAIL_COM, "Inviata da: " + email + " ->" + nome + " " + cognome,
				 descrizione);
		return WebsiteIndice.REDIRECTURL;
	}
	
	@GetMapping(LOGGED_HOME)
	public String mostraVistaLogged(HttpServletRequest request, ModelMap map) {
		HttpSession status = request.getSession();
		IAccount loggedAccount = (IAccount)status.getAttribute(USER_ACCOUNT);
		RequestsHandler handler = new RequestsHandler(loggedAccount);
		return handler
				.setModelMapAccount(map)
				.roleEq("cicerone", HOME_GLOBETROTTER)
				.ifElse(HOME_CICERONE)
				.build();
	}
	
	
}
