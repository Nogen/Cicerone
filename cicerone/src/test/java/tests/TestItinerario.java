package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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

import com.intuition.cicerone.autenticazione.utils.IdGenerator;
import com.intuition.cicerone.gestioneattivita.attivita.IItinerario;
import com.intuition.cicerone.gestioneattivita.attivita.Itinerario;
import com.intuition.cicerone.gestioneattivita.attivita.Itinerario.Retribuzione;

@RunWith(Parameterized.class)
public class TestItinerario {
	private LocalDate data;
	private LocalTime time;
	private String luogo;
	private IItinerario itinerario;
	private String descrizioneP;
	private Double valoreRetribuzione;
	private String id;
	private Set<String> lingue;
	private static final String  troppoLunga = "0FsLH1DwfDhlo5lklHuzZUYMsAii3HWiKa5h401z5AXfuskU75ZP2zIOIrNpVWA4u8Y5z4BawZMjV"
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
		time = LocalTime.now();
		data = LocalDate.now();
		luogo = "Bitonto";
		descrizioneP = "Prova descrizione";
		valoreRetribuzione = Double.valueOf(50D);
		lingue = new HashSet<>();
		lingue.add("Francese");
		lingue.add("Spagnolo");
		id = IdGenerator.generateId();
		itinerario = new Itinerario.BuilderItinerario()
				.setIdItinerario(id)
				.setDescrizioneAttivita(descrizioneP)
				.setRetribuzione(Retribuzione.RETRIBUITO, valoreRetribuzione)
				.setLingua(lingue)
				.build();
		itinerario.setDataIncontro(data);
		itinerario.setLuogo(luogo);
		itinerario.setOraIncontro(time);
	}
	
	
	@Parameter(0)	
	public String idCasuale;
	
	@Parameter(1)
	public Set<String> linguaTest;
	
	@Parameter(2)
	public ArrayList<String> fotoTest;
	
	@Parameter(3)	
	public String descrizioneTest;
	
	@Parameter(4)
	public Itinerario.Retribuzione retribuzioneTest;
	
	@Parameter(5)
	public Double valoreRetribuzioneTest;
	


    // creates the test data
    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {  
        	{null, null, null, null, null, null},
        	{null, null, null, troppoLunga, Itinerario.Retribuzione.RETRIBUITO, Double.valueOf(-1)},
        	{null, null, null, "", Itinerario.Retribuzione.RETRIBUITO, Double.valueOf(-1)},
        	};
        return Arrays.asList(data);
    }
	
    @Test(expected = IllegalArgumentException.class)
	public void testBuilderid() {
		new Itinerario.BuilderItinerario().setIdItinerario(idCasuale);
	}
    
    @Test(expected = IllegalArgumentException.class)
    public void testBuilderLingua() {
    	new Itinerario.BuilderItinerario().setLingua(linguaTest);
    }
        
    @Test(expected = IllegalArgumentException.class)
    public void testBuilderDescrizione() {
    	new Itinerario.BuilderItinerario().setDescrizioneAttivita(descrizioneTest);
    }
    
    @Test
    public void testBuilderDescrizioneSuccess() {
    	IItinerario i = new Itinerario.BuilderItinerario().setDescrizioneAttivita("Descrizione di prova").build();
    	assertEquals("Descrizione di prova", i.getDescrizioneAttivita());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBuilderRetribuzione() {
    	new Itinerario.BuilderItinerario().setRetribuzione(retribuzioneTest, valoreRetribuzioneTest);
    }
    
    @Test
    public void testBuilderRetribuzioneValore() {
    	IItinerario i = new Itinerario.BuilderItinerario().setRetribuzione(Itinerario.Retribuzione.RETRIBUITO, 5.5D).build();
    	assertEquals(Itinerario.Retribuzione.RETRIBUITO, i.getRetribuzione());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBuilderRetribuzioneValoreNegativo() {
    	IItinerario i = new Itinerario.BuilderItinerario().setRetribuzione(Itinerario.Retribuzione.RETRIBUITO, -5.5D).build();
    	assertEquals(Double.valueOf(-5.5D), i.getValoreRetribuzione());
    }
    
    @Test
    public void testBuilderRetribuzioneNonRetribuito() {
    	IItinerario i = new Itinerario.BuilderItinerario().setRetribuzione(Itinerario.Retribuzione.NONRETRIBUITO, 5.5D).build();
    	assertEquals(Itinerario.Retribuzione.NONRETRIBUITO, i.getRetribuzione());
    }
    
    @Test
    public void testItinierarioEqualsFalse() {
    	assertFalse(itinerario.equals(null));
    }
    
    @Test
    public void testItinierarioEqualsFalseNull() {
    	IItinerario i = new Itinerario.BuilderItinerario()
    			.generaIdItinerario()
    			.build();
    	assertFalse(itinerario.equals(i));
    }
    
    @Test
    public void testItinierarioEqualsTrue() {
    	IItinerario i = new Itinerario.BuilderItinerario()
    			.setIdItinerario(id)
    			.build();
    	assertTrue(itinerario.equals(i));
    }
    
    @Test
    public void testItinierarioHashCode() {
    	assertEquals(itinerario.hashCode(), Objects.hashCode(id));
    }
    
    @Test
	public void getValoreRetribuzione() {
		assertEquals(valoreRetribuzione, this.itinerario.getValoreRetribuzione());
	}
	
	@Test
	public void getDataIncontro() {
		assertEquals(data, this.itinerario.getDataIncontro());
	}
	
	@Test
	public void getDescrizioneAttivita() {
		assertEquals(descrizioneP, this.itinerario.getDescrizioneAttivita());
	}

	@Test
	public void getIdItinerario() {
		assertEquals(id, this.itinerario.getIdItinerario());
	}

	@Test
	public void getLingua() {
		assertEquals(lingue, this.itinerario.getLingua());
	}

	@Test
	public void getLuogo() {
		this.setLuogo();
		assertEquals(luogo, this.itinerario.getLuogo());
	}


	@Test
	public void getOraIncontro() {
		this.setOraIncontro();
		assertEquals(time, this.itinerario.getOraIncontro());
	}

	@Test
	public void getRetribuzione() {
		assertEquals(Retribuzione.RETRIBUITO, this.itinerario.getRetribuzione());
	}
	
	@Test
	public void getxIncontro() {
		setCoordinateIncontroSuccess();
		assertEquals(Double.valueOf(5D), this.itinerario.getxIncontro());
	}
	
	@Test
	public void getyIncontro() {
		setCoordinateIncontroSuccess();
		assertEquals(Double.valueOf(5D), this.itinerario.getyIncontro());
	}
    
	
	@Test
	public void setCoordinateIncontroSuccess() {
		this.itinerario.setCoordinateIncontro(5D, 5D);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setCoordinateIncontroNull() {
		this.itinerario.setCoordinateIncontro(null, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setCoordinateIncontroNullPartialX() {
		this.itinerario.setCoordinateIncontro(5D, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setCoordinateIncontroNullPartialY() {
		this.itinerario.setCoordinateIncontro(null, 5D);
	}

	@Test
	public void setDataIncontro() {
		this.itinerario.setDataIncontro(data);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setDataIncontroNull() {
		this.itinerario.setDataIncontro(null);
	}
	

	@Test
	public void setLuogo() {
		this.itinerario.setLuogo(luogo);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setLuogoShort() {
		this.itinerario.setLuogo("bit");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setLuogoLong() {
		this.itinerario.setLuogo(troppoLunga);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setLuogoSpecialChar() {
		this.itinerario.setLuogo("bitonto, puglia@");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void setLuogoNull() {
		this.itinerario.setLuogo(null);
	}
	
	
	
	@Test
	public void setOraIncontro() {
		this.itinerario.setOraIncontro(time);

	}	
	
	@Test(expected = IllegalArgumentException.class)
	public void setOraIncontroNull() {
		this.itinerario.setOraIncontro(null);

	}	
	
	@Test
	public void setDescrizioneSuccess() {
		this.itinerario.setDescrizioneAttivita("descrizioneTestà");
		assertEquals("descrizioneTestà", this.itinerario.getDescrizioneAttivita());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setDescrizioneFail1() {
		this.itinerario.setDescrizioneAttivita(troppoLunga);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setDescrizioneFail2() {
		this.itinerario.setDescrizioneAttivita("");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setDescrizioneFail3() {
		this.itinerario.setDescrizioneAttivita(null);
	}
	
	
}
