package com.theankhguide.darkmoon;

import com.google.gson.annotations.SerializedName;

public class RetroNasaMediaItems {
    // Properties
    @SerializedName("links")
    private RetroNasaMediaLinks links;

    @SerializedName("data")
    private RetroNasaMediaData data;

    // Methods
    // Getters
    public RetroNasaMediaLinks get_links(){
        return links;
    }
    public String get_items(){
        return items;
    }

    // Setters
    public void set_links(RetroNasaMediaLinks links){
        this.links = links;
    }
    public void set_(String items){
        this.items = items;
    }

}
