package com.theankhguide.darkmoon;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
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

        getApod();

        /**Bottom Navigation*/
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        // Setting the listener for when something is selected in the bottom nav
//        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.action_apod:
//                        // do something here
//                        return true;
//                    case R.id.action_other:
//                        // do something here
//                        onTapSearch();
//                        return true;
//                    default: return true;
//                }
//            }
//        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_apod:
                        // do something here
                        return true;
                    case R.id.action_search:
                        // do something here
//                        onTapSearch();
                        return true;
                    default: return true;
                }
            }
        });
    }

    public void getApod(){
        /**Getting the NASA APOD data and display it*/
        //Create a handler for the RetrofitInstance interface//
        GetNasaApodData service = RetrofitNasaClient.getRetrofitInstance().create(GetNasaApodData.class);

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
                Toast.makeText(MainActivity.this, "Unable to load APOD data", Toast.LENGTH_SHORT).show();
                Log.d("nasa", "onFailure:" + throwable);
            }
        });
    }

    public void onTapSearch(){
        Intent intent = new Intent(this, NasaSearchActivity.class);
        startActivity(intent);
    }

    private void loadDataList (RetroNasa nasaList) {
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
