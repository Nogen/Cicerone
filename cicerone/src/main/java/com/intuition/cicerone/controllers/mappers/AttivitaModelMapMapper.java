package com.intuition.cicerone.controllers.mappers;

import java.util.Set;

import org.springframework.ui.ModelMap;

import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;

public class AttivitaModelMapMapper implements IMapper<ModelMap>{

	private IAttivita attivita;
	
	public AttivitaModelMapMapper(IAttivita attivita) {
		this.attivita = attivita;
	}
	
	@Override
	public ModelMap converti() {
		ModelMap map = new ModelMap();
		
		String resLingue = "";
		Set<String> lingue = attivita.getItinerario().getLingua();
		if(lingue == null || lingue.isEmpty()) {
			resLingue = "";
		} else {
			resLingue = lingue.toString()
					.replace("[", "#")
					.replace("]", "")
					.replace(", ", " #");
		}
		
		map.addAttribute("luogo", attivita.getLuogo()); 
		map.addAttribute("xIncontro", attivita.getxIncontro()); 
		map.addAttribute("yIncontro", attivita.getyIncontro()); 
		map.addAttribute("dataIncontro", attivita.getDataIncontro().toString()); 
		map.addAttribute("oraIncontro", attivita.getOraIncontro().toString()); 
		
		map.addAttribute("dataApertura", attivita.getDataApertura().toString());
		map.addAttribute("dataModifica", attivita.getDataModifica().toString());
		map.addAttribute("idAttivita", attivita.getIdAttivita());
		map.addAttribute("titoloAttivita", attivita.getTitolo());
		map.addAttribute("descrizioneAttivita", attivita.getDescrizioneAttivita());
		map.addAttribute("lingue", resLingue);
		map.addAttribute("retribuzione", attivita.getItinerario().getRetribuzione().toString());
		map.addAttribute("valoreRetribuzione", attivita.getItinerario().getValoreRetribuzione().toString());
		map.addAttribute("statoAttivita", attivita.getStatoAttivita().toString());
		map.addAttribute("idCreatore", attivita.getCreatore().getIdAccount());
		map.addAttribute("nomeCreatore", attivita.getCreatore().getAnagrafica().getNome());
		map.addAttribute("cognomeCreatore", attivita.getCreatore().getAnagrafica().getCognome());
		return map;
	}
}
