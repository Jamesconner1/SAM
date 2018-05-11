/**
 * StudentIDFactory is implemented as a singleton to ensure there's only one instance so that any StudentID instances it
 * creates are unique across the application.
 *
 * It maintains the current letter and number of the student ID, incrementing each as required.
 */
public class StudentIDFactory {

	private static final char START_LETTER = 'a'; // 

	private static final char START_NUMBER = 1000;

	private char currentLetter = START_LETTER;
	
	private int currentNumber = START_NUMBER;
	
	/* START OF SINGLETON PATTERN */
	private static StudentIDFactory instance;
	
	// the only class that can construct this class is itself
	private StudentIDFactory() {
		// private!
	}
	
	public static StudentIDFactory getInstance() {
		// only construct a new instance if we don't have one already
		if (instance == null) {
			instance = new StudentIDFactory();
		}
		return instance;
	}
	/* END OF SINGLETON PATTERN */

	// reset method to make unit testing easier
	public void reset() {
		instance = new StudentIDFactory();
	}

    /**
     * Creates a new StudentID based on the given arguments.
     */
	public StudentID createStudentID() {
		StudentID studentID = new StudentID(currentLetter, currentNumber);
		allocateNewNumber();
		return studentID;
	}

	private void allocateNewNumber() {
		int newNumber = currentNumber + 1; // adding one to the current number ensures uniqueness
		if (newNumber > 9999) { // if number is greater that 9999 than the letter goes up by 1
			currentLetter++;
			currentNumber = 1000; // and number reset to 1000
			return;
		}
		currentNumber = newNumber;
	}
}
