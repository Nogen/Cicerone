package com.intuition.cicerone.persistenza.database.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ChiaveLinguaAttivita implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7074498328698521369L;
	
	@Column(columnDefinition = "VARCHAR(32)")
	private String idItinerario;
	@Column(columnDefinition = "VARCHAR(100)")
	private String lingua;
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the idItinerario
	 */
	public String getIdItinerario() {
		return idItinerario;
	}
	/**
	 * @return the lingua
	 */
	public String getLingua() {
		return lingua;
	}
	/**
	 * @param idItinerario the idItinerario to set
	 */
	public void setIdItinerario(String idItinerario) {
		this.idItinerario = idItinerario;
	}
	/**
	 * @param lingua the lingua to set
	 */
	public void setLingua(String lingua) {
		this.lingua = lingua;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idItinerario == null) ? 0 : idItinerario.hashCode());
		result = prime * result + ((lingua == null) ? 0 : lingua.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiaveLinguaAttivita other = (ChiaveLinguaAttivita) obj;
		if (idItinerario == null) {
			if (other.idItinerario != null) {
				return false;
			}
		} else if (!idItinerario.equals(other.idItinerario)) {
			return false;
		} if (lingua == null) {
			if (other.lingua != null) {
				return false;
			}
		} else if (!lingua.equals(other.lingua)) {
			return false;
		}
		return true;
	}

	
}
