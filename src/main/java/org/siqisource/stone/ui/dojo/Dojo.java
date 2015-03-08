package org.siqisource.stone.ui.dojo;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.siqisource.stone.orm.SqlKey;
import org.siqisource.stone.orm.expression.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

public class Dojo {

	/**
	 * only support single field
	 * 
	 * @param sortParam
	 * @return
	 */
	public static Order getOrder(String sortParam) {
		if (StringUtils.isBlank(sortParam)) {
			return null;
		}
		String separetor = " ";
		String sortDirection = SqlKey.ASC;
		if (sortParam.indexOf("-") != -1) {
			separetor = "-";
			sortDirection = SqlKey.DESC;
		}

		Order order = new Order();
		String[] params = sortParam.split(separetor);

		order.setDirection(sortDirection);
		order.setField(params[1].replace(")", ""));

		return order;
	}

	public static RowBounds getRowBounds(String rangeParam) {
		rangeParam = rangeParam.replaceAll("items=", "");
		String[] parsed = rangeParam.split("-");
		int begin = new Integer(parsed[0]);
		int end = new Integer(parsed[1]);
		return new RowBounds(begin, end - begin + 1);
	}

	public static <T> HttpEntity<List<T>> getListData(List<T> data,
			RowBounds rowBounds, int totalCount) {
		int firstResult = rowBounds.getOffset();
		int pageCount = rowBounds.getLimit();
		StringBuilder contentRange = new StringBuilder("items " + firstResult
				+ "-");
		if (pageCount == 0) {
			contentRange.append("0");
		} else {
			contentRange.append(firstResult + pageCount - 1);
		}
		contentRange.append("/" + totalCount);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Range", contentRange.toString());
		return new HttpEntity<List<T>>(data, headers);
	}

	public static <T> HttpEntity<List<T>> getListData(List<T> data) {
		int dataSize = data.size();
		return getListData(data, new RowBounds(0, dataSize), dataSize);
	}
}
