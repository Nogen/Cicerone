package com.intuition.cicerone.gestioneattivita.richiesta;

import java.time.LocalDate;
import java.util.Objects;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.autenticazione.utils.IdGenerator;
import com.intuition.cicerone.gestioneattivita.ErrorMessage;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;

public class Richiesta implements IRichiesta{
	
	public static class BuilderRichiesta {
		private IAccount account;
		private LocalDate dataRichiesta;
		private String idRichiesta;
		private IAttivita attivita;

		public Richiesta build() {
			return new Richiesta(this);
		}

		public BuilderRichiesta setAccount(IAccount account) {
			if(account != null) {
				this.account = account;
			} else {
				throw new IllegalArgumentException(ErrorMessage.ACCOUNTERROR);
			}
			return this;
		}

		public BuilderRichiesta setDataRichiesta(LocalDate dataRichiesta) {
			if(dataRichiesta != null) {
				this.dataRichiesta = dataRichiesta;
			} else {
				throw new IllegalArgumentException(ErrorMessage.DATAERROR);
			}
			return this;
		}
		
		public BuilderRichiesta generaIdRichiesta() {
			this.idRichiesta = IdGenerator.generateId();
			return this;
		}

		public BuilderRichiesta setIdRichiesta(String id) {
			if(id != null) {
				this.idRichiesta = id;
			} else {
				throw new IllegalArgumentException(ErrorMessage.IDERROR);
			}			
			return this;
		}	

		public BuilderRichiesta setAttivita(IAttivita attivita) {
			if(attivita != null) {
				this.attivita = attivita;
			} else {
				throw new IllegalArgumentException(ErrorMessage.ATTIVITAERROR);
			}			
			return this;
		}
	}
	
	private IAccount account;
	private LocalDate dataRichiesta;
	private String idRichiesta;
	private IStatoRichiesta statoRichiesta;
	private IAttivita attivita;

	private Richiesta(BuilderRichiesta builder) {
		this.idRichiesta = builder.idRichiesta;
		this.attivita = builder.attivita;
		this.account = builder.account;
		this.dataRichiesta = builder.dataRichiesta;
		this.statoRichiesta = new InSospeso(this);
	}

	@Override
	public IStatoRichiesta accetta() {
		return this.statoRichiesta.accetta();
	}

	@Override
	public IAttivita getAttivita() {
		return attivita;
	}

	@Override
	public LocalDate getDataRichiesta() {
		return dataRichiesta;
	}

	@Override
	public String getIdRichiesta() {
		return this.idRichiesta;
	}

	@Override
	public IAccount getMittente() {
		return account;
	}

	@Override
	public IStatoRichiesta getStatoRichiesta() {
		return statoRichiesta;
	}


	@Override
	public IStatoRichiesta rifiuta() {
		return this.statoRichiesta.rifiuta();
	}

	@Override
	public Boolean setStatoRichiesta(IStatoRichiesta statoRichiesta) {
		this.statoRichiesta = statoRichiesta;
		return true;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Richiesta) {
			Richiesta termine2 = (Richiesta)o;
			return termine2.getIdRichiesta().equals(this.idRichiesta);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this.idRichiesta);
		
	}
}
