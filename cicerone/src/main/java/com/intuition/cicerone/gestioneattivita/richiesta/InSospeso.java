package com.intuition.cicerone.gestioneattivita.richiesta;

public class InSospeso implements IStatoRichiesta{
	private IRichiesta richiesta;
	
	public InSospeso(IRichiesta richiesta) {
		this.richiesta = richiesta;		
	}

	public IStatoRichiesta accetta() {
		IStatoRichiesta stato = new Accettata();
		this.richiesta.getAttivita().aggiungiPartecipante(this.richiesta.getMittente());
		this.richiesta.setStatoRichiesta(stato);	
		return stato;
	}

	public IStatoRichiesta rifiuta() {
		IStatoRichiesta stato = new Rifiutata();
		this.richiesta.setStatoRichiesta(stato);
		return stato;
	}

	@Override
	public String toString() {
		return "in sospeso";
	}
	

}
