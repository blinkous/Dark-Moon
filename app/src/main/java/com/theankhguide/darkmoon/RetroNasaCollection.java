package com.theankhguide.darkmoon;

import com.google.gson.annotations.SerializedName;

public class RetroNasaCollection {
    // Properties
    @SerializedName("collection")
    private RetroNasaMedia collection;

    // Methods
    // Getters
    public RetroNasaMedia get_collection(){
        return collection;
    }

    // Setters
    public void set_collection(RetroNasaMedia collection){
        this.collection = collection;
    }
}
