package org.siqisource.stone.user.model;

			
public class GroupUser {

	/*id*/
	private  java.lang.Integer id;

	/*用户Id*/
	private  java.lang.Integer userId;

	/*群组id*/
	private  java.lang.Integer groupId;
	
	/*序号*/
	private  java.lang.Integer sortNo;

	/*是否默认群组*/
	private  java.lang.Boolean isDefault;


	public  java.lang.Integer getId(){
		return this.id;
	}
	 
	public  void setId(java.lang.Integer id){
		this.id = id ;
	}


	public  java.lang.Integer getUserId(){
		return this.userId;
	}
	 
	public  void setUserId(java.lang.Integer userId){
		this.userId = userId ;
	}


	public  java.lang.Integer getGroupId(){
		return this.groupId;
	}
	 
	public  void setGroupId(java.lang.Integer groupId){
		this.groupId = groupId ;
	}


	public  java.lang.Boolean getIsDefault(){
		return this.isDefault;
	}
	 
	public  void setIsDefault(java.lang.Boolean isDefault){
		this.isDefault = isDefault ;
	}

	public java.lang.Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(java.lang.Integer sortNo) {
		this.sortNo = sortNo;
	}
	
}