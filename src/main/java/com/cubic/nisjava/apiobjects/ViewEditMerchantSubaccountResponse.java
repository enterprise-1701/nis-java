/**
 * 
 */
package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author 203610
 * Jul 6, 2018
 */
public class ViewEditMerchantSubaccountResponse 
{
	@SerializedName("user")
	@Expose
	private WSRetailCustomerUserInfo user;
	
	@SerializedName("errorHeader")
	@Expose
	private WSHdr errorHeader;

	public WSHdr getErrorHeader() {
		return errorHeader;
	}

	public void setErrorHeader(WSHdr errorHeader) {
		this.errorHeader = errorHeader;
	}

	public WSRetailCustomerUserInfo getUser() {
	return user;
	}

	public void setUser(WSRetailCustomerUserInfo user) {
	this.user = user;
	}
}
