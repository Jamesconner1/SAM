/**
 * Immutable data class for smart card numbers, ie. does not provide any setters to mutate the internal state.
 */
public class SmartCardNumber {

	private String initials;
	private int yearOfIssue;
	private int serialNumber;

	/**
	 * Constructs a new SmartCardNumber from the given arguments, ensuring that the given initials are valid.
	 */
	public SmartCardNumber(String initials, int yearOfIssue, int serialNumber) {
		validateInitials(initials);
		this.initials = initials;
		this.yearOfIssue = yearOfIssue;
		this.serialNumber = serialNumber;
	}

	private void validateInitials(String initials) {
		if (initials.length() != 2) {
			throw new RuntimeException("Initials should have 2 characters: " + initials);
		}
		validateLetter(initials.charAt(0));
		validateLetter(initials.charAt(1));
	}

	private void validateLetter(char letter) {
		if (letter < 'A' || letter > 'Z') {
			throw new RuntimeException("Invalid letter: " + letter);
		}
	}

	public String toString() {
		return initials + "-" + String.valueOf(yearOfIssue) + "-" + String.valueOf(serialNumber);
	}
}
