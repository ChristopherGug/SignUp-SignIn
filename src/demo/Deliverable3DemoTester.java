/**
 * 
 */
package demo;

import java.sql.Connection;
import java.util.Vector;

/**
 * 
 *
 */
public class Deliverable3DemoTester {

	public static void main(String[] args) {
		  
		Connection c = null;
		try{  
	        // initialize the databases (i.e. connect)
	        c = CustomerConnect.initialize();
	        Customer.initialize(c);
	        Customer aCustomer;
	        try // get a customer
	        {
	                aCustomer = Customer.find("123-4567");
	                System.out.println(aCustomer.toString());
	        }
	        catch(NotFoundException e)
	                {	System.out.println(e);}
		  }catch(Exception e)
		  {
			  System.out.println(e);
		  }

	}

}
