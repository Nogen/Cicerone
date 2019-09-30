package tests;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

import com.intuition.cicerone.persistenza.database.entity.RichiestaEntity;

public class TestRichiestaEntity {
	private RichiestaEntity richiesta = new RichiestaEntity();
	
	
	
	
	public String getIdRichiesta() {
		return richiesta.getIdRichiesta();
	}
	
	@Test
	public void setIdRichiesta() {
		this.richiesta.setIdRichiesta("idRichiesta");
		assertEquals("idRichiesta", this.getIdRichiesta());
	}
	
	
	public String getStatoRichiesta() {
		return richiesta.getStatoRichiesta();
	}
	
	@Test
	public void setStatoRichiesta() {
		this.richiesta.setStatoRichiesta("stato");
		assertEquals("stato", this.richiesta.getStatoRichiesta());
	}
	
	
	public LocalDate getDataRichiesta() {
		return richiesta.getDataRichiesta();
	}
	
	@Test
	public void setDataRichiesta() {
		this.richiesta.setDataRichiesta(LocalDate.of(1994, 4, 30));
		assertEquals(LocalDate.of(1994, 4, 30), this.richiesta.getDataRichiesta());
	}
	
	
	public String getIdRichiedente() {
		return richiesta.getIdRichiedente();
	}
	
	@Test
	public void setIdRichiedente() {
		this.richiesta.setIdRichiedente("idRichiedente");
		assertEquals("idRichiedente", richiesta.getIdRichiedente());
	}
	
	
	public String getIdAttivita() {
		return richiesta.getIdAttivita();
	}
	
	@Test
	public void setIdAttivita() {
		this.richiesta.setIdAttivita("idAttivita");
		assertEquals("idAttivita", richiesta.getIdAttivita());
	}	

}
