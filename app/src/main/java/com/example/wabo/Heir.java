package com.example.wabo;

public class Heir {
    private String heirsname;
    private String heirsic;
    private String heirsadd;
    private String heirfrom;

    public Heir(String heirsname, String heirsic, String heirsadd, String heirfrom) {
        this.heirsname = heirsname;
        this.heirsic = heirsic;
        this.heirsadd = heirsadd;
        this.heirfrom = heirfrom;
    }

    public Heir() {
    }

    public String getHeirsname() {
        return heirsname;
    }

    public void setHeirsname(String heirsname) {
        this.heirsname = heirsname;
    }

    public String getHeirsic() {
        return heirsic;
    }

    public void setHeirsic(String heirsic) {
        this.heirsic = heirsic;
    }

    public String getHeirsadd() {
        return heirsadd;
    }

    public void setHeirsadd(String heirsadd) {
        this.heirsadd = heirsadd;
    }

    public String getHeirfrom() {
        return heirfrom;
    }

    public void setHeirfrom(String heirfrom) {
        this.heirfrom = heirfrom;
    }
}
