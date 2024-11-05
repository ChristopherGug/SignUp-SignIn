<% String title = "Register Page"; %>
<%@ include file="./header.jsp" %>

<%   String errorMessage = (String)session.getAttribute("errorMessage"); 
	String id = (String)session.getAttribute("id");
	String firstName = (String)session.getAttribute("firstName");
    String lastName = (String)session.getAttribute("lastName");
    String emailAddress = (String)session.getAttribute("emailAddress");
    String password = (String)session.getAttribute("password");
    String programCode = (String)session.getAttribute("programCode");
    String programDescription = (String)session.getAttribute("programDescription");
    String year = (String)session.getAttribute("year");
	if(errorMessage == null)
		errorMessage= "";
	if(id == null)
		id = "";
    if(firstName == null)
		firstName = "";
    if(lastName == null)
		lastName = "";
    if(emailAddress == null)
		emailAddress = "";
    if(password == null)
		password = "";
    if(programCode == null)
		programCode = "";
    if(programDescription == null)
        programDescription = "";
    if(year == null)
		year = "";
%>
<% if (aStudent != null) { %>
    <meta http-equiv="refresh" content="0; URL=./dashboard.jsp" />
<% } %>
    <center>
    <form name="Register" method="post" action="./Register">
		<table border=0 bgcolor="lightgoldenrodyellow" cellpadding=10 >
            
            
            <tr><td colspan="2"></td><%= errorMessage %></tr>
            <tr>
                <td><strong>Id  </strong></td>
                <td><input type="text" name="Id" required="" value="<%= id %>" size=20></td>
            </tr>
            <tr>
                <td><strong>First Name  </strong></td>
                <td><input type="text" name="FirstName" required="" value="<%= firstName %>" size=20></td>
            </tr>
            <tr>
                <td><strong>Last Name  </strong></td>
                <td><input type="text" name="LastName" required="" value="<%= lastName %>" size=20></td>
            </tr>
            <tr> 
                <td><strong>Email Address  </strong></td>
                <td><input type="email" name="EmailAddress" required="" value="<%= emailAddress %>" size=20></td>
            </tr>
            <tr> 
                <td><strong>Password  </strong></td>
                <td><input type="text" name="Password" required="" value="<%= password %>" size=20></td>
            </tr>
            <tr> 
                <td><strong>Program Code  </strong></td>
                <td><input type="text" name="ProgramCode" required="" value="<%= programCode %>" size=20></td>
            </tr>
            <tr> 
                <td><strong>Program Description  </strong></td>
                <td><input type="text" name="ProgramDescription" required="" value="<%= programDescription %>" size=20></td>
            </tr>
            <tr> 
                <td><strong>Year  </strong></td>
                <td><input type="text" name="Year" required="" value="<%= year %>" size=20></td>
            </tr>
            </table>
            <table border=0 cellspacing=15 >
            <tr>
                <td><input type="submit" value = "Register"></td>
                <td><input type="reset" value = "Clear"></td>
            </tr>
        </table>
	</form>
</center>

<%@ include file="./footer.jsp" %>