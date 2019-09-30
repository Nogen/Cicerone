package tests;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import com.intuition.cicerone.persistenza.database.entity.DateConverter;
public class TestDataConverter {
	private DateConverter converter;
	private LocalDate ldata;
	private Date sqldata;

	@Before
	public void setUp() {
		ldata = LocalDate.now();
		sqldata = Date.valueOf(ldata);
		converter = new DateConverter();
	}
	
	@Test
	public void testConvertToDb() {
		assertEquals(sqldata, converter.convertToDatabaseColumn(ldata));
	}
	
	@Test
	public void testConvertToDbNull() {
		assertEquals(null, converter.convertToDatabaseColumn(null));
	}
	
	
	@Test
	public void testConvertFromDb() {
		assertEquals(ldata, converter.convertToEntityAttribute(sqldata));
	}
	
	@Test
	public void testConvertFromDbNull() {
		assertEquals(null, converter.convertToEntityAttribute(null));
	}
}
