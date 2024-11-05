package GugelmeierC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class DatabaseTester {

	static String url = "jdbc:postgresql://127.0.0.1:5432/gugelmeierc_db";
	static Connection aConnection;
	static String user = "inft_user";
	static String password = "inft_user";

	
	public static void main(String[] args) {
		StudentDA.initialize(DatabaseConnect.initialize());
		
//		initialize();
//		createPreparedStatement();
//		getUserList();
//		updateUser(234512514, 1);
//		getUserList();

	}

	public static void getUserList()
	{
		try
		{
			Statement stmt = aConnection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM users;");
			while (rs.next())
			{
				System.out.println("got: " + rs.getInt("id"));
			}
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	public static void createPreparedStatement()
	{
		Random rand = new Random();
		int num = rand.nextInt(1000);
		try
		{
			PreparedStatement stmt = aConnection.prepareStatement("INSERT INTO users VALUES (?, ?, ?, ?, ?, ?)");
			stmt.setInt(1, num);
			stmt.setString(2, "testing for inft enterprise");
			stmt.setString(3, "randomPassword");
			stmt.setString(4, "Adam");
			stmt.setString(5, "Kumz");
			stmt.setTimestamp(6, null);
			stmt.execute();
			System.out.println("Added: " + num);
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	public static void updateUser(int old_id, int new_id)
	{
		Random rand = new Random();
		int num = rand.nextInt(1000);
		try
		{
			PreparedStatement stmt = aConnection.prepareStatement("UPDATE users SET id = ? WHERE id = ?");
			stmt.setInt(1, new_id);
			stmt.setInt(2, old_id);
			stmt.execute();
			System.out.println("Changed: " + old_id + " to: " + new_id);
		}
		catch (SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	public static Connection initialize()
	{
		try
		{
			Class.forName("org.postgresql.Driver");
		// load the JDBC Driver for PostGreSQL
			aConnection = DriverManager.getConnection(url, user, password);
		// create connection instance
		}
		catch (ClassNotFoundException e)
		//will occur if the driver is not found
		{
			System.out.println(e);
		}
		catch (SQLException e)
		//will occur if the db does not exist, authentication errors etc.
		{
			System.out.println(e);
		}
		return aConnection;
	}
}
