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
String payerName = request.getParameter("payerName");
String payerZip = request.getParameter("payerZip");
String ccNum = request.getParameter("ccNum");
String expMonth = request.getParameter("expMonth");
String expYear = request.getParameter("expYear");
for (int i=0;i<numOfPassengers;i++){
	if (passengerName[i] == null || passengerName[i].trim() ==""|| passengerGender[i] == null || passengerDob[i] == null){
		error = true;
		%>
		Error: Passenger<%=i+1 %> details incomplete.<br>
		<% 
	}
}
if (payerName == null || payerName.trim() =="" || payerZip == null || payerZip.trim() =="" || ccNum == null || ccNum.trim() =="" ){
	error = true;
	%>
	Error: Payment details incomplete.<br>
	<% 
}
if (ccNum.length() != 16 || !projectUtilities.isNumeric(ccNum)){
	error = true;
	%>
	Error: Credit Card should be 16 digits and all Numeric<br>
	<% 
}
if (payerZip.length() != 5 || !projectUtilities.isNumeric(payerZip)){
	error = true;
	%>
	Error: Zip code should be 5 digits and all Numeric<br>
	<% 
}
int numOfSeatsAvailable = checkSeatAvailability.getSeats(flightId);
if (numOfSeatsAvailable < numOfPassengers){
	error = true;
	%>
	Error: The Seats are no longer Available, Please return to <a href="/pgfsd-flyaway-project/flyaway-homepage.jsp">Homepage</a> are restart your booking<br>
	
	<% 
}

%>

<%=projectUtilities.isNumeric(ccNum)%><br>
<%=numOfSeatsAvailable%><br>
<%=passengerName[0] %><br>
<%=passengerGender[0] %><br>
<%=passengerDob[0] %><br>
<%=payerName %><br>
<%=payerZip %><br>
<%=ccNum %><br>
<%=expMonth %><br>
<%=expYear %> <br>
<%=numOfPassengers %><br>
<%=flightId %><br>
<%=totalAmount %><br>

Booking confirmation!
</body>
</html>