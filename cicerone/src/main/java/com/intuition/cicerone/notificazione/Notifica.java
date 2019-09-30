package com.intuition.cicerone.notificazione;

import java.time.LocalDate;
import java.util.Objects;

import com.intuition.cicerone.autenticazione.utils.IdGenerator;
import com.intuition.cicerone.gestioneattivita.ErrorMessage;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;

public class Notifica implements INotifica { 

	private INotificable destinatario;
	private IAttivita attivita; 
	private LocalDate dataDiApertura; 
	private IEventoCommand evento;
	private String idNotifica; 
	
	
	private Notifica(BuilderNotifica builder) {
		super();
		this.destinatario = builder.destinatario;
		this.attivita = builder.attivita;
		this.dataDiApertura = builder.dataDiApertura;
		this.evento = builder.evento;
		this.idNotifica = builder.idNotifica;
	}
	
	public static class BuilderNotifica {
		private INotificable destinatario;
		private IAttivita attivita;
		private LocalDate dataDiApertura; 
		private IEventoCommand evento;
		private String idNotifica;
		
		public BuilderNotifica setDestinatario(INotificable destinatario) {
			if(destinatario != null) {
				this.destinatario = destinatario;
			} else {
				throw new IllegalArgumentException(ErrorMessage.ACCOUNTERROR);
			}
			return this;
		}
		
		public BuilderNotifica setAttivita(IAttivita attivita) {
			if(attivita != null) {
				this.attivita = attivita;
			} else {
				throw new IllegalArgumentException(ErrorMessage.ATTIVITAERROR);
			}			
			return this;
		}
		
		public BuilderNotifica setDataDiApertura(LocalDate dataDiApertura) {
			if(dataDiApertura != null) {
				this.dataDiApertura = dataDiApertura;
			} else {
				throw new IllegalArgumentException(ErrorMessage.DATAERROR);
			}			
			return this;
		}

		public BuilderNotifica setEvento(IEventoCommand evento) {
			if(evento != null) {
				this.evento = evento;
			} else {
				throw new IllegalArgumentException(ErrorMessage.EVENTOERROR);
			}			
			return this;
		}

		public BuilderNotifica generaIdNotifica() {
			this.idNotifica = IdGenerator.generateId();
			return this;
		}

		public BuilderNotifica setIdNotifica(String id) {
			if(id != null) {
				this.idNotifica = id;
			} else {
				throw new IllegalArgumentException(ErrorMessage.IDERROR);
			}			
			return this; 
		}
		
		public INotifica build() {
			return new Notifica(this);
		}
		
	}

	@Override
	public INotificable getDestinatario() {
		return this.destinatario;
	}

	@Override
	public IAttivita getAttivita() {
		return this.attivita;
	}

	@Override
	public LocalDate getDataDiApertura() {
		return this.dataDiApertura;
	}

	@Override
	public IEventoCommand getEvento() {
		return this.evento;
	}

	@Override
	public String getIdNotifica() {
		return this.idNotifica;
	}
	
	@Override
	public void inoltra() {
		this.evento.notifica(this.destinatario);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Notifica) {
			INotifica termine2 = (INotifica)o;
			return termine2.getIdNotifica().equals(this.idNotifica);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this.idNotifica);
	}
	
}
