package org.siqisource.stone.datafilter.model;

import java.util.Date;
			
public class DataFilterItem {


	/*表达式id*/
	private  int id;


	/*前缀*/
	private  String prefixCode;


	/*列编码*/
	private  String columnCode;


	/*操作符*/
	private  String compareSymbol;


	/*第一值*/
	private  String firstValue;


	/*第二值*/
	private  String secondValue;


	/*后缀*/
	private  String suffixCode;


	/*数值类型*/
	private  String dataType;


	/*表达式类型*/
	private  String type;


	/*序号*/
	private  int sortNo;


	/*数据过滤器*/
	private  int dataFilterId;


 


	public  int getId(){
		return this.id;
	}
	 
	public  void setId(int id){
		this.id = id ;
	}



	public  String getPrefixCode(){
		return this.prefixCode;
	}
	 
	public  void setPrefixCode(String prefixCode){
		this.prefixCode = prefixCode ;
	}



	public  String getColumnCode(){
		return this.columnCode;
	}
	 
	public  void setColumnCode(String columnCode){
		this.columnCode = columnCode ;
	}



	public  String getCompareSymbol(){
		return this.compareSymbol;
	}
	 
	public  void setCompareSymbol(String compareSymbol){
		this.compareSymbol = compareSymbol ;
	}



	public  String getFirstValue(){
		return this.firstValue;
	}
	 
	public  void setFirstValue(String firstValue){
		this.firstValue = firstValue ;
	}



	public  String getSecondValue(){
		return this.secondValue;
	}
	 
	public  void setSecondValue(String secondValue){
		this.secondValue = secondValue ;
	}



	public  String getSuffixCode(){
		return this.suffixCode;
	}
	 
	public  void setSuffixCode(String suffixCode){
		this.suffixCode = suffixCode ;
	}



	public  String getDataType(){
		return this.dataType;
	}
	 
	public  void setDataType(String dataType){
		this.dataType = dataType ;
	}



	public  String getType(){
		return this.type;
	}
	 
	public  void setType(String type){
		this.type = type ;
	}



	public  int getSortNo(){
		return this.sortNo;
	}
	 
	public  void setSortNo(int sortNo){
		this.sortNo = sortNo ;
	}



	public  int getDataFilterId(){
		return this.dataFilterId;
	}
	 
	public  void setDataFilterId(int dataFilterId){
		this.dataFilterId = dataFilterId ;
	}


	 
}
