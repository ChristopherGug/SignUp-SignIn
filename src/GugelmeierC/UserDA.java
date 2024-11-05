package GugelmeierC;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class UserDA {
	static Connection connection;
	static Statement stmt;
	static User User;
	static long id;
	static String password;
	static String firstName;
	static String lastName;
	static String emailAddress;
	static Date lastAccess;
	static Date enrolDate;
	static boolean enabled;
	static char type;
	static String programCode;
	static String programDescription;
	static int year;
	static Vector<Mark> marks;
	
	private static final SimpleDateFormat SQL_DF = new SimpleDateFormat("yyy-MM-dd");
	
	/**
	 * Initialize the connection to the database
	 * @param aConnection The connection object to the database
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
	 * Terminate the connection to the database
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
	 * Create a User in the database given certain information
	 * @param User The Users information meant to be added into the database
	 * @return Whether the User was created or not
	 * @throws DuplicateException
	 */
	public static boolean create(User user) throws DuplicateException
	{
		boolean inserted = false;
		try {
			if (retrieve(user.getId()) == null) {
				throw new DuplicateException("The id: " + user.getId()+ " is already in the database!");
			}
		}
		catch (NotFoundException e) {
			try {
				PreparedStatement ps = connection.prepareStatement("INSERT INTO users(id, password, firstName, lastName, emailAddress, lastAccess, enrolDate, enabled, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
				ps.setLong(1, user.getId());
				ps.setString(2, Hasher.HashPassword(user.getPassword()));
				ps.setString(3, user.getFirstName());
				ps.setString(4, user.getLastName());
				ps.setString(5, user.getEmailAddress());
				ps.setDate(6, new Date(user.getLastAccess().getTime()));
				ps.setDate(7, new Date(user.getEnrolDate().getTime()));
				ps.setBoolean(8, user.isEnabled());
				ps.setString(9, String.valueOf(user.getType()));
				inserted = (ps.executeUpdate() == 1);
			}
			catch (SQLException ex) {
				System.out.println(ex);
			}
		}
		return inserted;
	}
	
	/**
	 * Retrieve a User from the database from the id
	 * @param id The Users id to look for
	 * @return The User object if found
	 * @throws NotFoundException
	 */
	public static User retrieve(long id) throws NotFoundException{
		User user = null;
		try {
			ResultSet rs = stmt.executeQuery("SELECT * FROM Users WHERE id = " + id + "");
			if (rs.next())
			{
				try {
					user = new User(rs.getLong("id"), rs.getString("password"), rs.getString("firstName"), rs.getString("lastName"),
										rs.getString("emailAddress"), rs.getDate("lastAccess"), rs.getDate("enrolDate"), rs.getBoolean("enabled"),
										rs.getString("type").charAt(0));
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
		return user;
	}
	
	public static Vector<User> retrieveAll(){
		Vector<User> users = new Vector<User>();
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT * FROM Users ORDER BY id");
			ResultSet rs = ps.executeQuery();
			boolean isMore = rs.next();
			while (isMore)
			{
				try {
					User user = new User(rs.getLong("id"), rs.getString("password"), rs.getString("firstName"), rs.getString("lastName"),
										rs.getString("emailAddress"), rs.getDate("lastAccess"), rs.getDate("enrolDate"), rs.getBoolean("enabled"),
										rs.getString("type").charAt(0));
					users.addElement(user);
				}
				catch (Exception e) {
					System.out.println(e);
				}
				isMore = rs.next();
			}
		}
		catch (SQLException e) {
			System.out.println(e);
		}
		return users;
	}
	
	/**
	 * Update a Users information within the database
	 * @param User The Users new information to update
	 * @return The records updated
	 */
	public static int update(User user) {
		int records = 0;
		try {
			User tempUser = User.retrieve(user.getId());
			PreparedStatement ps;
			if (user.getPassword() == Hasher.HashPassword(user.getPassword())) {
				
				ps = connection.prepareStatement("UPDATE Users SET firstName = ?, lastName = ?, emailAddress = ?, lastAccess = ?,"
						+ "enrolDate = ?, type = ?, enabled = ? WHERE id = ?");
				ps.setString(1, user.getFirstName());
				ps.setString(2, user.getLastName());
				ps.setString(3, user.getEmailAddress());
				ps.setDate(4, new Date(user.getLastAccess().getTime()));
				ps.setDate(5, new Date(user.getEnrolDate().getTime()));
				ps.setString(6, String.valueOf(user.getType()));
				ps.setBoolean(7, user.isEnabled());
				ps.setLong(8, user.getId());
				records = ps.executeUpdate();
			}
			else {
				ps = connection.prepareStatement("UPDATE Users SET password = ?, firstName = ?, lastName = ?, emailAddress = ?, lastAccess = ?,"
						+ "enrolDate = ?, type = ?, enabled = ? WHERE id = ?");
				ps.setString(1, Hasher.HashPassword(user.getPassword()));
				ps.setString(2, user.getFirstName());
				ps.setString(3, user.getLastName());
				ps.setString(4, user.getEmailAddress());
				ps.setDate(5, new Date(user.getLastAccess().getTime()));
				ps.setDate(6, new Date(user.getEnrolDate().getTime()));
				ps.setString(7, String.valueOf(user.getType()));
				ps.setBoolean(8, user.isEnabled());
				ps.setLong(9, user.getId());
				records = ps.executeUpdate();
			}
		}
		catch (NotFoundException e) {
			System.out.println(e);
		}
		catch (SQLException e) {
			System.out.println(e);
		}
		return records;
	}
	
	/**
	 * Delete a User from the database
	 * @param User The User that is meant to be deleted
	 * @return How many records were found
	 */
	public static int delete(User user) {
		int records = 0;
		String sqlUserDelete = "DELETE FROM Users WHERE id = " + user.getId() + "";
		
		try {
			User.retrieve(user.getId());
			records = stmt.executeUpdate(sqlUserDelete);
		}
		catch (NotFoundException e)
		{
			System.out.println("A User with the id " + user.getId() + " cannot be deleted as they do not exist in the database.");
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		return records;
	}
	
	/**
	 * Check the database to match the given id and password
	 * @param id The id to look for
	 * @param password The password to authenticate
	 * @return The User object if found
	 * @throws NotFoundException
	 */
	public static User authenticate(long id, String password) throws NotFoundException {
		User user = null;
		user = User.retrieve(id);
		if (user == null || !user.getPassword().equals(Hasher.HashPassword(password))) {
			throw new NotFoundException("The user was not found");
		}
		return User;
	}
	
}
