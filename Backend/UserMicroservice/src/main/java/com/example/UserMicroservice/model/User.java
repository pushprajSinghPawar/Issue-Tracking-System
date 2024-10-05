package com.example.UserMicroservice.model;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
// TODO: Auto-generated Javadoc

/**
 * The Class User.
 */
@Data
@Document(collection = "User")
public class User {

    /** The userid. */
    @Id
    @NotBlank(message = "USERID NOT BE FILLED BLANK")
    private String userid;

    /** The name. */
    @NotBlank(message = "USERNAME NOT BE FILLED BLANK")
    @Size(max = 50, message = "USERNAME CANNOT BE MORE THAN 50")
    private String name;

    /** The position. */
    @Size(max = 50, message = "POSITION CANNOT BE MORE THAN 50")
	@NotBlank(message = "position Cannot be left blank")
    private String position;

    /** The company name. */
    @Size(max = 50, message = "POSITION CANNOT BE MORE THAN 50")
	@NotBlank(message = "company name cannot be left blank")
    private String companyName;

    /** The password. */
    @NotBlank(message = "password NOT BE FILLED BLANK")
    private String password;
    
    /** The issues. */
    private List<String> issues;
    
    /** The feedbacks. */
    private List<String> feedbacks;
	
	/** The roles. */
	private List<String> roles;
	
	/**
	 * Instantiates a new user.
	 *
	 * @param userid the userid
	 * @param name the name
	 * @param position the position
	 * @param companyName the company name
	 * @param password the password
	 * @param issues the issues
	 * @param feedbacks the feedbacks
	 */
	public User(String userid, String name, String position, String companyName, String password,
			List<String> issues, List<String> feedbacks) {
		super();
		this.userid = userid;
		this.name = name;
		this.position = position;
		this.companyName = companyName;
		this.password = password;
		this.issues = issues;
		this.feedbacks = feedbacks;
		this.roles=Arrays.asList("User");
	}
	
	/**
	 * Gets the userid.
	 *
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}
	
	/**
	 * Sets the userid.
	 *
	 * @param userid the new userid
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}
	
	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	
	/**
	 * Gets the company name.
	 *
	 * @return the company name
	 */
	public String getCompanyName() {
		return companyName;
	}
	
	/**
	 * Sets the company name.
	 *
	 * @param companyName the new company name
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	 * Gets the issues.
	 *
	 * @return the issues
	 */
	public List<String> getIssues() {
		return issues;
	}
	
	/**
	 * Sets the issues.
	 *
	 * @param issues the new issues
	 */
	public void setIssues(List<String> issues) {
		this.issues = issues;
	}
	
	/**
	 * Gets the feed backs.
	 *
	 * @return the feed backs
	 */
	public List<String> getFeedBacks() {
		return feedbacks;
	}
	
	/**
	 * Sets the feed backs.
	 *
	 * @param feedBacks the new feed backs
	 */
	public void setFeedBacks(List<String> feedBacks) {
		this.feedbacks = feedBacks;
	}
	
	/**
	 * Gets the roles.
	 *
	 * @return the roles
	 */
	public List<String> getRoles() {
		return roles;
	}
	
	/**
	 * Sets the roles.
	 *
	 * @param roles the new roles
	 */
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
	
    
}
