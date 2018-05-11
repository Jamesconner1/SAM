/**
 * Simple data class for modules.
 */
public class Module {

	private String name;
	
	private String moduleCode;
	
	private int credits;
	
	private int percentageMark;

	public Module(String moduleCode, String name, int credits) {
		this.name = name;
		this.moduleCode = moduleCode;
		this.credits = credits;
	}

	public int getPercentageMark() {
		return percentageMark;
	}

	public void setPercentageMark(int percentageMark) {
		// TODO: validate that percentageMark is <= 100?
		this.percentageMark = percentageMark;
	}

	public String getName() {
		return name;
	}

	public String getModuleCode() {
		return moduleCode;
	}

	public int getCredits() {
		return credits;
	}
}
