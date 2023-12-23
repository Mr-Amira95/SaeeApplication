package com.smartedge.saee.Networking.Models;

/* loaded from: classes10.dex */
public class UserInputModel {
    private boolean confirmed;
    private boolean delivered;
    private String end_date;
    private boolean received;
    private boolean rejected;
    private boolean requested;
    private String start_date;
    private String type;
    private boolean at_company;
    public UserInputModel(boolean requested, boolean rejected, boolean received, boolean delivered, boolean atCompany, boolean confirmed, String start_date, String end_date, String type) {
        this.requested = requested;
        this.rejected = rejected;
        this.received = received;
        this.delivered = delivered;
        this.confirmed = confirmed;
        this.start_date = start_date;
        this.end_date = end_date;
        this.type = type;
    }

    public boolean isRequested() {
        return this.requested;
    }

    public void setRequested(boolean requested) {
        this.requested = requested;
    }

    public boolean isRejected() {
        return this.rejected;
    }

    public void setRejected(boolean rejected) {
        this.rejected = rejected;
    }

    public boolean isReceived() {
        return this.received;
    }

    public void setReceived(boolean received) {
        this.received = received;
    }

    public boolean isDelivered() {
        return this.delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public boolean isAt_company() {
        return this.at_company;
    }

    public void setAt_company(boolean at_company) {
        this.at_company = at_company;
    }

    public boolean isConfirmed() {
        return this.confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getStart_date() {
        return this.start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return this.end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
