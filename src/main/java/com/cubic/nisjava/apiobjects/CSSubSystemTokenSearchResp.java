package com.cubic.nisjava.apiobjects;

import java.util.List;

import com.cubic.backoffice.apiobjects.ApiRespHeader;
import com.cubic.backoffice.apiobjects.WSTravelTokenSearchResult;

/**
 * CRMSubSystemTokenSearchResp
 * Implements the JSON object to hold the Sub-System Token Search POST Response from NIS Customer Service API
 */
public class CSSubSystemTokenSearchResp {

	private List<WSTravelTokenSearchResult> searchResults;
	private int totalCount;
	
	// Optional Error block (if returned in Response)
	private ApiRespHeader fault;
	
	/**
	 * CSSubSystemTokenSearchResp()
	 * Default constructor
	 */
	public CSSubSystemTokenSearchResp() {
		// Default
	}
	
	/**
	 * CSSubSystemTokenSearchResp()
	 * * Constructor that takes all parameters to initialize member data
	 * @param searchResults - The records that match the token search criteria
	 * @param totalCount - The total number of results
	 * @param fault - Optional error object
	 */
	public CSSubSystemTokenSearchResp(List<WSTravelTokenSearchResult> searchResults, int totalCount,
			ApiRespHeader fault) {
		this.searchResults = searchResults;
		this.totalCount = totalCount;
		this.fault = fault;
	}

	/**
	 * @return the searchResults
	 */
	public List<WSTravelTokenSearchResult> getSearchResults() {
		return searchResults;
	}

	/**
	 * @param searchResults the searchResults to set
	 */
	public void setSearchResults(List<WSTravelTokenSearchResult> searchResults) {
		this.searchResults = searchResults;
	}

	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
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
