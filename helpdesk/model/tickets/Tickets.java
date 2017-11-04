package com.bcj.helpdesk.model.tickets;
//@Component
public class Tickets {

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */

	private int ticketId;
	private String ticketNumber;
	private String category;
	private String subcategory;
	private String description;
	private String stat;
	private String comments;
	private String username;
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tickets [ticketId=" + ticketId + ", ticketNumber=" + ticketNumber + ", category=" + category
				+ ", subcategory=" + subcategory + ", description=" + description + ", stat=" + stat + ", comments="
				+ comments + ", login_loginId=" + login_loginId + "]";
	}
	private int login_loginId;

	/**
	 * @return the login_loginId
	 */
	public int getLogin_loginId() {
		return login_loginId;
	}
	/**
	 * @param login_loginId the login_loginId to set
	 */
	public void setLogin_loginId(int login_loginId) {
		this.login_loginId = login_loginId;
	}
	/**
	 * @return the ticketId
	 */
	public int getTicketId() {
		return ticketId;
	}
	/**
	 * @param ticketId the ticketId to set
	 */
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the subcategory
	 */
	public String getSubcategory() {
		return subcategory;
	}
	/**
	 * @param subcategory the subcategory to set
	 */
	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the stat
	 */
	public String getStat() {
		return stat;
	}
	/**
	 * @param stat the stat to set
	 */
	public void setStat(String stat) {
		this.stat = stat;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return the ticketNumber
	 */
	public String getTicketNumber() {
		return ticketNumber;
	}
	/**
	 * @param ticketNumber the ticketNumber to set
	 */
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}


}
