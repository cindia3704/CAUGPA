package com.example.caugpa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TextView gotoMainText = findViewById(R.id.splash_start);
        gotoMainText.setOnClickListener(view -> {
            Intent intentToMain = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intentToMain);
        });

    }



}