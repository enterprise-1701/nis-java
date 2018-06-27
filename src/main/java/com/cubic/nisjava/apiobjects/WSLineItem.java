package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSLineItem {

    @SerializedName("lineItemId")
    @Expose
    private Integer lineItemId;
    @SerializedName("lineItemType")
    @Expose
    private String lineItemType;
    @SerializedName("lineItemTypeDescription")
    @Expose
    private String lineItemTypeDescription;
    @SerializedName("lineItemStatus")
    @Expose
    private String lineItemStatus;
    @SerializedName("lineItemStatusDescription")
    @Expose
    private String lineItemStatusDescription;
    @SerializedName("financiallyResponsibleOperatorId")
    @Expose
    private Integer financiallyResponsibleOperatorId;
    @SerializedName("financiallyResponsibleOperatorName")
    @Expose
    private String financiallyResponsibleOperatorName;
    @SerializedName("subsystem")
    @Expose
    private String subsystem;
    @SerializedName("subsystemAccountReference")
    @Expose
    private String subsystemAccountReference;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("itemAmount")
    @Expose
    private Integer itemAmount;
    @SerializedName("feeAmount")
    @Expose
    private Integer feeAmount;
    @SerializedName("depositAmount")
    @Expose
    private Integer depositAmount;
    @SerializedName("itemTotalAmount")
    @Expose
    private Integer itemTotalAmount;
    @SerializedName("salesTaxAmount")
    @Expose
    private Integer salesTaxAmount;
    @SerializedName("fulfillDtm")
    @Expose
    private String fulfillDtm;

    public Integer getLineItemId() {
        return lineItemId;
    }

    public void setLineItemId(Integer lineItemId) {
        this.lineItemId = lineItemId;
    }

    public String getLineItemType() {
        return lineItemType;
    }

    public void setLineItemType(String lineItemType) {
        this.lineItemType = lineItemType;
    }

    public String getLineItemTypeDescription() {
        return lineItemTypeDescription;
    }

    public void setLineItemTypeDescription(String lineItemTypeDescription) {
        this.lineItemTypeDescription = lineItemTypeDescription;
    }

    public String getLineItemStatus() {
        return lineItemStatus;
    }

    public void setLineItemStatus(String lineItemStatus) {
        this.lineItemStatus = lineItemStatus;
    }

    public String getLineItemStatusDescription() {
        return lineItemStatusDescription;
    }

    public void setLineItemStatusDescription(String lineItemStatusDescription) {
        this.lineItemStatusDescription = lineItemStatusDescription;
    }

    public Integer getFinanciallyResponsibleOperatorId() {
        return financiallyResponsibleOperatorId;
    }

    public void setFinanciallyResponsibleOperatorId(Integer financiallyResponsibleOperatorId) {
        this.financiallyResponsibleOperatorId = financiallyResponsibleOperatorId;
    }

    public String getFinanciallyResponsibleOperatorName() {
        return financiallyResponsibleOperatorName;
    }

    public void setFinanciallyResponsibleOperatorName(String financiallyResponsibleOperatorName) {
        this.financiallyResponsibleOperatorName = financiallyResponsibleOperatorName;
    }

    public String getSubsystem() {
        return subsystem;
    }

    public void setSubsystem(String subsystem) {
        this.subsystem = subsystem;
    }

    public String getSubsystemAccountReference() {
        return subsystemAccountReference;
    }

    public void setSubsystemAccountReference(String subsystemAccountReference) {
        this.subsystemAccountReference = subsystemAccountReference;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(Integer itemAmount) {
        this.itemAmount = itemAmount;
    }

    public Integer getFeeAmount() {
        return feeAmount;
    }

    public void setFeeAmount(Integer feeAmount) {
        this.feeAmount = feeAmount;
    }

    public Integer getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(Integer depositAmount) {
        this.depositAmount = depositAmount;
    }

    public Integer getItemTotalAmount() {
        return itemTotalAmount;
    }

    public void setItemTotalAmount(Integer itemTotalAmount) {
        this.itemTotalAmount = itemTotalAmount;
    }

    public Integer getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(Integer salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public String getFulfillDtm() {
        return fulfillDtm;
    }

    public void setFulfillDtm(String fulfillDtm) {
        this.fulfillDtm = fulfillDtm;
    }

}
