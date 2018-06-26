package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public class WSOrderHistory {

    @SerializedName("orderId")
    @Expose
    private Integer orderId;
    @SerializedName("origin")
    @Expose
    private String origin;
    @SerializedName("orderDateTime")
    @Expose
    private String orderDateTime;
    @SerializedName("insertedBy")
    @Expose
    private String insertedBy;
    @SerializedName("orderLastUpdate")
    @Expose
    private String orderLastUpdate;
    @SerializedName("lastUpdatedBy")
    @Expose
    private String lastUpdatedBy;
    @SerializedName("orderType")
    @Expose
    private String orderType;
    @SerializedName("orderTypeDescription")
    @Expose
    private String orderTypeDescription;
    @SerializedName("orderStatus")
    @Expose
    private String orderStatus;
    @SerializedName("orderStatusDescription")
    @Expose
    private String orderStatusDescription;
    @SerializedName("lineItems")
    @Expose
    private List<WSLineItem> lineItems = null;
    @SerializedName("orderTotalAmount")
    @Expose
    private Integer orderTotalAmount;
    @SerializedName("feeTotalAmount")
    @Expose
    private Integer feeTotalAmount;
    @SerializedName("itemsSubtotalAmount")
    @Expose
    private Integer itemsSubtotalAmount;
    @SerializedName("salesTaxAmount")
    @Expose
    private Integer salesTaxAmount;
    @SerializedName("depositTotalAmount")
    @Expose
    private Integer depositTotalAmount;
    @SerializedName("paymentTotalAmount")
    @Expose
    private Integer paymentTotalAmount;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(String orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public String getInsertedBy() {
        return insertedBy;
    }

    public void setInsertedBy(String insertedBy) {
        this.insertedBy = insertedBy;
    }

    public String getOrderLastUpdate() {
        return orderLastUpdate;
    }

    public void setOrderLastUpdate(String orderLastUpdate) {
        this.orderLastUpdate = orderLastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderTypeDescription() {
        return orderTypeDescription;
    }

    public void setOrderTypeDescription(String orderTypeDescription) {
        this.orderTypeDescription = orderTypeDescription;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatusDescription() {
        return orderStatusDescription;
    }

    public void setOrderStatusDescription(String orderStatusDescription) {
        this.orderStatusDescription = orderStatusDescription;
    }

    public List<WSLineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<WSLineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public Integer getOrderTotalAmount() {
        return orderTotalAmount;
    }

    public void setOrderTotalAmount(Integer orderTotalAmount) {
        this.orderTotalAmount = orderTotalAmount;
    }

    public Integer getFeeTotalAmount() {
        return feeTotalAmount;
    }

    public void setFeeTotalAmount(Integer feeTotalAmount) {
        this.feeTotalAmount = feeTotalAmount;
    }

    public Integer getItemsSubtotalAmount() {
        return itemsSubtotalAmount;
    }

    public void setItemsSubtotalAmount(Integer itemsSubtotalAmount) {
        this.itemsSubtotalAmount = itemsSubtotalAmount;
    }

    public Integer getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(Integer salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public Integer getDepositTotalAmount() {
        return depositTotalAmount;
    }

    public void setDepositTotalAmount(Integer depositTotalAmount) {
        this.depositTotalAmount = depositTotalAmount;
    }

    public Integer getPaymentTotalAmount() {
        return paymentTotalAmount;
    }

    public void setPaymentTotalAmount(Integer paymentTotalAmount) {
        this.paymentTotalAmount = paymentTotalAmount;
    }

}
