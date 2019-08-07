package com.theankhguide.darkmoon;

import com.google.gson.annotations.SerializedName;

public class RetroNasa {
    // Properties
    @SerializedName("url")
    private String url;
    @SerializedName("title")
    private String title;
    @SerializedName("explanation")
    private String explanation;

    // Methods
    // Getters
    public String get_url(){
        return url;
    }
    public String get_title(){
        return title;
    }
    public String get_explanation(){
        return explanation;
    }

    // Setters
    public void set_url(String url){
        this.url = url;
    }
    public void set_title(String title){
        this.title = title;
    }
    public void set_explanation(String explanation){
        this.explanation = explanation;
    }

}
