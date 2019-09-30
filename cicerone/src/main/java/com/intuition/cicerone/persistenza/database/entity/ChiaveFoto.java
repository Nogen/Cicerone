package com.intuition.cicerone.persistenza.database.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class ChiaveFoto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -335956602259405444L;
	
	@NotNull
	@Column(columnDefinition="VARCHAR(32)")
	private String idItinerario;
	@NotNull
	@Column(columnDefinition="VARCHAR(200)")
	private String foto;
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
	 * @return the foto
	 */
	public String getFoto() {
		return foto;
	}
	/**
	 * @param idItinerario the idItinerario to set
	 */
	public void setIdItinerario(String idItinerario) {
		this.idItinerario = idItinerario;
	}
	/**
	 * @param foto the foto to set
	 */
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((foto == null) ? 0 : foto.hashCode());
		result = prime * result + ((idItinerario == null) ? 0 : idItinerario.hashCode());
		return result;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ChiaveFoto other = (ChiaveFoto) obj;
		if (foto == null) {
			if (other.foto != null) {
				return false;
			}
		} else if (!foto.equals(other.foto)) {
			return false;
		}
		if (idItinerario == null) {
			if (other.idItinerario != null) {
				return false;
			}
		} else if (!idItinerario.equals(other.idItinerario)) {
			return false;
		}
		return true;
	}
	
	

}
