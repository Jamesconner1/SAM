import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of StudentManagement that stores students to disk + maintains a local map.
 *
 * The registerStudent method is responsible for validating the age of the students + issuing smart cards before storing them to disk.
 */
public class StudentsOnDisk implements StudentManagement {
	
	private Map<String, Student> students = new HashMap<>();

	private File rootFolder;
	
	public StudentsOnDisk(File folder) {
		if (!folder.exists()) {
			folder.mkdir();
		};
		assert folder.isDirectory();
		rootFolder = folder;
		// load students from disk and put them in the map?
	}
	
	public int numberOfStudents() {
		File[] studentFiles = rootFolder.listFiles();
		return studentFiles.length;
	}

	@Override
	public void registerStudent(Student student) {
	    student.validateAge();
		SmartCard smartCard = new SmartCard(student.getFirstName(), student.getLastName(), student.getDateOfBirth());
		student.issueSmartCard(smartCard);
		writeToDisk(student);
	}

	@Override
	public void amendStudent(StudentID studentID, Student student) {
		File studentFile = new File(rootFolder, studentID.toString());
		if (!studentFile.exists()) {
			throw new RuntimeException("Student not found on disk: " + studentID);
		}
		studentFile.delete();
		writeToDisk(student);
	}

	@Override
	public void terminateStudent(StudentID studentID) {
		students.remove(studentID.toString());
		File studentFile = new File(rootFolder, studentID.toString());
		if (!studentFile.exists()) {
			throw new RuntimeException("Student not found on disk: " + studentID);
		}
		studentFile.delete();
	}

    private void writeToDisk(Student student) {
        // store in map
        students.put(student.getStudentId().toString(), student);

        // write to disk
        File studentFile = new File(rootFolder, student.getStudentId().toString());
        BufferedWriter buffer = null;
        try {
            buffer = new BufferedWriter(new FileWriter(studentFile));
            // write student type to file
            buffer.write(student.getClass().getSimpleName());
            buffer.newLine();
            // write student's modules to file
            // TODO: + anything else that might be useful
            for (Module module: student.getModules()) {
                buffer.write(String.format("%s,%s,%s", module.getModuleCode(), module.getName(), module.getCredits()));
                buffer.newLine();
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (buffer != null) {
                try {
                    buffer.close();
                }
                catch (IOException e) {
                    // give up
                }
            }
        }
    }
}
