package com.intuition.cicerone.persistenza;

import java.util.List;
import java.util.Optional;

import com.intuition.cicerone.feedback.IFeedback;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;

public interface FeedbackCrud {

	boolean salvaFeedback(IFeedback f);

	Optional<IFeedback> trovaFeedbackByIdCreatore(String idCreatore, String idAttivita);

	List<IFeedback> trovaByAttivita(IAttivita a);

	boolean eliminaByAttivita(String idAttivita);

}