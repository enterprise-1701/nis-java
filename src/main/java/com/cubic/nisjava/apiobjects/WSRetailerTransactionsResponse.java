/**
 * @author 203610
 * Jun 14, 2018
 */

package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSRetailerTransactionsResponse 
{
	@SerializedName("transactions")
	@Expose
	private WSRetailerTransactions transactions;

	public WSRetailerTransactions getTransactions() {
	return transactions;
	}

	public void setTransactions(WSRetailerTransactions transactions) 
	{
		this.transactions = transactions;
	}
	
	private WSErrorHdr hdr;
	public WSErrorHdr getHdr() {
		return hdr;
	}
	public void setHdr(WSErrorHdr hdr) {
		this.hdr = hdr;
	}
}



