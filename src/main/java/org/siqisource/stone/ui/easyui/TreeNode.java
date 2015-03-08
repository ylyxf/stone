package org.siqisource.stone.ui.easyui;

import java.util.Map;

public interface TreeNode {

	public static final String STATA_CLOSE = "closed";

	public static final String STATA_OPEN = "open";

	public int getId();

	public String getText();

	public String getState();

	public Boolean getChecked();

	public Map<String, String> getAttributes();

}
