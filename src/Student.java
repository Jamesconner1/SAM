import java.util.Date;
import java.util.List;

/**
 * Interface for students that provides default implementations of getSupervisorName + setSupervisorName.
 *
 * This is because we want all students to have a standard interface but only PostGradResearch students have supervisors.
 *
 * Undergraduate + PostGradTaught students will throw an UnsupportedOperationException if either of these methods is invoked.
 */
public interface Student { //created interface for everything that could be related to the student
	
	StudentID getStudentId();
	
	Date getDateOfBirth();
	
	String getFirstName();
	
	String getLastName();
	
	default String getSupervisorName() {
		throw new UnsupportedOperationException();
	}

	default void setSupervisorName(String supervisorName) {
		throw new UnsupportedOperationException();
	}

	List<Module> getModules();

	void addModule(Module module);
	
	boolean hasPassed();

	SmartCard getSmartCard();
	
	void issueSmartCard(SmartCard smartCard);
}
