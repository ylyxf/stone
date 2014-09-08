package org.siqisource.stone.orm;

import java.util.Arrays;
import java.util.List;

import org.siqisource.stone.orm.expression.SqlCompareExpression;
import org.siqisource.stone.orm.expression.impl.BetweenExpression;
import org.siqisource.stone.orm.expression.impl.EnumExpression;
import org.siqisource.stone.orm.expression.impl.NoValueExpression;
import org.siqisource.stone.orm.expression.impl.SingleExpression;
import org.siqisource.stone.utils.NameConverter;

public class SqlBuilder {

	public static SqlCompareExpression singleValue(String prefix,
			String propertyName, String compareSymbol, Object value,
			String suffix) {
		String column = NameConverter
				.propertyToColumnWithTableAlias(propertyName);
		return new SingleExpression(prefix, column, compareSymbol, value,
				suffix);
	}

	public static SqlCompareExpression noValue(String prefix,
			String propertyName, String compareSymbol, String suffix) {
		String column = NameConverter
				.propertyToColumnWithTableAlias(propertyName);
		return new NoValueExpression(prefix, column, compareSymbol, suffix);
	}

	public static SqlCompareExpression betweenValue(String prefix,
			String propertyName, Object begin, Object end, String suffix) {
		String column = NameConverter
				.propertyToColumnWithTableAlias(propertyName);
		return new BetweenExpression(prefix, column, begin, end, suffix);
	}

	public static SqlCompareExpression listValue(String prefix,
			String propertyName, String compareSymbol, List<?> value,
			String suffix) {
		String column = NameConverter
				.propertyToColumnWithTableAlias(propertyName);
		return new EnumExpression(prefix, column, compareSymbol, value, suffix);
	}

	public static SqlCompareExpression listValue(String prefix,
			String propertyName, String compareSymbol, String values,
			String suffix) {
		String column = NameConverter
				.propertyToColumnWithTableAlias(propertyName);
		return new EnumExpression(prefix, column, compareSymbol,
				Arrays.asList((Object[]) values.split(",")), suffix);
	}

}
