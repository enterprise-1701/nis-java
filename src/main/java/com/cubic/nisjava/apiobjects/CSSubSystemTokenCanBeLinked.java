package com.cubic.nisjava.apiobjects;

import com.cubic.backoffice.apiobjects.WSTravelToken;

/**
 * CRMSubSystemTokenCanBeLinked
 * Implements the JSON object for subsystem/<subsystem-id>/traveltoken/canbelinked POST Request via NIS Customer Service API
 */
public class CSSubSystemTokenCanBeLinked {

	private String subsystemId;
	private WSTravelToken travelToken;
	private String foreignCustomerId;
	private String foreignTokenSubsystem;
	private boolean autoCreateFlag;
	
	/**
	 * CRMSubSystemTokenCanBeLinked()
	 * Default constructor
	 */
	public CSSubSystemTokenCanBeLinked() {
		// Default
	}
	
	/**
	 * CRMSubSystemTokenCanBeLinked()
	 * * Constructor that takes all parameters to initialize member data
	 * @param subsystemId - Unique identifier for the subsystem where the travel token is registered
	 * @param travelToken - Identifier of the travel token
	 * @param foreignCustomerId - Unique identifier for the foreign customer
	 * @param foreignTokenSubsystem - Unique identifier for the foreign token subsystem if the token is owned by a different subsystem than the account-based subsystem
	 * @param autoCreateFlag - To indicate if the token should be auto created in the subsystem in case it is not yet created
	 */
	public CSSubSystemTokenCanBeLinked(String subsystemId, WSTravelToken travelToken, String foreignCustomerId,
			String foreignTokenSubsystem, boolean autoCreateFlag) {
		this.subsystemId = subsystemId;
		this.travelToken = travelToken;
		this.foreignCustomerId = foreignCustomerId;
		this.foreignTokenSubsystem = foreignTokenSubsystem;
		this.autoCreateFlag = autoCreateFlag;
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
	public WSTravelToken getTravelToken() {
		return travelToken;
	}

	/**
	 * @param travelToken the travelToken to set
	 */
	public void setTravelToken(WSTravelToken travelToken) {
		this.travelToken = travelToken;
	}

	/**
	 * @return the foreignCustomerId
	 */
	public String getForeignCustomerId() {
		return foreignCustomerId;
	}

	/**
	 * @param foreignCustomerId the foreignCustomerId to set
	 */
	public void setForeignCustomerId(String foreignCustomerId) {
		this.foreignCustomerId = foreignCustomerId;
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

	/**
	 * @return the autoCreateFlag
	 */
	public boolean isAutoCreateFlag() {
		return autoCreateFlag;
	}

	/**
	 * @param autoCreateFlag the autoCreateFlag to set
	 */
	public void setAutoCreateFlag(boolean autoCreateFlag) {
		this.autoCreateFlag = autoCreateFlag;
	}
}
