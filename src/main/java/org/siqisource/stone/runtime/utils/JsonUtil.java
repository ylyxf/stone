package org.siqisource.stone.runtime.utils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.siqisource.stone.runtime.exceptions.BusinessException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	private static ObjectMapper objectMapper = new ObjectMapper();

	public static String toJson(Object object) {
		String returnValue = "";
		try {
			returnValue = objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnValue;
	}

	public static String clearString(String jsonString) {
		if (jsonString != null) {
			Pattern p = Pattern.compile("\t|\r|\n");
			Matcher m = p.matcher(jsonString);
			jsonString = m.replaceAll("");
			return jsonString.replaceAll("\"", "'");
		} else {
			return "";
		}
	}

	public static Map<String, String> stringToMap(String jsonString) {
		// convert JSON string to Map
		Map<String, String> result;
		try {
			result = objectMapper.readValue(jsonString,
					new TypeReference<Map<String, String>>() {
					});
		} catch (Exception e) {
			 throw new BusinessException("解析json时出错");
		}  
		return result;
	}

}
