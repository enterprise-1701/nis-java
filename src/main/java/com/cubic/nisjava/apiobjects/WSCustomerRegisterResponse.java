/***********************************************************************************
* Copyright (c) 2017  CUBIC Transportation Systems. All rights reserved.
* CUBIC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
***********************************************************************************/
package com.cubic.nisjava.apiobjects;


/**
 * @since Feb 20, 2018
 * @author VijayaBhaskar Palem
 */
public class WSCustomerRegisterResponse  {
	
	private String patronAccountId;
	private String supportRefNum;
	private WSHdr hdr;
	public String getpatronAccountId() {
		return patronAccountId;
	}
	public void setIpatronAccountId(String patronAccountId) {
		this.patronAccountId = patronAccountId;
	}
	public String getSupportRefNum() {
		return supportRefNum;
	}
	public void setSupportRefNum(String supportRefNum) {
		this.supportRefNum = supportRefNum;
	}
	public WSHdr getHdr() {
		return hdr;
	}
	public void setHdr(WSHdr hdr) {
		this.hdr = hdr;
	}
	

	
}

