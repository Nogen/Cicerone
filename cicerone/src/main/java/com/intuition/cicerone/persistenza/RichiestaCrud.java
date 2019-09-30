package com.intuition.cicerone.persistenza;

import java.util.List;
import java.util.Optional;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.gestioneattivita.richiesta.IRichiesta;

public interface RichiestaCrud {

	boolean salvaRichiesta(IRichiesta r);

	Optional<IRichiesta> trovaRichiestaById(String id);

	List<IRichiesta> trovaRichiestaByAccount(IAccount a);

	List<IRichiesta> trovaRichiestaByAttivita(IAttivita a);
	
	boolean eliminaRichiesta(String idRichiesta);

}