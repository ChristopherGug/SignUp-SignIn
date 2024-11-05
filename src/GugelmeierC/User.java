package GugelmeierC;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;
import java.sql.Connection;
import java.text.DateFormat;

/**
 * This is a base user class
 * @author Christopher Gugelmeier
 * @Since 1.0
 * @Version 1.0
 */
public class User implements CollegeInterface{

	/**
	 * The default user ID
	 */
	public static final long DEFAULT_ID = 100123456;
	/**
	 * The default user password
	 */
	public static final String DEFAULT_PASSWORD = "password";
	/**
	 * The minimum length of a password
	 */
	public static final byte MINIMUM_PASSWORD_LENGTH = 8;
	/**
	 * The maximum length of a password
	 */
	public static final byte MAXIMUM_PASSWORD_LENGTH = 40;
	/**
	 * The default user first name
	 */
	public static final String DEFAULT_FIRST_NAME = "John";
	/**
	 * The default user last name
	 */
	public static final String DEFAULT_LAST_NAME = "Doe";
	/**
	 * The default user email address
	 */
	public static final String DEFAULT_EMAIL_ADDRESS = "john.doe@dcmail.ca";
	/**
	 * The default 
	 */
	public static final boolean DEFAULT_ENABLED_STATUS = true;
	/**
	 * Set user type to User by default
	 */
	public static final char DEFAULT_TYPE = 's';
	/**
	 * The user id length must be 9
	 */
	public static final byte ID_NUMBER_LENGTH = 9;
	/**
	 * The format for the date
	 */
	public static final DateFormat DF = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.CANADA);

	/**
	 * The user's id
	 */
	private long id;
	/**
	 * The user's password
	 */
	private String password;
	/**
	 * The user's first name
	 */
	private String firstName;
	/**
	 * The user's last name
	 */
	private String lastName;
	/**
	 * The user's email address
	 */
	private String emailAddress;
	/**
	 * The last date the user's accesses their account
	 */
	private Date lastAccess;
	/**
	 * The date the user was enrolled into the college
	 */
	private Date enrolDate;
	/**
	 * Whether the user is enabled or not
	 */
	private boolean enabled;
	/**
	 * The type of user (User or faculty)
	 */
	private char type;

	public static void initialize(Connection aConnection) {
    	UserDA.initialize(aConnection);
    }
    public static User retrieve (long id) throws NotFoundException {
    	return UserDA.retrieve(id);
    }
    public static Vector<User> retrieveAll() {
    	return UserDA.retrieveAll();
    }
    public static void terminate() {
    	UserDA.terminate();
    }
    
    public static User authenticate(long id, String password) throws NotFoundException {
    	return UserDA.authenticate(id, password);
    }
    
    public boolean create() throws DuplicateException {
    	return UserDA.create(this);
    }
    
    public int delete() {
    	return UserDA.delete(this);
    }
    
    public int update() {
    	return UserDA.update(this);
    }
	
	/**
	 * Parameterized user constructor to create a unique user
	 * @param id The users id
	 * @param password The users password
	 * @param firstName The users first name
	 * @param lastName The users last name
	 * @param emailAddress The users email address
	 * @param lastAccess The last time the users account was accessed
	 * @param enrolDate The date the user was enrolled
	 * @param enabled The activity status of the user
	 * @param type The type of user (Student or faculty)
     * @throws InvalidIdException
     * @throws InvalidPasswordException
     * @throws InvalidNameException
     * @throws InvalidUserDataException
	 */
	public User(long id, String password, String firstName, String lastName, String emailAddress, Date lastAccess, Date enrolDate, boolean enabled, char type)
			throws InvalidUserDataException
	{
		try
		{
			setId(id);
			setPassword(password);
			setFirstName(firstName);
			setLastName(lastName);
			setEmailAddress(emailAddress);
			setLastAccess(lastAccess);
			setEnrolDate(enrolDate);
			setEnabled(enabled);
			setType(type);
		}
		catch (Exception e)
		{
			throw new InvalidUserDataException("User not created: " + e.getMessage());
		}
	}

	/**
	 * The default constructor for the user class
     * @throws InvalidIdException
     * @throws InvalidPasswordException
     * @throws InvalidNameException
     * @throws InvalidUserDataException
	 */
	public User() throws InvalidUserDataException
	{
		this(DEFAULT_ID, DEFAULT_PASSWORD, DEFAULT_FIRST_NAME, DEFAULT_LAST_NAME, DEFAULT_EMAIL_ADDRESS, new Date(), new Date(), DEFAULT_ENABLED_STATUS, DEFAULT_TYPE);
	}
	
	/**
	 * Returns the user's information in a formated string
	 */
	public String toString() 
	{
		return String.format("User Info for: %d\r\n"
				+ "\tName: %s %s (%s)\r\n"
				+ "\tCreated on: %s\r\n"
				+ "\tLast access: %s\r\n"
				+ "", getId(), getFirstName(), getLastName(), getEmailAddress(), DF.format(getEnrolDate()), DF.format(getLastAccess()));	
	}
	
	/**
	 * Prints out the user's formatted information
	 */
	public void dump()
	{
		System.out.println(toString());
	}
	
	/**
	 * Checks if the user's id is the required length
	 * @param id The user's id
	 * @return Whether the id is the required length or not
	 */
	public static boolean verifyId(long id)
	{
		if (Long.toString(id).length() == ID_NUMBER_LENGTH)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @return the lastAccess
	 */
	public Date getLastAccess() {
		return lastAccess;
	}
	/**
	 * @return the enrolDate
	 */
	public Date getEnrolDate() {
		return enrolDate;
	}
	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}
	/**
	 * @return the type
	 */
	public char getType() {
		return type;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) throws InvalidIdException{
		if (!verifyId(id))
		{
			throw new InvalidIdException("The Id must be 9 digits long");
		}
		else
		{
			this.id = id;
		}
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) throws InvalidPasswordException {
		if (password.length() > MAXIMUM_PASSWORD_LENGTH)
			throw new InvalidPasswordException("The password must be no more than " + MAXIMUM_PASSWORD_LENGTH + " characters long");
		else if (password.length() < MINIMUM_PASSWORD_LENGTH)
			throw new InvalidPasswordException("The password must be at least " + MINIMUM_PASSWORD_LENGTH + " characters long");
		else
			this.password = password;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) throws InvalidNameException{
		if (firstName.length() <= 0)
			throw new InvalidNameException("Please enter the user's first name");
		else if (MyUtil.containsNumber(firstName))
			throw new InvalidNameException("First name cannot contain any numbers");
		else
			this.firstName = firstName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) throws InvalidNameException {
		if (lastName.length() <= 0)
			throw new InvalidNameException("Please enter the user's last name");
		else if (MyUtil.containsNumber(lastName))
			throw new InvalidNameException("Last name cannot contain any numbers");
		else
			this.lastName = lastName;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * @param lastAccess the lastAccess to set
	 */
	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}
	/**
	 * @param enrolDate the enrolDate to set
	 */
	public void setEnrolDate(Date enrolDate) {
		this.enrolDate = enrolDate;
	}
	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(char type) {
		this.type = type;
	}

	@Override
	public String getTypeForDisplay() {
		return "User";
	}
}
