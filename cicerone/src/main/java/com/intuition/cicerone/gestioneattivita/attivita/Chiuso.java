package com.intuition.cicerone.gestioneattivita.attivita;

import java.time.LocalDate;
import java.time.LocalTime;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.gestioneattivita.ErrorMessage;

public class Chiuso implements IStatoAttivita{

	


	@Override
	public void aggiungi(IAccount a) {
		throw new UnsupportedOperationException();
		
	}
	
	@Override
	public void elimina(IAccount a) {
		throw new UnsupportedOperationException(ErrorMessage.ATTIVITACHIUSA);
		
	}

	@Override
	public Boolean modificaPunto(Double x, Double y, String luogo) {
		throw new UnsupportedOperationException(ErrorMessage.ATTIVITACHIUSA);
		
	}

	@Override
	public Boolean modificaDataEOra(LocalDate d, LocalTime t) {
		throw new UnsupportedOperationException(ErrorMessage.ATTIVITACHIUSA);
		
	}

	@Override
	public Boolean modificaDescrizione(String descrizione) {
		throw new UnsupportedOperationException(ErrorMessage.ATTIVITACHIUSA);
	}
	
	@Override
	public Boolean isIscrivibile(IAccount a) {
		throw new UnsupportedOperationException(ErrorMessage.ATTIVITACHIUSA);
	}
	
	
	@Override
	public String toString() {
		return "chiuso";
	}

}
