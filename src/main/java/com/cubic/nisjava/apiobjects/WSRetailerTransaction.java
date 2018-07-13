/**
 * @author 203610
 * Jun 15, 2018
 */
package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSRetailerTransaction 
{
	@SerializedName("itemType")
	@Expose
	private String itemType;
	@SerializedName("quantity")
	@Expose
	private Integer quantity;
	@SerializedName("lineItemTotal")
	@Expose
	private Integer lineItemTotal;
	@SerializedName("employeeInfo")
	@Expose
	private WSRetailCustomerUserInfo employeeInfo;

	public String getItemType() {
	return itemType;
	}

	public void setItemType(String itemType) {
	this.itemType = itemType;
	}

	public Integer getQuantity() {
	return quantity;
	}

	public void setQuantity(Integer quantity) {
	this.quantity = quantity;
	}

	public Integer getLineItemTotal() {
	return lineItemTotal;
	}

	public void setLineItemTotal(Integer lineItemTotal) {
	this.lineItemTotal = lineItemTotal;
	}

	public WSRetailCustomerUserInfo getEmployeeInfo() {
	return employeeInfo;
	}

	public void setEmployeeInfo(WSRetailCustomerUserInfo employeeInfo) {
	this.employeeInfo = employeeInfo;
	}

}
