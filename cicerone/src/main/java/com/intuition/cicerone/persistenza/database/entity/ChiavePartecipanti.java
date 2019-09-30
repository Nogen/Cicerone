package com.intuition.cicerone.persistenza.database.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class ChiavePartecipanti implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3684279009685172167L;

	@NotNull
	@Column(columnDefinition="VARCHAR(32)")
	private String idAttivita;
	
	@NotNull
	@Column(columnDefinition="VARCHAR(32)")
	private String idAccount;

	
	
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the idAttivita
	 */
	public String getIdAttivita() {
		return idAttivita;
	}

	/**
	 * @return the idAccount
	 */
	public String getIdAccount() {
		return idAccount;
	}

	/**
	 * @param idAttivita the idAttivita to set
	 */
	public void setIdAttivita(String idAttivita) {
		this.idAttivita = idAttivita;
	}

	/**
	 * @param idAccount the idAccount to set
	 */
	public void setIdAccount(String idAccount) {
		this.idAccount = idAccount;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idAccount == null) ? 0 : idAccount.hashCode());
		result = prime * result + ((idAttivita == null) ? 0 : idAttivita.hashCode());
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
		ChiavePartecipanti other = (ChiavePartecipanti) obj;
		if (idAccount == null) {
			if (other.idAccount != null) {
				return false;
			}
		} else if (!idAccount.equals(other.idAccount)) {
			return false;
		} if (idAttivita == null) {
			if (other.idAttivita != null) {
				return false;
			}
		} else if (!idAttivita.equals(other.idAttivita)) {
			return false;
		}
		return true;
	}
	
	
	

}
