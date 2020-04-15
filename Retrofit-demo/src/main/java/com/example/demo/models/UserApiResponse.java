package com.example.demo.models;




public class UserApiResponse {
	
	private User data;

	
	public User getUser() {
		return data;
	}

	public void setUser(User data) {
		this.data = data;
	}
	
	 public String toString() {
	        return "UserApiResponse [data=" + data + "]";
	    }

}
