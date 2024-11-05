package GugelmeierC;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

public class FacultyDA {

	/**
	 * The connection to the database
	 */
	static Connection connection;
	/**
	 * The statemembt object
	 */
	static Statement stmt;
	/**
	 * A Faculty object to be manipulated
	 */
	static Faculty faculty;
	/**
	 * The faculty id
	 */
	static long id;
	/**
	 * The faculty password
	 */
	static String password;
	/**
	 * The faculty's first name
	 */
	static String firstName;
	/**
	 * The faculty's last name
	 */
	static String lastName;
	/**
	 * The faculty's email address
	 */
	static String emailAddress;
	/**
	 * The faculty's last access date
	 */
	static Date lastAccess;
	/**
	 * The faculty's enrol date
	 */
	static Date enrolDate;
	/**
	 * Whether the faculty member is enabled or not
	 */
	static boolean enabled;
	/**
	 * The user type (f)
	 */
	static char type;
	/**
	 * The faculty's school code
	 */
	static String schoolCode;
	/**
	 * The faculty' school description
	 */
	static String schoolDescription;
	/**
	 * The faculty's office
	 */
	static String office;
	/**
	 * The faculty's extension
	 */
	static int extension;
	
	/**
	 * Simple date formatter for the date objects
	 */
	private static final SimpleDateFormat SQL_DF = new SimpleDateFormat("yyy-MM-dd");
	
