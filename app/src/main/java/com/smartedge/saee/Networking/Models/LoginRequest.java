package com.smartedge.saee.Networking.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes10.dex */
public class LoginRequest {
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("password")
    @Expose
    private String password;

    public LoginRequest(String number, String password) {
        this.number = number;
        this.password = password;
    }
}
