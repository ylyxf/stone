package org.siqisource.stone.runtime.mapper.condition.expression;

public class ParenthesisExpression implements CompareExpression {

	private String parenthesis = "";

	public ParenthesisExpression(String parenthesis) {
		this.parenthesis = parenthesis;
	}

	@Override
	public String getMybatisSql(int index) {
		return this.parenthesis;
	}

}
