package com.intuition.cicerone.persistenza.database.entity;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


@Entity
public class LinguaAccountEntity {
	@EmbeddedId
	private ChiaveLinguaAccount chiaveComposta;

	/**
	 * @return the chiaveComposta
	 */
	public ChiaveLinguaAccount getChiaveComposta() {
		return chiaveComposta;
	}

	/**
	 * @param chiaveComposta the chiaveComposta to set
	 */
	public void setChiaveComposta(ChiaveLinguaAccount chiaveComposta) {
		this.chiaveComposta = chiaveComposta;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((chiaveComposta == null) ? 0 : chiaveComposta.hashCode());
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
		LinguaAccountEntity other = (LinguaAccountEntity) obj;
		if (chiaveComposta == null) {
			if (other.chiaveComposta != null) {
				return false;
			}
		} else if (!chiaveComposta.equals(other.chiaveComposta)) {
			return false;
		}
		return true;
	}

	
}
