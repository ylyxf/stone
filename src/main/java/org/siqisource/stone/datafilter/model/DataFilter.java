package org.siqisource.stone.datafilter.model;

			
public class DataFilter {


	/*数据过滤器*/
	private  int id;


	/*名称*/
	private  String name;


	/*备注*/
	private  String remark;


 


	public  int getId(){
		return this.id;
	}
	 
	public  void setId(int id){
		this.id = id ;
	}



	public  String getName(){
		return this.name;
	}
	 
	public  void setName(String name){
		this.name = name ;
	}



	public  String getRemark(){
		return this.remark;
	}
	 
	public  void setRemark(String remark){
		this.remark = remark ;
	}


	 
}
