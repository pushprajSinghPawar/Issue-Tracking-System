package com.security.model;

import java.util.List;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// TODO: Auto-generated Javadoc
/**
 * The Class Expert.
 */
public class Expert {
	
	/** The expertid. */
	private String expertid;
	
	/** The name. */
	@NotBlank(message = "Expert Name cannot be blank")
	@Size(min = 4, max = 10,message = "username cannot be more than 10 and less than 4")
	private String name;
	
	/** The password. */
	@NotBlank(message = "password must not be empty")
	@Size(min = 4, max=10, message = "password must more than 3 and less than 11")
	private String password;
	
	/** The specialist in. */
	@NotEmpty(message = "Expert Must be assigned  some value at time of creation")
	@NotNull(message = "Expert Must be assigned  some value at time of creation")
	private List<String> specialistIn;
	
	/** The issuesids. */
	private List<String> issuesids;
	
	/** The feedbackids. */
	private List<String> feedbackids;
	
	/** The roles. */
	private List<String> roles;
	
	/**
	 * Instantiates a new expert.
	 *
	 * @param expertid the expertid
	 * @param name the name
	 * @param password the password
	 * @param specialistIn the specialist in
	 * @param issuesids the issuesids
	 * @param feedbackids the feedbackids
	 */
	public Expert(String expertid, String name, String password, List<String> specialistIn, List<String> issuesids,
			List<String> feedbackids) {
		super();
		this.expertid = expertid;
		this.name = name;
		this.password = (password);
		this.specialistIn = specialistIn;
		this.issuesids = issuesids;
		this.feedbackids = feedbackids;
	}
	
	/**
	 * Gets the expertid.
	 *
	 * @return the expertid
	 */
	public String getExpertid() {
		return expertid;
	}
	
	/**
	 * Sets the expertid.
	 *
	 * @param expertid the new expertid
	 */
	public void setExpertid(String expertid) {
		this.expertid = expertid;
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
	 * Gets the specialist in.
	 *
	 * @return the specialist in
	 */
	public List<String> getSpecialistIn() {
		return specialistIn;
	}
	
	/**
	 * Sets the specialist in.
	 *
	 * @param specialistIn the new specialist in
	 */
	public void setSpecialistIn(List<String> specialistIn) {
		this.specialistIn = specialistIn;
	}
	
	/**
	 * Gets the issuesids.
	 *
	 * @return the issuesids
	 */
	public List<String> getIssuesids() {
		return issuesids;
	}
	
	/**
	 * Sets the issuesids.
	 *
	 * @param issuesids the new issuesids
	 */
	public void setIssuesids(List<String> issuesids) {
		this.issuesids = issuesids;
	}
	
	/**
	 * Gets the feedbackids.
	 *
	 * @return the feedbackids
	 */
	public List<String> getFeedbackids() {
		return feedbackids;
	}
	
	/**
	 * Sets the feedbackids.
	 *
	 * @param feedbackids the new feedbackids
	 */
	public void setFeedbackids(List<String> feedbackids) {
		this.feedbackids = feedbackids;
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
