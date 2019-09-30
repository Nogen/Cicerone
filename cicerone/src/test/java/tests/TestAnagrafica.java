package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import com.intuition.cicerone.autenticazione.Anagrafica;
import com.intuition.cicerone.autenticazione.Anagrafica.BuilderAnagrafica;
import com.intuition.cicerone.autenticazione.IAnagrafica;
import com.intuition.cicerone.autenticazione.utils.IdGenerator;

@RunWith(Parameterized.class)
public class TestAnagrafica {
	public String nome;
	public String cognome;
	public LocalDate date;
	private IAnagrafica anagrafica;
	private Set<String> lingue;
	private String id = IdGenerator.generateId();
	private static final String troppoLunga = "0FsLH1DwfDhlo5lklHuzZUYMsAii3HWiKa5h401z5AXfuskU75ZP2zIOIrNpVWA4u8Y5z4BawZMjV"
			+ "xMAidcyoJIou92QXTMYvL1VYDatQdM31AJn9cGdR9ByXh2aJlFh8lf6JEtVMgsxKuBok3x1kl9bIBFLiB1mgOw4o3gBJk0UVCVYBWvOBdrWL"
			+ "QGns6KODnnj1mKXL2br5vn35M6G7P06mf4GtyGuLMwJBk7McccT9tbdNjXdBHTYgLnmNVliVP9pphXOU2BjaiczS5K3vTMYeeAztlDeXdlUqN"
			+ "ZohsBcNi01HZEY2v7N3khQsiQ5ks08y2Vb2agByqt2XlqDHi4VLuFNKZ6i352hiQaPAJiVcL0VMBH8mSbnweFNKDLzmzjGj4XGORAtivuh9CZ"
			+ "Bo36fpbrAwOvrk0Ju7TbhSF76e9R3OdCKJw4sVfAucA1tgJrMd9IBTUFid0699AkTAAyOyEFVnNUIh7kI4MOqVkjUlpLUCcVheDYaEMRn4snN"
			+ "LEPzfbZzalKIW9SZOQQwwXV96xr9Kx8AbInYOSJy11eWkdWumbVKolLhXejdKNi9HKQYhZadOrdvn0teHB6QZGEZNYPkIbJas2scAtrNKZH3H"
			+ "6JVTkFbwO6slOcKtRlAzqTbwcamxIYyR5f7JyCfT41gVRxptp6ElSCTI5O4VpTYiupruClFSozShZOtsX3DAEX0Vff3EcTTSodFipAjIjkmxI"
			+ "UXisAaCh8mPwzNufsLkxWrkyEQsGqN17PKFkYHpmSmc5mJuVXQL5z0tty88w48DWznZLbIlNsIZ6K1D96eJU0EHUxJE4DBGaRzQbDdY8PcqbX"
			+ "ON8zuXxl1DR3ML8ZRrurkdGP1XQYwSO6Z2UZO9iNrLoAU9q5T09S52xiGx8VpHWWeOSweMFCHkJWlYrUcrk2NwkaU7gNT1FUHJr9Jjl7rmUB5"
			+ "f69nqLF5VZdgnCDCr5KZo1Uor0rkxqrqgTt8in3S3hD0FHXz7na4";

	@Before
	public void setUp() {
		nome = "Mario";
		cognome = "Rossi";
		date = LocalDate.now();
		lingue = new HashSet<>();
		lingue.add("Spagnolo");
		lingue.add("Francese");
		lingue.add("Giamaicano");
		this.anagrafica = new Anagrafica.BuilderAnagrafica().setIdAnagrafica(id).setCognome("Rossi").setNome(nome)
				.setDataDiNascita(LocalDate.now()).setNumeroDiTelefono("3333333333").setPaeseCitta("Bitonto")
				.setSesso(Anagrafica.Sesso.UOMO).setLingueParlate(lingue).build();
	}

	@Parameter(0)
	public String nomeParam;

	@Parameter(1)
	public String cognomeParam;

	@Parameter(2)
	public String residenza;

	@Parameter(3)
	public String nTelefono;

	@Parameter(4)
	public LocalDate bday;

	@Parameter(5)
	public String linguaTest;

	@Parameter(6)
	public String idCasuale;

