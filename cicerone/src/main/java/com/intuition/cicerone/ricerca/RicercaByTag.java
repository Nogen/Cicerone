package com.intuition.cicerone.ricerca;

import java.util.ArrayList;
import java.util.List;

import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;

public class RicercaByTag extends  RicercaDecoratore {
	private String key;
	

	public RicercaByTag(IGestoreRicerca r, String key) {
		super(r);
		this.key = key;
	}

	@Override
	public List<IAttivita> ricerca() {
		List<IAttivita> risultato = new ArrayList<>();
		for (IAttivita a : super.ricerca.ricerca()) {
			Boolean flag = true;
			String luog = a.toString().toLowerCase();
			for (String s : this.key.split(" ")) {
				flag &= luog.contains(s.toLowerCase());
			}
			if (flag) {
				risultato.add(a);
			}
		}
		return risultato;
	}

}
