package com.example.AdminMicroservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
// TODO: Auto-generated Javadoc

/**
 * The Class credentials.
 *
 * @author pushpraj singh pawar
 * @category model
 */
@Document(collection = "credentials")
public class credentials {

	/** The credentials id. */
	@Id
	@NotBlank(message="credentialsId cannot be blank")
	@Size(min = 1,max = 10,message = "credentialsId cannot be more than 10 or less than 1")
	private String credentialsId;
	
	/** The username. */
	@NotBlank(message="username cannot be blank")
	@Size(min = 1,max = 15,message = "username cannot be more than 15 or less than 1")
	private String username;
	
	/** The password. */
	@Size(min = 4,max = 10,message = "password cannot be more than 10 or less than 4")
	private String password;
	
	/** The role. */
	@NotNull(message ="please provide role")
	private String role;

	/**
	 * Instantiates a new credentials.
	 */
	public credentials() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Instantiates a new credentials.
	 *
	 * @param credentialsId the credentials id
	 * @param username the username
	 * @param password the password
	 * @param role the role
	 */
	public credentials(String credentialsId, String username, String password, String role) {
		super();
		this.credentialsId = credentialsId;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	/**
	 * Gets the credentials id.
	 *
	 * @return the credentials id
	 */
	public String getCredentialsId() {
		return credentialsId;
	}

	/**
	 * Sets the credentials id.
	 *
	 * @param credentialsId the new credentials id
	 */
	public void setCredentialsId(String credentialsId) {
		this.credentialsId = credentialsId;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	private String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "credentials [credentialsId=" + credentialsId + ", username=" + username + ", password=" + password
				+ ", role=" + role + "]";
	}

}
