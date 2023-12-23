package com.smartedge.saee.Networking.Models.OrderDetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes4.dex */
public class Attachment {
    @SerializedName("client_signature")
    @Expose
    private Object clientSignature;
    @SerializedName("customer_signature")
    @Expose
    private String customerSignature;
    @SerializedName("id_image")
    @Expose
    private String idImage;

    public String getCustomerSignature() {
        return this.customerSignature;
    }

    public void setCustomerSignature(String customerSignature) {
        this.customerSignature = customerSignature;
    }

    public Object getClientSignature() {
        return this.clientSignature;
    }

    public void setClientSignature(Object clientSignature) {
        this.clientSignature = clientSignature;
    }

    public String getIdImage() {
        return this.idImage;
    }

    public void setIdImage(String idImage) {
        this.idImage = idImage;
    }
}
