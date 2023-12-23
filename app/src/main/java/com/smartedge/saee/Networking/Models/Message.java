package com.smartedge.saee.Networking.Models;

import androidx.core.app.NotificationCompat;

import com.google.firebase.database.core.ServerValues;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes10.dex */
public class Message {
    @SerializedName("adminSeen")
    @Expose
    private Boolean adminSeen;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("receiverId")
    @Expose
    private String receiverId;
    @SerializedName("senderId")
    @Expose
    private String senderId;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    @Expose
    private String status;
    @SerializedName(ServerValues.NAME_OP_TIMESTAMP)
    @Expose
    private Long timestamp;

    public Boolean getAdminSeen() {
        return this.adminSeen;
    }

    public void setAdminSeen(Boolean adminSeen) {
        this.adminSeen = adminSeen;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiverId() {
        return this.receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getSenderId() {
        return this.senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Message(Boolean adminSeen, String message, String receiverId, String senderId, String status, Long timestamp) {
        this.adminSeen = adminSeen;
        this.message = message;
        this.receiverId = receiverId;
        this.senderId = senderId;
        this.status = status;
        this.timestamp = timestamp;
    }

    public Message() {
    }
}
