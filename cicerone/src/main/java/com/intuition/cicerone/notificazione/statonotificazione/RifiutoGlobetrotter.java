package com.intuition.cicerone.notificazione.statonotificazione;

import com.intuition.cicerone.notificazione.IEventoCommand;
import com.intuition.cicerone.notificazione.INotificable;

public class RifiutoGlobetrotter implements IEventoCommand{

	public void notifica(INotificable n) { 
		n.notificaMe(this); 
	}

	@Override
	public String toString() {
		return "Sei stato rifiutato da";
	}
}
