package GugelmeierC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnect {
	static String url = "jdbc:postgresql://127.0.0.1:5432/inft2201_db";
	static Connection aConnection;
	static String user = "inft2201_admin";
	static String password = "inft2201_password";
	
	public static Connection initialize() {
		try {
			Class.forName("org.postgresql.Driver");
			aConnection = DriverManager.getConnection(url, user, password);
		}
		catch (ClassNotFoundException e) {
			System.out.println(e);
		}
		catch (SQLException e) {
			System.out.println(e);
		}
		return aConnection;
	}
	
	public static void terminate() {
		try {
			aConnection.endRequest();
		}
		catch (SQLException e) {
			System.out.println(e);
		}
	}
}
