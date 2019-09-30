package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.ricerca.IGestoreRicerca;
import com.intuition.cicerone.ricerca.RicercaLuogo;

public class TestRicercaLuogo {
	private List<IAttivita> lista;
	private IAttivita att1;
	private IAttivita att2;
	private IAttivita att3;
	private String luogo;
	private IGestoreRicerca ricercaPadre;
	private IGestoreRicerca ricerca1;
	
	
	@Before
	public void setUp() {
		lista = new ArrayList<>();
		att1 = mock(IAttivita.class);
		att2 = mock(IAttivita.class);
		att3 = mock(IAttivita.class);
		luogo = "Bitonto bari";
		when(att1.getLuogo()).thenReturn("Bari, Bari, Italia");
		when(att2.getLuogo()).thenReturn("Bitonto, Bari, Italia");
		when(att3.getLuogo()).thenReturn("bitonto, bari, italia");
		lista.add(att1);
		lista.add(att2);
		lista.add(att3);
		ricercaPadre = mock(IGestoreRicerca.class);
		when(ricercaPadre.ricerca()).thenReturn(lista);
		ricerca1 = new RicercaLuogo(ricercaPadre, luogo);
	}
	
	@Test
	public void RicercaAtt1() {
		List<IAttivita> a = this.ricerca1.ricerca();
		assertFalse(a.contains(att1));
	}
	
	
	@Test
	public void RicercaAtt2() {
		List<IAttivita> a = this.ricerca1.ricerca();
		assertTrue(a.contains(att2));
	}
	
	@Test
	public void RicercaAtt3() {
		List<IAttivita> a = this.ricerca1.ricerca();
		assertTrue(a.contains(att3));
	}
}
