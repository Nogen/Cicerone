package com.intuition.cicerone.controllers.mappers;

import org.springframework.ui.ModelMap;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.gestioneattivita.richiesta.IRichiesta;

public class RichiestaModelMapMapper implements IMapper<ModelMap> {

	private IRichiesta richiesta;
	
	public RichiestaModelMapMapper(IRichiesta richiesta) {
		this.richiesta = richiesta;
	}
	
	@Override
	public ModelMap converti() {
		ModelMap map = new ModelMap();
		
		IAccount mittente = richiesta.getMittente();
		
		map.addAttribute("idAccount", mittente.getIdAccount());
		map.addAttribute("nome", mittente.getAnagrafica().getNome());
		map.addAttribute("cognome", mittente.getAnagrafica().getCognome());
		map.addAttribute("email", mittente.getEmail());
		map.addAttribute("ntelefono", mittente.getAnagrafica().getNumeroDiTelefono());
		map.addAttribute("dataRichiesta", richiesta.getDataRichiesta().toString());
		map.addAttribute("idAttivita", richiesta.getAttivita().getIdAttivita());
		map.addAttribute("titoloAttivita", richiesta.getAttivita().getTitolo());		
		return map;
	}
}
