package com.theankhguide.darkmoon;

import com.google.gson.annotations.SerializedName;

public class RetroNasaMediaLinks {
    // Properties
    @SerializedName("rel")
    private String rel;
    @SerializedName("href")
    private String href;
    @SerializedName("render")
    private String render;

    // Methods
    // Getters
    public String getRel(){
        return rel;
    }
    public String get_href(){
        return href;
    }
    public String get_render(){
        return render;
    }

    // Setters
    public void set_rel(String rel){
        this.rel = rel;
    }
    public void set_href(String href){
        this.href = href;
    }
    public void set_render(String render){
        this.render = render;
    }

}
