package com.example.intentssample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        //we can set the title of the activity here...
        //setTitle("Title Of Activity");

        //or, we can set it in the Android manifest...

        textView = findViewById(R.id.textView);

        //we can just get what has been stored in the intent,
        //and then check for the string we want by the key we stored it in
        //but it's best to check if the information is in the intent before trying to retrieve it, else, the app is likely to crash!

        //checking if there's any information in the intent...
        if(getIntent() != null){
            //retrieve the string, which we stored with the key "username" from MainActivity.java
            String retrievedValue = getIntent().getStringExtra("username");
            //for example, we could use it to welcome the user...
            textView.setText("Welcome, " + retrievedValue);
        }

    }
}