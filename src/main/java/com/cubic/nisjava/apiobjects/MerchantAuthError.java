/**
 * @author 203610
 * Jul 11, 2018
 */
package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MerchantAuthError 
{
	@SerializedName("errorKey")
	@Expose
	private String errorKey;

	public String getErrorKey() {
		return errorKey;
	}

	public void setErrorKey(String errorKey)
	{
		this.errorKey = errorKey;
	}
}
