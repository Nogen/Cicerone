package com.intuition.cicerone.gestioneattivita;

public final class ErrorMessage {
	public static final String ATTIVITACHIUSA = "Non possibile eseguire l'operazione perchè l'attività è chiusa";
	public static final String IDERROR = "Id non valido";
	public static final String EMAILERROR = "Inserire un indirizzo di email valido (nick@host.dom)";
	public static final String EMAILERROR2 = "L'email è già collegato ad un account esistente";
	public static final String RUOLOERROR = "Ruolo non valido";
	public static final String PSWERROR = "Password non valida";
	public static final String PSWERROR2 = "Nuova password identica alla precedente.";
	public static final String ANAGRAFICAERROR = "Anagrafica non valida";
	public static final String CONTATORENOTIFICHEERROR = "Il contatore notifiche non può essere negativo";
	public static final String ACCOUNTERROR = "Account non valido";
	public static final String ACCOUNTERROR2  = "Account già presente";
	public static final String ACCOUNTERROR3  = "Account non presente";	
	public static final String ACCOUNTERROR4 = "Non esiste nessun account collegato a questa email";
	public static final String ATTIVITAERROR = "Attivita' non valida";
	public static final String DATAERROR = "Data di nascita errata.";
	public static final String DATAERROR2  = "Data e ora non valide";
	public static final String DATAERROR3 = "Data non valida.";
	public static final String ORARIOERROR = "Orario non valido";
	public static final String EVENTOERROR = "Evento non valido";
	public static final String COMMENTOERROR = "Il commento è troppo lungo. (Max: 752 caratteri)";
	public static final String VALUTAZIONEERROR = "La valutazione deve essere compresa tra 0 e 5";
	public static final String TITOLOERROR = "Titolo troppo lungo (max: 150 caratteri)";
	public static final String ITINERARIOERROR = "Itinerario non valido";
	public static final String PARTECIPANTIERROR = "Lista partecipanti non valida";
	public static final String STATOATTIVITAERROR = "Stato non valido";
	public static final String COORDINATEERROR = "Coordinate non valide";
	public static final String NOMEERROR = "Nome non valido.";
	public static final String COGNOMEERROR = "Cognome non valido.";
	public static final String TELEFONOERROR = "Numero di telefono non valido.";
	public static final String LINGUEERROR = "Lista non valida";
	public static final String LINGUEERROR2 = "La lingua deve avere una lunghezza compresa tra 0 e 100 caratteri";
	public static final String RESIDENZAERROR = "La residenza deve avere una lunghezza comrpesa tra 0 e 100 caratteri.";
	public static final String DESCRIZIONEERROR = "Descrizione non valida";
	public static final String FOTOERROR = "Percorso non valido";
	public static final String RETRIBUZIONEERROR = "Il valore della retribuzione non può essere negativo";
	public static final String RETRIBUZIONEERROR2 = "Retribuzione non valida";
	public static final String LUOGOERROR = "Luogo non valido";	
	public static final String UTENTEERROR = "Errore: Nessun utente trovato";
	public static final String MESSAGGIOWELCOME  = "Grazie per esserti iscritto!";
	public static final String PROFILOERROR = "Errore: non puoi modificare il profilo perchè non sei loggato";
	public static final String PROFILOERROR2 = "Profilo modificato con Successo";
	public static final String PROFILOERROR3 = "Effettua il login per modificare il tuo profilo";
	public static final String ATTIVITA_CREATA_CON_SUCCESSO = "Attività creata con successo!";
	public static final String VISUALIZZA_LE_ATTIVITA_DA_TE_CREATE = "Visualizza le attività da te create";
	public static final String ERROREATTIVITANONESISTENTE = "Errore non puoi iscriverti ad un'attività non esistente";
	public static final String ERROREATTIVITAISCRITTO = "Errore non puoi iscriverti ad un'attività che ti appartiene/a cui sei già iscritto";
	public static final String VISUALIZZAATTIVITAMSG = "Ritorna a visualizzare le attività";	
	public static final String ISCRITTO_CON_SUCESSO = "Richiesta inviata con successo! ";
	public static final String ERROREMODIFICAREATTIVITA = "Errore non puoi modificare un'attività che non ti appartiene";
	public static final String ERROREMODIFICAREATTIVITA_NON_ESISTENTE = "Errore non puoi modificare un'attività non esistente";
	public static final String ATTIVITA_MODIFICATA_CON_SUCCESSO = "Attività modificata con successo";
	public static final String ERROREVISUALIZZAREATTIVITA = "Errore non puoi visualizzare i partecipanti di un'attività che non ti appartiene";
	public static final String ERROREVISUALIZZAREPARTECIPANTIATTIVITA_NON_ESISTENTE = "Errore non puoi visualizzare i partecipanti di un'attività non esistente";
	public static final String RITORNA_ALLA_HOME = "Ritorna alla home";
	public static final String ERRORE_L_ATTIVITA_NON_ESISTE = "Errore l'attività non esiste";	
	
	private ErrorMessage() {
		throw new UnsupportedOperationException();
	}
}
