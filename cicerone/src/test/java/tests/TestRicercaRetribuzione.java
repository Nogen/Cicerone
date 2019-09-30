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
import com.intuition.cicerone.gestioneattivita.attivita.Itinerario;
import com.intuition.cicerone.ricerca.IGestoreRicerca;
import com.intuition.cicerone.ricerca.RicercaRetribuzione;

public class TestRicercaRetribuzione {
	private List<IAttivita> lista;
	private IAttivita att1;
	private IAttivita att2;
	private IAttivita att3;
	private Itinerario.Retribuzione valido;
	private Itinerario.Retribuzione nonValido;
	private Double valore;
	private IGestoreRicerca ricercaPadre;
	private IGestoreRicerca ricerca1;
	
	
	@Before
	public void setUp() {
		lista = new ArrayList<>();
		att1 = mock(IAttivita.class);
		att2 = mock(IAttivita.class);
		att3 = mock(IAttivita.class);
		nonValido = Itinerario.Retribuzione.NONRETRIBUITO;
		valido = Itinerario.Retribuzione.RETRIBUITO;
		valore = 5.5D;
		when(att1.getRetribuzione()).thenReturn(nonValido);
		
		when(att2.getRetribuzione()).thenReturn(valido);
		when(att2.getValoreRetribuzione()).thenReturn(5D);
		
		when(att3.getRetribuzione()).thenReturn(valido);
		when(att3.getValoreRetribuzione()).thenReturn(10D);
		
		lista.add(att1);
		lista.add(att2);
		lista.add(att3);
		ricercaPadre = mock(IGestoreRicerca.class);
		when(ricercaPadre.ricerca()).thenReturn(lista);
		ricerca1 = new RicercaRetribuzione(ricercaPadre, valore);
	}
	
	@Test
	public void RicercaAtt1() {
		List<IAttivita> a = this.ricerca1.ricerca();
		assertFalse(a.contains(att1));
	}
	
	
	@Test
	public void RicercaAtt2() {
		List<IAttivita> a = this.ricerca1.ricerca();
		assertFalse(a.contains(att2));
	}
	
	@Test
	public void RicercaAtt3() {
		List<IAttivita> a = this.ricerca1.ricerca();
		assertTrue(a.contains(att3));
	}
}
