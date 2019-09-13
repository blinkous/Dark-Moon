package com.theankhguide.darkmoon;

import android.content.Intent;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NasaSearchFragment.OnFragmentInteractionListener, ApodFragment.OnFragmentInteractionListener{
    private static final String EXTRA_MESSAGE = "com.theankhguide.darkmoon.MESSAGE";

    /** Using fragment variables to contain each fragment: used to maintain the state of the fragment when switching*/
    private final Fragment apodFragment = new ApodFragment();
    private final Fragment searchFragment = new NasaSearchFragment();
    private Fragment activeFragment = apodFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**Bottom Navigation*/
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavSelectListener);

        /** Adding the fragments to the fragment manager, and hiding the inactive fragments: used to maintain fragment state*/
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, searchFragment).hide(searchFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,apodFragment).commit();
    }

    //    WAS IN ON CREATE METHOD: Swipe gesture listeners
        /**Swipe gesture listeners*/
/*//        View myView = (View) findViewById(R.id.container_view);
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
        });*/

    /** Setting the listener for when something is selected in the bottom nav */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavSelectListener =
        new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                /** Switching between fragments by hiding the current one,
                 * showing the selected and setting the selected to active
                 * in order to save the state of each fragment */
                case R.id.action_apod:
                    Log.d("a", "selected apod");
                    getSupportFragmentManager().beginTransaction().hide(activeFragment).show(apodFragment).commit();
                    activeFragment = apodFragment;
                    return true; // Return true to indicate that we want to select the item

                case R.id.action_search:
                    Log.d("a", "selected search");
                    getSupportFragmentManager().beginTransaction().hide(activeFragment).show(searchFragment).commit();
                    activeFragment = searchFragment;
                    return true;
            }
            return false;
        }
    };

/*
    private void getApod(){
        */
/**Getting the NASA APOD data and display it*//*

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
*/

    public void onSearchButtonTap(View view){
        Intent intent = new Intent(this, NasaMediaRecyclerView.class);
        // Grab the search text, convert to string, and put it in the intent
        EditText editText = (EditText) findViewById(R.id.media_search_text);
        String message = editText.getText().toString();
        if(!message.isEmpty()) {
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Please enter search criteria.", Toast.LENGTH_SHORT).show();
        }
        Log.d("a", "onSearchButtonTap: ***YOU'VE CLICKED SEARCH****");
    }

/*    private void loadDataList (RetroNasa nasaList) {
        if(!nasaList.get_title().isEmpty() && !nasaList.get_explanation().isEmpty() && !nasaList.get_url().isEmpty()){
            // Get references to the objects in the layout
            TextView textTitle = findViewById(R.id.textTitle);
            TextView desc = findViewById(R.id.textDesc);
            ImageView imageView = findViewById(R.id.imageView);

            // Setting the layout objects
            textTitle.setText(nasaList.get_title());
            desc.setText(nasaList.get_explanation());
            Picasso.get().load(nasaList.get_url()).into(imageView);
//            Log.d("home", "loadDataList: **********" + nasaList.get_url() + "*************");
        }
    }*/

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.d("frag", "onFragmentInteraction: YOU INTERACTED WITH FRAGMENT");
    }
}
