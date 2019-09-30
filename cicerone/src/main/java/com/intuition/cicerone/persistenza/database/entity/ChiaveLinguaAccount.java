package com.intuition.cicerone.persistenza.database.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class ChiaveLinguaAccount implements Serializable {

	/**
	 * Id generato automaticamente
	 */
	private static final long serialVersionUID = -3796355294477665865L;
	
	@NotNull
	@Column(columnDefinition = "VARCHAR(32)")
	private String idAnagrafica;
	@NotNull
	@Column(columnDefinition = "VARCHAR(32)")
	private String lingua;
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	/**
	 * @return the idAnagrafica
	 */
	public String getIdAnagrafica() {
		return idAnagrafica;
	}
	/**
	 * @return the lingua
	 */
	public String getLingua() {
		return lingua;
	}
	/**
	 * @param idAnagrafica the idAnagrafica to set
	 */
	public void setIdAnagrafica(String idAnagrafica) {
		this.idAnagrafica = idAnagrafica;
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
		result = prime * result + ((idAnagrafica == null) ? 0 : idAnagrafica.hashCode());
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
		ChiaveLinguaAccount other = (ChiaveLinguaAccount) obj;
		if (idAnagrafica == null) {
			if (other.idAnagrafica != null) {
				return false;
			}
		} else if (!idAnagrafica.equals(other.idAnagrafica)) {
			return false;
		}
		if (lingua == null) {
			if (other.lingua != null) {
				return false;
			}
		} else if (!lingua.equals(other.lingua)) {
			return false;
		}
		return true;
	}

	
	
}
