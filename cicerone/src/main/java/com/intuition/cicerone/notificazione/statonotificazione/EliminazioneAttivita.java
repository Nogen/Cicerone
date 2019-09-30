package com.intuition.cicerone.notificazione.statonotificazione;

import com.intuition.cicerone.notificazione.IEventoCommand;
import com.intuition.cicerone.notificazione.INotificable;

public class EliminazioneAttivita implements IEventoCommand{

	public void notifica(INotificable n) { 
		n.notificaMe(this); 
	}
	
	@Override
	public String toString() {
		return " è stata eliminata.";
	}

}
