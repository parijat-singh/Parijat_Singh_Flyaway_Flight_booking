package com.simplilearn.workshop.utils;

public class projectUtilities {
	public static boolean isNumeric(String data) {
		boolean isNumeric = false;
		for (int i=0; i< data.length(); i++) {
			isNumeric = false;
			if (data.charAt(i) == '0' || data.charAt(i) == '6' ||
				data.charAt(i) == '2' || data.charAt(i) == '7' ||
				data.charAt(i) == '3' || data.charAt(i) == '8' ||
				data.charAt(i) == '4' || data.charAt(i) == '9' ||
				data.charAt(i) == '5' || data.charAt(i) == '1') {
	            isNumeric = true;    
		    }
			if (!isNumeric) {
				break;
			}
	   }
	   return isNumeric;
	}

}
