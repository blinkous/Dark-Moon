package com.theankhguide.darkmoon;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitNasaClient {
    private static Retrofit retrofit;

    //Define the base URL//
    private static final String BASE_URL = "https://api.nasa.gov/";

    // Create the Retrofit instance
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)

                    // Add the converter
                    .addConverterFactory(GsonConverterFactory.create())

                    // Build the Retrofit instance
                    .build();
        }
        return retrofit;
    }
}
