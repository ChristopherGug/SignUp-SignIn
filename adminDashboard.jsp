<% String title = "Admin Page"; %>
<%@ include file="./header.jsp" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.sql.*" %>
<% 
Vector<User> users = new Vector<>();
try {
    Connection c = DatabaseConnect.initialize();
    User.initialize(c);
    users = User.retrieveAll();
    User.terminate();
} catch (Exception e) {}


%>

<body>
    <center>
        <table class="table">
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email Address</th>
                <th>Type</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <% 
            for (int i = 0; i < users.size(); i++) { 
                User user = users.get(i);
                %>
            <tr>
                <td><%= user.getId() %></td>
                <td><%= user.getFirstName() %></td>
                <td><%= user.getLastName() %></td>
                <td><%= user.getEmailAddress() %></td>
                <td><%= user.getType() %></td>
                <td><a href="update.jsp?userID=<%= user.getId() %>">Edit</a></td>
                <td><a href="delete.jsp?userID=<%= user.getId() %>" onclick="confirm('Delete selected item?')">Delete</a></td>
            </tr>
            <% } %>    
            </table>
</center>


<%@ include file="./footer.jsp" %>