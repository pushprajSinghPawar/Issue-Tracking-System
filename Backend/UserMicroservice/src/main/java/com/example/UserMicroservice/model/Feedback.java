package com.example.UserMicroservice.model;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class Feedback.
 */
public class Feedback {
	
	/** The feedbackid. */
	String feedbackid;
	
	/** The userid. */
	String userid;
	
	/** The issueid. */
	String issueid;
	
	/** The expertid. */
	String expertid;
	
	/** The feedback description. */
	String feedbackDescription;
	
	/** The datefeedbackform. */
	Date datefeedbackform;
	
	/**
	 * Instantiates a new feedback.
	 *
	 * @param feedbackid the feedbackid
	 * @param userid the userid
	 * @param issueid the issueid
	 * @param expertid the expertid
	 * @param feedbackDescription the feedback description
	 */
	public Feedback(String feedbackid, String userid, String issueid, String expertid, String feedbackDescription) {
		super();
		this.feedbackid = feedbackid;
		this.userid = userid;
		this.issueid = issueid;
		this.expertid = expertid;
		this.feedbackDescription = feedbackDescription;
	}
	
	/**
	 * Gets the feedbackid.
	 *
	 * @return the feedbackid
	 */
	public String getFeedbackid() {
		return feedbackid;
	}
	
	/**
	 * Sets the feedbackid.
	 *
	 * @param feedbackid the new feedbackid
	 */
	public void setFeedbackid(String feedbackid) {
		this.feedbackid = feedbackid;
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
	 * Gets the issueid.
	 *
	 * @return the issueid
	 */
	public String getIssueid() {
		return issueid;
	}
	
	/**
	 * Sets the issueid.
	 *
	 * @param issueid the new issueid
	 */
	public void setIssueid(String issueid) {
		this.issueid = issueid;
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
	 * Gets the feedback description.
	 *
	 * @return the feedback description
	 */
	public String getFeedbackDescription() {
		return feedbackDescription;
	}
	
	/**
	 * Sets the feedback description.
	 *
	 * @param feedbackDescription the new feedback description
	 */
	public void setFeedbackDescription(String feedbackDescription) {
		this.feedbackDescription = feedbackDescription;
	}
	
	/**
	 * Gets the feedbackgiven.
	 *
	 * @return the feedbackgiven
	 */
	public Date getFeedbackgiven() {
		return datefeedbackform;
	}
	
	/**
	 * Sets the feedbackgiven.
	 *
	 * @param feedbackgiven the new feedbackgiven
	 */
	public void setFeedbackgiven(Date feedbackgiven) {
		this.datefeedbackform = feedbackgiven;
	}
}
