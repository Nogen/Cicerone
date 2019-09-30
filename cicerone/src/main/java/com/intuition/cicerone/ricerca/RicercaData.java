package com.intuition.cicerone.ricerca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;



public class RicercaData extends RicercaDecoratore {
	private LocalDate dataRicerca;

	public RicercaData(IGestoreRicerca r, LocalDate dataRicerca) {
		super(r);
		this.dataRicerca = dataRicerca;
	}

	@Override
	public List<IAttivita> ricerca() {
		List<IAttivita> risultato = new ArrayList<>();
		for (IAttivita a : super.ricerca.ricerca()) {
			if (a.getDataIncontro().compareTo(this.dataRicerca) == 0) {
				risultato.add(a);
			}
		}
		return risultato;
	}

}
