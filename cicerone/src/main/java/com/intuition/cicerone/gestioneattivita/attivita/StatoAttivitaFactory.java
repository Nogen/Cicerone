package com.intuition.cicerone.gestioneattivita.attivita;

import com.intuition.cicerone.gestioneattivita.Factory;

public class StatoAttivitaFactory implements Factory<IStatoAttivita, String> {
	private IAttivita a;

	public StatoAttivitaFactory(IAttivita a) {
		this.a = a;
	}
	
	@Override
	public IStatoAttivita crea(String nomeStato) {
		switch(nomeStato.toLowerCase()) {
		case "aperto":
			return new Aperto(a);

		case "chiuso":
			return new Chiuso();

		case "modificato":
			return new Modificato(a);

		default:
			throw new IllegalArgumentException("Stato inesistente");
		}
	}

}
