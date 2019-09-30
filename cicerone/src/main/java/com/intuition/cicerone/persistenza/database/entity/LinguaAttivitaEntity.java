package com.intuition.cicerone.persistenza.database.entity;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


@Entity
public class LinguaAttivitaEntity {
	
	@EmbeddedId
	private ChiaveLinguaAttivita chiaveLinguaAttivita;

	/**
	 * @return the chiaveLinguaAttivita
	 */
	public ChiaveLinguaAttivita getChiaveLinguaAttivita() {
		return chiaveLinguaAttivita;
	}

	/**
	 * @param chiaveLinguaAttivita the chiaveLinguaAttivita to set
	 */
	public void setChiaveLinguaAttivita(ChiaveLinguaAttivita chiaveLinguaAttivita) {
		this.chiaveLinguaAttivita = chiaveLinguaAttivita;
	}
	
	
}
