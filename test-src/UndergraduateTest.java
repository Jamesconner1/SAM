import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class UndergraduateTest {
	
	private Student undergraduate;

	@BeforeEach
	public void setup() {
		undergraduate = new Undergraduate(new StudentID('a', 1234));
	}

	@Test
	public void testThatTotalCreditsCannotExceed120() {
		undergraduate.addModule(new Module("CSC1022", "Programming II", 20));
		undergraduate.addModule(new Module("CSC1022", "Programming II", 20));
		undergraduate.addModule(new Module("CSC1022", "Programming II", 20));
		undergraduate.addModule(new Module("CSC1022", "Programming II", 20));
		undergraduate.addModule(new Module("CSC1022", "Programming II", 20));
		undergraduate.addModule(new Module("CSC1022", "Programming II", 20));
		
		try {
			undergraduate.addModule(new Module("CSC1022", "Programming II", 20));
			Assertions.fail("Expected exception");
		}
		catch (Exception e) {
			Assertions.assertEquals("Module not added (exceeded max credits: 120)", e.getMessage());
		}
	}
	
	@Test
	public void testThatStudentPassesIfAllModulesAreMarked40OrMore() {
		undergraduate.addModule(moduleWithMark(50));
		undergraduate.addModule(moduleWithMark(60));
		undergraduate.addModule(moduleWithMark(70));
		undergraduate.addModule(moduleWithMark(45));
		undergraduate.addModule(moduleWithMark(55));
		undergraduate.addModule(moduleWithMark(65));

		Assertions.assertTrue(undergraduate.hasPassed());
	}
	
	@Test
	public void testThatStudentFailsIfAModulesIsMarkedLessThan40() {
		undergraduate.addModule(moduleWithMark(50));
		undergraduate.addModule(moduleWithMark(60));
		undergraduate.addModule(moduleWithMark(70));
		undergraduate.addModule(moduleWithMark(35));
		undergraduate.addModule(moduleWithMark(55));
		undergraduate.addModule(moduleWithMark(65));

		Assertions.assertFalse(undergraduate.hasPassed());
	}
	
	@Test 
	public void testThatGetSupervisorNameFails() {
		try {
			undergraduate.getSupervisorName();
			Assertions.fail("Expected exception");
		}
		catch (Exception e) {
			Assertions.assertTrue(e instanceof UnsupportedOperationException);
		}
	}
	
	private Module moduleWithMark(int percentageMark) {
		Module module = new Module("CSC1022", "Programming II", 20);
		module.setPercentageMark(percentageMark);
		return module;
	}
}
