package com.intuition.cicerone.notificazione.statonotificazione;

import com.intuition.cicerone.notificazione.IEventoCommand;
import com.intuition.cicerone.notificazione.INotificable;

public class AccettazioneGlobetrotter implements IEventoCommand{

	public void notifica(INotificable n) { 
		n.notificaMe(this); 
	}

	@Override
	public String toString() {
		return "la tua iscrizione è stata accettata";
	}

}
