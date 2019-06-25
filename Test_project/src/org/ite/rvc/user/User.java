package org.ite.rvc.user;

import java.io.InputStream;

public class User {
	private Integer id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String gender;
	private String email;
	// private String photo;
	private InputStream photo;

	private String registrationdate;
	private String usertype;
	private boolean activated;
	private String activate_code;


	public InputStream getPhoto() {
		return photo;
	}

	public void setPhoto(InputStream photo) {
		this.photo = photo;
	}

	// for login
	private boolean valid;
	// for insert
	private boolean executionResult;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	// public Part getPhoto() {
	// return photo;
	// }
	// public void setPhoto(Part photo) {
	// this.photo = photo;
	// }
	public String getRegistrationdate() {
		return registrationdate;
	}

	public void setRegistrationdate(String registrationdate) {
		this.registrationdate = registrationdate;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public boolean isExecutionResult() {
		return executionResult;
	}

	public void setExecutionResult(boolean executionResult) {
		this.executionResult = executionResult;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean newValid) {
		this.valid = newValid;
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	public String getActivate_code() {
		return activate_code;
	}

	public void setActivate_code(String activate_code) {
		this.activate_code = activate_code;
	}

	
}
