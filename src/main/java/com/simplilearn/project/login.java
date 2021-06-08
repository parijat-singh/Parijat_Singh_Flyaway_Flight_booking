package com.simplilearn.project;

import java.io.*;
import java.io.PrintWriter;
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
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
private static final String SELECT_SQL= "SELECT * FROM login";
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("text/html");// set MIME type
		PrintWriter out = response.getWriter();
				
		//Read parameters from Admin HTML
		String loginid = request.getParameter("adminID");
		String password = request.getParameter("PASSWORD");
		

		//create a session
        HttpSession session = request.getSession();
        session.setAttribute("admindID", loginid);
		
		try {
			// step #1 initialize  the database
			Connection connection = MySQLDatabaseUtils.getConnection();
			
			// step #2 obtain a Statement Object from Connection
			Statement statement = connection.createStatement();
			
			//step #3 execute the query obtain a ResultSet
			ResultSet rs = statement.executeQuery(SELECT_SQL);
			
			boolean match = false;
			//step #4 traverse the ResultSet and obtain the values
			while(rs.next()) {
				if (loginid.equals(rs.getString("loginid")) && password.equals(rs.getString("password"))) {
					match = true;
					break;
				}
				
			}
			if (match) {
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Welcome</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h3> Login Successful</h3>");
				out.println("<p> Welcome " + loginid + "</p>");
				out.println("<form action=\"admin-action\" method=\"post\">");
				out.println("<br/><b> What would you like to do:</b>");
				out.println("<br/>\r\n"
						+ "	<input type=\"radio\" name=\"adminAction\" value=\"List Airlines\"> List Airlines <br/>\r\n"
						+ "	<input type=\"radio\" name=\"adminAction\" value=\"List Places\"> List Places <br/>\r\n"
						+ "	<input type=\"radio\" name=\"adminAction\" value=\"List Flights\"> List Flights <br/>\r\n"
						+ "	<input type=\"radio\" name=\"adminAction\" value=\"Change Password\"> Change Password <br/>");			
				out.println("<br/>");
				out.println("<input type=\"submit\" value=\"Submit\" name=\"btnsubmit\">");
				out.println("</form>");
				out.println("</body>");
				out.println("</html>");
			} else {
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Unauthorized</title>");
				out.println("</head>");
				out.println("<body>");
				out.println("<h3> Login Unsuccesful</h3>");
				out.println("<p> Please go back and try again </p>");
				out.println("</body>");
				out.println("</html>");
			}
			rs.close();
			statement.close();
			connection.close();
			out.println("</table>");
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
	}

}
