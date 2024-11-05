<% String title = "Update Page"; %>
<%@ include file="./header.jsp" %>
<%@ page import="java.sql.*" %>
<%
User user = null;
try {
    Connection c = DatabaseConnect.initialize();
    User.initialize(c);
    user = User.retrieve(Integer.parseInt(request.getParameter("userID")));
    User.terminate();
} catch (Exception e) {}
%>
<center>
<form name="Update" method="post" action="./Update">
	<table cellpadding="10">
		<tr>
			<td><strong>Id  </strong></td>
			<td><input type="text" name="Id" readonly value="<%= user.getId() %>" size=20></td>
		</tr>
		<tr>
			<td><strong>First Name  </strong></td>
			<td><input type="text" name="FirstName" required="" value="<%= user.getFirstName() %>" size=20></td>
		</tr>
		<tr>
			<td><strong>Last Name  </strong></td>
			<td><input type="text" name="LastName" required="" value="<%= user.getLastName() %>" size=20></td>
		</tr>
		<tr> 
			<td><strong>Email Address  </strong></td>
			<td><input type="email" name="EmailAddress" required="" value="<%= user.getEmailAddress() %>" size=20></td>
		</tr>
		</table>
		<table cellpadding="10" >
		<tr>
			<td><input type="submit" value = "Submit"></td>
			<td><input type="reset" value = "Clear"></td>
		</tr>
	</table>
</form>
</center>
<%@ include file="./footer.jsp" %>