package com.intuition.cicerone.persistenza.database.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class FotoItinerarioEntity {
	@EmbeddedId
	private ChiaveFoto chiaveFoto;

	/**
	 * @return the chiaveFoto
	 */
	public ChiaveFoto getChiaveFoto() {
		return chiaveFoto;
	}

	/**
	 * @param chiaveFoto the chiaveFoto to set
	 */
	public void setChiaveFoto(ChiaveFoto chiaveFoto) {
		this.chiaveFoto = chiaveFoto;
	}
	
	
}
