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
import com.intuition.cicerone.notificazione.statonotificazione.RifiutoGlobetrotter;


public class TestRifiutoGlobetrotter {
	private String toString;
	private IEventoCommand cmd;
	private INotificable n;
	
	@Before
	public void setUp() {
		toString = "Sei stato rifiutato da";
		n = mock(INotificable.class);
		cmd = new RifiutoGlobetrotter();
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
