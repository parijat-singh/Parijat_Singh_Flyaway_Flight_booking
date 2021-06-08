package com.simplilearn.project;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.simplilearn.workshop.utils.MySQLDatabaseUtils;

/**
 * Servlet implementation class adminActionServlet
 */
@WebServlet("/admin-action")
public class adminActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String SELECT_SQL_AL= "SELECT * FROM airlines";
	private static final String SELECT_SQL_PL= "SELECT * FROM places";
	private static final String SELECT_SQL_FL= "SELECT * FROM flights";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("text/html");// set MIME type
		PrintWriter out = response.getWriter();
		
				
		//Read parameters from Admin HTML
		String adminAction = request.getParameter("adminAction");
		//out.println(adminAction);
		//create a session
        HttpSession session = request.getSession();
        session.setAttribute("admindAction", adminAction);
		
		
			//ResultSet rs_PL = statement1.executeQuery(SELECT_SQL_PL);
			//ResultSet rs_FL = statement1.executeQuery(SELECT_SQL_FL);
			out.println("<html>");
			out.println("<head>");
			out.println("<title>welcome</title>");
			out.println("</head>");
			out.println("<body>");
			if (adminAction == null) {
				out.println("<p> No Input provided. Go back and select an option.<p>");
			}else {
			switch (adminAction) {
			case "List Airlines":
				try {
					// step #1 initialize  the database
					Connection connection = MySQLDatabaseUtils.getConnection();
					
					// step #2 obtain a Statement Object from Connection
					Statement statement = connection.createStatement();
					
					//step #3 execute the query obtain a ResultSet
					ResultSet rs = statement.executeQuery(SELECT_SQL_AL);
					out.println("<div align='center'>");
					out.println("<br/><b> List of Airlines<b><br/>");
					out.println("<table border=1>");
					out.println("<tr>");
					out.println("<th>Airline Code</th>");
					out.println("<th>Airline Name</th>");
					out.println("<th>Last Updated</th>");
					out.println("</tr>");
					while(rs.next()) {
						out.println("<tr>");
						out.println("<td>"+ rs.getString("airline_code")+"</td>");
						out.println("<td>"+rs.getString("airline_name")+"</td>");
						out.println("<td>"+rs.getDate("last_updated")+"</td>");
						out.println("</tr>");
						
					}
					rs.close();
					statement.close();
					connection.close();
				} catch (ClassNotFoundException | SQLException e) {
					
					e.printStackTrace();
				}
					//out.println("<br/> Case List Airlines");
				break;
			case "List Places":
				try {
					// step #1 initialize  the database
					Connection connection = MySQLDatabaseUtils.getConnection();
					
					// step #2 obtain a Statement Object from Connection
					Statement statement = connection.createStatement();
					
					//step #3 execute the query obtain a ResultSet
					ResultSet rs = statement.executeQuery(SELECT_SQL_PL);
					out.println("<div align='center'>");
					out.println("<br/><b> List of Destinations<b><br/>");
					out.println("<table border=1>");
					out.println("<tr>");
					out.println("<th>Airport Code</th>");
					out.println("<th>Airport Name</th>");
					out.println("<th>City</th>");
					out.println("<th>Last Updated</th>");
					out.println("</tr>");
					while(rs.next()) {
						out.println("<tr>");
						out.println("<td>"+ rs.getString("airport_code")+"</td>");
						out.println("<td>"+rs.getString("airport_name")+"</td>");
						out.println("<td>"+rs.getString("city_name")+"</td>");
						out.println("<td>"+rs.getDate("last_updated")+"</td>");
						out.println("</tr>");
						
					}
					rs.close();
					statement.close();
					connection.close();
				} catch (ClassNotFoundException | SQLException e) {
					
					e.printStackTrace();
				}
				//out.println("<br/> Case List Places");
				break;
			case "List Flights":
				try {
					// step #1 initialize  the database
					Connection connection = MySQLDatabaseUtils.getConnection();
					
					// step #2 obtain a Statement Object from Connection
					Statement statement = connection.createStatement();
					
					//step #3 execute the query obtain a ResultSet
					ResultSet rs = statement.executeQuery(SELECT_SQL_FL);
					out.println("<div align='center'>");
					out.println("<br/><b> List of Available Flights<b><br/>");
					out.println("<table border=1>");
					out.println("<tr>");
					out.println("<th>Flight Number</th>");
					out.println("<th>Airline Code</th>");
					out.println("<th>From Airport</th>");
					out.println("<th>To Airport</th>");
					out.println("<th>Flight Departure Date</th>");
					out.println("<th>Flight Departure Time</th>");
					out.println("<th>Flight Arrival Date</th>");
					out.println("<th>Flight Arrival Time</th>");
					out.println("<th>Num of seats Available</th>");
					out.println("<th>Price per seat</th>");
					out.println("<th>Last Updated</th>");
					out.println("</tr>");
					while(rs.next()) {
						out.println("<tr>");
						out.println("<td>"+ rs.getString("flight_num")+"</td>");
						out.println("<td>"+rs.getString("airline_code")+"</td>");
						out.println("<td>"+rs.getString("from_airport")+"</td>");
						out.println("<td>"+rs.getString("to_airport")+"</td>");
						out.println("<td>"+rs.getDate("start_datetime")+"</td>");
						out.println("<td>"+rs.getTime("start_datetime")+"</td>");
						out.println("<td>"+rs.getDate("end_datetime")+"</td>");
						out.println("<td>"+rs.getTime("end_datetime")+"</td>");
						out.println("<td>"+rs.getInt("num_of_seats_available")+"</td>");
						out.println("<td>"+rs.getFloat("price")+"</td>");
						out.println("<td>"+rs.getDate("last_updated")+"</td>");
						out.println("</tr>");
						
					}
					rs.close();
					statement.close();
					connection.close();
				} catch (ClassNotFoundException | SQLException e) {
					
					e.printStackTrace();
				}
				
				break;
			case "Change Password":
				response.sendRedirect("/pgfsd-flyaway-project/chgpw.html");
				break;
			default:
				break;
			} 
			out.println("</table>");
			out.println("</div>");
			}
			out.println("</body>");
			out.println("</html>");
			
		
	}

}
