package org.siqisource.stone.datafilter.service;

import java.util.List;

import org.siqisource.stone.datafilter.mapper.DataFilterMapper;
import org.siqisource.stone.datafilter.model.DataFilter;
import org.siqisource.stone.datafilter.model.DataFilterItem;
import org.siqisource.stone.runtime.exceptions.BusinessException;
import org.siqisource.stone.runtime.mapper.SingleKeyMapper;
import org.siqisource.stone.runtime.mapper.condition.Condition;
import org.siqisource.stone.runtime.mapper.condition.SimpleCondition;
import org.siqisource.stone.runtime.mapper.condition.expression.CompareExpression;
import org.siqisource.stone.runtime.service.AbstractSingleKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataFilterService extends AbstractSingleKeyService<DataFilter,Integer> {

	@Autowired
	DataFilterMapper mapper;

	@Autowired
	DataFilterItemService dataFilterItemService;

	@Override
	protected SingleKeyMapper<DataFilter,Integer> getMapper() {
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
			CompareExpression compareExpression = dataFilterItemService
					.toSqlCompareExpression(dataFilterItem);
			if (compareExpression == null) {
				throw new BusinessException("数据过滤器数据转换错误，dataFilterItem："
						+ dataFilterItem.getId());
			}
			result.addExpression(compareExpression);
		}

		return result;
	}
}
