import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PostGradTaughtTest {
	
	private Student postgrad;

	@BeforeEach
	public void setup() {
		postgrad = new PostGradTaught(new StudentID('a', 1234));
	}

	@Test
	public void testThatTotalCreditsCannotExceed120() {
		postgrad.addModule(new Module("CSC1022", "Programming II", 30));
		postgrad.addModule(new Module("CSC1022", "Programming II", 30));
		postgrad.addModule(new Module("CSC1022", "Programming II", 30));
		postgrad.addModule(new Module("CSC1022", "Programming II", 30));
		postgrad.addModule(new Module("CSC1022", "Programming II", 30));
		postgrad.addModule(new Module("CSC1022", "Programming II", 30));
		
		try {
			postgrad.addModule(new Module("CSC1022", "Programming II", 30));
			Assertions.fail("Expected exception");
		}
		catch (Exception e) {
			Assertions.assertEquals("Module not added (exceeded max credits: 180)", e.getMessage());
		}
	}

	@Test
	public void testThatStudentPassesIfAllModulesAreMarked50OrMore() {
		postgrad.addModule(moduleWithMark(50));
		postgrad.addModule(moduleWithMark(60));
		postgrad.addModule(moduleWithMark(70));
		postgrad.addModule(moduleWithMark(55));
		postgrad.addModule(moduleWithMark(65));
		postgrad.addModule(moduleWithMark(75));

		Assertions.assertTrue(postgrad.hasPassed());
	}
	
	@Test
	public void testThatStudentFailsIfAModulesIsMarkedLessThan50() {
		postgrad.addModule(moduleWithMark(50));
		postgrad.addModule(moduleWithMark(60));
		postgrad.addModule(moduleWithMark(70));
		postgrad.addModule(moduleWithMark(45));
		postgrad.addModule(moduleWithMark(65));
		postgrad.addModule(moduleWithMark(75));

		Assertions.assertFalse(postgrad.hasPassed());
	}

	@Test 
	public void testThatGetSupervisorNameFails() {
		try {
			postgrad.getSupervisorName();
			Assertions.fail("Expected exception");
		}
		catch (Exception e) {
			Assertions.assertTrue(e instanceof UnsupportedOperationException);
		}
	}	

	private Module moduleWithMark(int percentageMark) {
		Module module = new Module("CSC1022", "Programming II", 30);
		module.setPercentageMark(percentageMark);
		return module;
	}
}
