package com.intuition.cicerone.gestioneattivita.richiesta;

import com.intuition.cicerone.gestioneattivita.Factory;

public class StatoRichiestaFactory implements Factory<IStatoRichiesta, String> {
	private IRichiesta r;
	
	public StatoRichiestaFactory(IRichiesta r) {
		this.r = r;
	}

	@Override
	public IStatoRichiesta crea(String param) {
		switch(param.toLowerCase()) {
		case "accettata":
			return new Accettata();
		case "in sospeso":
			return new InSospeso(this.r);
		case "rifiutata":
			return new Rifiutata();
		default:
			throw new IllegalArgumentException("Stato non valido");
			
		}
	}

}
