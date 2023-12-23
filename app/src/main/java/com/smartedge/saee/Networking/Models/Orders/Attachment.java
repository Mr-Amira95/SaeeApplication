package com.smartedge.saee.Networking.Models.Orders;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes7.dex */
public class Attachment {
    @SerializedName("client_signature")
    @Expose
    private String clientSignature;
    @SerializedName("customer_signature")
    @Expose
    private String customerSignature;
    @SerializedName("id_image")
    @Expose
    private String idImage;

    public String getClientSignature() {
        return this.clientSignature;
    }

    public void setClientSignature(String clientSignature) {
        this.clientSignature = clientSignature;
    }

    public String getCustomerSignature() {
        return this.customerSignature;
    }

    public void setCustomerSignature(String customerSignature) {
        this.customerSignature = customerSignature;
    }

    public String getIdImage() {
        return this.idImage;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
    }
}
