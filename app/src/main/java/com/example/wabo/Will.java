package com.example.wabo;

public class Will {

    String willtitle, willdescription;


    public Will(String willtitle, String willdescription) {
        this.willtitle = willtitle;
        this.willdescription = willdescription;

    }

    public String getwilltitle() {
        return willtitle;
    }

    public void setwilltitle(String willtitle) {
        this.willtitle = willtitle;
    }

    public String getwilldescription() {
        return willdescription;
    }

    public void setwilldescription(String willdescription) {
        this.willdescription = willdescription;
    }

    public Will() {
    }
}