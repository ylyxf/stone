package org.siqisource.stone.datafilter.model;

import java.util.Date;
			
public class EnumValue {


	/*null*/
	private  int id;


	/*表达式id*/
	private  int dataFilterItemId;


	/*null*/
	private  String dataValue;


 


	public  int getId(){
		return this.id;
	}
	 
	public  void setId(int id){
		this.id = id ;
	}



	public  int getDataFilterItemId(){
		return this.dataFilterItemId;
	}
	 
	public  void setDataFilterItemId(int dataFilterItemId){
		this.dataFilterItemId = dataFilterItemId ;
	}



	public  String getDataValue(){
		return this.dataValue;
	}
	 
	public  void setDataValue(String dataValue){
		this.dataValue = dataValue ;
	}


	 
}
