package org.siqisource.stone.dict.model;

import org.siqisource.stone.runtime.mapper.annotation.Table;

@Table("st_dict")			
public class Dict {

	/**/
	private  java.lang.String code;

	/**/
	private  java.lang.String name;

	/**/
	private  java.lang.Integer sortNo;

	/**/
	private  java.lang.String remark;

 

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


	public  java.lang.Integer getSortNo(){
		return this.sortNo;
	}
	 
	public  void setSortNo(java.lang.Integer sortNo){
		this.sortNo = sortNo ;
	}


	public  java.lang.String getRemark(){
		return this.remark;
	}
	 
	public  void setRemark(java.lang.String remark){
		this.remark = remark ;
	}
	 
}