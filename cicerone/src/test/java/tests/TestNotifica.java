package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;

import com.intuition.cicerone.autenticazione.utils.IdGenerator;
import com.intuition.cicerone.gestioneattivita.attivita.IAttivita;
import com.intuition.cicerone.notificazione.IEventoCommand;
import com.intuition.cicerone.notificazione.INotifica;
import com.intuition.cicerone.notificazione.INotificable;
import com.intuition.cicerone.notificazione.Notifica;

public class TestNotifica {
	private INotificable destinatario;
	private IAttivita attivita; 
	private LocalDate data; 
	private IEventoCommand evento;
	private String id; 
	private INotifica notifica;
	
	@Before
	public void setUp() {
		destinatario = mock(INotificable.class);
		attivita = mock(IAttivita.class);
		data = LocalDate.now();
		evento = mock(IEventoCommand.class);
		id = IdGenerator.generateId();
		notifica = new Notifica.BuilderNotifica()
				.setAttivita(attivita)
				.setDataDiApertura(data)
				.generaIdNotifica()
				.setDestinatario(destinatario)
				.setEvento(evento)
				.setIdNotifica(id)
				.build();
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testBuilderDestinatario() {
		new Notifica.BuilderNotifica().setDestinatario(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testBuilderId() {
		new Notifica.BuilderNotifica().setIdNotifica(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testBuilderAttivita() {
		new Notifica.BuilderNotifica().setAttivita(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testBuilderData() {
		new Notifica.BuilderNotifica().setDataDiApertura(null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testBuilderEvento() {
		new Notifica.BuilderNotifica().setEvento(null);
	}
	
	@Test
	public void getDestinatario() {
		assertEquals(destinatario, this.notifica.getDestinatario());
	}

	@Test
	public void getAttivita() {
		assertEquals(attivita, this.notifica.getAttivita());
	}

	@Test
	public void getDataDiApertura() {
		assertEquals(data, this.notifica.getDataDiApertura());
	}

	@Test
	public void getEvento() {
		assertEquals(evento, this.notifica.getEvento());
	}

	@Test
	public void getIdNotifica() {
		assertEquals(id, this.notifica.getIdNotifica());
	}
	
	@Test
	public void inoltra() {
		doNothing().when(evento).notifica(any(INotificable.class));
		this.notifica.inoltra();
		assertTrue(true);
	}
	
	@Test
	public void testEquals() {
		INotifica n = new Notifica.BuilderNotifica()
				.generaIdNotifica()
				.build();
		assertFalse(n.equals(this.notifica));
	}
	
	@Test
	public void testEqualsTrue() {
		INotifica n = new Notifica.BuilderNotifica()
				.setIdNotifica(id)
				.build();
		assertTrue(n.equals(this.notifica));
	}
	
	@Test
	public void testEqualsNull() {
		assertFalse(this.notifica.equals(null));
	}
	
	@Test
	public void testHashCode() {
		assertEquals(Objects.hashCode(id), this.notifica.hashCode());
	}
	
}
