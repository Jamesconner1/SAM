public class StudentID {
	private char letter;
	private int number;
	
	public StudentID(char letter, int number) {
		this.validateLetter(letter);
		this.validateNumber(number);
		this.letter = letter;
		this.number = number;
	}
	
	private void validateLetter(char letter) {
		if (letter < 'a' || letter > 'z') {
			throw new RuntimeException("Invalid letter: " + letter);
		}
	}

	private void validateNumber(int number) {
		if (number < 1000 || number > 9999) {
			throw new RuntimeException("Invalid number: " + number);
		}
	}

	public String toString() {
		return Character.toString(letter) + Integer.toString(number);
	}
}
