public interface StudentManagement {
 
	// calling methods
	
	public int numberOfStudents();
	
	public void registerStudent(Student student);
	
	public void amendStudent(StudentID studentID, Student student);
	
	public void terminateStudent(StudentID studentID);
}
