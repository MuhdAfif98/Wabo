package com.example.wabo;

public class Will {

    String willTitle, willDescription, willPenerima;


    public Will(String willTitle, String willDescription, String willPenerima) {
        this.willTitle = willTitle;
        this.willDescription = willDescription;
        this.willPenerima = willPenerima;
    }

    public String getwilltitle() {
        return willTitle;
    }

    public void setwilltitle(String willTitle) {
        this.willTitle = willTitle;
    }

    public String getwilldescription() {
        return willDescription;
    }

    public void setwilldescription(String willDescription) { this.willDescription = willDescription; }

    public Will() {
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