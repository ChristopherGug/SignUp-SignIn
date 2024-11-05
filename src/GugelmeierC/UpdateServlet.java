package GugelmeierC;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class UpdateServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,
                            HttpServletResponse response) 
					throws ServletException, IOException
    {
        try
        {
            HttpSession session = request.getSession(true);
            Connection c = null;
            c = DatabaseConnect.initialize();
            User.initialize(c);
            User user = User.retrieve(Long.valueOf(request.getParameter("Id")));
            
            user.setFirstName(request.getParameter("FirstName"));
            user.setLastName(request.getParameter("LastName"));
            user.setEmailAddress(request.getParameter("EmailAddress"));
            
            user.update();
            
            User.terminate();
            
            response.sendRedirect("./adminDashboard.jsp");
        }
        catch (NumberFormatException nfe) {
        	System.out.println(request.getParameter("userID"));
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