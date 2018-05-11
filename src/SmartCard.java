import java.util.Date;

/**
 * SmartCard class that will generate a SmartCardNumber for itself on construction.
 */
public class SmartCard {

	private SmartCardNumber smartCardNumber;
	
	private String firstName;
	
	private String lastName;
	
	private Date dateOfBirth;
	
	private Date dateOfIssue;

	/*
	 * Constructs a new SmartCard from the given arguments, where dateOfIssue is the current date/time.
	 */
	public SmartCard(String firstName, String lastName, Date dateOfBirth) {
		// invoke the other constructor, providing the current date/time of as the dateOfIssue
		this(firstName, lastName, dateOfBirth, new Date());
	}

	/*
	 * Overloaded constructor that constructs a new SmartCard from the given arguments, allowing dateOfIssue to be provided.
	 */
	public SmartCard(String firstName, String lastName, Date dateOfBirth, Date dateOfIssue) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.dateOfIssue = dateOfIssue;
		// use SmartCardNumberFactory to create smart card number
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
