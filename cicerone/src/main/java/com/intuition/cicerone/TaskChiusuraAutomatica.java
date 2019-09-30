package com.intuition.cicerone;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.intuition.cicerone.gestioneattivita.attivita.Chiuso;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.gestioneattivita.richiesta.IRichiesta;
import com.intuition.cicerone.gestioneattivita.richiesta.Rifiutata;
import com.intuition.cicerone.persistenza.AttivitaCrud;
import com.intuition.cicerone.persistenza.RichiestaCrud;
import com.intuition.cicerone.ricerca.IGestoreRicerca;
import com.intuition.cicerone.ricerca.RicercaData;
import com.intuition.cicerone.ricerca.RicercaOdierna;

@Component
public class TaskChiusuraAutomatica {
	@Autowired
	public AttivitaCrud attivitaCrud;
	@Autowired
	public RichiestaCrud richiestaCrud;
	
	@Scheduled(cron = "0 59 23 * * *")
	public void kiudi() {
		LocalDate now = LocalDate.now();
		List<IAttivita> attivitaOpt = attivitaCrud.trovaAttivitaByStato("aperto");
		attivitaOpt.addAll(attivitaCrud.trovaAttivitaByStato("modificato"));
		IGestoreRicerca ricerca = new RicercaOdierna(attivitaOpt);
		ricerca = new RicercaData(ricerca, now);
		for(IAttivita a : ricerca.ricerca()) {
			a.setStatoAttivita(new Chiuso());
			attivitaCrud.salvaAttivita(a);
			for(IRichiesta r: richiestaCrud.trovaRichiestaByAttivita(a)) {
				if (r.getStatoRichiesta().toString().equals("in sospeso")) {
					r.setStatoRichiesta(new Rifiutata());
					richiestaCrud.salvaRichiesta(r);
				}
			}
		}
	}

}
