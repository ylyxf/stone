package org.siqisource.stone.ui.easyui;

public class TreeNode {

	public static final String STATA_CLOSE = "closed";

	public static final String STATA_OPEN = "open";

	/**
	 * An identity value bind to the node.
	 */
	private String id;

	private String parentId;

	/**
	 * Text to be showed.
	 */
	private String text = "";

	/**
	 * The css class to display icon.
	 */
	private String iconCls = "";

	private String checked = "";

	/** The node state, 'open' or 'closed'. */
	private String state = "";

	/**
	 * Custom attributes bind to the node.
	 */
	private Object attributes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
}
