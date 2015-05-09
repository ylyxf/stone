package org.siqisource.stone.datafilter.service;

import java.util.List;

import org.siqisource.stone.datafilter.mapper.DataFilterMapper;
import org.siqisource.stone.datafilter.model.DataFilter;
import org.siqisource.stone.datafilter.model.DataFilterItem;
import org.siqisource.stone.exceptions.BusinessException;
import org.siqisource.stone.orm.MybatisMapper;
import org.siqisource.stone.orm.condition.Condition;
import org.siqisource.stone.orm.condition.SimpleCondition;
import org.siqisource.stone.orm.expression.SqlCompareExpression;
import org.siqisource.stone.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataFilterService extends AbstractService<DataFilter> {

	@Autowired
	DataFilterMapper mapper;

	@Autowired
	DataFilterItemService dataFilterItemService;

	@Override
	protected MybatisMapper<DataFilter> getMapper() {
		return this.mapper;
	}

	public Condition readAsConditon(Integer dataFilterId) {
		SimpleCondition result = new SimpleCondition();

		SimpleCondition queryCondition = new SimpleCondition();
		queryCondition.andEqual("dataFilterId", dataFilterId);
		queryCondition.orderAsc("sortNo");
		List<DataFilterItem> dataFilterItmeList = this.dataFilterItemService
				.list(queryCondition);

		for (DataFilterItem dataFilterItem : dataFilterItmeList) {
			SqlCompareExpression sqlCompareExpression = dataFilterItemService
					.toSqlCompareExpression(dataFilterItem);
			if (sqlCompareExpression == null) {
				throw new BusinessException("数据过滤器数据转换错误，dataFilterItem："
						+ dataFilterItem.getId());
			}
			result.addExpression(sqlCompareExpression);
		}

		return result;
	}
}
