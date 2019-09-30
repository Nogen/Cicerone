package tests;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.intuition.cicerone.gestioneattivita.Factory;
import com.intuition.cicerone.gestioneattivita.attivita.Aperto;
import com.intuition.cicerone.gestioneattivita.attivita.Chiuso;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.gestioneattivita.attivita.IStatoAttivita;
import com.intuition.cicerone.gestioneattivita.attivita.StatoAttivitaFactory;
import com.intuition.cicerone.gestioneattivita.attivita.Modificato;
import com.intuition.cicerone.gestioneattivita.richiesta.Accettata;
import com.intuition.cicerone.gestioneattivita.richiesta.IRichiesta;
import com.intuition.cicerone.gestioneattivita.richiesta.IStatoRichiesta;
import com.intuition.cicerone.gestioneattivita.richiesta.InSospeso;
import com.intuition.cicerone.gestioneattivita.richiesta.Rifiutata;
import com.intuition.cicerone.gestioneattivita.richiesta.StatoRichiestaFactory;

public class TestStatoFactory {
	private Factory<IStatoAttivita, String> factory;
	private IAttivita attivita;
	
	private Factory<IStatoRichiesta, String> factory1;
	private IRichiesta richiesta;
	
	@Before
	public void setUp() {
		attivita = mock(IAttivita.class);
		richiesta = mock(IRichiesta.class);
		factory = new StatoAttivitaFactory(attivita);
		factory1 = new StatoRichiestaFactory(richiesta);
	}
	
	@Test
	public void testAperto() {
		assertTrue(factory.crea("aperto") instanceof Aperto);
	}
	
	
	@Test
	public void testModificato() {
		assertTrue(factory.crea("modificato") instanceof Modificato);
	}

	@Test
	public void testChiuso() {
		assertTrue(factory.crea("chiuso") instanceof Chiuso);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testStatoAttivitaFail() {
		factory.crea("prova");
	}
	
	@Test
	public void testInSopseso() {
		assertTrue(factory1.crea("in sospeso") instanceof InSospeso);
	}
	
	@Test
	public void testAccettata() {
		assertTrue(factory1.crea("accettata") instanceof Accettata);
	}
	
	@Test
	public void testRifiutata() {
		assertTrue(factory1.crea("rifiutata") instanceof Rifiutata);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testStatoRichiestaFail() {
		factory1.crea("prova");
	}
}
