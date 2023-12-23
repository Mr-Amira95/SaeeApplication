package com.smartedge.saee.Networking.Models.OrderDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes4.dex */
public class Client {
    @SerializedName("client_area")
    @Expose
    private String clientArea;
    @SerializedName("client_building")
    @Expose
    private String clientBuilding;
    @SerializedName("client_city")
    @Expose
    private String clientCity;
    @SerializedName("client_name")
    @Expose
    private String clientName;
    @SerializedName("client_number")
    @Expose
    private String clientNumber;
    @SerializedName("client_street")
    @Expose
    private String clientStreet;

    public String getClientName() {
        return this.clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientNumber() {
        return this.clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getClientCity() {
        return this.clientCity;
    }

    public void setClientCity(String clientCity) {
        this.clientCity = clientCity;
    }

    public String getClientArea() {
        return this.clientArea;
    }

    public void setClientArea(String clientArea) {
        this.clientArea = clientArea;
    }

    public String getClientStreet() {
        return this.clientStreet;
    }

    public void setClientStreet(String clientStreet) {
        this.clientStreet = clientStreet;
    }

    public String getClientBuilding() {
        return this.clientBuilding;
    }

    public void setClientBuilding(String clientBuilding) {
        this.clientBuilding = clientBuilding;
    }
}
