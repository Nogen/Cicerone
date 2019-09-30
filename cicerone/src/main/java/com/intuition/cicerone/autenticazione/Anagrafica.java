package com.intuition.cicerone.autenticazione;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

import com.intuition.cicerone.autenticazione.utils.IdGenerator;
import com.intuition.cicerone.gestioneattivita.ErrorMessage;



public class Anagrafica implements IAnagrafica, Serializable{

	/**
	 * Generated serialID
	 */
	private static final long serialVersionUID = -5733246354565896033L;

	public enum Sesso {
		UOMO(0), DONNA(1);
		private int valore;
		private Sesso(int valore) {
			this.valore = valore;
		}

		public int getValore() {
			return this.valore;
		}
	}
	
	private String idAnagrafica;
	private String nome;
	private String cognome;
	private String numeroDiTelefono;
	private Sesso sesso;
	private Set<String> lingueParlate;
	private String paeseCitta;
	private LocalDate dataDiNascita;

	private Anagrafica(BuilderAnagrafica builder) {
		super();
		this.nome = builder.nome;
		this.cognome = builder.cognome;
		this.numeroDiTelefono = builder.numeroDiTelefono;
		this.sesso = builder.sesso;
		this.lingueParlate = builder.lingueParlate;
		this.paeseCitta = builder.paeseCitta;
		this.dataDiNascita = builder.dataDiNascita;
		this.idAnagrafica = builder.idAnagrafica;
	}

	public static class BuilderAnagrafica {
		private String nome;
		private String cognome;
		private String numeroDiTelefono;
		private Sesso sesso;
		private Set<String> lingueParlate;
		private String paeseCitta;
		private LocalDate dataDiNascita;
		private String idAnagrafica;
		private LocalDate now = LocalDate.now();
		
		public BuilderAnagrafica generaIdAnagrafica() {
			this.idAnagrafica = IdGenerator.generateId();
			return this;
		}
		
		public BuilderAnagrafica setIdAnagrafica(String idAnagrafica) {
			if (idAnagrafica != null) {
				this.idAnagrafica = idAnagrafica;
			} else {
				throw new IllegalArgumentException(ErrorMessage.IDERROR);
			}
			return this;
		}

		public BuilderAnagrafica setNome(String nome){
			if (nome != null && nome.matches("[A-Z][a-z]{1,50}")) {
				this.nome = nome;
			} else {
				throw new IllegalArgumentException(ErrorMessage.NOMEERROR);
			}
			return this;
		}

		public BuilderAnagrafica setCognome(String cognome) {
			if (cognome != null && cognome.matches("[A-Z][A-Za-z ]{1,50}")) {
				this.cognome = cognome;
			} else {
				throw new IllegalArgumentException(ErrorMessage.COGNOMEERROR);
			}
			return this;
		}

		public BuilderAnagrafica setNumeroDiTelefono(String numeroDiTelefono) {
			if (numeroDiTelefono != null && numeroDiTelefono.matches("[0-9]{10}")) {
				this.numeroDiTelefono = numeroDiTelefono;
			} else {
				throw new IllegalArgumentException(ErrorMessage.TELEFONOERROR);
			}
			return this;
		}

		public BuilderAnagrafica setSesso(Sesso sesso) {
			this.sesso = sesso;
			return this;
		}

		public BuilderAnagrafica setLingueParlate(Set<String> lingueParlate) {
			if (lingueParlate != null) {
				this.lingueParlate = lingueParlate;
			} else {
				throw new IllegalArgumentException(ErrorMessage.LINGUEERROR);
			}
			return this;
		}

		public BuilderAnagrafica setPaeseCitta(String paeseCitta) {
			if (paeseCitta != null && paeseCitta.matches("[a-zA-Z0-9.,'#/ -]{4,300}")) {
				this.paeseCitta = paeseCitta;
			} else {
				throw new IllegalArgumentException(ErrorMessage.RESIDENZAERROR);
			}
			return this;
		}

		public BuilderAnagrafica setDataDiNascita(LocalDate dataDiNascita) {
			if (dataDiNascita != null && dataDiNascita.compareTo(now) < 1) {
				this.dataDiNascita = dataDiNascita;
			} else { 
				throw new IllegalArgumentException(ErrorMessage.DATAERROR);
			}
			return this;
		}

		public IAnagrafica build() {
			return new Anagrafica(this);
		}
	}

	@Override
	public String getIdAnagrafica() {
		return this.idAnagrafica;
	}
	
	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public String getCognome() {
		return cognome;
	}

	@Override
	public String getNumeroDiTelefono() {
		return numeroDiTelefono;
	}

	@Override
	public Sesso getSesso() {
		return sesso;
	}

	@Override
	public Set<String> getLingueParlate() {
		return lingueParlate;
	}

	@Override
	public String getPaeseCitta() {
		return paeseCitta;
	}

	@Override
	public LocalDate getDataDiNascita() {
		return dataDiNascita;
	}

	@Override
	public void modificaNumeroDiTelefono(String numeroDiTelefono) {
		if (numeroDiTelefono != null && numeroDiTelefono.matches("[0-9]{10}")) {
			this.numeroDiTelefono = numeroDiTelefono;
		} else {
			throw new IllegalArgumentException(ErrorMessage.TELEFONOERROR);
		}
	}

	@Override
	public void aggiungiLingueParlate(String lingueParlate) {
		if (lingueParlate != null && !this.lingueParlate.contains(lingueParlate) && lingueParlate.matches("[a-zA-Z]{4,100}")) {
			this.lingueParlate.add(lingueParlate);
		} else {
			throw new IllegalArgumentException(ErrorMessage.LINGUEERROR2);
		}
	}

	@Override
	public void modificaPaeseCitta(String paeseCitta) {
		if (paeseCitta != null && paeseCitta.matches("[a-zA-Z, ]{4,100}")) {
			this.paeseCitta = paeseCitta;
		} else {
			throw new IllegalArgumentException(ErrorMessage.RESIDENZAERROR);
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Anagrafica) {
			Anagrafica termine2 = (Anagrafica)o;
			return termine2.getIdAnagrafica().equals(this.idAnagrafica);
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this.idAnagrafica);
	}

}
