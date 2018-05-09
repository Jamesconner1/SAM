import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class StudentIDFactoryTest {

	@Test
	void testThatIDsAreAllocatedCorrectly() {
		// I've put 3 tests into one method due to StudentIDFactory being a singleton
		
		// test the ID's start at correct value
		StudentIDFactory factory1 = StudentIDFactory.getInstance();
		StudentID studentID1 = factory1.createStudentID();
		assertEquals("a1000", studentID1.toString());

		// test that ID's are incremental
		StudentIDFactory factory2 = StudentIDFactory.getInstance();
		StudentID studentID2 = factory2.createStudentID();
		assertEquals("a1001", studentID2.toString());

		// test that letter is incremented when number exceeds 9999
		StudentIDFactory factory3 = StudentIDFactory.getInstance();
		for (int i = 0; i < 8998; i++) {
			factory3.createStudentID();
		}
		StudentID studentID = factory3.createStudentID();
		assertEquals("b1000", studentID.toString());
	}
}
