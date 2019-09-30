package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.ricerca.IGestoreRicerca;
import com.intuition.cicerone.ricerca.RicercaLingua;

public class TestRicercaLingua {
	private List<IAttivita> lista;
	private IAttivita att1;
	private IAttivita att2;
	private IAttivita att3;
	private String lingua;
	private IGestoreRicerca ricercaPadre;
	private IGestoreRicerca ricerca1;
	
	
	@Before
	public void setUp() {
		lista = new ArrayList<>();
		Set<String> lista1 = new HashSet<>();
		Set<String> lista2 = new HashSet<>();
		Set<String> lista3 = new HashSet<>();
		att1 = mock(IAttivita.class);
		att2 = mock(IAttivita.class);
		att3 = mock(IAttivita.class);
		lingua = "Italiano";
		lista1.add("Italiano");
		lista1.add("Francesce");
		lista1.add("Spagnolo");
		
		lista2.add("Inglese");
		
		lista3.add("Svedese");
		
		when(att1.getLingua()).thenReturn(lista1);
		when(att2.getLingua()).thenReturn(lista2);
		when(att3.getLingua()).thenReturn(lista3);
		ricercaPadre = mock(IGestoreRicerca.class);
		lista.add(att1);
		lista.add(att2);
		lista.add(att3);
		when(ricercaPadre.ricerca()).thenReturn(lista);
		ricerca1 = new RicercaLingua(ricercaPadre, lingua);
	}
	
	@Test
	public void RicercaAtt1() {
		List<IAttivita> a = this.ricerca1.ricerca();
		assertTrue(a.contains(att1));
	}
	
	
	@Test
	public void RicercaAtt2() {
		List<IAttivita> a = this.ricerca1.ricerca();
		assertFalse(a.contains(att2));
	}
	
	@Test
	public void RicercaAtt3() {
		List<IAttivita> a = this.ricerca1.ricerca();
		assertFalse(a.contains(att3));
	}
}
