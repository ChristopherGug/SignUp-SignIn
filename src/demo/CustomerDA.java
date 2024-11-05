/* CustomerDA 
*/

package demo;

import java.sql.*;

public class CustomerDA
{
	static Customer aCustomer;

	// declare variables for the database connection
	static Connection aConnection;
	static Statement aStatement;

	// declare variables for Customer attribute values
	static String name;
	static String address;
	static String phoneNumber;

	// establish the database connection
	public static void initialize(Connection c)
	{
		try {
			aConnection=c;
			aStatement=aConnection.createStatement();
		}
		catch (SQLException e)
			{ System.out.println(e);	}
	}

	// close the database connection
	public static void terminate()
	{
		try
 		{ 	// close the statement
    		aStatement.close();
		}
		catch (SQLException e)
			{ System.out.println(e);	}
	}

	public static Customer find(String id) throws NotFoundException
	{ // retrieve Customer and Boat data
		aCustomer = null;
		// define the SQL query statement using the phone number key
		String sqlQuery = "SELECT name, address, phoneNumber " +
                                      	"FROM customers " +
                                        " WHERE phoneNumber = '" + id +"'" ;
                
                // execute the SQL query statement
		try
 		{
	    	ResultSet rs = aStatement.executeQuery(sqlQuery);
			// next method sets cursor & returns true if there is data
			boolean gotIt = rs.next();
	    	if (gotIt)
	    		{	// extract the data
					name = rs.getString("name");
					address = rs.getString("address");
					phoneNumber = rs.getString("phoneNumber");
					// create Customer & Boat instance
					aCustomer = new Customer(name, address, phoneNumber);
					
				}
			else	// nothing was retrieved
				{throw (new NotFoundException("not found "));}
			rs.close();
	   	}
		catch (SQLException e)
		{ System.out.println(e);}
		return aCustomer;
	}
  
	public static boolean isExistingLogin(String id)
	{ 
		// retrieve Customer data
		// define the SQL query statement using the id key
		String sqlQuery = "SELECT  * " +
							" FROM customers " +
							" WHERE id= '" + id +"'";
			//System.out.println(sqlQuery);
		 boolean exists = true;                  
		// execute the SQL query statement
		try
		{
			ResultSet rs = aStatement.executeQuery(sqlQuery);
			exists = rs.next();
		}catch (SQLException e)
		{ 
			System.out.println(e);
		}
		return exists;
	}
	public static void update(Customer aCustomer) throws NotFoundException
	{	// retrieve the customer attribute values
		phoneNumber = aCustomer.getPhoneNumber();
		name = aCustomer.getName();
		address = aCustomer.getAddress();

		// define the SQL query statement using the phone number key
		String sqlUpdate = "Update customers " +
								 " SET name      = '" + name +"', " +
								 " address   = '" + address +"' " +
								 " WHERE phoneNumber = '" + phoneNumber + "'";

		// see if this customer exists in the database
		// NotFoundException is thrown by find method
		try
		{
			Customer c = Customer.find(phoneNumber);
    		// if found, execute the SQL update statement
    		int result = aStatement.executeUpdate(sqlUpdate);
   	}
		catch (SQLException e)
			{ System.out.println(e);	}
	}

}

