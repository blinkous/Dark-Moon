package com.theankhguide.darkmoon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**GETTING THE NASA DATA & DISPLAYING IT*/
        //Create a handler for the RetrofitInstance interface//
        GetNasaData service = RetroNasaClient.getRetrofitInstance().create(GetNasaData.class);
        Log.d("nasa", "created get nasa data service");

        Call<RetroNasa> call = service.getAllNasa();

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
