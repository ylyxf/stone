package org.siqisource.stone.group.model;



			
public class Group {

	/*群组id*/
	private  java.lang.Integer id;

	/*父群组id*/
	private  java.lang.Integer parentId;

	/*群组编码*/
	private  java.lang.String code;

	/*群组名称*/
	private  java.lang.String name;

	/*是否叶子节点*/
	private  java.lang.Boolean isLeaf;

	/*序号*/
	private  java.lang.Integer sortNo;

	/*组织类型*/
	private  java.lang.String type;
	
	/*逻辑删除*/
	private  java.lang.Boolean logicDeleted;

 

	public  java.lang.Integer getId(){
		return this.id;
	}
	 
	public  void setId(java.lang.Integer id){
		this.id = id ;
	}


	public  java.lang.Integer getParentId(){
		return this.parentId;
	}
	 
	public  void setParentId(java.lang.Integer parentId){
		this.parentId = parentId ;
	}


	public  java.lang.String getCode(){
		return this.code;
	}
	 
	public  void setCode(java.lang.String code){
		this.code = code ;
	}


	public  java.lang.String getName(){
		return this.name;
	}
	 
	public  void setName(java.lang.String name){
		this.name = name ;
	}


	public  java.lang.Boolean getIsLeaf(){
		return this.isLeaf;
	}
	 
	public  void setIsLeaf(java.lang.Boolean isLeaf){
		this.isLeaf = isLeaf ;
	}


	public  java.lang.Integer getSortNo(){
		return this.sortNo;
	}
	 
	public  void setSortNo(java.lang.Integer sortNo){
		this.sortNo = sortNo ;
	}


	public java.lang.String getType() {
		return type;
	}

	public void setType(java.lang.String type) {
		this.type = type;
	}

	public java.lang.Boolean getLogicDeleted() {
		return logicDeleted;
	}

	public void setLogicDeleted(java.lang.Boolean logicDeleted) {
		this.logicDeleted = logicDeleted;
	}

}
