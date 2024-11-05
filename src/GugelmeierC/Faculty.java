package GugelmeierC;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.Date;

/**
 * This is a faculty user class
 * @author Christopher Gugelmeier
 * @Since 1.0
 * @Version 1.0
 */
public class Faculty extends User {
	/**
	 * The default school code for a faculty member
	 */
	public static final String DEFAULT_SCHOOL_CODE = "SET";
	/**
	 * The default school description for a faculty member
	 */
	public static final String DEFAULT_SCHOOL_DESCRIPTION = "School of Engineering & Technology";
	/**
	 * The default office for a faculty member
	 */
	public static final String DEFAULT_OFFICE = "H-140";
	/**
	 * The default phone extension for a faculty member
	 */
	public static final int DEFAULT_PHONE_EXTENSION = 1234;
	
	/**
	 * The faculty member's school code
	 */
	private String schoolCode;
	/**
	 * The faculty member's school description
	 */
	private String schoolDescription;
	/**
	 * The faculty member's office
	 */
	private String office;
	/**
	 * The faculty member's extension
	 */
	private int extension;

	public static void initialize(Connection aConnection) {
    	FacultyDA.initialize(aConnection);
    }
    public static Faculty retrieve (long id) throws NotFoundException {
    	return FacultyDA.retrieve(id);
    }
    public static void terminate() {
    	FacultyDA.terminate();
    }
    
    public boolean create() throws DuplicateException {
    	return FacultyDA.create(this);
    }
    
    public int delete() {
    	return FacultyDA.delete(this);
    }
    
    public int update() {
    	return FacultyDA.update(this);
    }
	
	/**
	 * This is the parameterized constructor for the Faculty class to create unique faculty members
	 * @param id The faculty member's id
	 * @param password The faculty member's password
	 * @param firstName The faculty member's first name
	 * @param lastName The faculty member's last name
	 * @param emailAddress The faculty member's email address
	 * @param lastAccess The faculty member's last access date
	 * @param enrolDate The faculty member's enrol date
	 * @param enabled The faculty member's activity status
	 * @param type The faculty member's account type
	 * @param schoolCode The faculty member's school code
	 * @param schoolDescription The faculty member's school description
	 * @param office The faculty member's office
	 * @param extension The faculty member's extension
     * @throws InvalidIdException
     * @throws InvalidPasswordException
     * @throws InvalidNameException
     * @throws InvalidUserDataException
	 */
	public Faculty(long id,
				   String password,
				   String firstName,
				   String lastName,
				   String emailAddress,
				   Date lastAccess,
				   Date enrolDate,
				   boolean enabled,
				   char type,
				   String schoolCode,
				   String schoolDescription,
				   String office,
				   int extension)
	throws InvalidUserDataException
	{
		super(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type);
		setSchoolCode(schoolCode);
		setSchoolDescription(schoolDescription);
		setOffice(office);
		setExtension(extension);
	}

	/**
	 * This is the default constructor where a faculty member is created with all default values
     * @throws InvalidIdException
     * @throws InvalidPasswordException
     * @throws InvalidNameException
     * @throws InvalidUserDataException
	 */
	public Faculty() throws InvalidUserDataException
	{
		super();
		setSchoolCode(DEFAULT_SCHOOL_CODE);
		setSchoolDescription(DEFAULT_SCHOOL_DESCRIPTION);
		setOffice(DEFAULT_OFFICE);
		setExtension(DEFAULT_PHONE_EXTENSION);
	}

	/**
	 * Simply returns the type of user
	 * @return a string that says "Faculty"
	 */
	public String getTypeForDisplay()
	{
		return "Faculty";
	}

	/**
	 * Returns a string with the faculty members information
	 */
	public String toString()
	{
		String string = super.toString();
		string = string.replaceFirst("User", getTypeForDisplay());
		string += String.format("\t%s (%s)\n" +
				  "\tOffice: %s\n" +
				  "\t%s x%d\n", getSchoolDescription(), getSchoolCode(), getOffice(), CollegeInterface.PHONE_NUMBER, getExtension());
		return string;
	}

	/**
	 * @return the schoolCode
	 */
	public String getSchoolCode() {
		return schoolCode;
	}
	/**
	 * @return the schoolDescription
	 */
	public String getSchoolDescription() {
		return schoolDescription;
	}
	/**
	 * @return the office
	 */
	public String getOffice() {
		return office;
	}
	/**
	 * @return the extension
	 */
	public int getExtension() {
		return extension;
	}
	/**
	 * @param schoolCode the schoolCode to set
	 */
	public void setSchoolCode(String schoolCode) {
		this.schoolCode = schoolCode;
	}
	/**
	 * @param schoolDescription the schoolDescription to set
	 */
	public void setSchoolDescription(String schoolDescription) {
		this.schoolDescription = schoolDescription;
	}
	/**
	 * @param office the office to set
	 */
	public void setOffice(String office) {
		this.office = office;
	}
	/**
	 * @param extension the extension to set
	 */
	public void setExtension(int extension) {
		this.extension = extension;
	}
	
}
