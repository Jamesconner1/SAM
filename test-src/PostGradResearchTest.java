import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PostGradResearchTest {
	
	private Student postgrad;

	@BeforeEach
	public void setup() {
		postgrad = new PostGradResearch(new StudentID('a', 1234));
	}

	@Test
	public void testThatGetModulesFails() {
		try {
			postgrad.getModules();
			Assertions.fail("Expected exception");
		}
		catch (Exception e) {
			Assertions.assertTrue(e instanceof UnsupportedOperationException);
		}
	}

	@Test
	public void testThatAddModuleFails() {
		try {
			postgrad.addModule(new Module("CSC1022", "Programming II", 30));
			Assertions.fail("Expected exception");
		}
		catch (Exception e) {
			Assertions.assertTrue(e instanceof UnsupportedOperationException);
		}
	}

	@Test
	public void testThatHasPassedFails() {
		try {
			postgrad.hasPassed();
			Assertions.fail("Expected exception");
		}
		catch (Exception e) {
			Assertions.assertTrue(e instanceof UnsupportedOperationException);
		}
	}
	
	@Test 
	public void testThatSupervisorIsReturned() {
		postgrad.setSupervisorName("Mr. Supervisor");
		Assertions.assertEquals("Mr. Supervisor", postgrad.getSupervisorName());
	}	
}
