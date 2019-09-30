package com.intuition.cicerone.autenticazione;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import com.intuition.cicerone.autenticazione.Anagrafica.Sesso;

public interface IAnagrafica extends Serializable{

	String getIdAnagrafica();

	String getNome();

	String getCognome();

	String getNumeroDiTelefono();

	Sesso getSesso();

	Set<String> getLingueParlate();

	String getPaeseCitta();

	LocalDate getDataDiNascita();

	void modificaNumeroDiTelefono(String numeroDiTelefono);

	void aggiungiLingueParlate(String lingueParlate);

	void modificaPaeseCitta(String paeseCitta);

}