package com.example.intentssample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button, buttonOpenIntentsActivity;
    TextView textUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textUsername = findViewById(R.id.textUsername);

        button  = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //we can use Intent to open another activity
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);

                //we can put some additional information in the intent before opening the new activity...
                intent.putExtra("username", textUsername.getText().toString());

                startActivity(intent);
            }
        });

        buttonOpenIntentsActivity = findViewById(R.id.buttonOpenIntentsActivity);
        buttonOpenIntentsActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), IntentsActivity.class));
            }
        });
    }
}