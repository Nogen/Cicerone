package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.autenticazione.utils.IdGenerator;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.gestioneattivita.richiesta.IRichiesta;
import com.intuition.cicerone.gestioneattivita.richiesta.IStatoRichiesta;
import com.intuition.cicerone.gestioneattivita.richiesta.Richiesta;
import com.intuition.cicerone.gestioneattivita.richiesta.Rifiutata;

@RunWith(Parameterized.class)
public class TestRichiesta {
	private IAccount account;
	private IAttivita attivita;
	private IRichiesta richiesta;
	private String id;
	private LocalDate data;
	
	@Before
	public void setUp() {
		LocalDate data = LocalDate.of(1994, 4, 4);
		id = IdGenerator.generateId();
		account = mock(IAccount.class);
		attivita = mock(IAttivita.class);
		richiesta = new Richiesta.BuilderRichiesta()
				.setIdRichiesta(id)
				.setAccount(account)
				.setAttivita(attivita)
				.setDataRichiesta(data)
				.build();
	}

	
	@Parameter(0)	
	public String idCasuale;
	
	@Parameter(1)
	public IAttivita attivitaTest;
	
	@Parameter(2)
	public IAccount accountTest;
	
	@Parameter(3)	
	public LocalDate dataTest;
	


    // creates the test data
    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {
        	{null, null, null, null}};
        return Arrays.asList(data);
    }
    
    
	@Test(expected = IllegalArgumentException.class)
	public void builderRichiestaId() {
		new Richiesta.BuilderRichiesta().setIdRichiesta(idCasuale);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void builderRichiestaAttivita() {
		new Richiesta.BuilderRichiesta().setAttivita(attivitaTest);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void builderRichiestaAccount() {
		new Richiesta.BuilderRichiesta().setAccount(accountTest);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void builderRichiestaData() {
		new Richiesta.BuilderRichiesta().setDataRichiesta(dataTest);
	}
	
	@Test
	public void richiestaAccetta() {
		IStatoRichiesta st = this.richiesta.accetta();
		assertEquals( "accettata", st.toString());
	}
	
	@Test
	public void richiestaRifiuta() {
		IStatoRichiesta st = this.richiesta.rifiuta();
		assertEquals("rifiutata", st.toString() );
	}
	
	@Test
	public void richiestaInSospeso() {
		IStatoRichiesta st = this.richiesta.getStatoRichiesta();
		assertEquals("in sospeso", st.toString() );
	}
	
	@Test
	public void richiestaSetStato() {
		assertTrue(this.richiesta.setStatoRichiesta(new Rifiutata()));
	}
	
	@Test
	public void richiestaEqualsFalse() {
		IRichiesta r = new Richiesta.BuilderRichiesta()
				.generaIdRichiesta()
				.build();
		assertFalse(this.richiesta.equals(r));
	}
	
	@Test
	public void richiestaEqualsFalseNull() {
		assertFalse(this.richiesta.equals(null));
	}
	
	@Test
	public void richiestaEqualsTrue() {
		IRichiesta r = new Richiesta.BuilderRichiesta()
				.setIdRichiesta(id)
				.build();
		assertTrue(this.richiesta.equals(r));
	}
	@Test
	public void richiestaHashCode() {
		assertEquals(this.richiesta.hashCode(), Objects.hashCode(id));
	}
	
	@Test
	public void getDataRichiesta() {
		 assertEquals(1994, this.richiesta.getDataRichiesta().getYear());
	}

}
