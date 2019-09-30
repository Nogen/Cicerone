package com.intuition.cicerone.persistenza.database.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


@Entity
public class PartecipantiEntity {
	
	@EmbeddedId
	private ChiavePartecipanti chiavePartecipanti;

	/**
	 * @return the chiavePartecipanti
	 */
	public ChiavePartecipanti getChiavePartecipanti() {
		return chiavePartecipanti;
	}

	/**
	 * @param chiavePartecipanti the chiavePartecipanti to set
	 */
	public void setChiavePartecipanti(ChiavePartecipanti chiavePartecipanti) {
		this.chiavePartecipanti = chiavePartecipanti;
	}
	
	
}
