package com.example.wabo;

public class Will {

    String willTitle, willDescription;


    public Will(String willTitle, String willDescription) {
        this.willTitle = willTitle;
        this.willDescription = willDescription;
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
}