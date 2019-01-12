package com.example.bhaskar.database;

public class Artist {
    String aid;
    String aname;
    String agenre;

    public  Artist() {

    }

    public Artist(String aid, String aname, String agenre) {
        this.aid = aid;
        this.aname = aname;
        this.agenre = agenre;
    }

    public String getAid() {
        return aid;
    }

    public String getAname() {
        return aname;
    }

    public String getAgenre() {
        return agenre;
    }
}
