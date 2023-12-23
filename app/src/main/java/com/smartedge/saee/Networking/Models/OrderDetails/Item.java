package com.smartedge.saee.Networking.Models.OrderDetails;

import androidx.core.app.NotificationCompat;

import com.smartedge.saee.Networking.Basic.PrefKeys;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes4.dex */
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
    @SerializedName("dates")
    @Expose
    private Dates dates;
    @SerializedName("delivery_on_customer")
    @Expose
    private Integer deliveryOnCustomer;
    @SerializedName("delivery_price")
    @Expose
    private String deliveryPrice;
    @SerializedName(PrefKeys.id)
    @Expose
    private Integer id;
    @SerializedName("IsDropOff")
    @Expose
    private Boolean isDropOff;
    @SerializedName("note")
    @Expose
    private String note;
    @SerializedName("order_price")
    @Expose
    private String orderPrice;
    @SerializedName("ref_code")
    @Expose
    private String refCode;
    @SerializedName("rejected_reason")
    @Expose
    private String rejectedReason;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRefCode() {
        return this.refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
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

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getRejectedReason() {
        return this.rejectedReason;
    }

    public void setRejectedReason(String rejectedReason) {
        this.rejectedReason = rejectedReason;
    }

    public Integer getDeliveryOnCustomer() {
        return this.deliveryOnCustomer;
    }

    public void setDeliveryOnCustomer(Integer deliveryOnCustomer) {
        this.deliveryOnCustomer = deliveryOnCustomer;
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

    public Dates getDates() {
        return this.dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public Attachment getAttachment() {
        return this.attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }
}
