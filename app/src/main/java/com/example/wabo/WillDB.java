package com.example.wabo;

public class WillDB {

    private String willIC;
    private String willStatus;

    public WillDB() {
    }

    public WillDB(String willIC, String willStatus) {
        this.willIC = willIC;
        this.willStatus = willStatus;
    }

    public String getWillIC() {
        return willIC;
    }

    public void setWillIC(String willIC) {
        this.willIC = willIC;
    }

    public String getWillStatus() {
        return willStatus;
    }

    public void setWillStatus(String willStatus) {
        this.willStatus = willStatus;
    }
}
