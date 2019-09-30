package com.intuition.cicerone.persistenza.database.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AccountEntity {

	@Id
	@Column(unique=true, columnDefinition="VARCHAR(32)")
	private String idAccount;
	@Column(unique=true, columnDefinition="VARCHAR(32)")
	private String idAnagrafica;
	@Column(unique=true, columnDefinition="VARCHAR(100)")
	private String email;
	
	private int numeroNotifiche;
	@Column(columnDefinition="VARCHAR(64)")
	private String password;
	@Column(columnDefinition="VARCHAR(100)")
	private String ruolo;

	public String getIdAccount() {
		return idAccount;
	}
	public void setIdAccount(String idAccount) {
		this.idAccount = idAccount;
	}
	public String getIdAnagrafica() {
		return idAnagrafica;
	}
	public void setIdAnagrafica(String idAnagrafica) {
		this.idAnagrafica = idAnagrafica;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getNumeroNotifiche() {
		return numeroNotifiche;
	}
	public void setNumeroNotifiche(int numeroNotifiche) {
		this.numeroNotifiche = numeroNotifiche;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((idAccount == null) ? 0 : idAccount.hashCode());
		result = prime * result + ((idAnagrafica == null) ? 0 : idAnagrafica.hashCode());
		result = prime * result + numeroNotifiche;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((ruolo == null) ? 0 : ruolo.hashCode());
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
		AccountEntity other = (AccountEntity) obj;
		if (email == null) {
			if (other.email != null) {
				return false;
			}
		} else if (!email.equals(other.email)) {
			return false;
		}
		if (idAccount == null) {
			if (other.idAccount != null) {
				return false;
			}
		} else if (!idAccount.equals(other.idAccount)) {
			return false;
		}
		if (idAnagrafica == null) {
			if (other.idAnagrafica != null) {
				return false;
			}
		} else if (!idAnagrafica.equals(other.idAnagrafica)) {
			return false;
		}
		if (numeroNotifiche != other.numeroNotifiche) {
			return false;
		}
		if (password == null) {
			if (other.password != null) {
				return false;
			}
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (ruolo == null) {
			if (other.ruolo != null) {
				return false;
			}
		} else if (!ruolo.equals(other.ruolo)) {
			return false;
		}
		return true;
	}	
	
}

