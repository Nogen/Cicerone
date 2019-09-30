package com.intuition.cicerone.gestioneattivita.attivita;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.autenticazione.utils.IdGenerator;
import com.intuition.cicerone.gestioneattivita.ErrorMessage;
import com.intuition.cicerone.gestioneattivita.attivita.Itinerario.Retribuzione;

public class Attivita implements IAttivita{
	private LocalDate dataApertura;
	private LocalDate dataModifica;
	private String idAttivita;
	private String titolo;
	private IItinerario itinerario;
	private IStatoAttivita statoAttivita;
	private Set<IAccount> partecipanti; 
	private IAccount creatore;
	
	public Attivita(BuilderAttivita builder) {
		this.idAttivita = builder.idAttivita;
		this.titolo = builder.titolo;
		this.itinerario = builder.itinerario;
		this.creatore = builder.creatore;
		this.dataApertura = builder.dataApertura;
		this.dataModifica = builder.dataApertura;
	}
	

	public static class BuilderAttivita {
		private String idAttivita;
		private String titolo;
		private IItinerario itinerario;
		private IAccount creatore;
		private LocalDate dataApertura;
		
		public BuilderAttivita generaIdAttivta() {
			this.idAttivita = IdGenerator.generateId();
			return this;
		}
		
		public BuilderAttivita setIdAttivita(String idAttivita) {
			if(idAttivita != null) {
				this.idAttivita = idAttivita;
			} else {
				throw new IllegalArgumentException(ErrorMessage.IDERROR);
			}
			return this;
		}
		public BuilderAttivita setTitolo(String titolo) {
			if (titolo != null && titolo.length() <= 200 && titolo.length() > 0) {
				this.titolo = titolo;
			} else { 
				throw new IllegalArgumentException(ErrorMessage.TITOLOERROR);
			}
			return this;
		}
		
		public BuilderAttivita setItinerario(IItinerario itinerario) {
			if(itinerario != null) {
				this.itinerario = itinerario;
			} else {
				throw new IllegalArgumentException(ErrorMessage.ITINERARIOERROR);
			}			
			return this;
		}
		
		public BuilderAttivita setCreatore(IAccount creatore) {
			if(creatore != null) {
				this.creatore = creatore;
			} else {
				throw new IllegalArgumentException("Account non valido");
			}			
			return this;
		}
		
		public BuilderAttivita setDataApertura(LocalDate dataApertura) {
			if(dataApertura != null) {
				this.dataApertura = dataApertura;
			} else {
				throw new IllegalArgumentException(ErrorMessage.DATAERROR);
			} 			
			return this;
		}
		
		public IAttivita build() {
			IAttivita attivita = new Attivita(this);
			attivita.setStatoAttivita(new Aperto(attivita));
			return attivita;
		}		
	}
	
	
	@Override
	public IAccount getCreatore() {
		return creatore;
	}

	@Override
	public String getTitolo() {
		return titolo;
	}

	@Override
	public LocalDate getDataIncontro() {
		return this.itinerario.getDataIncontro();
	}

	@Override
	public LocalTime getOraIncontro() {
		return this.itinerario.getOraIncontro();
	}

	@Override
	public Set<String> getLingua() {
		return this.itinerario.getLingua();
	}
	
	@Override
	public Retribuzione getRetribuzione() {
		return this.itinerario.getRetribuzione();
	}
	
	@Override
	public Double getValoreRetribuzione() {
		return this.itinerario.getValoreRetribuzione();
	}

	@Override
	public IStatoAttivita getStatoAttivita() {
		return statoAttivita;
	}

	@Override
	public Set<IAccount> getPartecipanti() {
		return partecipanti;
	}
	
	@Override
	public String getIdAttivita() {
		return idAttivita;
	}
	
	@Override
	public LocalDate getDataApertura() {
		return dataApertura;
	}

	@Override
	public LocalDate getDataModifica() {
		return dataModifica;
	}
	
	@Override
	public String getLuogo() {
		return this.itinerario.getLuogo();
	}	

	@Override
	public void setDataModifica(LocalDate dataModifica) {
		if ( dataModifica != null) {
			this.dataModifica = dataModifica;
		} else {
			throw new IllegalArgumentException(ErrorMessage.DATAERROR);
		}
	}

