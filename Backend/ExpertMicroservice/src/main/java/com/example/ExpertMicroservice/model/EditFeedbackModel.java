package com.example.ExpertMicroservice.model;

import jakarta.validation.constraints.Size;

// TODO: Auto-generated Javadoc
/**
 * The Class EditFeedbackModel.
 */
public class EditFeedbackModel {

	/** The feedback description. */
	@Size(min=3, max=200, message="size can only be anywhere from 3-400")
	String feedbackDescription;

	/**
	 * Gets the feedback description.
	 *
	 * @return the feedback description
	 */
	public String getFeedbackDescription() {
		return this.feedbackDescription;
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
	 * Instantiates a new edits the feedback model.
	 *
	 * @param feedbackDescription the feedback description
	 */
	public EditFeedbackModel( String feedbackDescription) {
		super();
		this.feedbackDescription = feedbackDescription;
	}

	/**
	 * Instantiates a new edits the feedback model.
	 */
	public EditFeedbackModel() {
		super();
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "EditFeedbackModel [feedbackDescription=" + feedbackDescription + "]";
	}
	
	

}
