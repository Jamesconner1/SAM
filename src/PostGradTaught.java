public class PostGradTaught extends AbstractStudent{

	public PostGradTaught(StudentID studentID) {
		// calls parent constructor studentID with max credits of 180 and a minimum module pass mark of 50
		super(studentID, 180, 50);
	}
}
