package demo;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,
                            HttpServletResponse response) 
					throws ServletException, IOException
    {
	   	  
	   	//CREATE A TEXT FILE 
	   	/*String logFile = "./test_log.log";
	    File f = new File(logFile);
	    PrintStream printStream = new PrintStream(new BufferedOutputStream(new FileOutputStream(f)), true);
	    System.setErr(printStream);
	    System.setOut(printStream);
	    System.out.println("Log started: " + new java.util.Date());
	    */
        try
        { 
            // connect to database
            Connection c = CustomerConnect.initialize();
            Customer.initialize(c);
            HttpSession session = request.getSession(true);
            String login = new String();
            try 
            {   // retrieve data from DB
                login = request.getParameter( "Login" ); //this is the name of the text input box on the form
                Customer aCustomer = Customer.find(login); //attempt to find the Customer, this could cause a NotFoundException
                // if the Customer was found and retrieved from the db
				//put the found Customer onto the session
                session.setAttribute("customer", aCustomer);
				//empty out any errors if there were some
                session.setAttribute("errors", "");
         
                // redirect the user to a JSP
                response.sendRedirect("./customerWelcome.jsp");
            }catch( NotFoundException nfe)
            {
                //new code == way better, if I do say so myself
                //sending errors to the page thru the session
                StringBuffer errorBuffer = new StringBuffer();
                errorBuffer.append("<strong>Your sign in information is not valid.<br/>");
                errorBuffer.append("Please try again.</strong>");
                if(Customer.isExistingLogin(login))
                  session.setAttribute("login", login);
                else
                {
                  errorBuffer.append("Invalid login id.</strong>");
                  session.setAttribute("login", "");
                }
                session.setAttribute("errors", errorBuffer.toString());
                response.sendRedirect("./login.jsp");
            
            //for the first deliverable you will have to check if there was a problem
            //with just the password, or login id and password
            //this will require a special method e.g. public static boolean isExistingLogin(String arg);
            }
        }    
   	 catch (Exception e) //not connected
        {
            System.out.println(e);
            String line1="<h2>A network error has occurred!</h2>";
            String line2="<p>Please notify your system " +
                                                    "administrator and check log. "+e.toString()+"</p>";
            formatErrorPage(line1, line2,response);
        }
    }
    public void doGet(HttpServletRequest request,
                            HttpServletResponse response)
                                    throws ServletException, IOException {
        doPost(request, response);
    }

    public void formatErrorPage( String first, String second,
            HttpServletResponse response) throws IOException
    {
        PrintWriter output = response.getWriter();
        response.setContentType( "text/html" );
        output.println(first);
        output.println(second);
        output.close();
    }
}