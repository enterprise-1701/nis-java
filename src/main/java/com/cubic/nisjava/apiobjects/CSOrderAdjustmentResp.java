package com.cubic.nisjava.apiobjects;

import java.util.List;

import com.cubic.backoffice.apiobjects.ApiRespHeader;
import com.cubic.backoffice.apiobjects.WSOrderError;

/**
 * CSOrderAdjustmentResp
 * Implements the JSON object to hold the Add a new Order POST Response from NIS Customer Service API
 */
public class CSOrderAdjustmentResp {

	private String orderId;
	private String responseCode;
	private List<WSOrderError> errors;

	// Optional Error block (if returned in Response)
	private ApiRespHeader fault;
		
	/**
	 * CSOrderAdjustmentResp()
	 * Default constructor
	 */
	public CSOrderAdjustmentResp() {
		// Default
	}
	
	/**
	 * CSOrderAdjustmentResp()
	 * Constructor that takes all parameters to initialize member data
	 * @param orderId - The unique order identifier from Order Management System 
	 * @param responseCode - The order result
	 * @param errors - A list of errors encountered during order processing
	 * @param fault - Optional error object
	 */
	public CSOrderAdjustmentResp(String orderId, String responseCode, List<WSOrderError> errors, ApiRespHeader fault) {
		this.orderId = orderId;
		this.responseCode = responseCode;
		this.errors = errors;
		this.fault = fault;
	}

	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the responseCode
	 */
	public String getResponseCode() {
		return responseCode;
	}

	/**
	 * @param responseCode the responseCode to set
	 */
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * @return the errors
	 */
	public List<WSOrderError> getErrors() {
		return errors;
	}

	/**
	 * @param errors the errors to set
	 */
	public void setErrors(List<WSOrderError> errors) {
		this.errors = errors;
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
