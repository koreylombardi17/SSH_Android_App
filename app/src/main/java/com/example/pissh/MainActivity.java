package com.example.pissh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_IP_ADDRESS = "com.example.pissh.IP_ADDRESS";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Called when user clicks send button
    public void sendIPAddress(View view) {
        Intent intent = new Intent(this, DisplayIPAddressActivity.class);
        EditText editText = (EditText) findViewById(R.id.editIpAddress);
        String ipAddress = editText.getText().toString();
        intent.putExtra(EXTRA_IP_ADDRESS, ipAddress);
        startActivity(intent);
    }
}