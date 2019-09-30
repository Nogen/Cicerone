package com.intuition.cicerone.controllers;

import static com.intuition.cicerone.controllers.WebsiteIndice.REDIRECT_LOGGED_HOME;
import static com.intuition.cicerone.controllers.WebsiteIndice.REDIRECT_LOGIN;

import org.springframework.ui.ModelMap;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.controllers.mappers.AccountModelMapMapper;
import com.intuition.cicerone.controllers.mappers.IMapper;


public class RequestsHandler {
	private String pageResult;
	private IAccount loggedAccount;


	public RequestsHandler(IAccount a) {
		this.loggedAccount = a;
		if(loggedAccount == null) {
			pageResult = REDIRECT_LOGIN;
		}
	}
	
	
	public RequestsHandler setModelMapAccount(ModelMap map) {
		if (pageResult == null) {
			IMapper<ModelMap> mapper = new AccountModelMapMapper(loggedAccount);
			map.addAllAttributes(mapper.converti());
		}
		return this;
	}
	
	public RequestsHandler roleEq(String role) {
		if (pageResult == null && !loggedAccount.getRuolo().equalsIgnoreCase(role)) {
			pageResult = REDIRECT_LOGGED_HOME;
		}
		return this;
	}
	
	
	public RequestsHandler roleEq(String role, String alternative) {
		if (pageResult == null && !loggedAccount.getRuolo().equalsIgnoreCase(role) && alternative != null) {
			pageResult = alternative;
		}
		return this;
	}
	
	public RequestsHandler ifCorrectReturn(String correctJSP) {
		if(pageResult == null) {
			pageResult = correctJSP;
		}
		return this;
	}
	
	public RequestsHandler ifElse(String alternative) {
		if (pageResult == null) {
			pageResult = alternative;
		}
		return this;
	}
	
	public String build() {
		if (pageResult != null) {
			return pageResult;
		} else {
			throw new UnsupportedOperationException();
		}
	}

}
