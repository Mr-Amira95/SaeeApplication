package com.smartedge.saee.Networking.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes10.dex */
public class Location {
    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("lat")
    @Expose
    private String lat;

    public Location(String lat, String lang) {
        this.lat = lat;
        this.lang = lang;
    }

    public String getLat() {
        return this.lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLang() {
        return this.lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
