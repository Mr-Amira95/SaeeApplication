package com.smartedge.saee.Networking.Models.Orders;

import androidx.core.app.NotificationCompat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class OrdersResults {
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private Boolean status;

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
