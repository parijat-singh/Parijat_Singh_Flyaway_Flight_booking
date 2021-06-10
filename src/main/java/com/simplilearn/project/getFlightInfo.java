package com.simplilearn.project;

import java.sql.*;
import com.simplilearn.workshop.utils.MySQLDatabaseUtils;


public class getFlightInfo {
	
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
	
	public static String[] getFlightDetails(String id) {
		final String SELECT_SQL_FL= "SELECT * FROM flights where id = " +id;
		String[] flightDetails = new String[10];
		try {
			// step #1 initialize  the database
			Connection connection = MySQLDatabaseUtils.getConnection();
			
			// step #2 obtain a Statement Object from Connection
			Statement statement = connection.createStatement();
			
			//step #3 execute the query obtain a ResultSet
			ResultSet rs = statement.executeQuery(SELECT_SQL_FL);
			rs.next();
			flightDetails[0] = rs.getString("flight_num");
			flightDetails[1] = rs.getString("airline_code");
			flightDetails[2] = rs.getString("from_airport");
			flightDetails[3] = rs.getString("to_airport");
			flightDetails[4] = rs.getString("start_datetime");
			flightDetails[5] = rs.getString("end_datetime");
			flightDetails[6] = rs.getString("price");
			rs.close();
			statement.close();
			connection.close();
			return flightDetails;
		} catch (ClassNotFoundException | SQLException e) {			
			e.printStackTrace();
		}
		return flightDetails;
	}

}
