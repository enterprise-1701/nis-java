/**
 * @author 203610
 * Jul 6, 2018
 */
package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewEditMerchantSubaccountRequest 
{
	@SerializedName("name")
	@Expose
	private WSName name;
	
	@SerializedName("password")
	@Expose
	private String password;

	public String getPassword() {
	return password;
	}

	public void setPassword(String password) {
	this.password = password;
	}

	public WSName getName()
	{
		return name;
	}

	public void setName(WSName name) 
	{
		this.name = name;
	}		
}
