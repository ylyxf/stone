package org.siqisource.stone.dict.service;

import java.util.List;

import org.siqisource.stone.dict.model.DictItem;

public interface Dictable {
	
	public String getDictName();

	public List<DictItem> getDictItemList();

}
