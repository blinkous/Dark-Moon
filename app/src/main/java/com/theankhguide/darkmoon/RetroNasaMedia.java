package com.theankhguide.darkmoon;

import com.google.gson.annotations.SerializedName;

public class RetroNasaMedia {
    // Properties
    @SerializedName("metadata")
    private String metadata;
    @SerializedName("items")
    private RetroNasaMediaItems items;

    // Methods
    // Getters
    public String get_metadata(){
        return metadata;
    }
    public RetroNasaMediaItems get_items(){
        return items;
    }

    // Setters
    public void set_metadata(String metadata){
        this.metadata = metadata;
    }
    public void set_items(RetroNasaMediaItems items){
        this.items = items;
    }

}