package com.intuition.cicerone.persistenza;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.gestioneattivita.Factory;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.gestioneattivita.richiesta.IRichiesta;
import com.intuition.cicerone.gestioneattivita.richiesta.IStatoRichiesta;
import com.intuition.cicerone.gestioneattivita.richiesta.Richiesta;
import com.intuition.cicerone.gestioneattivita.richiesta.StatoRichiestaFactory;
import com.intuition.cicerone.persistenza.database.entity.RichiestaEntity;
import com.intuition.cicerone.persistenza.database.repo.RichiestaRepo;

@Service
public class RichiestaCrudImpl implements RichiestaCrud {
	private RichiestaRepo richiestaRepo;
	private AttivitaCrud attivitaCrud;
	private AccountCrud accountCrud;

	@Autowired
	public RichiestaCrudImpl(RichiestaRepo richiestaRepo, AttivitaCrud attivitaCrud, AccountCrud accountCrud) {
		this.richiestaRepo = richiestaRepo;
		this.attivitaCrud = attivitaCrud;
		this.accountCrud = accountCrud;
	}

	@Override
	@Transactional
	public boolean salvaRichiesta(IRichiesta r) {
		RichiestaEntity re = new RichiestaEntity();
		re.setDataRichiesta(r.getDataRichiesta());
		re.setIdAttivita(r.getAttivita().getIdAttivita());
		re.setIdRichiedente(r.getMittente().getIdAccount());
		re.setIdRichiesta(r.getIdRichiesta());
		re.setStatoRichiesta(r.getStatoRichiesta().toString());
		richiestaRepo.save(re);
		return true;
	}

	@Override
	public Optional<IRichiesta> trovaRichiestaById(String id) {
		Optional<IRichiesta> result = Optional.empty();
		Optional<RichiestaEntity> optRichiesta = richiestaRepo.findById(id);
		if (optRichiesta.isPresent()) {
			RichiestaEntity richiestaEntity = optRichiesta.get();
			IAttivita attivita;
			IAccount richiedente;
			Optional<IAttivita> optAttivita = attivitaCrud.trovaAttivita(richiestaEntity.getIdAttivita(), null);
			Optional<IAccount> optAccount = accountCrud.trovaAccountById(richiestaEntity.getIdRichiedente());
			if (optAttivita.isPresent() && optAccount.isPresent()) {
				attivita = optAttivita.get();
				richiedente = optAccount.get();
				IRichiesta richiesta = new Richiesta.BuilderRichiesta()
						.setAccount(richiedente)
						.setAttivita(attivita)
						.setDataRichiesta(richiestaEntity.getDataRichiesta())
						.setIdRichiesta(richiestaEntity.getIdRichiesta())
						.build();
				Factory<IStatoRichiesta, String> factory = new StatoRichiestaFactory(richiesta);
				richiesta.setStatoRichiesta(factory.crea(richiestaEntity.getStatoRichiesta()));
				result = Optional.of(richiesta);
			}
		}
		return result;
	}

	@Override
	public List<IRichiesta> trovaRichiestaByAccount(IAccount a) {
		List<IRichiesta> result = new ArrayList<>();
		List<RichiestaEntity> listaRichieste = richiestaRepo.findByIdRichiedente(a.getIdAccount());
		if (!listaRichieste.isEmpty()) {
			for (RichiestaEntity r : listaRichieste) {
				Optional<IAttivita> optAttivita = attivitaCrud.trovaAttivita(r.getIdAttivita(), null);
				if (optAttivita.isPresent()) {
					IAttivita attivita = optAttivita.get();
					IRichiesta richiesta = new Richiesta.BuilderRichiesta()
							.setAccount(a)
							.setAttivita(attivita)
							.setIdRichiesta(r.getIdRichiesta())
							.setDataRichiesta(r.getDataRichiesta())
							.build();
					Factory<IStatoRichiesta, String> factory = new StatoRichiestaFactory(richiesta);
					richiesta.setStatoRichiesta(factory.crea(r.getStatoRichiesta()));

					result.add(richiesta);
				}
			}
		}
		return result;
	}


	@Override
	public List<IRichiesta> trovaRichiestaByAttivita(IAttivita a) {
		List<IRichiesta> result = new ArrayList<>();
		List<RichiestaEntity> listaRichieste = richiestaRepo.findByIdAttivita(a.getIdAttivita());
		if (!listaRichieste.isEmpty()) {
			for (RichiestaEntity r : listaRichieste) {
				Optional<IAccount> optAccount = accountCrud.trovaAccountById(r.getIdRichiedente());
				if (optAccount.isPresent()) {
					IAccount richiedente = optAccount.get();
					IRichiesta richiesta = new Richiesta.BuilderRichiesta()
							.setAccount(richiedente)
							.setAttivita(a)
							.setIdRichiesta(r.getIdRichiesta())
							.setDataRichiesta(r.getDataRichiesta())
							.build();
					Factory<IStatoRichiesta, String> factory = new StatoRichiestaFactory(richiesta);

					richiesta.setStatoRichiesta(factory.crea(r.getStatoRichiesta()));
					result.add(richiesta);
				}
			}
		}

		return result;
	}
	
	@Transactional
	public boolean eliminaRichiesta(String idAttivita) {
		richiestaRepo.deleteByIdAttivita(idAttivita);
		return true;
	}

}
