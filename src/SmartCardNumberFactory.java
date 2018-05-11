import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * SmartCardNumberFactory is implemented as a singleton to ensure there's only one instance so that any SmartCardNumber
 * instances it creates are unique across the application.
 *
 * It maintains a map of current serial numbers, keyed on student initials so students with the same initials are given
 * distinct serial numbers.
 */
public class SmartCardNumberFactory {

    // map of current serial numbers, keyed on student initials
	private Map<String, Integer> currentSerialNumbers = new HashMap<>();
	
	/* START OF SINGLETON PATTERN */
	private static SmartCardNumberFactory instance;
	
	private SmartCardNumberFactory() {
		// private!
	}
	
	public static SmartCardNumberFactory getInstance() {
		if (instance == null) {
			instance = new SmartCardNumberFactory();
		}
		return instance;
	}
	/* END OF SINGLETON PATTERN */

	// reset method to make unit testing easier
	public void reset() {
		instance = new SmartCardNumberFactory();
	}

    /**
     * Creates a new SmartCardNumber based on the given arguments.
     */
	public SmartCardNumber createSmartCardNumber(String firstName, String lastName, Date dateOfIssue) {
		String initials = getInitials(firstName, lastName);
		String yearOfIssue = (new SimpleDateFormat("yyyy")).format(dateOfIssue);
		int serialNumber = getNextSerialNumber(initials, yearOfIssue);
		return new SmartCardNumber(initials, Integer.parseInt(yearOfIssue), serialNumber);
	}

	private String getInitials(String firstName, String lastName) {
	    return String.valueOf(firstName.toUpperCase().charAt(0)) + String.valueOf(lastName.toUpperCase().charAt(0));
    }

	private int getNextSerialNumber(String initials, String yearOfIssue) {
		String serialKey = initials + yearOfIssue;
		if (currentSerialNumbers.containsKey(serialKey)) {
			int currentSerialNumber = currentSerialNumbers.get(serialKey); // auto-unbox
			int newSerialNumber = currentSerialNumber + 1;
			currentSerialNumbers.put(serialKey, newSerialNumber); // auto-box
			return newSerialNumber;
		}
		int newSerialNumber = 1;
		currentSerialNumbers.put(serialKey, newSerialNumber); // auto-box
		return newSerialNumber;		
	}
}
