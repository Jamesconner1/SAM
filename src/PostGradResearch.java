import java.util.List;

/**
 * Unlike the other student types, PostGradResearch students do not have modules so override getModules, addModule +
 * hasPassed; throwing an UnsupportedOperationException if any of these methods are invoked.
 *
 * This class also provides a supervisor name, overriding the default implementations of getSupervisorName +
 * setSupervisorName.
 */
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
