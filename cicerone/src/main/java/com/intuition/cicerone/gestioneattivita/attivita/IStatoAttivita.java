package com.intuition.cicerone.gestioneattivita.attivita;

import java.time.LocalDate;
import java.time.LocalTime;

import com.intuition.cicerone.autenticazione.IAccount;

public interface IStatoAttivita {
	void aggiungi(IAccount a);
	void elimina(IAccount a);
	Boolean modificaPunto(Double x, Double y, String luogo);
	Boolean modificaDataEOra(LocalDate d, LocalTime t);
	Boolean modificaDescrizione(String descrizione);
	Boolean isIscrivibile(IAccount a);
}
