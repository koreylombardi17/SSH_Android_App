package com.example.pissh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.apache.sshd.client.SshClient;

public class SshStatusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssh_status);

        // Gets the Intent that created the View
        Intent intent = getIntent();
        // Grab the ip address stored from form
        String ipAddress = intent.getStringExtra(MainActivity.EXTRA_IP_ADDRESS);
        String password = intent.getStringExtra(MainActivity.EXTRA_PASSWORD);
        // Set the TextView's text
        TextView textView = findViewById(R.id.textView);
        textView.setText(ipAddress);

        // Must manually set user.home in android apps that require resources
        String key = "user.home";
        Context SystemContext = getApplicationContext();
        String value = SystemContext.getApplicationInfo().dataDir;
        System.setProperty(key, value);

        // Todo: Finish client connection. Read through SshClient.java documentation at top of file.
        SshClient client = SshClient.setUpDefaultClient();
    }
}