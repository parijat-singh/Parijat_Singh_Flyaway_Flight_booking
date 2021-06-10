<%@page import="com.simplilearn.project.getFlightPrice"%>
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
	int numOfPassengers=(int)session.getAttribute("numOfPassengers"); 
	session.setAttribute("numOfPassengers",numOfPassengers);
	if (flightId == null) {
%>
		<h2>Error: Please make go back and make a selection</h2>

	<%	
	} else {
		String id = flightId.substring(6);
		session.setAttribute("id",id);
	
	%>
<form action="final-confirmation.jsp" method="POST">
<h2>Enter Passenger Details</h2>
(All Fields are mandatory!) <br><br>

<% for (int i=1; i<= numOfPassengers; i++) {
	%> 
	Passenger<%=i%> Name &nbsp&nbsp&nbsp: &nbsp&nbsp <input type="text" id="passenger<%=i%>Name" name="passenger<%=i%>Name">
	 <br>
	Passenger<%=i %> Gender &nbsp: &nbsp
	<input type="radio" id="passenger<%=i%>Gender" name="passenger<%=i%>Gender" value ="male"><label for="male">Male</label>
	<input type="radio" id="passenger<%=i%>Gender" name="passenger<%=i%>Gender" value ="female"> <label for="female">Female</label><br>
	Passenger<%=i %> DOB &nbsp&nbsp&nbsp: &nbsp&nbsp <input type="date" id="dob" name="passenger<%=i%>Dob"
       value=""
       min="1920-01-01" max="2021-12-31">
       <br>
       <br>
<%
     }
float totalAmount = getFlightPrice.getPrice(id) * numOfPassengers;
session.setAttribute("totalAmount",totalAmount);
%>
<h3>Enter Credit Card Details</h3>
<br>
Total amount to be charged: $<%=totalAmount %>
<br>
<br>
Contact Email &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp: <input type="email" id="payerEmail" name="payeremail"> <br><br>
Name on Credit Card: <input type="text" id="payerName" name="payerName"> <br><br>
Billing Zipcode &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp: <input type="text" id="payerZip" name="payerZip"> <br><br>
Credit Card Number : <input type="text" id="ccNum" name="ccNum"> <br><br>
Expiration Month &nbsp&nbsp&nbsp&nbsp:  <select name="expMonth" id="expMonth">
								            <option>1</option>
								            <option>2</option>
								            <option>3</option>
								            <option>4</option>
								            <option>4</option>
								            <option>5</option>
								            <option>6</option>
								            <option>7</option>
								            <option>8</option>
								            <option>9</option>
								            <option>10</option>
								            <option>11</option>
								            <option>12</option>
								          </select> <br><br>
Expiration Year&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp:<select name="expYear" id="expYear">
								            <option>2021</option>
								            <option>2022</option>
								            <option>2023</option>
								            <option>2024</option>
								            <option>2024</option>
								            <option>2025</option>
								            <option>2026</option>
								            <option>2027</option>
								            <option>2028</option>
								            <option>2029</option>
								            <option>2030</option>
								            <option>2031</option>
								            <option>2032</option>
								          </select> <br><br> 				
&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<input type="submit" value="Complete Booking" name="btnpay">

<%
}
%>
</form>
</body>
</html>