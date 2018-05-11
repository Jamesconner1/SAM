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
		SmartCardNumberFactory.getInstance().reset();
	}
	
	@Test
	void testSmartCardNumberStartsAtCorrectValue() {
		Calendar cal = Calendar.getInstance();
		cal.set(1998, 3, 25); // month is zero-based
		SmartCard smartCard = new SmartCard("James", "Conner", cal.getTime());
		Assertions.assertEquals("JC-2018-1", smartCard.getSmartCardNumber().toString());
		assertDateEquals("1998-04-25", smartCard.getDateOfBirth());
		assertDateEquals(today, smartCard.getDateOfIssue());
	}

	@Test
	void testSmartCardNumberIsIncrementedIfInitialsAndYearOfIssueMatch() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(1998, 3, 25); // month is zero-based
		new SmartCard("James", "Conner", cal1.getTime());
		Calendar cal2 = Calendar.getInstance();
		cal2.set(1980, 7, 20); // month is zero-based
		SmartCard smartCard = new SmartCard("Jerry", "Chambers", cal2.getTime());
		Assertions.assertEquals("JC-2018-2", smartCard.getSmartCardNumber().toString());
		assertDateEquals("1980-08-20", smartCard.getDateOfBirth());
		assertDateEquals(today, smartCard.getDateOfIssue());
	}

	@Test
	void testSmartCardNumberIsNotIncrementedIfInitialsAreDifferent() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(1998, 3, 25); // month is zero-based
		new SmartCard("James", "Conner", cal1.getTime());
		Calendar cal2 = Calendar.getInstance();
		cal2.set(1980, 7, 20); // month is zero-based
		SmartCard smartCard = new SmartCard("John", "Smith", cal2.getTime());
		Assertions.assertEquals("JS-2018-1", smartCard.getSmartCardNumber().toString());
		assertDateEquals("1980-08-20", smartCard.getDateOfBirth());
		assertDateEquals(today, smartCard.getDateOfIssue());
	}

	@Test
	void testSmartCardNumbersIsNotIncrementedIfYearOfIssueIsDifferent() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(1998, 3, 25); // month is zero-based
		new SmartCard("James", "Conner", cal1.getTime());
		Calendar cal2 = Calendar.getInstance();
		cal2.set(1980, 7, 20); // month is zero-based
		Calendar yearOfIssueCal = Calendar.getInstance();
		yearOfIssueCal.set(2017, 10, 20); // month is zero-based
		SmartCard smartCard = new SmartCard("Jerry", "Chambers", cal2.getTime(), yearOfIssueCal.getTime());
		Assertions.assertEquals("JC-2017-1", smartCard.getSmartCardNumber().toString());
		assertDateEquals("1980-08-20", smartCard.getDateOfBirth());
		assertDateEquals("2017-11-20", smartCard.getDateOfIssue());
	}

	private void assertDateEquals(String expected, Date dateToTest) {
		String actual = (new SimpleDateFormat("yyyy-MM-dd")).format(dateToTest);
		Assertions.assertEquals(expected, actual);
	}
}
