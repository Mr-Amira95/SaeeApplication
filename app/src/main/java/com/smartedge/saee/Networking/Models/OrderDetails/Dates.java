package com.smartedge.saee.Networking.Models.OrderDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes4.dex */
public class Dates {
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("drop_date")
    @Expose
    private String dropDate;
    @SerializedName("pickup_date")
    @Expose
    private String pickupDate;
    @SerializedName("request_date")
    @Expose
    private String requestDate;
    @SerializedName("transaction_date")
    @Expose
    private String transactionDate;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public String getRequestDate() {
        return this.requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getPickupDate() {
        return this.pickupDate;
    }

    public void setPickupDate(String pickupDate) {
        this.pickupDate = pickupDate;
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

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
