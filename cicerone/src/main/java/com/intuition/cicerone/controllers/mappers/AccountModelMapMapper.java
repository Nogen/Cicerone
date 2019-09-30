package com.intuition.cicerone.controllers.mappers;

import java.util.Set;

import org.springframework.ui.ModelMap;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.autenticazione.IAnagrafica;

public class AccountModelMapMapper implements IMapper<ModelMap>{

	private IAccount account;
	
	public AccountModelMapMapper(IAccount account) {
		this.account = account;
	}

	@Override
	public ModelMap converti() {
		ModelMap map = new ModelMap();
		String resLingue = "";
		Set<String> lingue = account.getAnagrafica().getLingueParlate();
		if(lingue == null || lingue.isEmpty()) {
			resLingue = "";
		} else {
			resLingue = lingue.toString()
					.replace("[", "#")
					.replace("]", "")
					.replace(", ", " #");
		}
		
		IAnagrafica anagrafica = account.getAnagrafica();
		map.addAttribute("nome", anagrafica.getNome());
		map.addAttribute("cognome", anagrafica.getCognome());
		map.addAttribute("bday", anagrafica.getDataDiNascita().toString());
		map.addAttribute("residenza", anagrafica.getPaeseCitta());
		map.addAttribute("email", account.getEmail());
		map.addAttribute("genere", anagrafica.getSesso().toString());
		map.addAttribute("ntelefono", anagrafica.getNumeroDiTelefono());
		map.addAttribute("lingue", resLingue);
		map.addAttribute("ruolo", account.getRuolo() == null ? "cicerone" : account.getRuolo().toLowerCase());
		map.addAttribute("id", account.getIdAccount());
		return map;
	}
}
