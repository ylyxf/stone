package org.siqisource.stone.utils;

import com.fasterxml.jackson.databind.ObjectMapper;



public class JsonUtil {
	
	private static ObjectMapper objectMapper = new ObjectMapper();

	public static String toJson(Object object) {
		String returnValue = "";
		try {
			returnValue =  objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return returnValue;
	}


}
