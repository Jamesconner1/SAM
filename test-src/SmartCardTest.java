import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SmartCardTest {

	private String today;
	
	@BeforeEach
	public void setup() {
		today = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
	}
	
	@Test
	void testSmartCardsAreAllocatedCorrectly() {
		Calendar cal = Calendar.getInstance();
		cal.set(1998, 3, 25); // month is zero-based
		SmartCard smartCard1 = new SmartCard("James", "Conner", cal.getTime());
		Assertions.assertEquals("JC-2018-1", smartCard1.getSmartCardNumber().toString());
		assertDateEquals("1998-04-25", smartCard1.getDateOfBirth());
		assertDateEquals(today, smartCard1.getDateOfIssue());
		
		cal = Calendar.getInstance();
		cal.set(1980, 7, 20); // month is zero-based
		SmartCard smartCard2 = new SmartCard("John", "Smith", cal.getTime());
		Assertions.assertEquals("JS-2018-1", smartCard2.getSmartCardNumber().toString());
		assertDateEquals("1980-08-20", smartCard2.getDateOfBirth());
		assertDateEquals(today, smartCard2.getDateOfIssue());

		SmartCard smartCard3 = new SmartCard("Jerry", "Sykes", cal.getTime());
		Assertions.assertEquals("JS-2018-2", smartCard3.getSmartCardNumber().toString());
		assertDateEquals("1980-08-20", smartCard3.getDateOfBirth());
		assertDateEquals(today, smartCard3.getDateOfIssue());

		Calendar cal2 = Calendar.getInstance();
		cal2.set(2017, 10, 20); // month is zero-based
		SmartCard smartCard4 = new SmartCard("Jamie", "Sugden", cal.getTime(), cal2.getTime()); // this student registered last year
		Assertions.assertEquals("JS-2017-1", smartCard4.getSmartCardNumber().toString());
		assertDateEquals("1980-08-20", smartCard4.getDateOfBirth());
		assertDateEquals("2017-11-20", smartCard4.getDateOfIssue());
	}
	
	private void assertDateEquals(String expected, Date dateToTest) {
		String actual = (new SimpleDateFormat("yyyy-MM-dd")).format(dateToTest);
		Assertions.assertEquals(expected, actual);
	}
}
