package com.smartedge.saee.Networking.Models.Transactions;

import androidx.core.app.NotificationCompat;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes9.dex */
public class Transactions {
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("meassage")
    @Expose
    private String meassage;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private Boolean status;

    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMeassage() {
        return this.meassage;
    }

    public void setMeassage(String meassage) {
        this.meassage = meassage;
    }

    public Data getData() {
        return this.data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
