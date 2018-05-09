import java.util.Date;

public class SmartCard {

	private SmartCardNumber smartCardNumber;
	
	private String firstName;
	
	private String lastName;
	
	private Date dateOfBirth;
	
	private Date dateOfIssue;

	public SmartCard(String firstName, String lastName, Date dateOfBirth) {
		this(firstName, lastName, dateOfBirth, new Date());
	}

	public SmartCard(String firstName, String lastName, Date dateOfBirth, Date dateOfIssue) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.dateOfIssue = dateOfIssue;
		// create smart card number
		SmartCardNumberFactory factory = SmartCardNumberFactory.getInstance();
		this.smartCardNumber = factory.createSmartCardNumber(firstName, lastName, dateOfIssue);
	}

	public SmartCardNumber getSmartCardNumber() {
		return smartCardNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public Date getDateOfIssue() {
		return dateOfIssue;
	}
}
