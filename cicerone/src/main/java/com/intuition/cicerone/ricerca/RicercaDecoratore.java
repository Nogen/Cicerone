package com.intuition.cicerone.ricerca;

public abstract class RicercaDecoratore implements IGestoreRicerca{
	IGestoreRicerca ricerca;
	
	public RicercaDecoratore(IGestoreRicerca r) {
		this.ricerca = r;
	}
}
