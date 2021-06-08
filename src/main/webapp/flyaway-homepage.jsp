<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="com.simplilearn.workshop.utils.*" %>
<%@ page import="java.sql.*" %>
<%ResultSet resultset =null;%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome to Flyaway</title>
</head>
<body>
	<div align='center'>
	<h2>Welcome to Flyaway Flight Booking System</h2>
	<br/>
	<a href='admin.html'>Admin Functions</a>
	</div>
	<div align='left'>
	<h3>Search Flights</h3>
	<br/>
	<form action="search-result.jsp" method="POST">
	From: 
	<%
    try{
 	   Connection connection = MySQLDatabaseUtils.getConnection();
 	   Statement statement = connection.createStatement() ;

       resultset =statement.executeQuery("select city_name from places") ;
	%>
        <select name="from" id="from">
        <option> </option>
        <%  while(resultset.next()){ %>
            <option><%= resultset.getString(1)%></option>
        <% } %>
        </select>

	<%
        }
        catch(Exception e)
        {
             out.println("wrong entry"+e);
        }
	%>
	&nbsp &nbsp To:
		<%
    try{
 	   Connection connection = MySQLDatabaseUtils.getConnection();
 	   Statement statement = connection.createStatement() ;

       resultset =statement.executeQuery("select city_name from places") ;
	%>
        <select name="to" id="to">
        <option> </option>
        <%  while(resultset.next()){ %>
            <option><%= resultset.getString(1)%></option>
        <% } %>
        </select>

	<%
        }
        catch(Exception e)
        {
             out.println("wrong entry"+e);
        }
	%>
	<br/>
	<br/>
	Date of travel: 
	<input type="date" id="travelDate" name="travelDate"
       value=""
       min="2021-01-01" max="2022-12-31">
     <br/>
     <br/>
     Number of Passengers:
      <select name="numOfPassengers" id="numOfPassengers">
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
        </select> 
     <br/>
     <br/>
     <input type="submit" value="search" name="btnsearch">
     &nbsp; <input type="reset" value="Clear" name="btnCLear">
	
	</div>
</body>
</html>