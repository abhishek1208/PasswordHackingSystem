package com.example.piyush.passwordhackingsystem.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WelcomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


         android.os.Handler handler;
        Runnable delayRunnable;
        handler=new android.os.Handler();
        delayRunnable=new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(i);
                finish();
            }
        };
        handler.postDelayed(delayRunnable,1200);

    }
}
