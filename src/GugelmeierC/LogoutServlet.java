package GugelmeierC;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LogoutServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,
                            HttpServletResponse response) 
					throws ServletException, IOException
    {
	   	  
	   	
        try
        {
            HttpSession session = request.getSession(true);
            if ((Student)session.getAttribute("student") != null) {
            	session.setAttribute("logoutMessage", "You have successfully logged out");
            }
            session.removeAttribute("student");
            session.removeAttribute("backDoor");
            response.sendRedirect("./login.jsp");
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