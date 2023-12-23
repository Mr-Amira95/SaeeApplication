package com.smartedge.saee.Networking.Models.Profile;

import com.smartedge.saee.Networking.Basic.PrefKeys;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes11.dex */
public class Data {
    @SerializedName("car_license")
    @Expose
    private String carLicense;
    @SerializedName("drive_license")
    @Expose
    private String driveLicense;
    @SerializedName(PrefKeys.id)
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("national_id")
    @Expose
    private String nationalId;
    @SerializedName("number")
    @Expose
    private String number;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNationalId() {
        return this.nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCarLicense() {
        return this.carLicense;
    }

    public void setCarLicense(String carLicense) {
        this.carLicense = carLicense;
    }

    public String getDriveLicense() {
        return this.driveLicense;
    }

    public void setDriveLicense(String driveLicense) {
        this.driveLicense = driveLicense;
    }
}
