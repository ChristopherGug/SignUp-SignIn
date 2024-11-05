<% String title = "Login Page"; %>
<%@ include file="./header.jsp" %>

<%   String errorMessage = (String)session.getAttribute("errors"); 
	String login = (String)session.getAttribute("login");
	String password = (String)session.getAttribute("password");
	if(errorMessage == null)
		errorMessage="";
	if(login == null)
		login = "";
	if (password == null)
		password = "";
%>
	<center>
	<hr>
	<img src = "images/smallimage1.gif">
	<img src = "images/smallimage2.gif">
	<img src = "images/smallimage3.gif">
	<img src = "images/smallimage4.gif">
	<img src = "images/smallimage5.gif">
	<img src = "images/smallimage6.gif">
	<img src = "images/smallimage7.gif">
	<img src = "images/smallimage8.gif">
	<img src = "images/smallimage9.gif">
	<img src = "images/smallimage10.gif">
	<hr>
	<h2>Please log in</h2>
	<p>Enter your login information below.<br>
	   If you are not a customer, please return to the
	   <a href="index.jsp">Bradshaw Marina</a> home page.</p>
	<p>
	   If you want to become a customer on our system, please <a href="register.jsp">register</a>.</p>
	<form name="Input" method="post" action="./Login" >
		<table border=0 bgcolor="lightgoldenrodyellow" cellpadding=10 >
		<tr><td colspan="2"><%= errorMessage %></td></tr>
		<tr>
			<td><strong>Login Id</strong></td>
			<td><input type="text" name="Login" value="<%= login %>" size=20></td>
		</tr>
		<tr>
			<td><strong>Password</strong></td>
			<td><input type="password" name="Password" value="<%= password %>" size=20></td>
		</tr>
		</table>
		<table border=0 cellspacing=15 >
		<tr>
			<td><input type="submit" value = "Log In"></td>
			<td><input type="reset" value = "Clear"></td>
		</tr>
		</table>
	</form>
	Please wait after pressing <strong>Log in</strong>
	while we retrieve your records from our database.<br>
	<em>(This may take a few moments)</em>
	</center>
<%@ include file="./footer.jsp" %>