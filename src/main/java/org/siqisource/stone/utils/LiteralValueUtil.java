package org.siqisource.stone.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class LiteralValueUtil {

	public static final String STRING = "string";

	public static final String INT = "int";

	public static final String FLOAT = "float";

	public static final String DATE = "date";// 2013-01-06

	public static final String TIME = "time";// 23:23:23

	public static final String DATE_TIME = "datetime";

	public static Object parse(String literalValue, String type) {
		if (type.equals(STRING)) {
			return literalValue;
		} else if (type.equals(INT)) {
			return Integer.parseInt(literalValue);
		} else if (type.equals(FLOAT)) {
			return Float.parseFloat(literalValue);
		} else if (type.equals(DATE)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				return sdf.parse(literalValue);
			} catch (ParseException e) {
				return null;
			}
		} else if (type.equals(TIME)) {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			try {
				return sdf.parse(literalValue);
			} catch (ParseException e) {
				return null;
			}
		} else if (type.equals(DATE_TIME)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(literalValue);
			} catch (ParseException e) {
				return null;
			}
		}
		return null;

	}
}
