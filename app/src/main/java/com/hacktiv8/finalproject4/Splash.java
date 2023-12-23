package com.hacktiv8.finalproject4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends AppCompatActivity {


    private static int SPLASH_TIMEOUT = 5000; // Waktu penundaan dalam milidetik (5 detik)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this,LoginActivity.class);
                startActivity(intent);
                startActivity(intent);
                finish();
            }
        }, SPLASH_TIMEOUT);


    }
}