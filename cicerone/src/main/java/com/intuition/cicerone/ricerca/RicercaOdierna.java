package com.intuition.cicerone.ricerca;

import java.util.List;

import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;


public class RicercaOdierna implements IGestoreRicerca {
	private List<IAttivita> listaAttivita;
	
	public RicercaOdierna(List<IAttivita> listaAttivita) {
		this.listaAttivita = listaAttivita;		
	}

	public List<IAttivita> ricerca() {
		return listaAttivita;
	}
}
