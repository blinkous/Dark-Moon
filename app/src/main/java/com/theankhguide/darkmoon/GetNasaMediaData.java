package com.theankhguide.darkmoon;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetNasaMediaData {
    //Specify the request type and pass the relative URL//
    @GET("search?q=" + BuildConfig.ApiKey1)

    //Wrap the response in a Call object with the type of the expected result//
    Call<RetroNasa> getAllNasa();
//    Call<List<RetroNasa>> getAllNasa(@QueryMap Map<String, String> options); // Call with parameters
}
