<html><head>
	<title>Customer Information Update</title>
	<script type="text/JavaScript1.2">
		function verify() {
			if (document.Input.Name.value == "") 
			{
				alert("You must enter a name!!");
				return false;
			}
			if (document.Input.Address.value == "") 
			{
				alert("You must enter an address!!");
				return false;
			}
			return true;
		}
	</script>
</head>
<%@ page import = "demo.*" %>
<% Customer aCustomer = (Customer)session.getAttribute("customer"); %>
<body>
	<center><hr>
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
	<h2>Customer Information Update</h2>
	<p>Please confirm the information below. Make changes if needed, then
	press <strong>Submit</strong>.<br>If you are not a customer, please 
	return to the <a href="index.html">Bradshaw Marina</a> home page.</p>
	<form method="post" action="./Update"> 
	<!-- NOTE: this is a horrible "action", but used to show how this form and the java servlet are linked inside of the web.xml file. -->
		<table border="0" bgcolor="#FAFADD" cellpadding="5">
		<tr>
			<td><strong>Name</strong></td>
			<td><input type="text" name = "Name" 
						value="<%= aCustomer.getName() %>"></td>
		</tr>
		<tr>
			<td><strong>Address</strong></td>
			<td><input type="text" name = "Address" 
						value="<%= aCustomer.getAddress() %>"></td>
		</tr>
		</table>
	<p><input type="button" value="Submit"
		onClick = "if (verify() == true) submit();"></p>
	</form>
	</center>
</body>
</html>
