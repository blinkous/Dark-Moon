package com.theankhguide.darkmoon;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface GetNasaMediaData {
    //Specify the request type and pass the relative URL//
    @GET("search?q=" + BuildConfig.ApiKey1)

    //Wrap the response in a Call object with the type of the expected result//
//    Call<RetroNasaMedia> getAllNasaMedia();
//    Call<List<RetroNasaCollection>> getAllNasaMedia(@QueryMap Map<String, String> options); // Call with parameters
    Call<RetroNasaCollection> getAllNasaMedia(@QueryMap Map<String, String> options); // Call with parameters
}
