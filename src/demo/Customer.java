/* Customer class 
*/

package demo;

import java.sql.*;

public class Customer
{
	// attribute definitions
 	private String name;
 	private String address;
 	private String phoneNumber;
 	

	// DA static methods *********************************
	public static void initialize(Connection c)
		{CustomerDA.initialize(c);}
	public static Customer find(String id) throws NotFoundException
		{ return CustomerDA.find(id);}
	public static boolean isExistingLogin(String id)
    { return CustomerDA.isExistingLogin(id);}
	public static void terminate()
		{CustomerDA.terminate();}

	// DA instance methods *********************************
	public void update() throws NotFoundException
		{CustomerDA.update(this);}

	// constructor with parameters
	public Customer(String aName, String anAddress, String aPhoneNo)
	{
		// invoke accessors to populate attributes
		setName(aName);
		setAddress(anAddress);
		setPhoneNumber(aPhoneNo);
    	}

	// get accessors
 	public String getName()
 		{ return name;}
	public String getAddress()
 		{ return address;}
	public String getPhoneNumber()
 		{ return phoneNumber;}
	
	// set accessors
	public void setName(String newName)
		{ name = newName;}
	public void setAddress(String newAddress)
		{ address = newAddress;}
	public void setPhoneNumber(String newPhoneNumber)
		{ phoneNumber = newPhoneNumber;}
	

	public String toString()
	{
		String customerDetails = "Owner is " + getName() +
				" living in " + getAddress() +
				" with phone " + getPhoneNumber();
		return customerDetails;
	}
}
