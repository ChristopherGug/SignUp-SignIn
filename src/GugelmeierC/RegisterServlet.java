package GugelmeierC;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.Date;
import java.util.IllegalFormatException;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class RegisterServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,
                            HttpServletResponse response) 
					throws ServletException, IOException
    {
    	String errorMessage = "";
    	boolean noErrors = true;
    	String id = "";
    	String password = "";
    	String firstName = "";
    	String lastName = "";
    	String emailAddress = "";
    	String programCode = "";
    	String programDescription = "";
    	String year = "";
	   	Student student = null;
		try {
			student = new Student();
		} catch (InvalidUserDataException e) {
			e.printStackTrace();
		}
    	
        try
        { 
            HttpSession session = request.getSession(true);
            try {
            	id = request.getParameter("Id");
            	//Student.retrieve(Long.valueOf(id));
            	student.setId(Long.valueOf(id));
            	session.setAttribute("id", id);
            }
            catch (NumberFormatException nfe) {
            	System.out.println("id error 1");
            	session.setAttribute("id", "");
            	errorMessage += "The id must be a number. ";
            	noErrors = false;
            }
            catch (InvalidIdException iie) {
            	System.out.println("id error 3");
            	session.setAttribute("id", "");
            	errorMessage += iie.getMessage() + ". ";
            	noErrors = false;
            }
            catch (Exception e) {
            	System.out.println("id error 4: " + e.getMessage());
            	session.setAttribute("id", "");
            	errorMessage += "There was an error with the id. ";
            	noErrors = false;
            }
            
            try {
            	password = request.getParameter("Password");
            	student.setPassword(password);
            	session.setAttribute("password", password);
            }
            catch (InvalidPasswordException ipe) {
            	System.out.println("password error 1");
            	session.setAttribute("password", "");
            	errorMessage += ipe.getMessage() + ". ";
            	noErrors = false;
            }
            catch (Exception e) {
            	System.out.println("password error 2");
            	session.setAttribute("password", "");
            	errorMessage += "There was an error with the password. ";
            	noErrors = false;
            }
            
            try {
            	firstName = request.getParameter("FirstName");
            	student.setFirstName(firstName);
            	session.setAttribute("firstName", firstName);
            }
            catch (InvalidNameException ine) {
            	System.out.println("firstName error 1");
            	session.setAttribute("firstName", "");
            	errorMessage += ine.getMessage() + ". ";
            	noErrors = false;
            }
            catch (Exception e) {
            	System.out.println("firstName error 2");
            	session.setAttribute("firstName", "");
            	errorMessage += "There was an error with the first name. ";
            	noErrors = false;
            }
            
            try {
            	lastName = request.getParameter("LastName");
            	student.setLastName(lastName);
            	session.setAttribute("lastName", lastName);
            }
            catch (InvalidNameException ine) {
            	System.out.println("lastName error 1");
            	session.setAttribute("lastName", "");
            	errorMessage += ine.getMessage() + ". ";
            	noErrors = false;
            }
            catch (Exception e) {
            	System.out.println("lastName error 2");
            	session.setAttribute("lastName", "");
            	errorMessage += "There was an error with the last name. ";
            	noErrors = false;
            }
            
            try {
            	emailAddress = request.getParameter("EmailAddress");
            	InternetAddress email = new InternetAddress(emailAddress);
            	student.setEmailAddress(emailAddress);
            	session.setAttribute("emailAddress", emailAddress);
            }
            catch (AddressException ife) {
            	System.out.println("emailAddress error 1");
            	session.setAttribute("emailAddress", "");
            	errorMessage += ife.getMessage() + ". ";
            	noErrors = false;
            }
            catch (Exception e) {
            	System.out.println("emailAddress error 2");
            	session.setAttribute("emailAddress", "");
            	errorMessage += "There was an error with the email address. ";
            	noErrors = false;
            }
            
            programCode = request.getParameter("ProgramCode");
            if (!programCode.equals("")) {
            	student.setProgramCode(programCode);
            	session.setAttribute("programCode", student.getProgramCode());
            }
            else {
            	session.setAttribute("programCode", "");
            	errorMessage += "you must enter a program code. ";
            	noErrors = false;
            }
            
            programDescription = request.getParameter("ProgramDescription");
            if (!programDescription.equals("")) {
            	student.setProgramDescription(programDescription);
            	session.setAttribute("programDescription", student.getProgramDescription());
            }
            else {
            	session.setAttribute("programDescription", "");
            	errorMessage += " you must enter a program description. ";
            	noErrors = false;
            }

            
            try {
            	year = request.getParameter("Year");
            	student.setYear(Integer.valueOf(year));
            	session.setAttribute("year", year);
            }
            catch (NumberFormatException nfe) {
            	System.out.println("year error 1");
            	session.setAttribute("year", "");
            	errorMessage += "the year must be a number. ";
            	noErrors = false;
            }
            catch (Exception e) {
            	System.out.println("year error 2");
            	session.setAttribute("year", "");
            	errorMessage += "There was an error with the year. ";
            	noErrors = false;
            }
            
            if (noErrors) {
            	session.removeAttribute("id");
            	session.removeAttribute("password");
            	session.removeAttribute("firstName");
            	session.removeAttribute("lastName");
            	session.removeAttribute("emailAddress");
            	session.removeAttribute("programCode");
            	session.removeAttribute("programDescription");
            	session.removeAttribute("year");
            	session.removeAttribute("errorMessage");
            	System.out.println(student.toString());
            	student.create();
            	session.setAttribute("student", student);
            	response.sendRedirect("./dashboard.jsp");
            }
            else {
            	
            	session.setAttribute("errorMessage", errorMessage);
            	response.sendRedirect("./register.jsp");
            }
        }
        catch (Exception e) //not connected
        {
            e.printStackTrace();
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