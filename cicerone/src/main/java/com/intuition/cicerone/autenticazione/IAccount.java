package com.intuition.cicerone.autenticazione;


import java.io.Serializable;

import com.intuition.cicerone.notificazione.IEventoCommand;

public interface IAccount extends Serializable{

	String getEmail();

	String getPassword();

	IAnagrafica getAnagrafica();

	Long getContatoreNotifiche();

	void cambiaPassword(String password);

	void aggiungiLingua(String lingua);

	void modificaResidenza(String paese);

	void modificaNumeroDiTelefono(String numero);

	void aggiungiNotifica();

	void sottraiNotifica();

	void azzeraNotifca();

	void notificaMe(IEventoCommand cmd);

	String getRuolo();

	String getIdAccount();
	
	void modificaRuolo(String ruolo);

}