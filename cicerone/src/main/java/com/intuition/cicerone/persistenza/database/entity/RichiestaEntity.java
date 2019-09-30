package com.intuition.cicerone.persistenza.database.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RichiestaEntity {

	@Id
	@Column(unique=true, columnDefinition="VARCHAR(32)")
	private String idRichiesta;
	@Column(columnDefinition="VARCHAR(20)")
	private String statoRichiesta;
	private LocalDate dataRichiesta;
	@Column(columnDefinition="VARCHAR(32)")
	private String idRichiedente;
	@Column(columnDefinition="VARCHAR(32)")
	private String idAttivita;
	
	public String getIdRichiesta() {
		return idRichiesta;
	}
	
	public void setIdRichiesta(String idRichiesta) {
		this.idRichiesta = idRichiesta;
	}
	
	public String getStatoRichiesta() {
		return statoRichiesta;
	}
	
	public void setStatoRichiesta(String statoRichiesta) {
		this.statoRichiesta = statoRichiesta;
	}
	
	public LocalDate getDataRichiesta() {
		return dataRichiesta;
	}
	
	public void setDataRichiesta(LocalDate dataRichiesta) {
		this.dataRichiesta = dataRichiesta;
	}
	
	public String getIdRichiedente() {
		return idRichiedente;
	}
	
	public void setIdRichiedente(String idRichiedente) {
		this.idRichiedente = idRichiedente;
	}
	
	public String getIdAttivita() {
		return idAttivita;
	}
	
	public void setIdAttivita(String idAttivita) {
		this.idAttivita = idAttivita;
	}	
}
