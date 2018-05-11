import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentIDFactoryTest {

	@BeforeEach
	public void setup() {
		StudentIDFactory.getInstance().reset();
	}

	@Test
	void testThatIdStartsAtCorrectValue() {
		StudentIDFactory factory = StudentIDFactory.getInstance();
		StudentID studentID = factory.createStudentID();
		assertEquals("a1000", studentID.toString());
	}

	@Test
	void testThatIdIsIncrementedCorrectly() {
		StudentIDFactory factory = StudentIDFactory.getInstance();
		factory.createStudentID();
		StudentID studentID = factory.createStudentID();
		assertEquals("a1001", studentID.toString());
	}

	@Test
	void testThatLetterRollsOverWhenNumberExceeds9999() {
		StudentIDFactory factory = StudentIDFactory.getInstance();
		for (int i = 0; i < 9000; i++) {
			factory.createStudentID();
		}
		StudentID studentID = factory.createStudentID();
		assertEquals("b1000", studentID.toString());
	}
}