	/**
	 * This method initializes the connection to the database for the FacultyDA
	 * @param aConnection connection to the database
	 */
	public static void initialize(Connection aConnection) {
		try {
			connection = aConnection;
			stmt = connection.createStatement();
		}
		catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	/**
	 * This method terminates the connection to the database
	 */
	public static void terminate() {
		try {
			stmt.close();
		}
		catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	/**
	 * This method creates a Faculty member in the database by checking if the id already exists and if not then adding the user
	 * @param faculty The faculty member to be added/created into the database
	 * @return return if the creation was successful or not
	 * @throws DuplicateException
	 * @throws InvalidIdException
	 * @throws InvalidPasswordException
	 * @throws InvalidNameException
	 * @throws InvalidUserDataException
	 * @throws NoSuchAlgorithmException
	 * @throws NotFoundException
	 */
	public static boolean create(Faculty faculty) throws DuplicateException
	{
		boolean inserted = false;
		try {
			if (retrieve(faculty.getId()) == null) {
				throw new DuplicateException("The id: " + faculty.getId()+ " is already in the database!");
			}
		}
		catch (NotFoundException e) {
			try {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO users(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type) VALUES (?, ENCODE(DIGEST(?, 'sha1'), 'hex'), ?, ?, ?, ?, ?, ?, ?);");
				ps.setLong(1, faculty.getId());
				ps.setString(2, faculty.getPassword());
				ps.setString(3, faculty.getFirstName());
				ps.setString(4, faculty.getLastName());
				ps.setString(5, faculty.getEmailAddress());
				ps.setDate(6, new Date(faculty.getLastAccess().getTime()));
				ps.setDate(7, new Date(faculty.getEnrolDate().getTime()));
				ps.setBoolean(8, faculty.isEnabled());
				ps.setString(9, String.valueOf(faculty.getType()));
				ps.executeUpdate();
				ps = connection.prepareStatement("INSERT INTO Faculty (id, schoolCode, schoolDescription, office, extension) VALUES (?, ?, ?, ?, ?);");
				ps.setLong(1, faculty.getId());
				ps.setString(2, faculty.getSchoolCode());
				ps.setString(3, faculty.getSchoolDescription());
				ps.setString(4, faculty.getOffice());
				ps.setString(5, String.valueOf(faculty.getExtension()));
				ps.executeUpdate();
				inserted = true;
			}
			catch (SQLException ex) {
				System.out.println(ex);
			}
		}
		return inserted;
	}
	
	/**
	 * This method checks the database for a user with the given id and returns all the data if found
	 * @param id The id of the desired faculty member to be retrieved
	 * @return The Faculty member that was retrieved
	 * @throws InvalidIdException
	 * @throws InvalidPasswordException
	 * @throws InvalidNameException
	 * @throws InvalidUserDataException
	 * @throws NotFoundException
	 */
	public static Faculty retrieve(long id) throws NotFoundException{
		Faculty faculty = null;
		try {
			ResultSet rs = stmt.executeQuery("SELECT Users.id, password, firstName, lastName, emailAddress, lastAccess, "
					+ "enrolDate, enabled, type, schoolCode, schoolDescription, office, extension "
					+ "FROM Users, Faculty WHERE Users.id = " + id + "");
			if (rs.next())
			{
				try {
					faculty = new Faculty(rs.getLong("id"), rs.getString("password"), rs.getString("firstName"), rs.getString("lastName"),
										rs.getString("emailAddress"), rs.getDate("lastAccess"), rs.getDate("enrolDate"), rs.getBoolean("enabled"),
										rs.getString("type").charAt(0), rs.getString("schoolCode"), rs.getString("schoolDescription"),
										rs.getString("office"), Integer.valueOf(rs.getString("extension")));
				}
				catch (Exception e) {
					System.out.println(e);
				}
			}
			else {
				throw new NotFoundException("A user with that ID was not found in the database");
			}
		}
		catch (SQLException e) {
			System.out.println(e);
		}
		return faculty;
	}
	
	/**
	 * This method updates the desired faculty member within the database
	 * @param faculty The new information to be updated
	 * @return the number of records updated
	 * @throws NotFoundException
	 * @throws InvalidUserDataException
	 * @throws InvalidIdException
	 * @throws InvalidPasswordException
	 * @throws InvalidNameException
	 */
	public static int update(Faculty faculty) {
		int records = 0;
		try {
			Faculty.retrieve(faculty.getId());
			PreparedStatement ps = connection.prepareStatement("UPDATE Users SET firstName = ?, lastName = ?, emailAddress = ?, lastAccess = ?,"
																+ "enrolDate = ?, type = ?, enabled = ? WHERE id = ?");
			ps.setString(1, faculty.getFirstName());
			ps.setString(2, faculty.getLastName());
			ps.setString(3, faculty.getEmailAddress());
			ps.setDate(4, new Date(faculty.getLastAccess().getTime()));
			ps.setDate(5, new Date(faculty.getEnrolDate().getTime()));
			ps.setString(6, String.valueOf(faculty.getType()));
			ps.setBoolean(7, faculty.isEnabled());
			ps.setLong(8, faculty.getId());
			records = ps.executeUpdate();
			ps = connection.prepareStatement("UPDATE Faculty SET schoolCode = ?, schoolDescription = ?, office = ?, extension = ? WHERE id = ?");
			ps.setString(1, faculty.getSchoolCode());
			ps.setString(2, faculty.getSchoolDescription());
			ps.setString(3, faculty.getOffice());
			ps.setString(4, String.valueOf(faculty.getExtension()));
			ps.setLong(5, faculty.getId());
			records += ps.executeUpdate();
		}
		catch (NotFoundException nfe) {
			System.out.println(nfe);
		}
		catch (SQLException e) {
			System.out.println(e);
		}
		return records;
	}
	
	/**
	 * THis method deletes the desired faculty member within the database
	 * @param faculty The faculty member to be deleted from the database
	 * @return the number of records updated
	 * @throws NotFoundException
	 * @throws InvalidUserDataException
	 * @throws InvalidIdException
	 * @throws InvalidPasswordException
	 * @throws InvalidNameException
	 */
	public static int delete(Faculty faculty) {
		int records = 0;
		
		try {
			Faculty.retrieve(faculty.getId());
			PreparedStatement ps = connection.prepareStatement("DELETE FROM Faculty WHERE id = ?");
			ps.setLong(1, faculty.getId());
			records = ps.executeUpdate();
			ps = connection.prepareStatement("DELETE FROM Users WHERE id = ?");
			ps.setLong(1, faculty.getId());
			records += ps.executeUpdate();
		}
		catch (NotFoundException nfe) {
			System.out.println(nfe);
		}
		catch (SQLException e) {
			System.out.println(e);
		}
		return records;
	}
	
}
