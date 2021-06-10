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

import com.simplilearn.workshop.utils.MySQLDatabaseUtils;

/**
 * Servlet implementation class chgpwd
 */
@WebServlet("/chgpwd")
public class chgpwd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("text/html");// set MIME type
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Welcome</title>");
		out.println("</head>");
		out.println("<body>");
				
		//Read parameters from chgpw HTML
		String loginid = request.getParameter("loginid");
		String currPassword = request.getParameter("currPassword");
		String newPassword = request.getParameter("newPassword");
		String confirmPassword = request.getParameter("confirmPassword");
		
		final String SELECT_SQL= "SELECT * FROM login where loginid = '" + loginid +"' AND password = '" + currPassword + "'";
		final String UPDATE_SQL= "UPDATE login SET password = '" + newPassword + "', last_update = current_timestamp()\r\n"
				+ "WHERE loginid = '" + loginid +"'";
		try {
			// step #1 initialize  the database
			Connection connection = MySQLDatabaseUtils.getConnection();
			
			// step #2 obtain a Statement Object from Connection
			Statement statement = connection.createStatement();
			
			//step #3 execute the query obtain a ResultSet
			ResultSet rs_select = statement.executeQuery(SELECT_SQL);
			if (rs_select.next()) {
				if (newPassword.equals(confirmPassword)) {
					statement.executeUpdate(UPDATE_SQL);
					out.println("<h3> Success: Password updated Successfully</h3>");
					out.println("<p> Please go back to <a href=\"/pgfsd-flyaway-project/admin.html\">Login Screen</a> and login with you new password </p>");
				} else {
					out.println("<h3> Password Mismatch</h3>");
					out.println("<p> Passowrds don't match </p>");
				}
			} else {
				out.println("<h3> Unauthorized User</h3>");
				out.println("<p> Login ID and/or Password incorrect </p>");
			}
			
			rs_select.close();
			statement.close();
			connection.close();
			
			out.println("</body>");
			out.println("</html>");
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		

	}

}
