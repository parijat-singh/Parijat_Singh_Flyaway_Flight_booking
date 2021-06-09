<%@page import="java.io.PrintWriter"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="com.simplilearn.workshop.utils.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.sql.*" %>
<%ResultSet rs =null;%>
<%ResultSet rs1 =null;%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Result</title>
</head>
<body>
<% String from = request.getParameter("from");
   String to = request.getParameter("to");
   int numOfPassengers = Integer.parseInt(request.getParameter("numOfPassengers"));
   String travelDate = request.getParameter("travelDate");
   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
   Date tDate = formatter.parse(travelDate);
   Date today = new java.util.Date();
   int dateDiff = tDate.compareTo(today);
   if (from.trim() == "" || to.trim() == "" || dateDiff <0 ){
%>
	   <b>Invalid inputs, please try again!</b>
	   <br/>
	   From and To cities are required
	   <br/>
	   Travel Date should be a future date.
<%   } else {
%>
	<h2>Search Result</h2>
	<br/>
	<form action="book-flight.jsp" method="POST">
	
	  <%
	  try {
		  	Connection connection = MySQLDatabaseUtils.getConnection();
	 	  	Statement statement = connection.createStatement() ;

	       	 rs = statement.executeQuery("select * from places where city_name ='" + from + "'") ;
	       	 rs.next();
	       	 String fromAirportCode = rs.getString("airport_code");
	       	 String fromAirportName = rs.getString("airport_name");
			 rs.close();
			 rs = statement.executeQuery("select * from places where city_name ='" + to +"'") ;
			 rs.next();
			 String toAirportCode = rs.getString("airport_code");
	       	 String toAirportName = rs.getString("airport_name"); 
			 rs.close(); 
			 String FL_QUERY = "select * from flights where from_airport ='" 
					 + fromAirportCode +"' and to_airport = '" + toAirportCode 
					 + "' and start_datetime like '" + travelDate + "%' and num_of_seats_available >= "
					 + numOfPassengers;
			 rs = statement.executeQuery(FL_QUERY) ; 
			 %>
			 <table border=1>
			   <tr>
			     <th>Select</th>
			     <th>Airline Name </th>
			     <th>Flight Number </th>
			     <th>Departure Airport</th>
			     <th>Departure Date</th>
			     <th>Departure Time</th>
			     <th>Arrival Airport</th>
			     <th>Arrival Date</th>
			     <th>Arrival Time</th>
			     <th>Price per ticket</th>
			     <th>Number of Passengers</th>
			  </tr>
			 <%
			
			 while(rs.next()) { 
				 int flightId = rs.getInt("id");
				 String flightIdRadio = "flight" + flightId;
				 String airlineCode = rs.getString("airline_code");
				 String flightNum = airlineCode + rs.getString("flight_num");
				 String departureDate = rs.getString("start_datetime").substring(0,10);
				 String departureTime = rs.getString("start_datetime").substring(10,16);
				 String arrivalDate = rs.getString("end_datetime").substring(0,10);
				 String arrivalTime = rs.getString("end_datetime").substring(10,16);
				 Float ticketPrice = rs.getFloat("price");
				 
				 Statement statement1 = connection.createStatement() ;
				 rs1 = statement1.executeQuery("select * from airlines where airline_code ='" + airlineCode +"'") ;
				 rs1.next();
				 String airlineName = rs1.getString("airline_name");
				 rs1.close();
				 statement1.close();
				 
		   %>
			
			  <tr>
				 <td> <input type="radio" id="<%=flightIdRadio%>" name="flightSelection" value="<%=flightIdRadio%>"></td>
				 <td> <%=airlineName %> </td>
				 <td> <%=flightNum %> </td>
				 <td> <%=fromAirportName + " (" + fromAirportCode + ")"%> </td>
				 <td> <%=departureDate %> </td>
				 <td> <%=departureTime %> </td>
				 <td> <%=toAirportName + " (" + toAirportCode + ")" %> </td>
				 <td> <%=arrivalDate %> </td>
				 <td> <%=arrivalTime %> </td>
				 <td> $<%=ticketPrice %> </td>
				 <td> <%=numOfPassengers %> </td>
			  </tr>
					
			<%
			 }
			 %>
			 </table>
			<%
			 rs.close();
			 statement.close();
			 connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	         %>
	<br/>
     <br/>
     <input type="submit" value="Book Flight" name="btnbook">
	 
<% }%>

</form>
</body>
</html>