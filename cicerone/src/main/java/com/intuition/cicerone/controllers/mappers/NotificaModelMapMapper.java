package com.intuition.cicerone.controllers.mappers;

import org.springframework.ui.ModelMap;

import com.intuition.cicerone.notificazione.INotifica;

public class NotificaModelMapMapper implements IMapper<ModelMap> {

	private INotifica notifica;
	
	public NotificaModelMapMapper(INotifica notifica) {
		this.notifica = notifica;
	}
	
	@Override
	public ModelMap converti() {
		ModelMap map = new ModelMap();
		
		map.addAttribute("idAttivita", notifica.getAttivita().getIdAttivita());
		map.addAttribute("titoloAttivita", notifica.getAttivita().getTitolo());
		map.addAttribute("dataApertura", notifica.getDataDiApertura().toString());
		map.addAttribute("evento", notifica.getEvento().toString());
		return map;
	}
}
