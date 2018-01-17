package com.cubic.nisjava.apiobjects;

import java.util.List;

import com.cubic.backoffice.apiobjects.ApiRespHeader;
import com.cubic.backoffice.apiobjects.WSTokenTypeSubsystemMapping;

/**
 * CSSubSystemTokenTypeMappingsResp
 * Implements the JSON object to hold the Response Object for Sub-System Travel
 * Token Type Mappings Customer Service API Requests
 */
public class CSSubSystemTokenTypeMappingsResp {

	private List<WSTokenTypeSubsystemMapping> mappings;

	// Optional Error block (if returned in Response)
	private ApiRespHeader fault;
		
	/**
	 * CSSubSystemTokenTypeMappingsResp()
	 * Default constructor
	 */
	public CSSubSystemTokenTypeMappingsResp() {
		// Default
	}

	/**
	 * CSSubSystemTokenTypeMappingsResp()
	 * Parameterized Constructor
	 * @param mappings - Token Type Mappings
	 * @param fault - Optional error object
	 */
	public CSSubSystemTokenTypeMappingsResp(List<WSTokenTypeSubsystemMapping> mappings, ApiRespHeader fault) {
		this.mappings = mappings;
		this.fault = fault;
	}

	/**
	 * @return the mappings
	 */
	public List<WSTokenTypeSubsystemMapping> getMappings() {
		return mappings;
	}

	/**
	 * @param mappings the mappings to set
	 */
	public void setMappings(List<WSTokenTypeSubsystemMapping> mappings) {
		this.mappings = mappings;
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
