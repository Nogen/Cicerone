package com.intuition.cicerone.autenticazione;

import java.io.Serializable;
import java.util.Objects;
import java.util.regex.Pattern;

import com.intuition.cicerone.autenticazione.utils.HashnSalt;
import com.intuition.cicerone.autenticazione.utils.IdGenerator;
import com.intuition.cicerone.gestioneattivita.ErrorMessage;
import com.intuition.cicerone.notificazione.IEventoCommand;
import com.intuition.cicerone.notificazione.INotificable;



public class Account implements IAccount, INotificable, Serializable{
	/**
	 * Generated serialID
	 **/
	private static final long serialVersionUID = 8483331611890165089L;
	
	private String idAccount;
	private String email;
	private String password;
	private IAnagrafica anagrafica;
	private Long contatoreNotifiche;
	private String ruolo;
	
	private Account(BuilderAccount builder) {
		this.idAccount = builder.idAccount;
		this.email = builder.email;
		this.password = builder.password;
		this.anagrafica = builder.anagrafica;
		this.contatoreNotifiche = builder.contatoreNotifiche;
		this.ruolo = builder.ruolo;
	}
	
	
	public static class BuilderAccount {
		private String idAccount;
		private String email;
		private String password;
		private IAnagrafica anagrafica;
		private Long contatoreNotifiche;
		private String ruolo;
		
		public BuilderAccount generaIdAccount() {
			this.idAccount = IdGenerator.generateId();
			return this;
		}
		
		public BuilderAccount setIdAccount(String id) {
			if (id != null) {
				this.idAccount = id;
			} else {
				throw new IllegalArgumentException(ErrorMessage.IDERROR);
			}
			return this;
		}
		
		public BuilderAccount setEmail(String email) {
			if (email != null && Pattern.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", email.toUpperCase())) {
				this.email = email;
			} else {
				throw new IllegalArgumentException(ErrorMessage.EMAILERROR);
			}
			return this;
		}
		
		public BuilderAccount setRuolo(String ruolo) {
			if (ruolo != null && (ruolo.equalsIgnoreCase("cicerone")|| ruolo.equalsIgnoreCase("globetrotter"))) {
				this.ruolo = ruolo;
			} else {
				throw new IllegalArgumentException(ErrorMessage.RUOLOERROR);
			}
			return this;
		}
		
		public BuilderAccount setPassword(String password) {
			if (password != null) {
				this.password = password;
			} else {
				throw new IllegalArgumentException(ErrorMessage.PSWERROR);
			}
			return this;
		}
		
		public BuilderAccount hashPassword(String password) {
			if (password != null && Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$", password)) {
				String salt = HashnSalt.generateSalt();
				this.password = HashnSalt.encode(password, salt);
			} else {
				throw new IllegalArgumentException(ErrorMessage.PSWERROR);
			}
			return this;
		}
		
		public BuilderAccount setAnagrafica(IAnagrafica anagrafica) {
			if (anagrafica != null) {
				this.anagrafica = anagrafica;
			} else {
				throw new IllegalArgumentException(ErrorMessage.ANAGRAFICAERROR);
			}
			return this;
		}
		
		public BuilderAccount setContatoreNotifica(Long contatoreNotifiche) {
			if (contatoreNotifiche != null && contatoreNotifiche.compareTo(0L) >= 0) {
				this.contatoreNotifiche = contatoreNotifiche;
			} else {
				throw new IllegalArgumentException(ErrorMessage.CONTATORENOTIFICHEERROR);
			}
			return this;
		}
		
		public BuilderAccount generaContatoreNotifica() {
			this.contatoreNotifiche = (long)0;
			return this;
		}

		public IAccount build() {
			return new Account(this);
		}
	}

	@Override
	public String getRuolo() {
		return ruolo;
	}
	
	@Override
	public String getIdAccount() {
		return idAccount;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public IAnagrafica getAnagrafica() {
		return anagrafica;
	}
	
	@Override
	public Long getContatoreNotifiche() {
		return contatoreNotifiche;
	}

	@Override
	public void cambiaPassword(String password) {
		if (password != null && Pattern.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$", password) && !password.equals(this.password)) {
			String salt = HashnSalt.generateSalt();
			HashnSalt.encode(password, salt);
			this.password = password;
		} else {
			throw new IllegalArgumentException(ErrorMessage.PSWERROR2);
		}
	}
	
	@Override
	public void aggiungiLingua(String lingua) {
		this.anagrafica.aggiungiLingueParlate(lingua);
	}
	
	@Override
	public void modificaResidenza(String paese) {
		this.anagrafica.modificaPaeseCitta(paese);
	}
	
	@Override
	public void modificaNumeroDiTelefono(String numero) {
		this.anagrafica.modificaNumeroDiTelefono(numero);
	}
	
	@Override
	public void aggiungiNotifica() {
		this.contatoreNotifiche++;
	}
	
	@Override
	public void sottraiNotifica() {
		if (this.contatoreNotifiche > 0) {
			this.contatoreNotifiche--;
		} else {
			throw new UnsupportedOperationException(ErrorMessage.CONTATORENOTIFICHEERROR);
		}
	}
	
	
	@Override
	public void azzeraNotifca() {
		this.contatoreNotifiche = (long)0;
	}
	
	@Override
	public void notificaMe(IEventoCommand cmd) {
		this.aggiungiNotifica();
		
	}
	
	@Override
	public void modificaRuolo(String ruolo) {
		if(ruolo.equalsIgnoreCase("cicerone") || ruolo.equalsIgnoreCase("globetrotter") || ruolo.equalsIgnoreCase("admin")) {
			this.ruolo = ruolo;
		} else {
			throw new IllegalArgumentException(ErrorMessage.RUOLOERROR);
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Account) {
			Account termine2 = (Account)o;
			return termine2.getIdAccount().equals(this.idAccount);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this.idAccount);
	}	
}
