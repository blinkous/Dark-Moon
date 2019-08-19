package com.theankhguide.darkmoon;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RetroNasaMediaItems {
    // Properties
    @SerializedName("links")
    private List<RetroNasaMediaLinks> links;

    @SerializedName("data")
    private List<RetroNasaMediaData> data;

    // Methods
    // Getters
    public List<RetroNasaMediaLinks> get_links(){
        return (List<RetroNasaMediaLinks>) links;
    }
    public List<RetroNasaMediaData> get_data(){
        return (List<RetroNasaMediaData>) data;
    }

    // Setters
    public void set_links(RetroNasaMediaLinks links){
        this.links = (List<RetroNasaMediaLinks>) links;
    }
    public void set_data(RetroNasaMediaData data){
        this.data = (List<RetroNasaMediaData>) data;
    }

}
