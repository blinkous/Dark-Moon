package com.theankhguide.darkmoon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private MyNasaMAdapter MyNasaMAdapter;
    private RecyclerView myRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nasa_media_recycler_view);

        // Grab the search text "message" from the intent that started this activity
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        Log.d("search", "message: *" + message + "*");

        //Create a handler for the RetrofitInstance interface//
        GetNasaMediaData service = Retro.getRetrofitInstance().create(GetPlantData.class);

        // Create a hash map to store key value pairs for filtering the data in the api call
        Map<String, String> data = new HashMap<>();
        data.put("q", message);
        Call<List<RetroPlant>> call = service.getAllPlants(data);


        /**GETTING THE NASA DATA & DISPLAYING IT*/
        //Create a handler for the RetrofitInstance interface//
        GetNasaMediaData service = RetrofitNasaClient.getRetrofitInstance().create(GetNasaApodData.class);
        Log.d("nasa", "created get nasa data service");

        Call<RetroNasa> call = service.getAllNasaMedia();

        //Execute the request asynchronously//
        call.enqueue(new Callback<RetroNasa>() {
            //Handle a successful response//
            @Override
            public void onResponse(Call<RetroNasa> call, Response<RetroNasa> response) {
                loadDataList(response.body());
            }

            //Handle execution failures//
            @Override
            public void onFailure(Call<RetroNasa> call, Throwable throwable) {
                //If the request fails, then display the following toast//
                Toast.makeText(MainActivity.this, "Unable to load astronomical data", Toast.LENGTH_SHORT).show();
                Log.d("nasa", "onFailure:" + throwable);
            }
        });
    }

    private void loadDataList (RetroNasa nasaList) {
        Log.d("nasa", "**loading the data**");
        // Get references to the objects in the layout
        TextView textTitle = findViewById(R.id.textTitle);
        TextView desc = findViewById(R.id.textDesc);
        ImageView imageView = findViewById(R.id.imageView);

        // Setting the layout objects
        textTitle.setText(nasaList.get_title());
        desc.setText(nasaList.get_explanation());
        Picasso.get().load(nasaList.get_url()).into(imageView);
    }
}
