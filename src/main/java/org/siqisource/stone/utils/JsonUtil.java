package org.siqisource.stone.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	public static String clearString(String jsonString){
		if(jsonString != null){
			Pattern p = Pattern.compile("\t|\r|\n");
			Matcher m = p.matcher(jsonString);
			jsonString = m.replaceAll("");
			return jsonString.replaceAll("\"", "'");
		}else{
			return "";
		}
	}


}
