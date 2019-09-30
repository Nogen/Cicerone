package com.intuition.cicerone.persistenza.database.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class AnagraficaEntity {

	@Id
	@Column(unique=true, columnDefinition="VARCHAR(32)")
	private String idAnagrafica;
	@Column(columnDefinition="VARCHAR(100)")
	private String nome;
	@Column(columnDefinition="VARCHAR(100)")
	private String cognome;
	@Column(unique=true, columnDefinition="VARCHAR(10)")
	private String numeroDiTelefono;
	private Boolean sesso;
	@Column(columnDefinition="VARCHAR(100)")
	private String citta;
	
	private LocalDate dataDiNascita;
	
	
	
	public String getIdAnagrafica() {
		return idAnagrafica;
	}
	
	public void setIdAnagrafica(String idAnagrafica) {
		this.idAnagrafica = idAnagrafica;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getNumeroDiTelefono() {
		return numeroDiTelefono;
	}
	
	public void setNumeroDiTelefono(String numeroDiTelefono) {
		this.numeroDiTelefono = numeroDiTelefono;
	}
	
	public Boolean getSesso() {
		return sesso;
	}
	
	public void setSesso(Boolean sesso) {
		this.sesso = sesso;
	}
	
	public String getCitta() {
		return citta;
	}
	
	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}
	
	public void setDataDiNascita(LocalDate dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((citta == null) ? 0 : citta.hashCode());
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((dataDiNascita == null) ? 0 : dataDiNascita.hashCode());
		result = prime * result + ((idAnagrafica == null) ? 0 : idAnagrafica.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((numeroDiTelefono == null) ? 0 : numeroDiTelefono.hashCode());
		result = prime * result + ((sesso == null) ? 0 : sesso.hashCode());
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
		AnagraficaEntity other = (AnagraficaEntity) obj;
		if (citta == null) {
			if (other.citta != null) {
				return false;
			}
		} else if (!citta.equals(other.citta)) {
			return false;
		}
		if (cognome == null) {
			if (other.cognome != null) {
				return false;
			}
		} else if (!cognome.equals(other.cognome)) {
			return false;
		}
		if (dataDiNascita == null) {
			if (other.dataDiNascita != null) {
				return false;
			}
		} else if (!dataDiNascita.equals(other.dataDiNascita)) {
			return false;
		}
		if (idAnagrafica == null) {
			if (other.idAnagrafica != null) {
				return false;
			}
		} else if (!idAnagrafica.equals(other.idAnagrafica)) {
			return false;
		}
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		if (numeroDiTelefono == null) {
			if (other.numeroDiTelefono != null) {
				return false;
			}
		} else if (!numeroDiTelefono.equals(other.numeroDiTelefono)) {
			return false;
		}
		if (sesso == null) {
			if (other.sesso != null) {
				return false;
			}
		} else if (!sesso.equals(other.sesso)) {
			return false;
		}
		return true;
	}

	
	
	
}
