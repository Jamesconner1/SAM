import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Abstract base class for students that provides implementations of addModule + hasPassed.
 *
 * This is because Undergraduate + PostGradTaught students can both use this functionality as is - the only variation
 * being the max credits and pass mark. That's why we have an overloaded constructor that takes both these values: so the
 * implementations of Undergraduate + PostGradTaught can pass in their respective details.
 *
 * In the case of PostGradResearch students, modules are not applicable so they do not make use of this functionality.
 */
public abstract class AbstractStudent implements Student {

	private StudentID studentID;
	
	private Date dateOfBirth;
	
	private String firstName;
	
	private String lastName;

	// put this here because there are 2 student types that need it: undergraduate and post grad taught
	private List<Module> modules = new ArrayList<>();
	
	private SmartCard smartCard;
	
	private int maxCredits;
	
	private int passMark;
	
	public AbstractStudent(StudentID studentID) {
		this.studentID = studentID;
	}
	
	public AbstractStudent(StudentID studentID, int maxCredits, int passMark) {
		this.studentID = studentID;
		this.maxCredits = maxCredits;
		this.passMark = passMark;
	}	
	
	@Override
	public StudentID getStudentId() {
		return studentID;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public List<Module> getModules() {
		return modules;
	}

	@Override
	public void addModule(Module module) {
		int totalCredits = modules.stream().mapToInt(myModule -> module.getCredits()).sum();
		if (totalCredits + module.getCredits() > maxCredits) {
			// TODO: create an exception for this!
			throw new RuntimeException("Module not added (exceeded max credits: " + maxCredits + ")");
		}
		modules.add(module);
	}

	@Override
	public boolean hasPassed() {
		List<Module> modules = getModules(); 
		// makes sure the mark each student got for every module is >= pass mark
		return modules.stream().allMatch(module -> module.getPercentageMark() >= passMark);
	}

	@Override
	public SmartCard getSmartCard() {
		return smartCard;
	}

	@Override
	public void issueSmartCard(SmartCard smartCard) {
		this.smartCard = smartCard;
	}
}
