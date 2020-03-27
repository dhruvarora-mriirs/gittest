package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="users")
public class Users {
	
	@Id
	@Column(name="id")
	long id;
	
	@Column(name="user_name")
	String username;
	
	@Column(name="password")
	String password;
	
	@Column(name="gender")
	String gender;
	
	public Users() {}

	public Users(String username, String password,String gender) {
		super();
		this.username = username;
		this.password = password;
		this.gender=gender;
	}
	
	

	public Users(long id, String username, String password,String gender) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.gender=gender;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
		
}
