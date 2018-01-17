package com.cubic.nisjava.apiobjects;

import com.cubic.backoffice.apiobjects.ApiRespHeader;
import com.cubic.backoffice.apiobjects.WSTokenVerificationInfo;

/**
 * CRMSubSystemTokenCanBeLinked
 * Implements the JSON object for subsystem/<subsystem-id>/traveltoken/canbelinked POST Response via NIS Customer Service API
 */
public class CSSubSystemTokenCanBeLinkedResp {

	private String subsystemAccountReference;
	private WSTokenVerificationInfo tokenVerificationInfo;
	
	// Optional Error block (if returned in Response)
	private ApiRespHeader fault;
	
	/**
	 * CRMSubSystemTokenCanBeLinkedResp()
	 * Default constructor
	 */
	public CSSubSystemTokenCanBeLinkedResp() {
		// Default
	}
	
	/**
	 * CRMSubSystemTokenCanBeLinkedResp()
	 * Constructor that takes ONLY the sub-class parameters to initialize member data
	 * and uses the expected tokenType value to set the parent class member
	 * @param subsystemAccountReference - Account number of the travel token associated within the given subsystem
	 * @param tokenVerificationInfo - Verification information for this customer in the subsystem
	 * @param fault - Optional error object
	 */
	public CSSubSystemTokenCanBeLinkedResp(String subsystemAccountReference,
			WSTokenVerificationInfo tokenVerificationInfo, ApiRespHeader fault) {
		this.subsystemAccountReference = subsystemAccountReference;
		this.tokenVerificationInfo = tokenVerificationInfo;
		this.fault = fault;
	}

	/**
	 * @return the subsystemAccountReference
	 */
	public String getSubsystemAccountReference() {
		return subsystemAccountReference;
	}

	/**
	 * @param subsystemAccountReference the subsystemAccountReference to set
	 */
	public void setSubsystemAccountReference(String subsystemAccountReference) {
		this.subsystemAccountReference = subsystemAccountReference;
	}

	/**
	 * @return the tokenVerificationInfo
	 */
	public WSTokenVerificationInfo getTokenVerificationInfo() {
		return tokenVerificationInfo;
	}

	/**
	 * @param tokenVerificationInfo the tokenVerificationInfo to set
	 */
	public void setTokenVerificationInfo(WSTokenVerificationInfo tokenVerificationInfo) {
		this.tokenVerificationInfo = tokenVerificationInfo;
	}

	/**
	 * @return the fault
	 */
	public ApiRespHeader getFault() {
		return fault;
	}

	/**
	 * @param fault the fault to set
	 */
	public void setFault(ApiRespHeader fault) {
		this.fault = fault;
	}
}
