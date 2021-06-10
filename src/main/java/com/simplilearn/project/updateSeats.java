package com.simplilearn.project;

import java.sql.*;
import com.simplilearn.workshop.utils.MySQLDatabaseUtils;


public class updateSeats {
	
	public static void updateAvailableSeats(String id, int numOfPassengers) {
		final String UPDATE_SQL_FL= "UPDATE flights "
				+ "SET num_of_seats_available  = num_of_seats_available -"
				+ numOfPassengers + " WHERE id = " + id;
		
		try {
			// step #1 initialize  the database
			Connection connection = MySQLDatabaseUtils.getConnection();
			
			// step #2 obtain a Statement Object from Connection
			Statement statement = connection.createStatement();
			
			//step #3 execute the query obtain a ResultSet
			statement.executeUpdate(UPDATE_SQL_FL);
			statement.close();
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {			
			e.printStackTrace();
		}
	}

}
