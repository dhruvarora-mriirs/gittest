package com.example.exceptions;

public class ErrorDTO {
	 

	  private int code;

	  private String message;

	  public ErrorDTO(int code, String message) {
	    this.code = code;
	    this.message = message;
	  }
     public ErrorDTO() {}
	  public int getCode() {
	    return code;
	  }

	  public void setCode(int code) {
	    this.code = code;
	  }

	  public String getMessage() {
	    return message;
	  }

	  public void setMessage(String message) {
	    this.message = message;
	  }
}