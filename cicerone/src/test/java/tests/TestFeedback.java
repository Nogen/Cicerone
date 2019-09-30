package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.intuition.cicerone.autenticazione.IAccount;
import com.intuition.cicerone.autenticazione.utils.IdGenerator;
import com.intuition.cicerone.feedback.Feedback;
import com.intuition.cicerone.feedback.IFeedback;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;

@RunWith(Enclosed.class)
public class TestFeedback {



	@RunWith(Parameterized.class)
	public static class BuilderTest {
		
		
		@Parameter(0)
		public String commento;

		@Parameter(1)
		public Integer valutazione;




		// creates the test data
		@Parameters
		public static Collection<Object[]> data() {
			char[] chars = new char[501];
			Arrays.fill(chars, 'c');
			String troppoLunga = new String(chars);
			Object[][] data = new Object[][] {
				{null, null},
				{"", -1},
				{troppoLunga, 6}};
				return Arrays.asList(data);
		}

		@Test(expected=IllegalArgumentException.class)
		public void testFeedbackBuilderValutazione() {
			new Feedback.BuilderFeedback().setValutazione(valutazione);
		}

		@Test(expected=IllegalArgumentException.class)
		public void testFeedbackBuilderCommento() {
			new Feedback.BuilderFeedback().setCommento(commento);
		}

	}

	public static class OtherTests {
		private IAccount account;
		private String commento;
		private LocalDate data;
		private String id;
		private Integer valutazione;
		private IAttivita attivita;
		private IFeedback feedback;

		@Before
		public void setUp() {
			account = mock(IAccount.class);
			commento = "Commento generico feedback";
			data = LocalDate.now();
			id = IdGenerator.generateId();
			valutazione = 4;
			attivita = mock(IAttivita.class);
			feedback = new Feedback.BuilderFeedback()
					.setIdFeedback(id)
					.setAccount(account)
					.setCommento(commento)
					.setTempAtt(attivita)
					.setData(data)
					.setValutazione(valutazione)
					.build();
		}

		@Test(expected=IllegalArgumentException.class)
		public void testFeedbackBuilderId() {
			new Feedback.BuilderFeedback().setIdFeedback(null);
		}


		@Test
		public void testFeedbackBuilderGenerateId() {
			new Feedback.BuilderFeedback().generaIdFeedback();
		}


		@Test(expected=IllegalArgumentException.class)
		public void testFeedbackBuilderaccount() {
			new Feedback.BuilderFeedback().setAccount(null);
		}


		@Test(expected=IllegalArgumentException.class)
		public void testFeedbackBuilderattivita() {
			new Feedback.BuilderFeedback().setTempAtt(null);
		}

		@Test(expected=IllegalArgumentException.class)
		public void testFeedbackBuilderData() {
			new Feedback.BuilderFeedback().setData(null);
		}


		@Test
		public void getAttivita() {
			assertEquals(attivita, feedback.getAttivita());
		}

		@Test
		public void getAutore() {
			assertEquals(account, feedback.getAutore());
		}

		@Test
		public void getCommento() {
			assertEquals(commento, feedback.getCommento());
		}

		@Test
		public void getData() {
			assertEquals(data, feedback.getData());
		}

		@Test
		public void getValutazioneNumerica() {
			assertEquals(valutazione, feedback.getValutazione());
		}

		@Test
		public void getIdFeedback() {
			assertEquals(id, feedback.getIdFeedback());
		}


		@Test
		public void testEqualsToNull() {
			assertFalse(feedback.equals(null));
		}

		@Test
		public void testEqualsToNotequal() {
			IFeedback f = new Feedback.BuilderFeedback()
					.generaIdFeedback()
					.build();
			assertFalse(feedback.equals(f));
		}


		@Test
		public void testEqualsToEqual() {
			IFeedback f = new Feedback.BuilderFeedback()
					.setIdFeedback(id)
					.build();
			assertTrue(feedback.equals(f));
		}


		@Test
		public void testHashCode() {
			assertEquals(Objects.hashCode(id), feedback.hashCode());
		}
	}

}
