package com.example.user.bakingtime;

/**
 * Created by user on 6/13/2017.
 */

public class Recepie {
    String Url;
    String name;

    Recepie(String Url,String name){
        this.Url=Url;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return Url;
    }
}
