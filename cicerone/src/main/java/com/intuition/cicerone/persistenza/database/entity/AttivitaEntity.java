package com.intuition.cicerone.persistenza.database.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AttivitaEntity {

	@Id
	@Column(unique=true, columnDefinition="VARCHAR(32)")
	private String idAttivita;
	private LocalDate dataApertura;
	@Column(columnDefinition="VARCHAR(100)")
	private String titoloAttivita;
	@Column(columnDefinition="VARCHAR(20)")
	private String statoAttivita;
	private LocalDate dataModifica;
	@Column(columnDefinition="VARCHAR(32)")
	private String idItinerario;
	@Column(columnDefinition="VARCHAR(32)")
	private String idCreatore;
	
	public String getIdAttivita() {
		return idAttivita;
	}
	
	public void setIdAttivita(String idAttivita) {
		this.idAttivita = idAttivita;
	}
	
	public LocalDate getDataApertura() {
		return dataApertura;
	}
	
	public void setDataApertura(LocalDate dataApertura) {
		this.dataApertura = dataApertura;
	}
	
	public String getTitoloAttivita() {
		return titoloAttivita;
	}
	
	public void setTitoloAttivita(String titoloAttivita) {
		this.titoloAttivita = titoloAttivita;
	}
	
	public String getStatoAttivita() {
		return statoAttivita;
	}
	
	public void setStatoAttivita(String statoAttivita) {
		this.statoAttivita = statoAttivita;
	}
	
	public LocalDate getDataModifica() {
		return dataModifica;
	}
	
	public void setDataModifica(LocalDate dataModifica) {
		this.dataModifica = dataModifica;
	}
	
	public String getIdItinerario() {
		return idItinerario;
	}
	
	public void setIdItinerario(String idItinerario) {
		this.idItinerario = idItinerario;
	}
	
	public String getIdCreatore() {
		return idCreatore;
	}
	
	public void setIdCreatore(String idCreatore) {
		this.idCreatore = idCreatore;
	}	
}
