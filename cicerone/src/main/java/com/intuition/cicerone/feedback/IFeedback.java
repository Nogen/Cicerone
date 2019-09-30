package com.intuition.cicerone.feedback;

import java.time.LocalDate;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;

public interface IFeedback {
	IAccount getAutore();
	IAttivita getAttivita();
	String getIdFeedback();
	String getCommento();
	LocalDate getData();
	Integer getValutazione();
	
}
