package com.intuition.cicerone.gestioneattivita.richiesta;

public class Rifiutata implements IStatoRichiesta{

	public IStatoRichiesta accetta() {
		throw new UnsupportedOperationException("La richiesta non può essere accettata se già rifiutata");
	}

	public IStatoRichiesta rifiuta() {
		throw new UnsupportedOperationException("La richiesta non può essere rifiutata se già rifiutata");
	}
	
	@Override
	public String toString() {
		return "rifiutata";
	}
	
}
