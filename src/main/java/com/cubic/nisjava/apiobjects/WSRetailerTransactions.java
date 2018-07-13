/**
 * @author 203610
 * Jun 20, 2018
 */
package com.cubic.nisjava.apiobjects;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSRetailerTransactions 
{
	@SerializedName("transactions")
	@Expose
	private List<WSRetailerTransaction> transactions = null;
	
	@SerializedName("employees")
	@Expose
	private List<WSRetailCustomerUserInfo> employees = null;

	public List<WSRetailerTransaction> getTransactions() {
	return transactions;
	}

	public void setTransactions(List<WSRetailerTransaction> transactions) {
	this.transactions = transactions;
	}

	public List<WSRetailCustomerUserInfo> getEmployees() {
	return employees;
	}

	public void setEmployees(List<WSRetailCustomerUserInfo> employees) {
	this.employees = employees;
	}
}
