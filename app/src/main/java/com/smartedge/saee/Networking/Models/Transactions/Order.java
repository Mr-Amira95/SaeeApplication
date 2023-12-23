package com.smartedge.saee.Networking.Models.Transactions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes9.dex */
public class Order {
    @SerializedName("customer_name")
    @Expose
    private String customerName;
    @SerializedName("delivery_price")
    @Expose
    private String deliveryPrice;
    @SerializedName("drop_date")
    @Expose
    private String dropDate;
    @SerializedName("order_price")
    @Expose
    private String orderPrice;
    @SerializedName("ref_code")
    @Expose
    private String refCode;
    @SerializedName("transaction_date")
    @Expose
    private String transactionDate;

    public String getRefCode() {
        return this.refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrderPrice() {
        return this.orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getDeliveryPrice() {
        return this.deliveryPrice;
    }

    public void setDeliveryPrice(String deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getDropDate() {
        return this.dropDate;
    }

    public void setDropDate(String dropDate) {
        this.dropDate = dropDate;
    }

    public String getTransactionDate() {
        return this.transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
}
