package tests;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import com.intuition.cicerone.persistenza.database.entity.NotificaEntity;

public class TestNotificaEntity {
	private NotificaEntity notifica = new NotificaEntity();

	public String getIdNotifica() {
		return notifica.getIdNotifica();
	}
	
	@Test
	public void setIdNotifica() {
		notifica.setIdNotifica("idNotifica");
		assertEquals("idNotifica", notifica.getIdNotifica());
	}
	
	public LocalDate getDataApertura() {
		return notifica.getDataApertura();
	}
	
	@Test
	public void setDataApertura() {
		notifica.setDataApertura(LocalDate.of(1994, 4, 30));
		assertEquals(LocalDate.of(1994, 4, 30), notifica.getDataApertura());
	}
	
	public String getNomeEvento() {
		return notifica.getNomeEvento();
	}
	
	@Test
	public void setNomeEvento() {
		notifica.setNomeEvento("nomeEvento");
		assertEquals("nomeEvento", notifica.getNomeEvento());
	}
	
	public String getIdRicevente() {
		return notifica.getIdRicevente();
	}
	
	@Test
	public void setIdRicevente() {
		notifica.setIdRicevente("idRicevente");
		assertEquals("idRicevente",  notifica.getIdRicevente());
	}
	
	public String getIdAttivita() {
		return notifica.getIdAttivita();
	}
	
	@Test
	public void setIdAttivita() {
		notifica.setIdAttivita("idAttivita");
		assertEquals("idAttivita", notifica.getIdAttivita());
	}	

}
