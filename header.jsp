<%@ page import = "GugelmeierC.*" %>
<%
Student aStudent = (Student)session.getAttribute("student"); 
String backDoor = (String)session.getAttribute("backDoor");
%>
<% String test = "test"; %>
<html>
<head>
	<title><%= title %></title>
</head>
<body>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

<div class="container">
    <header class="d-flex justify-content-end py-3">
        <ul class="nav nav-pills">
            <li class="nav-item"><a href="./index.jsp" class="nav-link">Home</a></li>
        </ul>
        
        <div>
            <% if (aStudent == null && backDoor == null) { %>
                <button id="LoginBtn" class="btn btn-primary" onclick="location.href='./login.jsp'">Login</button>
                <button id="RegisterBtn" class="btn btn-secondary" onclick="location.href='./register.jsp'">Register</button>
            <% } else { %>
                <button id="LogoutBtn" class="btn btn-danger" onclick="location.href='./Logout'">Logout</button>
            <% } %>
            <% if (backDoor != null) { %>
                <button id="AdminDashboardBtn" class="btn btn-secondary" onclick="location.href='./adminDashboard.jsp'">Admin</button>
            <% } %>
        </div>
    </header>
    
    <h1 style="text-align: center;"><%= title %></h1>
</div>