package org.siqisource.stone.orm.expression;

import java.util.ArrayList;
import java.util.List;

import org.siqisource.stone.orm.SqlKey;
import org.siqisource.stone.utils.NameConverter;

public class OrderByExpression {

	private List<Order> orders = new ArrayList<Order>(1);

	public void orderAsc(String propertyNames) {
		order(propertyNames, SqlKey.ASC);
	}

	public void orderDesc(String propertyNames) {
		order(propertyNames, SqlKey.DESC);
	}

	@Override
	public String toString() {
		StringBuffer sbSql = new StringBuffer(64);
		sbSql.append(SqlKey.ORDER_BY);
		for (int i = 0; i < orders.size(); i++) {
			Order order = orders.get(i);
			if (i != 0) {
				sbSql.append(" , ");
			}

			sbSql.append(order.getField());
			sbSql.append(" ");
			sbSql.append(order.getDirection());
			sbSql.append(" ");
		}
		return sbSql.toString();
	}

	private void order(String propertyNames, String direction) {
		String[] propertyNameArray = propertyNames.split(",");
		for (String property : propertyNameArray) {
			Order order = new Order();
			order.setField(NameConverter.propertyToColumnWithTableAlias(property));
			order.setDirection(direction);
			orders.add(order);
		}
	}

	public OrderByExpression copy() {
		OrderByExpression orderBy = new OrderByExpression();
		for (Order order : orders) {
			orderBy.orders.add(order);
		}
		return orderBy;
	}

}
