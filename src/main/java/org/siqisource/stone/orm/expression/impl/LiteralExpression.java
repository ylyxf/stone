package org.siqisource.stone.orm.expression.impl;

import org.siqisource.stone.orm.expression.SqlCompareExpression;

public class LiteralExpression implements SqlCompareExpression {

	public LiteralExpression(String code) {
		this.code = code;
	}

	private String code;

	@Override
	public String getMybatisSql(int index) {
		return code;
	}

}
