package com.intuition.cicerone.gestioneattivita.richiesta;

import java.time.LocalDate;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;

public interface IRichiesta {
	
	IStatoRichiesta accetta();
	IStatoRichiesta rifiuta();
	IAccount getMittente();
	String getIdRichiesta();
	IAttivita getAttivita();
	LocalDate getDataRichiesta();
	IStatoRichiesta getStatoRichiesta();
	Boolean setStatoRichiesta(IStatoRichiesta statoRichiesta);
}