	// creates the test data
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { "V", "F", "bit", "033333", LocalDate.of(2020, 8, 30), "swe", null },
				{ "noletteraMaiuscola", "noletteraMaiuscola", "Caratteri, validi!£$%&/()=?^[]@#", "ABCD123456",
						LocalDate.of(2019, 9, 14), "swe123-,,df", null },
				{ "CaratteriSpeciali!£$%&/()=?^[]@# ", "CaratteriSpeciali!£$%&/()=?^[]@#",
						"CaratteriSpeciali!£$%&/()=?^[]@#", "011111111111", LocalDate.of(2050, 8, 30), "Francese",
						null },
				{ troppoLunga, troppoLunga, troppoLunga, "0-,111AA11111", LocalDate.of(2100, 5, 30), troppoLunga,
						null },
				{ null, null, null, null, null, null, null } };
		return Arrays.asList(data);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBuilderId() {
		new BuilderAnagrafica().setIdAnagrafica(idCasuale);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBuilderNome() {
		new BuilderAnagrafica().setNome(nomeParam);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBuilderCognome() {
		new BuilderAnagrafica().setCognome(cognomeParam);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBuilderResidenza() {
		new BuilderAnagrafica().setPaeseCitta(residenza);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBuilderData() {
		new BuilderAnagrafica().setDataDiNascita(bday);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBuilderTelefono() {
		new BuilderAnagrafica().setNumeroDiTelefono(nTelefono);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAnagraficaTelefono() {
		this.anagrafica.modificaNumeroDiTelefono(nTelefono);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testLingueParlate() {
		new BuilderAnagrafica().setLingueParlate(null);
	}

	@Test
	public void testLingueParlateSuccess() {
		IAnagrafica a = new BuilderAnagrafica().setLingueParlate(lingue).build();
		assertEquals(lingue, a.getLingueParlate());
	}

	@Test
	public void testNumeroTelefono() {
		String telefono = "8888888888";
		this.anagrafica.modificaNumeroDiTelefono(telefono);
		assertEquals(telefono, anagrafica.getNumeroDiTelefono());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAnagraficaLingue() {
		this.anagrafica.aggiungiLingueParlate(linguaTest);
	}

	@Test
	public void testlingue() {
		String lingua = "Portoghese";
		this.anagrafica.aggiungiLingueParlate(lingua);
		assertTrue(anagrafica.getLingueParlate().contains(lingua));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testResidenzaErrata() {
		this.anagrafica.modificaPaeseCitta(residenza);
	}

	@Test
	public void testResidenza() {
		String residenza = "Bitonto, Bari, Italia";
		this.anagrafica.modificaPaeseCitta(residenza);
		assertEquals(residenza, anagrafica.getPaeseCitta());
	}

	@Test
	public void testSesso() {
		assertEquals(Anagrafica.Sesso.UOMO, anagrafica.getSesso());
	}

	@Test
	public void testId() {
		assertEquals(id, anagrafica.getIdAnagrafica());
	}

	@Test
	public void testEqual() {
		IAnagrafica a = new Anagrafica.BuilderAnagrafica().generaIdAnagrafica().build();
		assertFalse(anagrafica.equals(a));
	}

	@Test
	public void testEqualSuccess() {
		IAnagrafica a = new Anagrafica.BuilderAnagrafica().setIdAnagrafica(id).build();
		assertTrue(anagrafica.equals(a));
	}

	@Test
	public void testEqualNull() {
		assertFalse(anagrafica.equals(null));
	}

	@Test
	public void testHashCode() {
		assertEquals(Objects.hashCode(id), anagrafica.hashCode());
	}

	@Test
	public void testSessoEnumUomo() {
		assertTrue(0 == Anagrafica.Sesso.UOMO.getValore());
	}

	@Test
	public void testSessoEnumDonna() {
		assertTrue(1 == Anagrafica.Sesso.DONNA.getValore());
	}

	@Test
	public void testgetNome() {
		assertEquals(nome, this.anagrafica.getNome());
	}

	@Test
	public void testgetCognome() {
		assertEquals(cognome, this.anagrafica.getCognome());
	}

	@Test
	public void getDataDiNascita() {
		assertEquals(date, this.anagrafica.getDataDiNascita());
	}

}
