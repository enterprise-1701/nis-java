package com.cubic.nisjava.apiobjects;

import com.cubic.backoffice.apiobjects.WSTravelTokenSearch;

/**
 * CRMSubSystemTokenSearch
 * Implements the JSON object for Sub-System Token Search POST Request via NIS Customer Service API
 */
public class CSSubSystemTokenSearch {

	private String subsystemId;
	private WSTravelTokenSearch travelToken;
	private String foreignTokenSubsystem;
	
	/**
	 * CRMSubSystemTokenSearch()
	 * Default constructor
	 */
	public CSSubSystemTokenSearch() {
		// Default
	}
	
	/**
	 * CRMSubSystemTokenSearch()
	 * * Constructor that takes all parameters to initialize member data
	 * @param subsystemId - Unique identifier for the subsystem where the travel token is registered
	 * @param travelToken - Identifier of the travel token
	 * @param foreignTokenSubsystem - Unique identifier for the foreign token subsystem if the token is owned by a different subsystem than the account-based subsystem
	 */
	public CSSubSystemTokenSearch(String subsystemId, WSTravelTokenSearch travelToken, String foreignTokenSubsystem) {
		this.subsystemId = subsystemId;
		this.travelToken = travelToken;
		this.foreignTokenSubsystem = foreignTokenSubsystem;
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
	 * @return the travelToken
	 */
	public WSTravelTokenSearch getTravelToken() {
		return travelToken;
	}

	/**
	 * @param travelToken the travelToken to set
	 */
	public void setTravelToken(WSTravelTokenSearch travelToken) {
		this.travelToken = travelToken;
	}

	/**
	 * @return the foreignTokenSubsystem
	 */
	public String getForeignTokenSubsystem() {
		return foreignTokenSubsystem;
	}

	/**
	 * @param foreignTokenSubsystem the foreignTokenSubsystem to set
	 */
	public void setForeignTokenSubsystem(String foreignTokenSubsystem) {
		this.foreignTokenSubsystem = foreignTokenSubsystem;
	}
}
