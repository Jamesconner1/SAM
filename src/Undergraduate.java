/**
 * Undergraduate students inherit module-based functionality from the AbstractStudent base class.
 */
public class Undergraduate extends AbstractStudent{

	private static final int MAX_CREDITS = 120;

	private static final int PASS_MARK = 40;

	public Undergraduate(StudentID studentID) {
		// calls parent constructor with max credits of 120 and a minimum module pass mark of 40
		super(studentID, MAX_CREDITS, PASS_MARK);
	}
}
