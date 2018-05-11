import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SmartCardNumberFactory {

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

	public SmartCardNumber createSmartCardNumber(String firstName, String lastName, Date dateOfIssue) {
		String initials = String.valueOf(firstName.charAt(0)) + String.valueOf(lastName.charAt(0));
		String yearOfIssue = (new SimpleDateFormat("yyyy")).format(dateOfIssue);
		int serialNumber = getNextSerialNumber(initials, yearOfIssue);
		return new SmartCardNumber(initials, Integer.parseInt(yearOfIssue), serialNumber);
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
