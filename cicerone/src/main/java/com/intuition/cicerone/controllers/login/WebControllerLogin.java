package com.intuition.cicerone.controllers.login;


import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.autenticazione.utils.HashnSalt;
import com.intuition.cicerone.controllers.WebConstant;
import com.intuition.cicerone.controllers.WebsiteIndice;
import com.intuition.cicerone.controllers.mappers.AccountModelMapMapper;
import com.intuition.cicerone.controllers.mappers.IMapper;
import com.intuition.cicerone.gestioneattivita.ErrorMessage;
import com.intuition.cicerone.persistenza.AccountCrud;

@Controller
public class WebControllerLogin {	
	
	@Autowired
	private AccountCrud accountService;
	
	@GetMapping(WebsiteIndice.LOGINURL)
	public String mostraVista(ModelMap map, 
			HttpServletRequest request) {
		map.addAttribute(WebConstant.HIDE, WebConstant.HIDDEN);
		HttpSession status = request.getSession();
		if (status.getAttribute(WebConstant.USER_ACCOUNT) != null) {
			IAccount account = (IAccount)status.getAttribute(WebConstant.USER_ACCOUNT);
			IMapper<ModelMap> mapper = new AccountModelMapMapper(account);
			map.addAllAttributes(mapper.converti());
			return WebsiteIndice.CHOOSEJSP;
		}
		
		return WebsiteIndice.LOGINJSP;
	}
	
	@PostMapping(WebsiteIndice.LOGINURL)
	public String validaLogin(@RequestParam("email") String email, 
			@RequestParam("password") String password,
			ModelMap map,
			HttpServletRequest request) {
		
		HttpSession status = request.getSession();	
		
		map.addAttribute(WebConstant.HIDE, WebConstant.HIDDEN);
		if(accountService.esisteAccountByEmail(email)) {
			Optional<IAccount> tmpresult = accountService.trovaAccountByEmail(email);
			if (tmpresult.isPresent()) {
				IAccount account = tmpresult.get();
				String salt = HashnSalt.extractSalt(account.getPassword());
				String passwordConferma = HashnSalt.encode(password, salt);
				if (passwordConferma.equals(account.getPassword())) {
					status.setAttribute(WebConstant.USER_ACCOUNT, account);
					IMapper<ModelMap> mapper = new AccountModelMapMapper(account);
					map.addAllAttributes(mapper.converti());
					return WebsiteIndice.CHOOSEJSP;
				} else {
					map.addAttribute(WebConstant.HIDE, WebConstant.NULL_VALUE);
					map.addAttribute(WebConstant.TYPE, WebConstant.TYPE_DANGER);
					map.addAttribute(WebConstant.MESSAGE, ErrorMessage.PSWERROR);
					map.addAttribute(WebConstant.LINK, WebConstant.NULL_VALUE);
					map.addAttribute(WebConstant.LINK_MESSAGE, WebConstant.NULL_VALUE);
				}
			}
		} else {
			map.addAttribute(WebConstant.HIDE, WebConstant.NULL_VALUE);
			map.addAttribute(WebConstant.TYPE, WebConstant.TYPE_DANGER);
			map.addAttribute(WebConstant.MESSAGE, ErrorMessage.ACCOUNTERROR4);
			map.addAttribute(WebConstant.LINK, WebConstant.NULL_VALUE);
			map.addAttribute(WebConstant.LINK_MESSAGE, WebConstant.NULL_VALUE);
		}
		
		return WebsiteIndice.LOGINJSP;
	}
	
	@GetMapping("chooseCicerone")
	public String validaRuoloCicerone(ModelMap map, 
			HttpServletRequest request) {
		HttpSession status = request.getSession();
		if (status.getAttribute(WebConstant.USER_ACCOUNT) != null) {
			IAccount account = (IAccount)status.getAttribute(WebConstant.USER_ACCOUNT);
			account.modificaRuolo("cicerone");
			status.setAttribute(WebConstant.USER_ACCOUNT, account);
			return WebsiteIndice.REDIRECT_LOGGED_HOME;
		}
		return WebsiteIndice.REDIRECT_LOGIN;
	}
	
	@GetMapping("chooseGlobetrotter")
	public String validaRuoloGlobetrotter(ModelMap map, 
			HttpServletRequest request) {
		HttpSession status = request.getSession();
		if (status.getAttribute(WebConstant.USER_ACCOUNT) != null) {
			IAccount account = (IAccount)status.getAttribute(WebConstant.USER_ACCOUNT);
			account.modificaRuolo("globetrotter");
			status.setAttribute(WebConstant.USER_ACCOUNT, account);
			return WebsiteIndice.REDIRECT_LOGGED_HOME;
		}
		return WebsiteIndice.REDIRECT_LOGIN;
	}	
	
	@GetMapping(WebsiteIndice.LOGOUTURL)
	public String validaLogout(HttpServletRequest request, 
			RedirectAttributes redirectAttributes,
			ModelMap map) {
		map.addAttribute(WebConstant.HIDE, WebConstant.HIDDEN);
		request.getSession().invalidate();
		return WebsiteIndice.LOGINJSP;
	}
}
