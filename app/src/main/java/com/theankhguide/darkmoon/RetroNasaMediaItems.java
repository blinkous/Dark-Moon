package com.theankhguide.darkmoon;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RetroNasaMediaItems {
    // Properties
    @SerializedName("links")
    private List<RetroNasaMediaLinks> links;

    @SerializedName("data")
    private RetroNasaMediaData data;

    // Methods
    // Getters
    public RetroNasaMediaLinks get_links(){
        return (RetroNasaMediaLinks) links;
    }
    public RetroNasaMediaData get_data(){
        return data;
    }

    // Setters
    public void set_links(RetroNasaMediaLinks links){
        this.links = (List<RetroNasaMediaLinks>) links;
    }
    public void set_data(RetroNasaMediaData data){
        this.data = data;
    }

}
