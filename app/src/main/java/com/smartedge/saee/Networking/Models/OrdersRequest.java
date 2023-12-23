package com.smartedge.saee.Networking.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes10.dex */
public class OrdersRequest {
    @SerializedName("at_company")
    @Expose
    boolean at_company;
    @SerializedName("confirmed")
    @Expose
    boolean confirmed;
    @SerializedName("delivered")
    @Expose
    boolean delivered;
    @SerializedName("end_date")
    @Expose
    String end_date;
    @SerializedName("received")
    @Expose
    boolean received;
    @SerializedName("rejected")
    @Expose
    boolean rejected;
    @SerializedName("requested")
    @Expose
    boolean requested;
    @SerializedName("start_date")
    @Expose
    String start_date;
    @SerializedName("type")
    @Expose
    String type;

    public OrdersRequest(boolean requested, boolean confirmed, boolean received, boolean at_company, boolean rejected, boolean delivered, String type, String start_date, String end_date) {
        this.requested = requested;
        this.confirmed = confirmed;
        this.received = received;
        this.at_company = at_company;
        this.rejected = rejected;
        this.delivered = delivered;
        this.type = type;
        this.start_date = start_date;
        this.end_date = end_date;
    }
}
