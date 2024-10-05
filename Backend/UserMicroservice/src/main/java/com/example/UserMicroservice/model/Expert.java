package com.example.UserMicroservice.model;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Expert.
 */
public class Expert {
	
	/** The expertid. */
	private String expertid;
	
	/** The name. */
	private String name;
	
	/** The password. */
	private String password;
	
	/** The specialist in. */
	private List<String> specialistIn;
	
	/** The issues. */
	private List<String> issues;
	
	/** The feedbacks. */
	private List<String> feedbacks;
	
	/**
	 * Instantiates a new expert.
	 *
	 * @param expertid the expertid
	 * @param name the name
	 * @param password the password
	 * @param specialistIn the specialist in
	 * @param issues the issues
	 * @param feedbacks the feedbacks
	 */
	public Expert(String expertid, String name, String password, List<String> specialistIn, List<String> issues,
			List<String> feedbacks) {
		super();
		this.expertid = expertid;
		this.name = name;
		this.password = password;
		this.specialistIn = specialistIn;
		this.issues = issues;
		this.feedbacks = feedbacks;
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
	 * Gets the feedbacks.
	 *
	 * @return the feedbacks
	 */
	public List<String> getFeedbacks() {
		return feedbacks;
	}
	
	/**
	 * Sets the feedbacks.
	 *
	 * @param feedbacks the new feedbacks
	 */
	public void setFeedbacks(List<String> feedbacks) {
		this.feedbacks = feedbacks;
	}
	
}