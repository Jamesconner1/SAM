import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StudentIDTest {

	@Test
	public void testThatValidInputsSucceed() {		
		StudentID studentID1 = new StudentID('a', 1000);
		Assertions.assertNotNull(studentID1);
		
		StudentID studentID2 = new StudentID('z', 9999);
		Assertions.assertNotNull(studentID2);
	}

	@Test
	public void testThatInvalidInputLetterFails() {
		try {
			new StudentID('A', 1234);
			Assertions.fail("Expected exception");
		}
		catch (Exception e) {
			Assertions.assertEquals("Invalid letter: A", e.getMessage());			
		}
	}

	@Test
	public void testThatInvalidInputNumberFails() {
		try {
			new StudentID('c', 12345);
			Assertions.fail("Expected exception");
		}
		catch (Exception e) {
			Assertions.assertEquals("Invalid number: 12345", e.getMessage());			
		}
	}

	@Test
	public void testThatToStringReturnsExpectedResult() {		
		StudentID studentID1 = new StudentID('a', 1000);
		Assertions.assertEquals("a1000", studentID1.toString());
		
		StudentID studentID2 = new StudentID('z', 9999);
		Assertions.assertNotNull("z9999", studentID2.toString());
	}
}
