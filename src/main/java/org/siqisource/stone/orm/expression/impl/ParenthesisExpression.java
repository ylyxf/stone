package org.siqisource.stone.orm.expression.impl;

import org.siqisource.stone.orm.expression.SqlCompareExpression;

public class ParenthesisExpression implements SqlCompareExpression {

	private String parenthesis = "";

	public ParenthesisExpression(String parenthesis) {
		this.parenthesis = parenthesis;
	}

	@Override
	public String getMybatisSql(int index) {
		return this.parenthesis;
	}

}
