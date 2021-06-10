package com.simplilearn.project;

import java.sql.*;

import com.simplilearn.workshop.utils.MySQLDatabaseUtils;


public class makeFinalReservation {
	
	public static String insertReservation(String flightId, int numOfPassengers, String payerName, String payerZip, String ccNum, String expiration, float totalAmount, String[] passengerName, String[] passengerGender, String[] passengerDob) {
		
		String passenger1Name = new String();
		String passenger1Gender = new String();
		String passenger1Dob = new String();
		String passenger2Name = new String();
		String passenger2Gender = new String();
		String passenger2Dob = new String();
		String passenger3Name = new String();
		String passenger3Gender = new String();
		String passenger3Dob = new String();
		String passenger4Name = new String();
		String passenger4Gender = new String();
		String passenger4Dob = new String();
		
		if (numOfPassengers >= 1) {
			passenger1Name = passengerName[0];
			passenger1Gender = passengerGender[0];
			passenger1Dob = passengerDob[0];	
		}
		if (numOfPassengers >= 2) {
			passenger2Name = passengerName[1];
			passenger2Gender = passengerGender[1];
			passenger2Dob = passengerDob[1];	
		}
		if (numOfPassengers >= 3) {
			passenger3Name = passengerName[2];
			passenger3Gender = passengerGender[2];
			passenger3Dob = passengerDob[2];	
		}
		if (numOfPassengers >= 4) {
			passenger4Name = passengerName[3];
			passenger4Gender = passengerGender[3];
			passenger4Dob = passengerDob[3];	
		}
		
		final String INSERT_SQL_FL= "INSERT INTO bookings "
				+ "(flight_id, num_of_seats, payer_name, payer_zip, creditcard_num,"
				+ " expiration, total_payment_amount,"
				+ " passenger1_name, passenger1_gender, passenger1_dob, "
				+ " passenger2_name, passenger2_gender, passenger2_dob, "
				+ " passenger3_name, passenger3_gender, passenger3_dob, "
				+ " passenger4_name, passenger4_gender, passenger4_dob, last_updated) "
				+ " VALUES ('" + flightId + "', '" + numOfPassengers + "', '" + payerName + "',"
						+ " '" + payerZip + "', '" + ccNum + "', '" + expiration + "', '" + totalAmount + "', "
				+ " '" + passenger1Name + "', '" + passenger1Gender + "', '" + passenger1Dob + "', "
				+ " '" + passenger2Name + "', '" + passenger2Gender + "', '" + passenger2Dob + "', "
				+ " '" + passenger3Name + "', '" + passenger3Gender + "', '" + passenger3Dob + "', "
				+ " '" + passenger4Name + "', '" + passenger4Gender + "', '" + passenger4Dob + "', "
				+ " current_timestamp)";
		final String SELECT_SQL_FL= "Select max(id) from bookings; ";
		int reservationId = 0;
		String confirmationiD = new String();
		try {
			// step #1 initialize  the database
			Connection connection = MySQLDatabaseUtils.getConnection();
			
			// step #2 obtain a Statement Object from Connection
			Statement statement = connection.createStatement();
			
			//step #3 execute the query obtain a ResultSet
			statement.executeUpdate(INSERT_SQL_FL);
			statement.close();
			
			Statement statement1 = connection.createStatement();
			ResultSet rs = statement1.executeQuery(SELECT_SQL_FL);
			rs.next();
			reservationId = rs.getInt("max(id)");
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {			
			e.printStackTrace();
		}
		return "flyaway" + reservationId;
	}

}
