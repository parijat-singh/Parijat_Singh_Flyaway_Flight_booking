package com.simplilearn.project;

import java.sql.*;
import com.simplilearn.workshop.utils.MySQLDatabaseUtils;


public class getFlightPrice {
	
	public static float getPrice(String id) {
		final String SELECT_SQL_FL= "SELECT * FROM flights where id = " +id;
		float flightPrice = 0;
		try {
			// step #1 initialize  the database
			Connection connection = MySQLDatabaseUtils.getConnection();
			
			// step #2 obtain a Statement Object from Connection
			Statement statement = connection.createStatement();
			
			//step #3 execute the query obtain a ResultSet
			ResultSet rs = statement.executeQuery(SELECT_SQL_FL);
			rs.next();
			flightPrice = rs.getFloat("price");
			rs.close();
			statement.close();
			connection.close();
			return flightPrice;
		} catch (ClassNotFoundException | SQLException e) {			
			e.printStackTrace();
		}
		return flightPrice;
	}

}