	@Override
	public void setPartecipanti(Set<IAccount> partecipanti) {
		if (partecipanti != null) {
			this.partecipanti = partecipanti;
		} else {
			throw new IllegalArgumentException(ErrorMessage.PARTECIPANTIERROR);
		}
	}
	
	@Override
	public void setStatoAttivita(IStatoAttivita statoAttivita) {
		if (statoAttivita != null) {
			this.statoAttivita = statoAttivita;
		} else {
			throw new IllegalArgumentException(ErrorMessage.STATOATTIVITAERROR);
		}
	}

	@Override
	public String getDescrizioneAttivita() {
		return this.itinerario.getDescrizioneAttivita();
	}
	
	@Override
	public Double getxIncontro() {
		return this.itinerario.getxIncontro();
	}
	
	@Override
	public Double getyIncontro() {
		return this.itinerario.getyIncontro();
	}
	
	@Override
	public Boolean aggiungiPartecipante(IAccount a) {
		if (a != null && !partecipanti.contains(a)) {
			statoAttivita.aggiungi(a);
			return true;
		} else {
			throw new IllegalArgumentException(ErrorMessage.ACCOUNTERROR2);
		}
	}
	
	@Override
	public Boolean eliminaPartecipante(IAccount a) {
		if (a != null && partecipanti.contains(a)) {
			statoAttivita.elimina(a);
			return true;
		} else {
			throw new IllegalArgumentException(ErrorMessage.ACCOUNTERROR3);
		}
	}
	
	@Override
	public Boolean modificaPuntoIncontro(Double x, Double y, String luogo) {
		if (x != null && y != null && luogo != null) {
			return statoAttivita.modificaPunto(x, y, luogo);
		} else {
			throw new IllegalArgumentException(ErrorMessage.COORDINATEERROR);
		}
	}
	
	@Override
	public Boolean modificaDataEOraIncontro(LocalDate d, LocalTime t) {
		if (d != null && t != null) {
			return statoAttivita.modificaDataEOra(d, t);
		} else {
			throw new IllegalArgumentException(ErrorMessage.DATAERROR2);
		}
	}
	
	public Boolean modificaDescrizione(String descrizione) {
		if (descrizione != null) {
			return statoAttivita.modificaDescrizione(descrizione);
		} else {
			throw new IllegalArgumentException(ErrorMessage.DESCRIZIONEERROR);
		}
	}
	
	@Override
	public Boolean chiudiAttivita() {
		statoAttivita = new Chiuso();
		return true;
	}
	
	@Override
	public Boolean isIscrivibile(IAccount a) {
		if (a != null) {
			return statoAttivita.isIscrivibile(a);
		} else {
			throw new IllegalArgumentException(ErrorMessage.ACCOUNTERROR);
		}
	}

	@Override
	public IItinerario getItinerario() {
		return this.itinerario;
	}

	@Override
	public void modificaItinerario(IItinerario itinerario) {
		if (itinerario != null) {
			this.itinerario = itinerario;
		} else {
			throw new IllegalArgumentException(ErrorMessage.ITINERARIOERROR);
		}
	}
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append(this.getIdAttivita());
		b.append(" ");
		b.append(this.getDescrizioneAttivita());
		b.append(" ");
		b.append(this.getLuogo());
		b.append(" ");
		b.append(this.getTitolo());
		b.append(" ");
		b.append(this.getCreatore().getAnagrafica().getNome());
		b.append(" ");
		b.append(this.getCreatore().getAnagrafica().getCognome());
		b.append(" ");
		b.append(this.getCreatore().getEmail());
		b.append(" ");
		b.append(this.getDataApertura().toString());
		b.append(" ");
		b.append(this.getDataIncontro().toString());
		b.append(" ");
		b.append(this.getDataModifica().toString());
		b.append(" ");
		b.append(this.getLingua().toString());
		b.append(" ");
		b.append(this.getOraIncontro().toString());
		b.append(" ");
		b.append(this.getRetribuzione().toString());
		b.append(" ");
		b.append(this.getStatoAttivita().toString());
		b.append(" ");
		b.append(this.getValoreRetribuzione());
		b.append(" ");
		b.append(this.getxIncontro());
		b.append(" ");
		b.append(this.getyIncontro());
		b.append(" ");
		return b.toString();
		
	}
}
