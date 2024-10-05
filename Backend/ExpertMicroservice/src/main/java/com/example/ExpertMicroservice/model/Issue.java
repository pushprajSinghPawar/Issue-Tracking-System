package com.example.ExpertMicroservice.model;


import java.util.Date;



// TODO: Auto-generated Javadoc
/**
 * The Class Issue.
 */
public class Issue{
	
	/** The issueid. */
	private String issueid;
	
	/** The software name. */
	String softwareName;
	
	/** The software issue type. */
	String softwareIssueType;
	
	/** The software issue title. */
	String softwareIssueTitle;
	
	/** The software issue description. */
	String softwareIssueDescription;
	
	/** The high priority. */
	boolean highPriority;
	
	/** The status. */
	String status;
	
	/** The paid ticket. */
	boolean paidTicket;
	
	/** The userid. */
	private String userid;
	
	/** The dateissueform. */
	Date dateissueform;
	
	/**
	 * Instantiates a new issue.
	 *
	 * @param issueid the issueid
	 * @param softwareName the software name
	 * @param softwareIssueType the software issue type
	 * @param softwareIssueTitle the software issue title
	 * @param softwareIssueDescription the software issue description
	 * @param highPriority the high priority
	 * @param status the status
	 * @param paidTicket the paid ticket
	 * @param userid the userid
	 */
	public Issue(String issueid, String softwareName, String softwareIssueType, String softwareIssueTitle,
			String softwareIssueDescription, boolean highPriority, String status, boolean paidTicket, String userid) {
		super();
		this.issueid = issueid;
		this.softwareName = softwareName;
		this.softwareIssueType = softwareIssueType;
		this.softwareIssueTitle = softwareIssueTitle;
		this.softwareIssueDescription = softwareIssueDescription;
		this.highPriority = highPriority;
		this.paidTicket = paidTicket;
		this.userid = userid;
		this.status=status;
		this.dateissueform = new Date();
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
	 * Gets the software name.
	 *
	 * @return the software name
	 */
	public String getSoftwareName() {
		return softwareName;
	}
	
	/**
	 * Sets the software name.
	 *
	 * @param softwareName the new software name
	 */
	public void setSoftwareName(String softwareName) {
		this.softwareName = softwareName;
	}
	
	/**
	 * Gets the software issue type.
	 *
	 * @return the software issue type
	 */
	public String getSoftwareIssueType() {
		return softwareIssueType;
	}
	
	/**
	 * Sets the software issue type.
	 *
	 * @param softwareIssueType the new software issue type
	 */
	public void setSoftwareIssueType(String softwareIssueType) {
		this.softwareIssueType = softwareIssueType;
	}
	
	/**
	 * Gets the software issue title.
	 *
	 * @return the software issue title
	 */
	public String getSoftwareIssueTitle() {
		return softwareIssueTitle;
	}
	
	/**
	 * Sets the software issue title.
	 *
	 * @param softwareIssueTitle the new software issue title
	 */
	public void setSoftwareIssueTitle(String softwareIssueTitle) {
		this.softwareIssueTitle = softwareIssueTitle;
	}
	
	/**
	 * Gets the software issue description.
	 *
	 * @return the software issue description
	 */
	public String getSoftwareIssueDescription() {
		return softwareIssueDescription;
	}
	
	/**
	 * Sets the software issue description.
	 *
	 * @param softwareIssueDescription the new software issue description
	 */
	public void setSoftwareIssueDescription(String softwareIssueDescription) {
		this.softwareIssueDescription = softwareIssueDescription;
	}
	
	/**
	 * Checks if is high priority.
	 *
	 * @return true, if is high priority
	 */
	public boolean isHighPriority() {
		return highPriority;
	}
	
	/**
	 * Sets the high priority.
	 *
	 * @param highPriority the new high priority
	 */
	public void setHighPriority(boolean highPriority) {
		this.highPriority = highPriority;
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Checks if is paid ticket.
	 *
	 * @return true, if is paid ticket
	 */
	public boolean isPaidTicket() {
		return paidTicket;
	}
	
	/**
	 * Sets the paid ticket.
	 *
	 * @param paidTicket the new paid ticket
	 */
	public void setPaidTicket(boolean paidTicket) {
		this.paidTicket = paidTicket;
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
	 * Gets the dateissueform.
	 *
	 * @return the dateissueform
	 */
	public Date getDateissueform() {
		return dateissueform;
	}
	
	/**
	 * Sets the dateissueform.
	 *
	 * @param dateissueform the new dateissueform
	 */
	public void setDateissueform(Date dateissueform) {
		this.dateissueform = dateissueform;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Issue [issueid=" + issueid + ", softwareName=" + softwareName + ", softwareIssueType="
				+ softwareIssueType + ", softwareIssueTitle=" + softwareIssueTitle + ", softwareIssueDescription="
				+ softwareIssueDescription + ", highPriority=" + highPriority + ", status=" + status + ", paidTicket="
				+ paidTicket + ", userid=" + userid + ", dateissueform=" + dateissueform + "]";
	}
	
	
}