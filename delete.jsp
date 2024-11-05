<% String title = "delete"; %>
<%@ include file="./header.jsp" %>
<%@ page import="java.sql.*" %>
<%
User user = null;
Student student = null;
Faculty faculty = null;
try {
    Connection c = DatabaseConnect.initialize();
    User.initialize(c);
    user = User.retrieve(Long.parseLong(request.getParameter("userID")));
    if (user.getType() == 's') {
        Student.initialize(c);
        student = new Student();
        student.setId(user.getId());
        student.delete();
        Student.terminate();
    }
    else {
        Faculty.initialize(c);
        faculty = new Faculty();
        faculty.setId(user.getId());
        faculty.delete();
        Faculty.terminate();
    }
    user.delete();
    User.terminate();
} catch (Exception e) {System.out.println("UH OH OH OH OH OH");}
%>

<meta http-equiv="refresh" content="0; URL=./adminDashboard.jsp" />

<%@ include file="./footer.jsp" %>