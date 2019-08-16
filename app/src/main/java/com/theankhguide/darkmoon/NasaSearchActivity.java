package com.theankhguide.darkmoon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class NasaSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nasa_search);
    }
    public void onButtonTap(View view){
        Intent intent = new Intent(this, NasaMediaRecyclerView.class);
        // Grab the search text, convert to string, and put it in the intent
        EditText editText = (EditText) findViewById(R.id.search_text);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
