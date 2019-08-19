package com.theankhguide.darkmoon;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NasaMediaRecyclerView extends AppCompatActivity {

    private MyNasaMediaAdapter myNasaMAdapter;
    private RecyclerView myRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nasa_media_recycler_view);

        // Grab the search text "message" from the intent that started this activity
        Intent intent = getIntent();
        String message = intent.getStringExtra(NasaSearchActivity.EXTRA_MESSAGE);
//        String message = "apollo 11";
        Log.d("search", "message: *" + message + "*");

        //Create a handler for the RetrofitInstance interface//
        GetNasaMediaData service = RetrofitNasaMediaClient.getRetrofitInstance().create(GetNasaMediaData.class);

        // Create a hash map to store key value pairs for filtering the data in the api call
        Map<String, String> data = new HashMap<>();
        data.put("q", message);
        Log.d("search", "message: *" + data + "*");

        Call<RetroNasaCollection> call = service.getAllNasaMedia(data);

        //Execute the request asynchronously//
        call.enqueue(new Callback<RetroNasaCollection>() {

            //Handle a successful response//
            @Override
            public void onResponse(Call<RetroNasaCollection> call, Response<RetroNasaCollection> response) {
                loadDataList(response.body());
                Log.d("search", "RESPONSE: *" + response.body() + "*");

            }

            //Handle execution failures//
            @Override
            public void onFailure(Call<RetroNasaCollection> call, Throwable throwable) {
                //If the request fails, then display the following toast//
                Log.d("search", "failure: *" + throwable + "*");

                Toast.makeText(NasaMediaRecyclerView.this, "Unable to load search", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadDataList (RetroNasaCollection nasaList) {
        //Get a reference to the RecyclerView//
        myRecyclerView = findViewById(R.id.myRecyclerView);
        myNasaMAdapter = new MyNasaMediaAdapter(nasaList);

        //Use a LinearLayoutManager with default vertical orientation//
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(NasaMediaRecyclerView.this);
        myRecyclerView.setLayoutManager(layoutManager);

        //Set the Adapter to the RecyclerView//
        myRecyclerView.setAdapter(myNasaMAdapter);
    }
}
