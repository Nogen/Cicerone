package com.intuition.cicerone.persistenza.database.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NotificaEntity {

	@Id
	@Column(unique=true, columnDefinition="VARCHAR(32)")
	private String idNotifica;
	private LocalDate dataApertura;
	@Column(columnDefinition="VARCHAR(100)")
	private String nomeEvento;
	@Column(columnDefinition="VARCHAR(32)")
	private String idRicevente;
	@Column(columnDefinition="VARCHAR(32)")
	private String idAttivita;
	
	public String getIdNotifica() {
		return idNotifica;
	}
	
	public void setIdNotifica(String idNotifica) {
		this.idNotifica = idNotifica;
	}
	
	public LocalDate getDataApertura() {
		return dataApertura;
	}
	
	public void setDataApertura(LocalDate dataApertura) {
		this.dataApertura = dataApertura;
	}
	
	public String getNomeEvento() {
		return nomeEvento;
	}
	
	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}
	
	public String getIdRicevente() {
		return idRicevente;
	}
	
	public void setIdRicevente(String idRicevente) {
		this.idRicevente = idRicevente;
	}
	
	public String getIdAttivita() {
		return idAttivita;
	}
	
	public void setIdAttivita(String idAttivita) {
		this.idAttivita = idAttivita;
	}	
}
