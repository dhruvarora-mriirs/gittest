package com.example.exceptions;

public class CustomException extends RuntimeException{

	
	ErrorCode code ;
	String message;
	public ErrorCode getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	public void setCode(ErrorCode code) {
		this.code = code;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public CustomException() {}
	public CustomException(ErrorCode code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public CustomException(ErrorCode code) {
	       this.code=code;}
	
}
