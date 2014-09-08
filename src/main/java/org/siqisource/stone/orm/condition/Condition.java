package org.siqisource.stone.orm.condition;

import java.util.ArrayList;
import java.util.List;

import org.siqisource.stone.orm.SqlKey;
import org.siqisource.stone.orm.expression.Order;
import org.siqisource.stone.orm.expression.OrderByExpression;
import org.siqisource.stone.orm.expression.SqlCompareExpression;

public class Condition {

	/** expressions */
	private List<SqlCompareExpression> expressions = null;

	/** orderBy */
	private OrderByExpression orderBy = null;

	public Condition() {

	}

	public void addExpression(SqlCompareExpression expression) {
		if (expressions == null) {
			expressions = new ArrayList<SqlCompareExpression>();
		}
		expressions.add(expression);
	}
	
	public String getComboedExpressions() {
		StringBuffer sql = new StringBuffer();
		if (expressions != null) {
			if (expressions.size() > 0) {
				for (int i = 0, iSize = expressions.size(); i < iSize; i++) {
					SqlCompareExpression expression = expressions.get(i);
					sql.append(expression.getMybatisSql(i));
				}
			}
		}

		return sql.toString();
	}

	public Condition copy() {
		Condition conditionList = new Condition();
		conditionList.orderBy = this.orderBy.copy();
		conditionList.expressions = new ArrayList<SqlCompareExpression>();
		for (SqlCompareExpression expression : this.expressions) {
			conditionList.expressions.add(expression);// fleet copy
		}
		return conditionList;
	}

	public void order(Order order) {
		if (order != null) {
			this.order(order.getField(), order.getDirection());
		}
	}

	public void orderAsc(String propertiesNames) {
		if (this.orderBy == null) {
			this.orderBy = new OrderByExpression();
		}
		orderBy.orderAsc(propertiesNames);
	}

	public void orderDesc(String propertiesNames) {
		if (this.orderBy == null) {
			this.orderBy = new OrderByExpression();
		}
		orderBy.orderDesc(propertiesNames);
	}

	public void order(String propertiesNames, String direction) {
		if (SqlKey.ASC.equals(direction)) {
			this.orderAsc(propertiesNames);
		}
		if (SqlKey.DESC.equals(direction)) {
			this.orderDesc(propertiesNames);
		}
	}

	public OrderByExpression getOrderBy() {
		return orderBy;
	}

	public List<SqlCompareExpression> getExpressions() {
		return expressions;
	}
}
