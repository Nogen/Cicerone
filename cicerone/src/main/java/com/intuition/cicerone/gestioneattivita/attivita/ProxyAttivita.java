package com.intuition.cicerone.gestioneattivita.attivita;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.gestioneattivita.attivita.Itinerario.Retribuzione;

public class ProxyAttivita implements IAttivita {
	private static final String PERMISSIONGLOBETROTTER = "globetrotter";
	private static final String PERMISSIONCICERONE = "cicerone";
	private static final String PERMISSIONADMIN = "admin";
	private static final String ERRORMSG = "Permessi insufficenti per l'utilizzo di questa operazione";
	
	
	private String ruolo;
	private IAttivita attivita;

	public ProxyAttivita(String ruolo, IAttivita attivita) {
		this.ruolo = ruolo;
		this.attivita = attivita;
	}

	@Override
	public IAccount getCreatore() {
		return this.attivita.getCreatore();
	}

	@Override
	public String getTitolo() {
		return this.attivita.getTitolo();
	}

	@Override
	public LocalDate getDataIncontro() {
		return this.attivita.getDataIncontro();
	}

	@Override
	public LocalTime getOraIncontro() {
		return this.attivita.getOraIncontro();
	}

	@Override
	public Set<String> getLingua() {
		return this.attivita.getLingua();
	}

	@Override
	public Retribuzione getRetribuzione() {
		return this.attivita.getRetribuzione();
	}
	
	@Override
	public Double getValoreRetribuzione() {
		return this.attivita.getValoreRetribuzione();
	}

	@Override
	public IStatoAttivita getStatoAttivita() {
		return this.attivita.getStatoAttivita();
	}

	@Override
	public Set<IAccount> getPartecipanti() {
		return this.attivita.getPartecipanti();
	}

	@Override
	public String getIdAttivita() {
		return this.attivita.getIdAttivita();
	}

	@Override
	public LocalDate getDataApertura() {
		return this.attivita.getDataApertura();
	}

	@Override
	public LocalDate getDataModifica() {
		return this.attivita.getDataModifica();
	}

	@Override
	public String getLuogo() {
		return this.attivita.getLuogo();
	}

	
	@Override
	public void setDataModifica(LocalDate dataModifica) {
		this.attivita.setDataModifica(dataModifica);
		
	}

	@Override
	public void setPartecipanti(Set<IAccount> partecipanti) {
		this.attivita.setPartecipanti(partecipanti);
		
	}

	@Override
	public void setStatoAttivita(IStatoAttivita statoAttivita) {
		this.attivita.setStatoAttivita(statoAttivita);
		
	}

	@Override
	public String getDescrizioneAttivita() {
		return this.attivita.getDescrizioneAttivita();
	}

	@Override
	public Double getxIncontro() {
		return this.attivita.getxIncontro();
	}

	@Override
	public Double getyIncontro() {
		return this.attivita.getyIncontro();
	}


	@Override
	public Boolean modificaDataEOraIncontro(LocalDate dataIncontro, LocalTime t) {
		if (this.ruolo.equalsIgnoreCase(PERMISSIONADMIN) || this.ruolo.equalsIgnoreCase(PERMISSIONCICERONE)) {
			return this.attivita.modificaDataEOraIncontro(dataIncontro, t);
		} else {
			throw new UnsupportedOperationException("");
		}
		
	}


	@Override
	public Boolean aggiungiPartecipante(IAccount a) {
		if (this.ruolo.equalsIgnoreCase(PERMISSIONADMIN) || this.ruolo.equalsIgnoreCase(PERMISSIONCICERONE)) {
			return this.attivita.aggiungiPartecipante(a);
		} else {
			throw new UnsupportedOperationException(ERRORMSG);
		}
		
	}

	@Override
	public Boolean eliminaPartecipante(IAccount a) {
		if (this.ruolo.equalsIgnoreCase(PERMISSIONADMIN) || this.ruolo.equalsIgnoreCase(PERMISSIONCICERONE)) {
			return this.attivita.eliminaPartecipante(a);
		} else {
			throw new UnsupportedOperationException(ERRORMSG);
		}
		
	}

	@Override
	public Boolean chiudiAttivita() {
		if (this.ruolo.equalsIgnoreCase(PERMISSIONADMIN) || this.ruolo.equalsIgnoreCase(PERMISSIONCICERONE)) {
			return this.attivita.chiudiAttivita();
		} else {
			throw new UnsupportedOperationException(ERRORMSG);
		}
		
	}

	@Override
	public Boolean isIscrivibile(IAccount a) {
		if (this.ruolo.equalsIgnoreCase(PERMISSIONADMIN) || this.ruolo.equalsIgnoreCase(PERMISSIONGLOBETROTTER)) {
			return this.attivita.isIscrivibile(a);
		} else {
			throw new UnsupportedOperationException(ERRORMSG);
		}
	}

	@Override
	public IItinerario getItinerario() {
		return this.attivita.getItinerario();
	}

	@Override
	public void modificaItinerario(IItinerario itinerario) {
		this.attivita.modificaItinerario(itinerario);
		
	}

	@Override
	public Boolean modificaPuntoIncontro(Double x, Double y, String luogo) {
		if (this.ruolo.equalsIgnoreCase(PERMISSIONADMIN) || this.ruolo.equalsIgnoreCase(PERMISSIONCICERONE)) {
			return this.attivita.modificaPuntoIncontro(x, y, luogo);
		} else {
			throw new UnsupportedOperationException(ERRORMSG);
		}
	}

	@Override
	public Boolean modificaDescrizione(String descrizione) {
		if (this.ruolo.equalsIgnoreCase(PERMISSIONADMIN) || this.ruolo.equalsIgnoreCase(PERMISSIONCICERONE)) {
			return this.attivita.modificaDescrizione(descrizione);
		} else {
			throw new UnsupportedOperationException(ERRORMSG);
		}
	}
}
