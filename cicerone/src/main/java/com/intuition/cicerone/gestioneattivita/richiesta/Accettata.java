package com.intuition.cicerone.gestioneattivita.richiesta;

public class Accettata implements IStatoRichiesta {

	public IStatoRichiesta accetta() {
		throw new UnsupportedOperationException("La richiesta non può essere accettata se già accettata.");
	}

	public IStatoRichiesta rifiuta() {
		throw new UnsupportedOperationException("La richiesta non può essere rifiutata se già accettata.");
	}
	
	@Override
	public String toString() {
		return "accettata";	
	}

}
