public class SmartCardNumber {

	private String initials;
	private int yearOfIssue;
	private int serialNumber;

	public SmartCardNumber(String initials, int yearOfIssue, int serialNumber) {
		this.initials = initials;
		this.yearOfIssue = yearOfIssue;
		this.serialNumber = serialNumber;
	}
	
	public String toString() {
		return initials + "-" + String.valueOf(yearOfIssue) + "-" + String.valueOf(serialNumber);
	}
}
