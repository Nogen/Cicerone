package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.ricerca.IGestoreRicerca;
import com.intuition.cicerone.ricerca.RicercaData;

public class TestRicercaData {
	private List<IAttivita> lista;
	private IAttivita att1;
	private IAttivita att2;
	private IAttivita att3;
	private LocalDate dataValida;
	private LocalDate dataNonValida;
	private IGestoreRicerca ricercaPadre;
	private IGestoreRicerca ricerca1;
	
	
	@Before
	public void setUp() {
		lista = new ArrayList<>();
		att1 = mock(IAttivita.class);
		att2 = mock(IAttivita.class);
		att3 = mock(IAttivita.class);
		dataNonValida = LocalDate.of(2100, 9, 30);
		dataValida = LocalDate.of(2019, 1, 30);
		when(att1.getDataIncontro()).thenReturn(dataNonValida);
		when(att2.getDataIncontro()).thenReturn(dataValida);
		when(att3.getDataIncontro()).thenReturn(dataValida);
		lista.add(att1);
		lista.add(att2);
		lista.add(att3);
		ricercaPadre = mock(IGestoreRicerca.class);
		when(ricercaPadre.ricerca()).thenReturn(lista);
		ricerca1 = new RicercaData(ricercaPadre, dataValida);
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
