package org.siqisource.stone.runtime.utils;

public class NameConverter {

	public static String firstLetterUpper(String string) {
		return Character.toUpperCase(string.charAt(0)) + string.substring(1);
	}

	public static String firstLetterLower(String string) {
		return Character.toLowerCase(string.charAt(0)) + string.substring(1);
	}

	public static String propertyToColumn(String property) {
		if (null == property) {
			return "";
		}
		char[] chars = property.toCharArray();
		StringBuffer field = new StringBuffer();
		for (char c : chars) {
			if (Character.isUpperCase(c)) {
				field.append("_");
			}
			field.append(Character.toLowerCase(c));
		}
		return field.toString().toLowerCase();
	}

	public static String columnToProperty(String column) {
		String[] words = column.toLowerCase().split("_");
		StringBuffer property = new StringBuffer();

		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			if (i == 0) {
				property.append(word);
			} else {
				property.append(Character.toUpperCase(word.charAt(0)));
				property.append(word.substring(1));
			}
		}
		return property.toString();
	}

	/**
	 * if className.propertyName it will ben classname.property_name
	 * 
	 * @param property
	 * @return
	 */
	public static String propertyToColumnWithTableAlias(String property) {
		String tableAlias = "";
		if (property.indexOf(".") != -1) {
			String[] parts = property.split("[.]");
			tableAlias = parts[0].toLowerCase() + ".";
			property = parts[1];
		}

		return " " + tableAlias + NameConverter.propertyToColumn(property)
				+ " ";
	}

}
