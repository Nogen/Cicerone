package com.intuition.cicerone.gestioneattivita.attivita;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import com.intuition.cicerone.gestioneattivita.attivita.Itinerario.Retribuzione;

public interface IItinerario {

	Double getValoreRetribuzione();

	LocalDate getDataIncontro();

	String getDescrizioneAttivita();

	String getIdItinerario();

	Set<String> getLingua();

	String getLuogo();

	LocalTime getOraIncontro();

	Retribuzione getRetribuzione();

	Double getxIncontro();

	Double getyIncontro();

	void setCoordinateIncontro(Double xIncontro, Double yIncontro);

	void setDataIncontro(LocalDate dataIncontro);

	void setLuogo(String luogo);

	void setOraIncontro(LocalTime oraIncontro);

	boolean equals(Object o);

	int hashCode();
	
	void setDescrizioneAttivita(String descrizioneAttivita);

}