package com.intuition.cicerone.ricerca;

import java.util.ArrayList;
import java.util.List;

import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;


public class RicercaLuogo extends RicercaDecoratore {
	private String luogo;

	public RicercaLuogo(IGestoreRicerca r, String luogo) {
		super(r);
		this.luogo = luogo;
	}

	@Override
	public List<IAttivita> ricerca() {
		List<IAttivita> risultato = new ArrayList<>();
		for (IAttivita a : super.ricerca.ricerca()) {
			Boolean flag = true;
			String luog = a.getLuogo().toLowerCase();
			for (String s : this.luogo.split(" ")) {
				flag &= luog.contains(s.toLowerCase());
			}
			if (flag) {
				risultato.add(a);
			}
		}
		return risultato;
	}

}
