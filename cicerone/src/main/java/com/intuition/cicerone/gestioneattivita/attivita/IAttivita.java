package com.intuition.cicerone.gestioneattivita.attivita;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.gestioneattivita.attivita.Itinerario.Retribuzione;

public interface IAttivita {

	IAccount getCreatore();

	String getTitolo();

	LocalDate getDataIncontro();

	LocalTime getOraIncontro();

	Set<String> getLingua();

	Retribuzione getRetribuzione();
	
	Double getValoreRetribuzione();

	IStatoAttivita getStatoAttivita();

	Set<IAccount> getPartecipanti();

	String getIdAttivita();

	LocalDate getDataApertura();

	LocalDate getDataModifica();

	String getLuogo();
	
	IItinerario getItinerario();
	
	String getDescrizioneAttivita();

	Double getxIncontro();

	Double getyIncontro();
	
	void modificaItinerario(IItinerario itinerario);

	void setDataModifica(LocalDate dataModifica);

	void setPartecipanti(Set<IAccount> partecipanti);

	void setStatoAttivita(IStatoAttivita statoAttivita);

	Boolean modificaPuntoIncontro(Double x, Double y, String luogo);

	Boolean modificaDataEOraIncontro(LocalDate dataIncontro, LocalTime oraIncontro);

	Boolean aggiungiPartecipante(IAccount a);

	Boolean eliminaPartecipante(IAccount a);

	Boolean chiudiAttivita();

	Boolean isIscrivibile(IAccount a);
	
	Boolean modificaDescrizione(String descrizione);

}