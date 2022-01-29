package com.example.pissh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_IP_ADDRESS = "com.example.pissh.IP_ADDRESS";
    public static final String EXTRA_PASSWORD = "com.example.pissh.PASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Called when user clicks send button
    public void makeSSHConnection(View view) {
        Intent intent = new Intent(this, SshStatusActivity.class);

        EditText editIPAddress = (EditText) findViewById(R.id.editIpAddress);
        EditText editPassword = (EditText) findViewById(R.id.editPassword);

        String ipAddress = editIPAddress.getText().toString();
        String password = editPassword.getText().toString();

        intent.putExtra(EXTRA_IP_ADDRESS, ipAddress);
        intent.putExtra(EXTRA_PASSWORD, password);

        startActivity(intent);
    }
}