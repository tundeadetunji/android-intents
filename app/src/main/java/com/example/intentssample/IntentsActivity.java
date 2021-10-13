package com.example.intentssample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntentsActivity extends AppCompatActivity {

    Button buttonShareData, buttonViewMap, buttonSendEmail, buttonSendSMSText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intents);

        buttonShareData = findViewById(R.id.buttonShareData);
        buttonShareData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //assuming we want to send a piece of text to whatever application on the
                //user's device that can handle it, e.g. SMS, Email, WhatsApp
                String whatToShare = "hi, there!";

                //we can create an intent to do this
                Intent intent = new Intent();

                //setting the intent to be able to get data out of the app to other apps
                intent.setAction(Intent.ACTION_SEND);
                //adding what we want to send into the intent, and setting it as string, so
                //the system knows better how to handle it
                intent.putExtra(Intent.EXTRA_TEXT, whatToShare);
                intent.setType("text/plain");
                //telling Android system to take over...
                startActivity(intent);
            }
        });

        buttonViewMap = findViewById(R.id.buttonViewMap);
        buttonViewMap.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("QueryPermissionsNeeded")
            @Override
            public void onClick(View v) {
                //One way to construct the string that represents the area
                //we want to view on the map, is longitude/latitude
                String geoLoc = "geo:3.939786,7.376736"; //long/lat for Ibadan
                //we can view the map via an app on the user's device that can handle it
                //by using Intent
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(geoLoc));
                //we should check if it can be done in the first place
                if(intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }
            }
        });

        buttonSendEmail = findViewById(R.id.buttonSendEmail);
        buttonSendEmail.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("QueryPermissionsNeeded")
            @Override
            public void onClick(View v) {
                //destination address...
                String [] address = {"email@provider.com"};

                //we can get the Android system to find an application that can send the email
                //using Intent
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                //defining properties of the email
                intent.putExtra(Intent.EXTRA_EMAIL, address);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Subject of the email here...");
                //only email apps should handle this intent...
                intent.setData(Uri.parse("mailto:"));
                //request that the email is sent (while, actually, the user likely has the option to choose which application should do it...)
                if (intent.resolveActivity(getPackageManager()) != null){
                    startActivity(intent);
                }
            }
        });

        buttonSendSMSText = findViewById(R.id.buttonSendSMSText);
        buttonSendSMSText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "hello...";
                composeMessage(message, null); //we pass null for attachment, since we're not sending MMS
            }
        });
    }

    @SuppressLint("QueryPermissionsNeeded")
    public void composeMessage(String message, Uri attachment){
        //we can use Intent to attempt to send text on the phone
        Intent intent = new Intent(Intent.ACTION_SEND);
        //setting properties of the intent, which is intended to access SMS service on the phone...
        intent.putExtra("sms_body", message);
        //assuming it's MMS, we can add media attachment...
        //intent.putExtra(Intent.EXTRA_STREAM, attachment);
        //let's ensure that only apps that can handle SMS, should respond to this intent
        intent.setData(Uri.parse("smsto:"));
        //finally, let's attempt to send the text, by checking if there's any app that can handle it...
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
        //we should actually request permission...
    }
}