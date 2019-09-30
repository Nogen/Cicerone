package com.intuition.cicerone.persistenza.database.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class FeedbackEntity {

	@Id
	@Column(unique=true, columnDefinition="VARCHAR(32)")
	private String idFeedback;
	@Column(columnDefinition="VARCHAR(500)")
	private String commento;
	private LocalDate dataRilascio;
	private Integer valutazione;
	@Column(columnDefinition="VARCHAR(32)")
	private String idAccount;
	@Column(columnDefinition="VARCHAR(32)")
	private String idAttivita;
	
	public String getIdFeedback() {
		return idFeedback;
	}
	
	public void setIdFeedback(String idFeedback) {
		this.idFeedback = idFeedback;
	}
	
	public String getCommento() {
		return commento;
	}
	
	public void setCommento(String commento) {
		this.commento = commento;
	}
	
	public LocalDate getDataRilascio() {
		return dataRilascio;
	}
	
	public void setDataRilascio(LocalDate dataRilascio) {
		this.dataRilascio = dataRilascio;
	}
	
	public Integer getValutazione() {
		return valutazione;
	}
	
	public void setValutazione(Integer valutazione) {
		this.valutazione = valutazione;
	}
	
	public String getIdAccount() {
		return idAccount;
	}
	
	public void setIdAccount(String idAccount) {
		this.idAccount = idAccount;
	}
	
	public String getIdAttivita() {
		return idAttivita;
	}
	
	public void setIdAttivita(String idAttivita) {
		this.idAttivita = idAttivita;
	}	
}
