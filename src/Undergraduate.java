/**
 * Undergraduate students inherit module-based functionality from the AbstractStudent base class.
 */
public class Undergraduate extends AbstractStudent{

	public Undergraduate(StudentID studentID) {
		// calls parent constructor with max credits of 120 and a minimum module pass mark of 40
		super(studentID, 120, 40); 
	}
}
