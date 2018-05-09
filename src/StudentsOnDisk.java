import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
		int age = calculateAge(student.getDateOfBirth());
		if (age < 17) {
			throw new RuntimeException("Student not old enough: age=" + age);
		}
		if ((student instanceof PostGradTaught || student instanceof PostGradResearch) && age < 20) {
			throw new RuntimeException("Student not old enough to be post grad: age=" + age);			
		}
		SmartCard smartCard = new SmartCard(student.getFirstName(), student.getLastName(), student.getDateOfBirth());
		student.issueSmartCard(smartCard);
		writeToDisk(student);
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
			// TODO: write anything else that might be useful
			// write student's modules to file
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

	@Override
	public void amendStudent(StudentID studentID, Student student) {
		File studentFile = new File(rootFolder, studentID.toString());
		studentFile.delete();
		writeToDisk(student);
	}

	@Override
	public void terminateStudent(StudentID studentID) {
		students.remove(studentID.toString());
		File studentFile = new File(rootFolder, studentID.toString());
		studentFile.delete();
	}
	
	
    private int calculateAge(Date dateOfBirth) {
    		LocalDate birthDate = toLocalDate(dateOfBirth);
    		LocalDate currentDate = toLocalDate(new Date());
        return Period.between(birthDate, currentDate).getYears();
    }
    
    private LocalDate toLocalDate(Date date) {
    		Calendar cal = Calendar.getInstance();
    		cal.setTime(date);
    		return LocalDate.of(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));    
    	}
}
