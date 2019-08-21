package com.theankhguide.darkmoon;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
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


        /**Swipe gesture listeners*/
//        View myView = (View) findViewById(R.id.container_view);
        TextView myView = (TextView) findViewById(R.id.textTopTitle);
        myView.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeDown() {
                Toast.makeText(MainActivity.this, "Down", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipeLeft() {
                Toast.makeText(MainActivity.this, "Left", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipeUp() {
                Toast.makeText(MainActivity.this, "Up", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSwipeRight() {
                Toast.makeText(MainActivity.this, "Right", Toast.LENGTH_SHORT).show();
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
                if(response.body() != null) {
                    loadDataList(response.body());
                }
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
        if(!nasaList.get_title().isEmpty() && !nasaList.get_explanation().isEmpty() && !nasaList.get_url().isEmpty()){
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
}
