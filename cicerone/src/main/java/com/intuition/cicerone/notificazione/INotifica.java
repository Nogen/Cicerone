package com.intuition.cicerone.notificazione;

import java.time.LocalDate;

import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;

public interface INotifica {

	INotificable getDestinatario();

	IAttivita getAttivita();

	LocalDate getDataDiApertura();

	IEventoCommand getEvento();

	String getIdNotifica();

	void inoltra();

	boolean equals(Object o);

	int hashCode();

}