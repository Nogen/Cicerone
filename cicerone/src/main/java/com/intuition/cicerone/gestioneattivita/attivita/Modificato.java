package com.intuition.cicerone.gestioneattivita.attivita;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import com.intuition.cicerone.autenticazione.IAccount;

public class Modificato implements IStatoAttivita{
private IAttivita attivita;
	
	public Modificato(IAttivita attivita) {
		this.attivita = attivita;
	}
	

	@Override
	public void aggiungi(IAccount a) {
		Set<IAccount> partecipanti = this.attivita.getPartecipanti();		
		partecipanti.add(a);
		this.attivita.setPartecipanti(partecipanti);
	}


	@Override
	public void elimina(IAccount a) {
		Set<IAccount> partecipanti = this.attivita.getPartecipanti();
		partecipanti.remove(a);
		this.attivita.setPartecipanti(partecipanti);		
	}


	@Override
	public Boolean modificaPunto(Double x, Double y, String luogo) {
		IItinerario itinerario = this.attivita.getItinerario();
		itinerario.setCoordinateIncontro(x, y);
		itinerario.setLuogo(luogo);
		this.attivita.modificaItinerario(itinerario);
		this.attivita.setDataModifica(LocalDate.now());
		return true;
		
	}


	@Override
	public Boolean modificaDataEOra(LocalDate d, LocalTime t) {
		IItinerario itinerario = this.attivita.getItinerario();
		itinerario.setDataIncontro(d);
		itinerario.setOraIncontro(t);
		this.attivita.modificaItinerario(itinerario);
		this.attivita.setStatoAttivita(new Modificato(this.attivita));
		this.attivita.setDataModifica(LocalDate.now());
		return true;
	}
	
	@Override
	public Boolean modificaDescrizione(String descrizione) {
		IItinerario itinerario = this.attivita.getItinerario();
		itinerario.setDescrizioneAttivita(descrizione);
		this.attivita.modificaItinerario(itinerario);
		this.attivita.setStatoAttivita(new Modificato(this.attivita));
		this.attivita.setDataModifica(LocalDate.now());
		return true;
	}

	@Override
	public Boolean isIscrivibile(IAccount a) {
		Set<IAccount> partecipanti = this.attivita.getPartecipanti();	
		return partecipanti.contains(a);
	}

	@Override
	public String toString() {
		return "modificato";
	}
}
