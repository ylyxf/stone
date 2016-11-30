package org.siqisource.stone.role.model;

import org.siqisource.stone.runtime.mapper.annotation.Table;

@Table("st_role")			
public class Role {

	/*角色Id*/
	private  java.lang.Integer id;

	/*角色名称*/
	private  java.lang.String name;

	/*是否启用*/
	private  java.lang.Boolean enabled;

	/*序号*/
	private  java.lang.Integer sortNo;

 

	public  java.lang.Integer getId(){
		return this.id;
	}
	 
	public  void setId(java.lang.Integer id){
		this.id = id ;
	}


	public  java.lang.String getName(){
		return this.name;
	}
	 
	public  void setName(java.lang.String name){
		this.name = name ;
	}


	public  java.lang.Boolean getEnabled(){
		return this.enabled;
	}
	 
	public  void setEnabled(java.lang.Boolean enabled){
		this.enabled = enabled ;
	}


	public  java.lang.Integer getSortNo(){
		return this.sortNo;
	}
	 
	public  void setSortNo(java.lang.Integer sortNo){
		this.sortNo = sortNo ;
	}

	 
}
