package com.smartedge.saee.Networking.Models.Transactions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/* loaded from: classes9.dex */
public class Data {
    @SerializedName("balance")
    @Expose
    private Double balance;
    @SerializedName("orders")
    @Expose
    private List<Order> orders;

    public Double getBalance() {
        return this.balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
