package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.intuition.cicerone.notificazione.IEventoCommand;
import com.intuition.cicerone.notificazione.INotificable;
import com.intuition.cicerone.notificazione.statonotificazione.AccettazioneGlobetrotter;


public class TestAccettazioneGlobetrotter {
	private String toString;
	private IEventoCommand cmd;
	private INotificable n;
	
	@Before
	public void setUp() {
		toString = "la tua iscrizione è stata accettata";
		n = mock(INotificable.class);
		cmd = new AccettazioneGlobetrotter();
	}
	
	@Test
	public void testToString() {
		assertEquals(toString, cmd.toString());
	}
	
	@Test
	public void testNotifica() {
		doNothing().when(n).notificaMe(any(IEventoCommand.class));
		this.cmd.notifica(n);
		assertTrue(true);
	}
}
