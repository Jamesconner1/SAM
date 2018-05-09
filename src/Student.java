import java.util.Date;
import java.util.List;

public interface Student { //created interface for everything that could be related to the student
	
	public StudentID getStudentId();
	
	public Date getDateOfBirth();
	
	public String getFirstName();
	
	public String getLastName();
	
	default public String getSupervisorName() {
		throw new UnsupportedOperationException();
	}

	default public void setSupervisorName(String supervisorName) {
		throw new UnsupportedOperationException();
	}

	public List<Module> getModules();

	public void addModule(Module module);
	
	public boolean hasPassed();

	public SmartCard getSmartCard();
	
	public void issueSmartCard(SmartCard smartCard);
}
