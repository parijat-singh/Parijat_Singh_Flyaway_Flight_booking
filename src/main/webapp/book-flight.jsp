<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Flight</title>
</head>
<body>
<%
String flightId = request.getParameter("flightSelection");
if (flightId == null) {
%>


<%	
}
%>
<%= flightId %>
Pay and book

</body>
</html>