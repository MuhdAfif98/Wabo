package com.example.wabo;

public class WillDB {

    private String willIC;
    private String willStatus;
    private String willPenerima;
    private String willDescription;
    private String willJumlah;
    private String willTitle;

    public String getWillOwner() {
        return willOwner;
    }

    public void setWillOwner(String willOwner) {
        this.willOwner = willOwner;
    }

    private String willOwner;

    public WillDB() {
    }

    public WillDB(String willIC, String willStatus, String willPenerima, String willDescription, String willJumlah, String willTitle) {
        this.willIC = willIC;
        this.willStatus = willStatus;
        this.willPenerima = willPenerima;
        this.willDescription = willDescription;
        this.willJumlah = willJumlah;
        this.willTitle = willTitle;
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

    public String getWillPenerima() {
        return willPenerima;
    }

    public void setWillPenerima(String willPenerima) {
        this.willPenerima = willPenerima;
    }

    public String getWillDescription() {
        return willDescription;
    }

    public void setWillDescription(String willDescription) {
        this.willDescription = willDescription;
    }

    public String getWillJumlah() {
        return willJumlah;
    }

    public void setWillJumlah(String willJumlah) {
        this.willJumlah = willJumlah;
    }

    public String getWillTitle() {
        return willTitle;
    }

    public void setWillTitle(String willTitle) {
        this.willTitle = willTitle;
    }
}
