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
    public RetroNasaMediaData get_data(){
        return data;
    }

    // Setters
    public void set_links(RetroNasaMediaLinks links){
        this.links = links;
    }
    public void set_data(RetroNasaMediaData data){
        this.data = data;
    }

}
