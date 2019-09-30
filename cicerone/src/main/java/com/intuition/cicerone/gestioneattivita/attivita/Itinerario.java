package com.intuition.cicerone.gestioneattivita.attivita;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;

import com.intuition.cicerone.autenticazione.utils.IdGenerator;
import com.intuition.cicerone.gestioneattivita.ErrorMessage;



public class Itinerario implements IItinerario {
	
	public static class BuilderItinerario {
		private String descrizioneAttivita;
		private String idItinerario;
		private Set<String> lingua;
		private Retribuzione retribuzione;
		private Double valoreRetribuzione;
		
		public IItinerario build() {
			return new Itinerario(this);
		}
		
		public BuilderItinerario setDescrizioneAttivita(String descrizioneAttivita) {
			if((descrizioneAttivita != null) && (descrizioneAttivita.length() > 0) && (descrizioneAttivita.length() <= 756)) {
				this.descrizioneAttivita = descrizioneAttivita;
			} else {
				throw new IllegalArgumentException(ErrorMessage.DESCRIZIONEERROR);				
			}		
			return this;
		}
		
		public BuilderItinerario generaIdItinerario() {
			this.idItinerario = IdGenerator.generateId();
			return this;
		}
		
		public BuilderItinerario setIdItinerario(String idItinerario) {
			if(idItinerario != null) {
				this.idItinerario = idItinerario;
			} else {
				throw new IllegalArgumentException(ErrorMessage.IDERROR);
			}
			return this;
		}
		
		public BuilderItinerario setLingua(Set<String> lingua) {
			if(lingua != null) {
				this.lingua = lingua;
			} else {
				throw new IllegalArgumentException(ErrorMessage.LINGUEERROR);
			}
			return this;
		}
		
		public BuilderItinerario setRetribuzione(Retribuzione retribuzione, Double valoreRetribuzione) {
			if((valoreRetribuzione != null)) {
				this.retribuzione = retribuzione;
				if (retribuzione.getValore() == 1 && valoreRetribuzione > 0 ) {
					this.valoreRetribuzione = valoreRetribuzione;
				} else if (retribuzione.getValore() == 0){
					this.valoreRetribuzione = (double)0;
				} else {
					throw new IllegalArgumentException(ErrorMessage.RETRIBUZIONEERROR);
				}
			} else {
				throw new IllegalArgumentException(ErrorMessage.RETRIBUZIONEERROR2);
			}
			return this;
		}		
	}
	
	public enum Retribuzione {
		NONRETRIBUITO(0), RETRIBUITO(1);
		private int valore;
		private Retribuzione(int valore) {
			this.valore = valore;
		}
		
		public int getValore() {
			return this.valore;
		}
	}
	
	private LocalDate dataIncontro;
	private String descrizioneAttivita;
	private String idItinerario;
	private Set<String> lingua;
	private String luogo;
	private LocalTime oraIncontro;
	private Retribuzione retribuzione;
	private Double xIncontro;
	private Double yIncontro;
	private Double valoreRetribuzione;
	
	private Itinerario(BuilderItinerario builder) {
		this.idItinerario = builder.idItinerario;
		this.descrizioneAttivita = builder.descrizioneAttivita;
		this.lingua = builder.lingua;
		this.retribuzione = builder.retribuzione;
		this.valoreRetribuzione = builder.valoreRetribuzione;
	}
	
	@Override
	public Double getValoreRetribuzione() {
		return this.valoreRetribuzione;
	}
	
	@Override
	public LocalDate getDataIncontro() {
		return dataIncontro;
	}
	
	@Override
	public String getDescrizioneAttivita() {
		return descrizioneAttivita;
	}


	@Override
	public String getIdItinerario() {
		return this.idItinerario;
	}

	@Override
	public Set<String> getLingua() {
		return lingua;
	}

	@Override
	public String getLuogo() {
		return luogo;
	}


	@Override
	public LocalTime getOraIncontro() {
		return oraIncontro;
	}

	@Override
	public Retribuzione getRetribuzione() {
		return retribuzione;
	}
	
	@Override
	public Double getxIncontro() {
		return xIncontro;
	}
	
	@Override
	public Double getyIncontro() {
		return yIncontro;
	}
	
	@Override
	public void setCoordinateIncontro(Double xIncontro, Double yIncontro) {
		if (xIncontro != null && yIncontro != null) {
			this.xIncontro = xIncontro;
			this.yIncontro = yIncontro;
		} else {
			throw new IllegalArgumentException(ErrorMessage.COORDINATEERROR);
		}
	}

	@Override
	public void setDataIncontro(LocalDate dataIncontro) {
		if (dataIncontro != null) {
			this.dataIncontro = dataIncontro;
		} else {
			throw new IllegalArgumentException(ErrorMessage.DATAERROR);
		}
	}

	@Override
	public void setLuogo(String luogo) {
		if (luogo != null && luogo.matches("[a-zA-ZÀ-ú0-9.,'#/ -]{4,300}")) {
			this.luogo = luogo;
		} else {
			throw new IllegalArgumentException(ErrorMessage.LUOGOERROR);
		}
	}

	@Override
	public void setOraIncontro(LocalTime oraIncontro) {
		if (oraIncontro != null) {
			this.oraIncontro = oraIncontro;
		} else {
			throw new IllegalArgumentException(ErrorMessage.ORARIOERROR);
		}
	}	
	
	public void setDescrizioneAttivita(String descrizioneAttivita) {
		if((descrizioneAttivita != null) && (descrizioneAttivita.length() > 0) && (descrizioneAttivita.length() <= 756)) {
			this.descrizioneAttivita = descrizioneAttivita;
		} else {
			throw new IllegalArgumentException(ErrorMessage.DESCRIZIONEERROR);				
		}	
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Itinerario) {
			IItinerario termine2 = (IItinerario)o;
			return termine2.getIdItinerario().equals(this.idItinerario);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this.idItinerario);
	}
	
}
