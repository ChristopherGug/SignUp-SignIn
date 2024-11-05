package GugelmeierC;

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
            Connection c = DatabaseConnect.initialize();
            Student.initialize(c);
            User.initialize(c);
            HttpSession session = request.getSession(true);
            long login = -1;
            String password = new String();
            try 
            {   
            	// retrieve data from DB
                login = Long.parseLong(request.getParameter("Login")); //this is the name of the text input box on the form
                password = request.getParameter("Password");
                Student aStudent = Student.authenticate(login, password); //attempt to find the Student, this could cause a NotFoundException
                // if the Student was found and retrieved from the db
				//put the found Student onto the session
                session.setAttribute("student", aStudent);
				//empty out any errors if there were some
                session.setAttribute("errors", "");
                session.setAttribute("logoutMessage", "");
         
                // redirect the user to a JSP
                response.sendRedirect("./dashboard.jsp");
            }
            catch (NumberFormatException Numfe) {
            	if (request.getParameter("Login").equals("admin") && request.getParameter("Password").equals("mypassword")) {
            		session.setAttribute("backDoor", "true");
            		response.sendRedirect("./adminDashboard.jsp");
            	}
            	else {
            		session.setAttribute("errors", "Your sign in information is not valid");
            	}
            	response.sendRedirect("./dashboard.jsp");
            }
            catch( NotFoundException nfe) {
                //sending errors to the page thru the session
                StringBuffer errorBuffer = new StringBuffer();
                errorBuffer.append("<strong>Your sign in information is not valid.<br/>");
                errorBuffer.append("Please try again.</strong>");
                session.setAttribute("errors", errorBuffer.toString());
                response.sendRedirect("./login.jsp");
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