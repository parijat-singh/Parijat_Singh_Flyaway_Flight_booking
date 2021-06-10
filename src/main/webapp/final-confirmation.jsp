<%@page import="com.simplilearn.project.getFlightInfo"%>
<%@page import="com.simplilearn.project.makeFinalReservation"%>
<%@page import="java.util.Date"%>
<%@page import="com.simplilearn.project.updateSeats"%>
<%@page import="com.simplilearn.workshop.utils.projectUtilities"%>
<%@page import="com.simplilearn.project.checkSeatAvailability"%>
<%@page import="java.util.regex.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Booking Confirmation</title>
</head>
<body>

<%
//Retreive all values passed from book-flight.jsp
int numOfPassengers=(int)session.getAttribute("numOfPassengers"); 
String flightId=(String)session.getAttribute("id"); 
float totalAmount = (float)session.getAttribute("totalAmount");
String[] passengerName = new String[numOfPassengers];
String[] passengerGender = new String[numOfPassengers];
String[] passengerDob = new String[numOfPassengers];
int j=0;
boolean error=false;
for (int i=0;i<numOfPassengers;i++){
	j=i+1;
	passengerName[i] = request.getParameter("passenger"+j+"Name");
	passengerGender[i] = (String)request.getParameter("passenger"+j+"Gender");
	passengerDob[i] = (String)request.getParameter("passenger"+j+"Dob");
}
String payerEmail = request.getParameter("payerEmail");
String payerName = request.getParameter("payerName");
String payerZip = request.getParameter("payerZip");
String ccNum = request.getParameter("ccNum");
String expMonth = request.getParameter("expMonth");
String expYear = request.getParameter("expYear");
for (int i=0;i<numOfPassengers;i++){
	if (passengerName[i] == null || passengerName[i].trim() ==""|| passengerGender[i] == null || passengerDob[i] == null){
		error = true;
		%>
		<b>Error:</b> Passenger<%=i+1 %> details incomplete.<br>
		<% 
	}
}
if (payerName == null || payerName.trim() =="" || payerZip == null || payerZip.trim() =="" || ccNum == null || ccNum.trim() =="" ){
	error = true;
	%>
	<b>Error:</b> Payment details incomplete.<br>
	<% 
}
if (ccNum.length() != 16 || !projectUtilities.isNumeric(ccNum)){
	error = true;
	%>
	<b>Error:</b> Credit Card should be 16 digits and all Numeric<br>
	<% 
}
if (payerZip.length() != 5 || !projectUtilities.isNumeric(payerZip)){
	error = true;
	%>
	<b>Error:</b> Zip code should be 5 digits and all Numeric<br>
	<% 
}
int numOfSeatsAvailable = checkSeatAvailability.getSeats(flightId);
if (numOfSeatsAvailable < numOfPassengers){
	error = true;
	%>
	<b>Error:</b> The Seats are no longer Available, Please return to 
	<a href="/pgfsd-flyaway-project/flyaway-homepage.jsp">Homepage</a> and restart your booking<br>
	<% 
}
if (!error){
	//Reduce number available seats in flights table
	updateSeats.updateAvailableSeats(flightId, numOfPassengers);
	String expiration = expMonth + "/" + expYear;
	//insert a row into the booking table with reservation details and get unique reservation identifier
	String confirmationId = makeFinalReservation.insertReservation(flightId, numOfPassengers, payerName, payerZip, ccNum, expiration, totalAmount, passengerName, passengerGender, passengerDob);
	String[] selectedFlight = getFlightInfo.getFlightDetails(flightId);
	%>
	<h1> Success: Reservation Complete</h1>
	<p> Your unique reservation identifier is <b><%=confirmationId %></b></p>
	<h2> Reservation Details:</h2>
	<b>Flight Number:</b> <%=selectedFlight[1] + selectedFlight[0] %><br>
	<b>Departure Airport:</b> <%=selectedFlight[2]%> <br>
	<b>Departure Date and time:</b> <%=selectedFlight[4].substring(0,10) + " @ " + selectedFlight[4].substring(10,16) %> <br>
	<b>Arrival Airport:</b> <%=selectedFlight[3]%> <br>
	<b>Arrival Date and time:</b> <%=selectedFlight[5].substring(0,10) + " @ " + selectedFlight[5].substring(10,16) %> <br>
	<b>Number of Passengers:</b> <%= numOfPassengers%> <br>
	<b>Amount Charged:</b> <%= "$" + totalAmount%> <br>
	<br>
	<br>
	<p> Please visit <a href="/pgfsd-flyaway-project/flyaway-homepage.jsp">FlyAway</a> again to make another flight Reservation</p>
	
<%
}

%>

</body>
</html>