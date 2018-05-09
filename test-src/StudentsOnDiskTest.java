import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentsOnDiskTest {

	private StudentsOnDisk studentsOnDisk;

	@BeforeEach
	public void setup() {
		File store = new File("/tmp/students");
		if (store.exists()) {
			File[] files = store.listFiles();
			for (File file : files) {
				file.delete();
			}
			store.delete();
		}
		studentsOnDisk = new StudentsOnDisk(new File("/tmp/students"));
	}

	@Test
	public void testThatStudentsAreStored() throws Exception {
		AbstractStudent student = new Undergraduate(new StudentID('a', 1234));
		student.setFirstName("James");
		student.setLastName("Conner");
		Date dateOfBirth = (new SimpleDateFormat("yyyy-MM-dd")).parse("1998-04-25");
		student.setDateOfBirth(dateOfBirth);
		student.addModule(new Module("H1", "Hello World", 20));
		student.addModule(new Module("H2", "Hey there", 30));
		studentsOnDisk.registerStudent(student);

		Assertions.assertTrue(new File("/tmp/students/a1234").exists());
		// TODO: read file contents and check they're correct
	}

	@Test
	public void testThatStudentsAreRejectedIfAgeRulesAreBroken() throws Exception {
		AbstractStudent student = new PostGradTaught(new StudentID('a', 1234));
		student.setFirstName("James");
		student.setLastName("Conner");
		Date dateOfBirth = (new SimpleDateFormat("yyyy-MM-dd")).parse("1999-04-25");
		student.setDateOfBirth(dateOfBirth);
		student.addModule(new Module("H1", "Hello World", 20));
		student.addModule(new Module("H2", "Hey there", 30));
		try {
			studentsOnDisk.registerStudent(student);
			Assertions.fail("Expected exception");
		}
		catch (Exception e) {
			Assertions.assertEquals("Student not old enough to be post grad: age=19", e.getMessage());
		}
	}
}
