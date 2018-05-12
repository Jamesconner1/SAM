/**
 * PostGradTaught students inherit module-based functionality from AbstractStudent, but override validateAge to ensure
 * all post grad taught students are at least 20yo.
 */
public class PostGradTaught extends AbstractStudent {

	private static final int MIN_AGE = 20;

	private static final int MAX_CREDITS = 180;

	private static final int PASS_MARK = 50;

	public PostGradTaught(StudentID studentID) {
		// calls parent constructor studentID with max credits of 180 and a minimum module pass mark of 50
		super(studentID, MAX_CREDITS, PASS_MARK);
	}

	@Override
	public void validateAge() {
		int age = calculateAge();
		if (age < MIN_AGE) {
			throw new RuntimeException("Student not old enough to be post grad: age=" + age);
		}
	}
}
