package com.intuition.cicerone.persistenza.database.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ItinerarioEntity {

	@Id
	@Column(unique=true, columnDefinition="VARCHAR(32)")
	private String idItinerario;
	@Column(columnDefinition="VARCHAR(500)")
	private String descrizioneAttivita;
	private LocalDate dataIncontro;
	private LocalTime oraIncontro;
	private Double xIncontro;
	private Double yIncontro;
	@Column(columnDefinition="VARCHAR(100)")
	private String nomeLuogo;
	private Boolean retribuzione;
	private Double valoreRetribuzione;
	
	public String getIdItinerario() {
		return idItinerario;
	}
	
	public void setIdItinerario(String idItinerario) {
		this.idItinerario = idItinerario;
	}
	
	public String getDescrizioneAttivita() {
		return descrizioneAttivita;
	}
	
	public void setDescrizioneAttivita(String descrizioneAttivita) {
		this.descrizioneAttivita = descrizioneAttivita;
	}
	
	public LocalDate getDataIncontro() {
		return dataIncontro;
	}
	
	public void setDataIncontro(LocalDate dataIncontro) {
		this.dataIncontro = dataIncontro;
	}
	
	public LocalTime getOraIncontro() {
		return oraIncontro;
	}
	
	public void setOraIncontro(LocalTime oraIncontro) {
		this.oraIncontro = oraIncontro;
	}
	
	public Double getxIncontro() {
		return xIncontro;
	}
	
	public void setxIncontro(Double xIncontro) {
		this.xIncontro = xIncontro;
	}
	
	public Double getyIncontro() {
		return yIncontro;
	}
	
	public void setyIncontro(Double yIncontro) {
		this.yIncontro = yIncontro;
	}
	
	public String getNomeLuogo() {
		return nomeLuogo;
	}
	
	public void setNomeLuogo(String nomeLuogo) {
		this.nomeLuogo = nomeLuogo;
	}
	
	public Boolean getRetribuzione() {
		return retribuzione;
	}
	
	public void setRetribuzione(Boolean retribuzione) {
		this.retribuzione = retribuzione;
	}
	
	public Double getValoreRetribuzione() {
		return valoreRetribuzione;
	}
	
	public void setValoreRetribuzione(Double valoreRetribuzione) {
		this.valoreRetribuzione = valoreRetribuzione;
	}	
}
