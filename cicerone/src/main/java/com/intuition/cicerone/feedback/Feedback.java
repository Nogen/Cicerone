package com.intuition.cicerone.feedback;

import java.time.LocalDate;
import java.util.Objects;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.autenticazione.utils.IdGenerator;
import com.intuition.cicerone.gestioneattivita.ErrorMessage;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;

public class Feedback implements IFeedback {

	public static class BuilderFeedback{
		private IAccount account;
		private String commento;
		private LocalDate data;
		private String idFeedback;
		private IAttivita tempAtt;
		private Integer valutazione;

		public Feedback build() {
			return new Feedback(this);
		}

		public BuilderFeedback setAccount(IAccount account) {
			if(account != null) {
				this.account = account;
			} else {
				throw new IllegalArgumentException(ErrorMessage.ACCOUNTERROR);
			}
			return this;
		}

		public BuilderFeedback generaIdFeedback() {
			this.idFeedback = IdGenerator.generateId();
			return this;
		}

		public BuilderFeedback setCommento(String commento) {
			if (commento != null && commento.length() <= 500 && commento.length() > 0) {
				this.commento = commento;
			} else {
				throw new IllegalArgumentException(ErrorMessage.COMMENTOERROR);
			}
			return this;
		}
		public BuilderFeedback setData(LocalDate data) {
			if(data != null) {
				this.data = data;
			} else {
				throw new IllegalArgumentException(ErrorMessage.DATAERROR);
			}
			return this;
		}
		public BuilderFeedback setIdFeedback(String idFeedback) {
			if(idFeedback != null) {
				this.idFeedback = idFeedback;
			} else {
				throw new IllegalArgumentException(ErrorMessage.IDERROR);
			}
			return this;
		}

		public BuilderFeedback setTempAtt(IAttivita tempAtt) {
			if(tempAtt != null) {
				this.tempAtt = tempAtt;
			} else {
				throw new IllegalArgumentException(ErrorMessage.ATTIVITAERROR);
			}
			return this;
		}

		public BuilderFeedback setValutazione(Integer valutazione) {
			if (valutazione != null && valutazione >= 0 && valutazione <= 5) {
				this.valutazione = valutazione;
			} else {
				throw new IllegalArgumentException(ErrorMessage.VALUTAZIONEERROR);
			}
			return this;
		}
	}

	private IAccount account;
	private String commento;
	private LocalDate data;
	private String idFeedback;
	private Integer valutazione;
	private IAttivita tempAtt;

	private Feedback(BuilderFeedback builder) {
		super();
		this.valutazione = builder.valutazione;
		this.account = builder.account;
		this.tempAtt = builder.tempAtt;
		this.idFeedback = builder.idFeedback;
		this.commento = builder.commento;
		this.data = builder.data;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Feedback) {
			Feedback termine2 = (Feedback)o;
			return termine2.getIdFeedback().equals(this.idFeedback);
		}
		return false;
	}

	@Override
	public IAttivita getAttivita() {
		return tempAtt;
	}

	@Override
	public IAccount getAutore() {
		return account;
	}

	@Override
	public String getCommento() {
		return commento;
	}

	@Override
	public LocalDate getData() {
		return data;
	}

	@Override
	public Integer getValutazione() {
		return this.valutazione;
	}

	@Override
	public String getIdFeedback() {
		return idFeedback;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.idFeedback);
	}
}

