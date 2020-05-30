package com.test.Swagger;

import java.io.Serializable;

public class ErrorResponseDto  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public enum TypeEnum {
		ERROR("error"),
		WARN("warn"),
		INVALID("invalid"),
		FATAL("fatal");
		
		private String value;
		
		TypeEnum(String value)
		{
			this.value= value;
		}
		
		@Override
		public String toString() {
			
			return String.valueOf(value);
		}
		
		public static TypeEnum fromValue(String text)
		{
			for(TypeEnum b: TypeEnum.values())
			{
				if(String.valueOf(b.value).equals(text))
				{
					return b;
				}
			}
			return null;
		}
		
		
	}
	
	private TypeEnum type= null;
	
	private String code = null;
	private String details= null;
	
	private String location = null;
	private String moreInfo= null;
	public TypeEnum getType() {
		return type;
	}
	public void setType(TypeEnum type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getMoreInfo() {
		return moreInfo;
	}
	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}
	
	
	

}
