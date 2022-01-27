package com.example.pissh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayIPAddressActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_ipaddress);

        // Gets the Intent that created the View
        Intent intent = getIntent();
        // Grab the ip address stored from form
        String ipAddress = intent.getStringExtra(MainActivity.EXTRA_IP_ADDRESS);
        // Set the TextView's text
        TextView textView = findViewById(R.id.textView);
        textView.setText(ipAddress);
    }
}