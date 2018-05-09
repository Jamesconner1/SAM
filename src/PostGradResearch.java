import java.util.List;

public class PostGradResearch extends AbstractStudent{

	private String supervisorName;
	
	public PostGradResearch(StudentID studentID) {
		super(studentID); // calling parent constructor
	}

	@Override
	public String getSupervisorName() {
		return supervisorName;
	}

	@Override
	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	@Override
	public boolean hasPassed() {
		// all module-related methods are not relevant to post grad research students and are therefore not implemented
		throw new UnsupportedOperationException();
	}

	@Override
	public List<Module> getModules() {
		// all module-related methods are not relevant to post grad research students and are therefore not implemented
		throw new UnsupportedOperationException();
	}

	@Override
	public void addModule(Module module) {
		// all module-related methods are not relevant to post grad research students and are therefore not implemented
		throw new UnsupportedOperationException();
	}
}
