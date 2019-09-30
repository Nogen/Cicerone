package com.intuition.cicerone.persistenza;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.feedback.Feedback;
import com.intuition.cicerone.feedback.IFeedback;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.persistenza.database.entity.FeedbackEntity;
import com.intuition.cicerone.persistenza.database.repo.FeedbackRepo;

@Service
public class FeedbackCrudImpl implements FeedbackCrud {
	private FeedbackRepo feedbackRepo;
	private AccountCrud accountCrud;
	private AttivitaCrud attivitaCrud;

	public FeedbackCrudImpl(FeedbackRepo feedbackRepo, AccountCrud accountCrud,
			AttivitaCrud attivitaCrud) {
		this.feedbackRepo = feedbackRepo;
		this.accountCrud = accountCrud;
		this.attivitaCrud = attivitaCrud;
	}

	@Override
	public boolean salvaFeedback(IFeedback f) {
		FeedbackEntity fEnt = new FeedbackEntity();
		fEnt.setCommento(f.getCommento());
		fEnt.setDataRilascio(f.getData());
		fEnt.setIdAccount(f.getAutore().getIdAccount());
		fEnt.setIdFeedback(f.getIdFeedback());
		fEnt.setValutazione(f.getValutazione());
		fEnt.setIdAttivita(f.getAttivita().getIdAttivita());
		feedbackRepo.save(fEnt);
		return true;
	}

	@Override
	public Optional<IFeedback> trovaFeedbackByIdCreatore(String idCreatore, String idAttivita) {
		Optional<IFeedback> result = Optional.empty();
		Optional<IAccount> accOpt = accountCrud.trovaAccountById(idCreatore);
		Optional<IAttivita> attOpt = attivitaCrud.trovaAttivita(idAttivita, null);
		Optional<FeedbackEntity> fEntOpt = feedbackRepo.findByIdAttivitaAndIdAccount(idAttivita, idCreatore);
		if (accOpt.isPresent() && attOpt.isPresent() && fEntOpt.isPresent()) {
			IAttivita attivita = attOpt.get();
			IAccount account = accOpt.get();
			FeedbackEntity fEnt = fEntOpt.get();
			IFeedback feedback = new Feedback.BuilderFeedback()
					.setAccount(account)
					.setTempAtt(attivita)
					.setIdFeedback(fEnt.getIdFeedback())
					.setCommento(fEnt.getCommento())
					.setData(fEnt.getDataRilascio())
					.setValutazione(fEnt.getValutazione())
					.build();
			result = Optional.of(feedback);

		} 
		return result;
	}

	@Override
	public List<IFeedback> trovaByAttivita(IAttivita a) {
		List<IFeedback> result = new ArrayList<>();
		List<FeedbackEntity> fEntList = feedbackRepo.findByIdAttivita(a.getIdAttivita());
		for (FeedbackEntity fEnt : fEntList) {
			Optional<IAccount> acc = accountCrud.trovaAccountById(fEnt.getIdAccount());
			if (acc.isPresent()) {
				IFeedback feedback = new Feedback.BuilderFeedback()
						.setAccount(acc.get())
						.setTempAtt(a)
						.setIdFeedback(fEnt.getIdFeedback())
						.setCommento(fEnt.getCommento())
						.setData(fEnt.getDataRilascio())
						.setValutazione(fEnt.getValutazione())
						.build();
				result.add(feedback);
			}
		}
		return result;
	}
	
	@Override
	@Transactional
	public boolean eliminaByAttivita(String idAttivita) {
		feedbackRepo.deleteByIdAttivita(idAttivita);
		return true;
	}

}
