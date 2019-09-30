package com.intuition.cicerone.persistenza;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.gestioneattivita.attivita.IItinerario;

public interface AttivitaCrud {

	boolean salvaAttivita(IAttivita attivita);

	Set<String> trovaLingue(String idItinerario);

	Set<IAccount> trovaPartecipanti(String idAttivita);

	Optional<IItinerario> trovaItinerario(String idAttivita);

	Optional<IAttivita> trovaAttivita(String idAttivita, Set<IAccount> partecipanti);

	List<IAttivita> trovaAttivitaByIdCreatore(String idCreatore);

	List<IAttivita> trovaAttivitaByStato(String stato);

	boolean eliminaAttivita(IAttivita a);

	boolean eliminaAttivitaById(String idAttivita);

	boolean eliminaPartecipante(String idAccount);
	
}