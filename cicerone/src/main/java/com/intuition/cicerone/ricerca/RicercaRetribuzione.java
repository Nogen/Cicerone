package com.intuition.cicerone.ricerca;

import java.util.ArrayList;
import java.util.List;


import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.gestioneattivita.attivita.Itinerario.Retribuzione;

public class RicercaRetribuzione extends RicercaDecoratore {
	private Double valore;
	
	public RicercaRetribuzione(IGestoreRicerca r, Double valore) {
		super(r);
		this.valore = valore;
	}

	@Override
	public List<IAttivita> ricerca() {
		List<IAttivita> risultato = new ArrayList<>();
		for (IAttivita a  : super.ricerca.ricerca()) {
			if (a.getRetribuzione() == Retribuzione.RETRIBUITO && a.getValoreRetribuzione() >= valore) {
				risultato.add(a);
			}
		}
		return risultato;
	}

}
