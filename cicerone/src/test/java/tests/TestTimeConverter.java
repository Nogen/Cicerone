package tests;

import static org.junit.Assert.assertEquals;

import java.sql.Time;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import com.intuition.cicerone.persistenza.database.entity.TimeConverter;

public class TestTimeConverter {
	private TimeConverter converter;
	private LocalTime ltime;
	private Time sqltime;

	@Before
	public void setUp() {
		ltime = LocalTime.now();
		sqltime = Time.valueOf(ltime);
		converter = new TimeConverter();
	}
	
	@Test
	public void testConvertToDb() {
		assertEquals(sqltime, converter.convertToDatabaseColumn(ltime));
	}
	
	@Test
	public void testConvertToDbNull() {
		assertEquals(null, converter.convertToDatabaseColumn(null));
	}
	
	
	@Test
	public void testConvertFromDb() {
		assertEquals(sqltime.toLocalTime(), converter.convertToEntityAttribute(sqltime));
	}
	
	@Test
	public void testConvertFromDbNull() {
		assertEquals(null, converter.convertToEntityAttribute(null));
	}

}
