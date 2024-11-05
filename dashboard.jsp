<% String title = "Dashboard Page"; %>
<%@ include file="./header.jsp" %>

	<% if (aStudent == null) { %>
		<meta http-equiv="refresh" content="0; URL=./login.jsp" />
	<% } else { %>
    <h2 style="text-align: center;">Welcome, <%= aStudent.getFirstName() %></h1>
	<% } %>
	<%@ include file="./footer.jsp" %>