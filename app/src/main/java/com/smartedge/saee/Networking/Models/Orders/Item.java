package com.smartedge.saee.Networking.Models.Orders;

import androidx.core.app.NotificationCompat;

import com.smartedge.saee.Networking.Basic.PrefKeys;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class Item {
    @SerializedName("attachment")
    @Expose
    private Attachment attachment;
    @SerializedName("client")
    @Expose
    private Client client;
    @SerializedName("customer")
    @Expose
    private Customer customer;
    @SerializedName("delivery_price")
    @Expose
    private String deliveryPrice;
    @SerializedName(PrefKeys.id)
    @Expose
    private Integer id;
    @SerializedName("IsDropOff")
    @Expose
    private Boolean isDropOff;
    @SerializedName("order_price")
    @Expose
    private String orderPrice;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Boolean getIsDropOff() {
        return this.isDropOff;
    }

    public void setIsDropOff(Boolean isDropOff) {
        this.isDropOff = isDropOff;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Attachment getAttachment() {
        return this.attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }
}
