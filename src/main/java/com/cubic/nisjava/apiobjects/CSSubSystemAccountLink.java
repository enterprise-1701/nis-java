package com.cubic.nisjava.apiobjects;

/**
 * CSSubSystemAccountLink
 * Implements Sub-System Token Link POST Request via NIS Customer Service API
 */
public class CSSubSystemAccountLink {

	private String oneAccountId;
	private String subsystemId;
	private String accountRef;
	private String subsystemAccountNickname;
	
	/**
	 * OAMSubSystemAccountLink()
	 * Default constructor
	 */
	public CSSubSystemAccountLink() {
		// Default
	}
	
	/**
	 * OAMSubSystemAccountLink()
	 * * Constructor that takes all parameters to initialize member data
	 * @param oneaccountId - Unique identifier for the one account
	 * @param subsystemId - Unique identifier for the subsystem where the travel token is registered
	 * @param accountRef - Account number of the travel token associated within the given subsystem
	 * @param subsystemAccountNickname - Specifies a nickname to be associated with the subsystem account
	 */
	public CSSubSystemAccountLink(String oneAccountId, String subsystemId, String accountRef, String subsystemAccountNickname) {
		this.oneAccountId = oneAccountId;
		this.subsystemId = subsystemId;
		this.accountRef = accountRef;
		this.subsystemAccountNickname = subsystemAccountNickname;
	}

	/**
	 * @return the oneAccountId
	 */
	public String getOneAccountId() {
		return oneAccountId;
	}

	/**
	 * @param oneAccountId the oneAccountId to set
	 */
	public void setOneAccountId(String oneAccountId) {
		this.oneAccountId = oneAccountId;
	}

	/**
	 * @return the subsystemId
	 */
	public String getSubsystemId() {
		return subsystemId;
	}

	/**
	 * @param subsystemId the subsystemId to set
	 */
	public void setSubsystemId(String subsystemId) {
		this.subsystemId = subsystemId;
	}

	/**
	 * @return the accountRef
	 */
	public String getAccountRef() {
		return accountRef;
	}

	/**
	 * @param accountRef the accountRef to set
	 */
	public void setAccountRef(String accountRef) {
		this.accountRef = accountRef;
	}

	/**
	 * @return the subsystemAccountNickname
	 */
	public String getSubsystemAccountNickname() {
		return subsystemAccountNickname;
	}

	/**
	 * @param subsystemAccountNickname the subsystemAccountNickname to set
	 */
	public void setSubsystemAccountNickname(String subsystemAccountNickname) {
		this.subsystemAccountNickname = subsystemAccountNickname;
	}
}
