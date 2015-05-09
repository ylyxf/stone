package org.siqisource.stone.datafilter.service;

import java.util.ArrayList;
import java.util.List;

import org.siqisource.stone.datafilter.mapper.DataFilterItemMapper;
import org.siqisource.stone.datafilter.model.DataFilterItem;
import org.siqisource.stone.datafilter.model.EnumValue;
import org.siqisource.stone.exceptions.BusinessException;
import org.siqisource.stone.orm.MybatisMapper;
import org.siqisource.stone.orm.condition.SimpleCondition;
import org.siqisource.stone.orm.expression.SqlCompareExpression;
import org.siqisource.stone.orm.expression.impl.BetweenExpression;
import org.siqisource.stone.orm.expression.impl.EnumExpression;
import org.siqisource.stone.orm.expression.impl.NoValueExpression;
import org.siqisource.stone.orm.expression.impl.ParenthesisExpression;
import org.siqisource.stone.orm.expression.impl.SingleExpression;
import org.siqisource.stone.service.AbstractService;
import org.siqisource.stone.utils.LiteralValueUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataFilterItemService extends AbstractService<DataFilterItem> {

	@Autowired
	DataFilterItemMapper mapper;

	@Autowired
	EnumValueService enumValueService;

	@Override
	protected MybatisMapper<DataFilterItem> getMapper() {
		return this.mapper;
	}

	public SqlCompareExpression toSqlCompareExpression(
			DataFilterItem dataFilterItem) {
		SqlCompareExpression sqlCompareExpression = null;
		String type = dataFilterItem.getType();
		if ("between".equals(type)) {
			sqlCompareExpression = toBetweenExpression(dataFilterItem);
		} else if ("enum".equals(type)) {
			sqlCompareExpression = toEnumExpression(dataFilterItem);
		} else if ("novalue".equals(type)) {
			sqlCompareExpression = toNoValueExpression(dataFilterItem);
		} else if ("parenthesis".equals(type)) {
			sqlCompareExpression = toParenthesisExpression(dataFilterItem);
		} else if ("single".equals(type)) {
			sqlCompareExpression = toSingleExpression(dataFilterItem);
		}

		return sqlCompareExpression;
	}

	private BetweenExpression toBetweenExpression(DataFilterItem dataFilterItem) {
		String dataType = dataFilterItem.getDataType();
		String firstValue = dataFilterItem.getFirstValue();
		Object beginValue = LiteralValueUtil.parse(firstValue, dataType);
		String secondValue = dataFilterItem.getSecondValue();
		Object endValue = LiteralValueUtil.parse(secondValue, dataType);

		if (beginValue == null || endValue == null) {
			throw new BusinessException("数据过滤器数据转换错误，dataFilterItem："
					+ dataFilterItem.getId());
		}
		String prefixCode = dataFilterItem.getPrefixCode();
		String columnCode = dataFilterItem.getColumnCode();
		String suffixCode = dataFilterItem.getSuffixCode();
		BetweenExpression betweenExpression = new BetweenExpression(prefixCode,
				columnCode, beginValue, endValue, suffixCode);
		return betweenExpression;
	}

	private EnumExpression toEnumExpression(DataFilterItem dataFilterItem) {
		String dataType = dataFilterItem.getDataType();
		SimpleCondition condition = new SimpleCondition();
		condition.andEqual("dataFilterItemId", dataFilterItem.getId());
		List<EnumValue> enumValueList = enumValueService.list(condition);
		List<Object> valueList = new ArrayList<Object>();
		for (EnumValue enumValue : enumValueList) {
			Object value = LiteralValueUtil.parse(enumValue.getDataValue(),
					dataType);
			if (dataType == null) {
				throw new BusinessException("数据过滤器数据转换错误，dataFilterItem："
						+ dataFilterItem.getId());
			}
			valueList.add(value);
		}
		if (valueList.size() <= 0) {
			throw new BusinessException("数据过滤器数据转换错误，dataFilterItem："
					+ dataFilterItem.getId());
		}

		String prefixCode = dataFilterItem.getPrefixCode();
		String columnCode = dataFilterItem.getColumnCode();
		String compareSymbol = dataFilterItem.getCompareSymbol();
		String suffixCode = dataFilterItem.getSuffixCode();

		EnumExpression enumExpression = new EnumExpression(prefixCode,
				columnCode, compareSymbol, valueList, suffixCode);
		return enumExpression;

	}

	private NoValueExpression toNoValueExpression(DataFilterItem dataFilterItem) {
		String prefixCode = dataFilterItem.getPrefixCode();
		String columnCode = dataFilterItem.getColumnCode();
		String compareSymbol = dataFilterItem.getCompareSymbol();
		String suffixCode = dataFilterItem.getSuffixCode();
		NoValueExpression noValueExpression = new NoValueExpression(prefixCode,
				columnCode, compareSymbol, suffixCode);
		return noValueExpression;
	}

	private ParenthesisExpression toParenthesisExpression(
			DataFilterItem dataFilterItem) {
		String compareSymbol = dataFilterItem.getCompareSymbol();
		ParenthesisExpression parenthesisExpression = new ParenthesisExpression(
				compareSymbol);
		return parenthesisExpression;
	}

	private SingleExpression toSingleExpression(DataFilterItem dataFilterItem) {
		String dataType = dataFilterItem.getDataType();
		String firstValue = dataFilterItem.getFirstValue();
		Object value = LiteralValueUtil.parse(firstValue, dataType);

		if (value == null) {
			throw new BusinessException("数据过滤器数据转换错误，dataFilterItem："
					+ dataFilterItem.getId());
		}
		String prefixCode = dataFilterItem.getPrefixCode();
		String columnCode = dataFilterItem.getColumnCode();
		String compareSymbol = dataFilterItem.getCompareSymbol();
		String suffixCode = dataFilterItem.getSuffixCode();
		SingleExpression singleExpression = new SingleExpression(prefixCode,
				columnCode, compareSymbol, value, suffixCode);
		return singleExpression;
	}

}
