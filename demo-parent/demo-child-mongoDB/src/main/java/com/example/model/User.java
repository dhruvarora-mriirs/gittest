package com.example.model;

import java.util.Date;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;




@Document("User")
public class User {

	@Id
	private long id;

	
	@Indexed(name="userName")
	@NotBlank
	@NotEmpty(message = "user name is required")
	private String userName;
	

	@NotBlank
	@NotEmpty(message = "gender is required")
	private String gender;
	
	
	
	private Date dob;
	
	@NotBlank
	@NotEmpty(message = "phone number is required")
	private String phone;
	
	@Email(message = "email should be valid")
	@NotBlank
	@NotEmpty(message = "Email is required")
	private String emailId;
	
	
	public User() {}
	public User(@NotBlank @NotEmpty(message = "user name is required") String userName,
			@NotBlank @NotEmpty(message = "gender is required") String gender,
			@NotEmpty(message = "Date of birth is required") Date dob,
			@NotBlank @NotEmpty(message = "phone number is required") String phone,
			@Email(message = "email should be valid") @NotBlank @NotEmpty(message = "Email is required") String emailId) {
		super();
		this.userName = userName;
		this.gender = gender;
		this.dob = dob;
		this.phone = phone;
		this.emailId = emailId;
	}


	public User(long id, @NotBlank @NotEmpty(message = "user name is required") String userName,
			@NotBlank @NotEmpty(message = "gender is required") String gender,
			@NotEmpty(message = "Date of birth is required") Date dob,
			@NotBlank @NotEmpty(message = "phone number is required") String phone,
			@Email(message = "email should be valid") @NotBlank @NotEmpty(message = "Email is required") String emailId) {
		super();
		this.id = id;
		this.userName = userName;
		this.gender = gender;
		this.dob = dob;
		this.phone = phone;
		this.emailId = emailId;
	}

	public String toString() {
		
		return "userName :"+userName +" gender :"+gender+" dob :"+dob+" phone :"+phone+" emailId :"+emailId;
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


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public Date getDob() {
		return dob;
	}


	public void setDob(Date dob) {
		this.dob = dob;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public static class UserBuilder{
		
		private long id;
		private String userName;
		private String gender;
		private Date dob;
		private String phone;
		private String emailId;
		

		public UserBuilder() {

		};

		public UserBuilder userId(long id)
		{
			this.id=id;
			return this;
		}
		public UserBuilder userName(String userName)
		{
			this.userName=userName;
			return this;
		}
		
		
	
		public UserBuilder phone(String phone) {
			this.phone = phone;
			return this;

		}

		public UserBuilder emailId(String emailId) {
			this.emailId = emailId;
			return this;

		}

	

		public UserBuilder gender(String gender) {
			this.gender = gender;
			return this;

		}

		public UserBuilder dob(Date dob) {
			this.dob = dob;
			return this;

		}

		public User createUser() {
			return new User(userName,gender,dob,phone,emailId);

		}

		static public User createUser(User requestuser,User dbUser) {
			return new User(dbUser.id, 
					StringUtils.defaultString(requestuser.userName, dbUser.userName),
				
					StringUtils.defaultString(requestuser.gender, dbUser.gender),
					requestuser.dob,
					StringUtils.defaultString(requestuser.phone, dbUser.phone),
					StringUtils.defaultString(requestuser.emailId, dbUser.emailId)
				     
					);

		}

	}
		
		
		
		
	}
	


