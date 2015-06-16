package org.siqisource.stone.role.model;

			
public class RoleOperation {

	/*主键*/
	private  java.lang.Integer id;

	/*角色Id*/
	private  java.lang.Integer roleId;

	/*操作代码*/
	private  java.lang.String operationCode;

	/*数据集合Id*/
	private  java.lang.Integer dataFilterId;

 

	public  java.lang.Integer getId(){
		return this.id;
	}
	 
	public  void setId(java.lang.Integer id){
		this.id = id ;
	}


	public  java.lang.Integer getRoleId(){
		return this.roleId;
	}
	 
	public  void setRoleId(java.lang.Integer roleId){
		this.roleId = roleId ;
	}


	public  java.lang.String getOperationCode(){
		return this.operationCode;
	}
	 
	public  void setOperationCode(java.lang.String operationCode){
		this.operationCode = operationCode ;
	}


	public  java.lang.Integer getDataFilterId(){
		return this.dataFilterId;
	}
	 
	public  void setDataFilterId(java.lang.Integer dataFilterId){
		this.dataFilterId = dataFilterId ;
	}

	 
}
