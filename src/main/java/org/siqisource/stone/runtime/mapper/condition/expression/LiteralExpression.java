package org.siqisource.stone.runtime.mapper.condition.expression;

public class LiteralExpression implements CompareExpression {

	public LiteralExpression(String code) {
		this.code = code;
	}

	private String code;

	@Override
	public String getMybatisSql(int index) {
		return code;
	}

}
