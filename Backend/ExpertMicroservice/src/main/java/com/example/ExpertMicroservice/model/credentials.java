package com.example.ExpertMicroservice.model;

// TODO: Auto-generated Javadoc
/**
 * The Class credentials.
 */
public class credentials {

	/** The credentials id. */
	private String credentialsId;
	
	/** The username. */
	private String username;
	
	/** The password. */
	private String password;
	
	/** The role. */
	private String role;

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
	public String getPassword() {
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

}
