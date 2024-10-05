package com.example.AdminMicroservice.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;

/**
 * The Payment info class.
 */
public class PaymentInfo {
	

	/** The user id. */
	private String userid;

	/** The transaction id. */
	private String transactionId;
	
	private Date date;
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * Gets the user id.
	 *
	 * @param user id the new user id
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * Sets the user id.
	 *
	 * @return payment info user id
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	
	/**
	 * Gets the transaction id.
	 *
	 * @return the transaction id
	 */
	public String getTransactionId() {
		return transactionId;
	}
	
	/**
	 * Sets the transaction id.
	 *
	 * @param transactionId the new transaction id
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	/**
	 * Instantiates a new PaymentInfo.
	 *
	 * @param user id the user id
	 * @param transactionId the transaction id
	 */
	public PaymentInfo(@NotBlank(message = "UserId Cannot be blank") String userid,
			@NotBlank(message = "TransactionId Cannot be blank") String transactionId) {
		super();
		this.userid = userid;
		this.transactionId = transactionId;
		this.date = new Date();
	}

}
