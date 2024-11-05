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

public class StudentDA {
	static Connection connection;
	static Statement stmt;
	static Student student;
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
	 * Create a student in the database given certain information
	 * @param student The students information meant to be added into the database
	 * @return Whether the student was created or not
	 * @throws DuplicateException
	 */
	public static boolean create(Student student) throws DuplicateException
	{
		boolean inserted = false;
		try {
			if (retrieve(student.getId()) == null) {
				throw new DuplicateException("The id: " + student.getId()+ " is already in the database!");
			}
		}
		catch (NotFoundException e) {
			try {
				connection.setAutoCommit(false);
				if (UserDA.create(student)) {					
					PreparedStatement ps = connection.prepareStatement("INSERT INTO Students(id, programCode, programDescription, year) VALUES (?, ?, ?, ?);");
					ps.setLong(1, student.getId());
					ps.setString(2, student.getProgramCode());
					ps.setString(3, student.getProgramDescription());
					ps.setString(4, String.valueOf(student.getYear()));
					if (ps.executeUpdate() == 1) {
						inserted = true;
						connection.commit();
					}
					else {
						connection.rollback();
					}
					connection.setAutoCommit(true);
				}
			}
			catch (SQLException ex) {
				System.out.println(ex);
			}
		}
		return inserted;
	}
	
	/**
	 * Retrieve a student from the database from the id
	 * @param id The students id to look for
	 * @return The student object if found
	 * @throws NotFoundException
	 */
	public static Student retrieve(long id) throws NotFoundException{
		Student student = null;
		try {
			ResultSet rs = stmt.executeQuery("SELECT id, programCode, programDescription, year "
					+ "FROM Students WHERE id = " + id + "");
			if (rs.next())
			{
				User tempUser = UserDA.retrieve(id);
				try {
					student = new Student();
					student.setId(tempUser.getId());
					student.setPassword(tempUser.getPassword());
					student.setFirstName(tempUser.getFirstName());
					student.setLastName(tempUser.getLastName());
					student.setEmailAddress(tempUser.getEmailAddress());
					student.setLastAccess(tempUser.getLastAccess());
					student.setEnrolDate(tempUser.getEnrolDate());
					student.setEnabled(tempUser.isEnabled());
					student.setType(tempUser.getType());
					student.setProgramCode(rs.getString("programCode"));
					student.setProgramDescription(rs.getString("programDescription"));
					student.setYear(rs.getInt("year"));
				}
				catch (Exception e) {
					System.out.println(e);
				}
			}
			else {
				throw new NotFoundException("A student with that ID was not found in the database");
			}
		}
		catch (SQLException e) {
			System.out.println(e);
		}
		return student;
	}
	
	/**
	 * Update a students information within the database
	 * @param student The students new information to update
	 * @return The records updated
	 */
	public static int update(Student student) {
		int records = 0;
		try {
			Student.retrieve(student.getId());
			PreparedStatement ps;
			ps = connection.prepareStatement("UPDATE Students SET programCOde = ?, programDescription = ?, year = ? WHERE id = ?");
			ps.setString(1, student.getProgramCode());
			ps.setString(2,  student.getProgramDescription());
			ps.setString(3, String.valueOf(student.getYear()));
			ps.setLong(4, student.getId());
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
	 * Delete a student from the database
	 * @param student The student that is meant to be deleted
	 * @return How many records were found
	 */
	public static int delete(Student student) {
		int records = 0;
		String sqlStudentDelete = "DELETE FROM Students WHERE id = " + student.getId() + "";
		
		try {
			Student.retrieve(student.getId());
			User tempUser = User.retrieve(student.getId());
			records += stmt.executeUpdate(sqlStudentDelete);
			tempUser.delete();
		}
		catch (NotFoundException e)
		{
			System.out.println("A student with the id " + student.getId() + " cannot be deleted as they do not exist in the database.");
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		return records;
	}
	
	/**
	 * Check the database to match the given id and password
	 * @param id The id to look for
	 * @param password The password to authenticate
	 * @return The student object if found
	 * @throws NotFoundException
	 */
	public static Student authenticate(long id, String password) throws NotFoundException {
		Student student = null;
		student = Student.retrieve(id);
		if (student == null || !student.getPassword().equals(Hasher.HashPassword(password))) {
			throw new NotFoundException("The user was not found");
		}
		return student;
	}
	
}
