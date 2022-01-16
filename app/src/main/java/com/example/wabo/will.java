package com.example.wabo;

public class will {

    private String willTitle;
    private String willPenerima;
    private String willDescription;

    public will(){}

    public will(String willTitle, String willPenerima, String willDescription) {
        this.willTitle = willTitle;
        this.willPenerima = willPenerima;
        this.willDescription = willDescription;
    }

    public String getWillTitle() {
        return willTitle;
    }

    public String getWillPenerima() {
        return willPenerima;
    }
    public String getWillDescription() {
        return willDescription;
    }

    public void setWillPenerima(String willPenerima) {
        this.willPenerima = willPenerima;
    }

    public void setWillTitle(String willTitle) {
        this.willTitle = willTitle;
    }

    public void setWillDescription(String willDescription) { this.willDescription = willDescription; }

}
