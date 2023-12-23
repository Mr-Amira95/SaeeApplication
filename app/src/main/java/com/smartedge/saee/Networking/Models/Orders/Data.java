package com.smartedge.saee.Networking.Models.Orders;

import com.smartedge.saee.Networking.Basic.AppConstants;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/* loaded from: classes7.dex */
public class Data {
    @SerializedName(AppConstants.Orders_URL)
    @Expose
    private List<Item> items;

    public List<Item> getItems() {
        return this.items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
