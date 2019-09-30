package com.intuition.cicerone.ricerca;

import java.util.ArrayList;
import java.util.List;

import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;


public class RicercaLingua extends RicercaDecoratore {
	private String lingua;

	public RicercaLingua(IGestoreRicerca r, String lingua) {
		super(r);
		this.lingua = lingua;
	}

	@Override
	public List<IAttivita> ricerca() {
		List<IAttivita> risultato = new ArrayList<>();
		for (IAttivita a : super.ricerca.ricerca()) {
			if(!a.getLingua().isEmpty() && a.getLingua().toString().contains(this.lingua)) {
				risultato.add(a);
			}
		}
		return risultato;
	}

}
