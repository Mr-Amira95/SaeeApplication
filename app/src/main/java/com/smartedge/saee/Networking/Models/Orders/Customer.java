package com.smartedge.saee.Networking.Models.Orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class Customer {
    @SerializedName("customer_area")
    @Expose
    private String customerArea;
    @SerializedName("customer_building")
    @Expose
    private String customerBuilding;
    @SerializedName("customer_city")
    @Expose
    private String customerCity;
    @SerializedName("customer_name")
    @Expose
    private String customerName;
    @SerializedName("customer_phone")
    @Expose
    private String customerPhone;
    @SerializedName("customer_street")
    @Expose
    private String customerStreet;

    public String getCustomerPhone() {
        return this.customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerCity() {
        return this.customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerArea() {
        return this.customerArea;
    }

    public void setCustomerArea(String customerArea) {
        this.customerArea = customerArea;
    }

    public String getCustomerStreet() {
        return this.customerStreet;
    }

    public void setCustomerStreet(String customerStreet) {
        this.customerStreet = customerStreet;
    }

    public String getCustomerBuilding() {
        return this.customerBuilding;
    }

    public void setCustomerBuilding(String customerBuilding) {
        this.customerBuilding = customerBuilding;
    }
}
