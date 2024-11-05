package GugelmeierC;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.Date;
import java.util.Vector;

/**
 * This is a class for a student user
 * @author Christopher Gugelmeier
 * @Since 1.0
 * @Version 1.0
 */
public class Student extends User {
    /**
     * The default program code for a student
     */
    public static final String DEFAULT_PROGRAM_CODE = "UNDC";
    /**
     * The default program description for a student
     */
    public static final String DEFAULT_PROGRAM_DESCRIPTION = "Undeclared";
    /**
     * The deault starting year for a student
     */
    public static final int DEFAULT_YEAR = 1;

    /**
     * The program code for the student
     */
    private String programCode;
    /**
     * The program description for the student
     */
    private String programDescription;
    /**
     * The current year for the student
     */
    private int year;
    /**
     * A vector containing the students marks
     */
    private Vector<Mark> marks;

    public static void initialize(Connection aConnection) {
    	StudentDA.initialize(aConnection);
    }
    public static Student retrieve (long id) throws NotFoundException {
    	return StudentDA.retrieve(id);
    }
    public static void terminate() {
    	StudentDA.terminate();
    }
    
    public static Student authenticate(long id, String password) throws NotFoundException {
    	return StudentDA.authenticate(id, password);
    }
    
    public boolean create() throws DuplicateException {    		
    	return StudentDA.create(this);
    }
    
    public int delete() {
    	return StudentDA.delete(this);
    }
    
    public int update() {
    	return StudentDA.update(this);
    }
    
    /**
     * This is the parameterized constructor for the Student class to create unique students with a list of marks
     * @param id The student's id
     * @param password The student's password
     * @param firstName The student's first name
     * @param lastName The student's last name
     * @param emailAddress The student's email address
     * @param lastAccess The student's last access date
     * @param enrolDate The student's enrol date
     * @param enabled The student's activity status
     * @param type The student's account type
     * @param programCode The student's program code
     * @param programDescription The student's program description
     * @param year The student's current year
     * @param marks The student's current marks
     * @throws InvalidIdException
     * @throws InvalidPasswordException
     * @throws InvalidNameException
     * @throws InvalidUserDataException
     * 
     */
    public Student(long id,
                   String password,
                   String firstName,
                   String lastName,
                   String emailAddress,
                   Date lastAccess,
                   Date enrolDate,
                   boolean enabled,
                   char type,
                   String programCode,
                   String programDescription,
                   int year,
                   Vector<Mark> marks)
    throws InvalidUserDataException
    {
        super(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type);
        setProgramCode(programCode);
        setProgramDescription(programDescription);
        setYear(year);
        setMarks(marks);
    }

    /**
     * This is the parameterized constructor for the Student class to create unique students with no marks
     * @param id The student's id
     * @param password The student's password
     * @param firstName The student's first name
     * @param lastName The student's last name
     * @param emailAddress The student's email address
     * @param lastAccess The student's last access date
     * @param enrolDate The student's enrol date
     * @param enabled The student's activity status
     * @param type The student's account type
     * @param programCode The student's program code
     * @param programDescription The student's program description
     * @param year The student's current year
     * @throws InvalidIdException
     * @throws InvalidPasswordException
     * @throws InvalidNameException
     * @throws InvalidUserDataException
     */
    public Student(long id,
                   String password,
                   String firstName,
                   String lastName,
                   String emailAddress,
                   Date lastAccess,
                   Date enrolDate,
                   boolean enabled,
                   char type,
                   String programCode,
                   String programDescription,
                   int year)
            throws InvalidUserDataException
    {
        this(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type, programCode, programDescription, year, new Vector<Mark>(0));
    }

    /**
     * Default Constructor for the student class
     * @throws InvalidIdException
     * @throws InvalidPasswordException
     * @throws InvalidNameException
     * @throws InvalidUserDataException
     */
    public Student() throws InvalidUserDataException
    {
        this(DEFAULT_ID, DEFAULT_PASSWORD, DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME,
                DEFAULT_EMAIL_ADDRESS, new Date(), new  Date(), DEFAULT_ENABLED_STATUS,
                DEFAULT_TYPE, DEFAULT_PROGRAM_CODE, DEFAULT_PROGRAM_DESCRIPTION, DEFAULT_YEAR);
    }

    /**
     * Returns a string with the students information
     */
    public String toString()
    {
        return String.format("Student Info for:\n" +
                "\t%s %s (%d)\n" +
                "\tCurrently in %d%s year of \"%s\" (%s)\n" +
                "\tEnrolled: %s\n", getFirstName(), getLastName(), getId(), getYear(),
                                    (getYear() == 1) ? "st" : getYear() == 2 ? "nd" : getYear() == 3 ? "rd" : "th",
                                    getProgramDescription(), getProgramCode(), DF.format(getEnrolDate()));
    }

    /**
     * @return The program code
     */
    public String getProgramCode() {
        return programCode;
    }

    /**
     * @param programCode The program code to set
     */
    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    /**
     * @return The program description
     */
    public String getProgramDescription() {
        return programDescription;
    }

    /**
     * @param programDescription The program description to set
     */
    public void setProgramDescription(String programDescription) {
        this.programDescription = programDescription;
    }

    /**
     * @return The year the student is in
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year The year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return The vector of marks
     */
    public Vector<Mark> getMarks() {
        return marks;
    }

    /**
     * @param marks The marks to set
     */
    public void setMarks(Vector<Mark> marks) {
        this.marks = marks;
    }
    
    @Override
	public String getTypeForDisplay() {
		return "Student";
	}
}
