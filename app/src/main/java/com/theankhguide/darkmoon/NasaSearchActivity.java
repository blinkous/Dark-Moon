package com.theankhguide.darkmoon;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NasaSearchActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.theankhguide.darkmoon.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nasa_search);

        // To make the bottom navigation work:
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        // Setting the listener for when something is selected in the bottom nav
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_apod:
                        // do something here
                        Toast.makeText(NasaSearchActivity.this, "Apod...", Toast.LENGTH_SHORT).show();
                        onTapApod();
                        return true;
                    case R.id.action_search:
                        // do something here
                        Toast.makeText(NasaSearchActivity.this, "Other...", Toast.LENGTH_SHORT).show();
                        return true;
                    default: return true;
                }
            }
        });
    }

    public void onTapApod(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

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
            Toast.makeText(NasaSearchActivity.this, "Please enter search criteria.", Toast.LENGTH_SHORT).show();
        }
    }
}
