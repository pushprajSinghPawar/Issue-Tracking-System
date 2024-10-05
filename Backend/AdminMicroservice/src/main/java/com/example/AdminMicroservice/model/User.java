package com.example.AdminMicroservice.model;

import java.util.Arrays;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class User.
 *
 * @author pushpraj singh pawar
 * @category model
 */
public class User {

    /** The userid. */
    private String userid;
    
    /** The name. */
    private String name;
    
    /** The position. */
    private String position;
    
    /** The company name. */
    private String companyName;
    
    /** The password. */
    private String password;
    
    /** The issuesids. */
    private List<String> issuesids;
    
    /** The feedbacksids. */
    private List<String> feedbacksids;
	
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
	 * @param issuesids the issuesids
	 * @param feedbacksids the feedbacksids
	 */
	public User(String userid, String name, String position, String companyName, String password,
			List<String> issuesids, List<String> feedbacksids) {
		super();
		this.userid = userid;
		this.name = name;
		this.position = position;
		this.companyName = companyName;
		this.password = password;
		this.issuesids = issuesids;
		this.feedbacksids = feedbacksids;
		roles = Arrays.asList("USER");
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
		return issuesids;
	}
	
	/**
	 * Sets the issues.
	 *
	 * @param issuesids the new issues
	 */
	public void setIssues(List<String> issuesids) {
		this.issuesids = issuesids;
	}
	
	/**
	 * Gets the feed backs.
	 *
	 * @return the feed backs
	 */
	public List<String> getFeedBacks() {
		return feedbacksids;
	}
	
	/**
	 * Sets the feed backs.
	 *
	 * @param feedbacksids the new feed backs
	 */
	public void setFeedBacks(List<String> feedbacksids) {
		this.feedbacksids = feedbacksids;
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
