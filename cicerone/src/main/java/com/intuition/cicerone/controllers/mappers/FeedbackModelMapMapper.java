package com.intuition.cicerone.controllers.mappers;

import org.springframework.ui.ModelMap;

import com.intuition.cicerone.feedback.IFeedback;

public class FeedbackModelMapMapper implements IMapper<ModelMap>{

	private IFeedback feedback;
	
	public FeedbackModelMapMapper(IFeedback feedback) {
		this.feedback = feedback;
	}
	
	@Override
	public ModelMap converti() {
		ModelMap map = new ModelMap();
		
		map.addAttribute("nome", feedback.getAutore().getAnagrafica().getNome());
		map.addAttribute("cognome", feedback.getAutore().getAnagrafica().getCognome());
		map.addAttribute("commento", feedback.getCommento());
		map.addAttribute("data", feedback.getData().toString());
		map.addAttribute("valutazione", feedback.getValutazione().toString());		
		return map;
	}
}
