package com.example.model;

import java.sql.Date;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.apache.commons.lang3.StringUtils;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "users")

public class DAOUser {

	@Id    
	@NotNull
	@Column(name = "id")
	private long id;
	
	@NotBlank
	@NotEmpty(message = "user name is required")
	@Column(name="user_name",unique = true)
	private String userName;
	
	
	
	
	//@Size(min=8 , max=20)
	@NotBlank
	@NotEmpty(message = "password is required")
	@Column(name="password")

	private String password;

	
	@NotBlank
	@NotEmpty(message = "gender is required")
	@Column(name="gender")
	private String gender;
	
	public DAOUser() {}
	


	public DAOUser(@NotNull long id, @NotBlank @NotEmpty(message = "user name is required") String userName,
			@NotBlank @NotEmpty(message = "password is required") String password,
			@NotBlank @NotEmpty(message = "gender is required") String gender) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.gender = gender;
	}

	

	public DAOUser(@NotBlank @NotEmpty(message = "user name is required") String userName,
			@NotBlank @NotEmpty(message = "password is required") String password,
			@NotBlank @NotEmpty(message = "gender is required") String gender) {
		super();
		this.userName = userName;
		this.password = password;
		this.gender = gender;
	}



	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
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