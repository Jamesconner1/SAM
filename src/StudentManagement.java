/**
 * Interface for managing students.
 */
public interface StudentManagement {
 
	// calling methods
	
	int numberOfStudents();
	
	void registerStudent(Student student);
	
	void amendStudent(StudentID studentID, Student student);
	
	void terminateStudent(StudentID studentID);
}
