package com.theankhguide.darkmoon;

import com.google.gson.annotations.SerializedName;

public class RetroNasaMediaData {
    // Properties
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("date_created")
    private String date_created;
    @SerializedName("media_type")
    private String media_type;
//    @SerializedName("keywords")
//    private RetroNasaKeywords keywords;

    // Getters and Setters

    public String get_title() {
        return title;
    }

    public void set_title(String title) {
        this.title = title;
    }

    public String get_description() {
        return description;
    }

    public void set_description(String description) {
        this.description = description;
    }

    public String get_date_created() {
        return date_created;
    }

    public void set_date_created(String date_created) {
        this.date_created = date_created;
    }

    public String get_media_type() {
        return media_type;
    }

    public void set_media_type(String media_type) {
        this.media_type = media_type;
    }
}
