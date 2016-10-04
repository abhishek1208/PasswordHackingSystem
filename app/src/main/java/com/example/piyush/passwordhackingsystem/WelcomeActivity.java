package com.example.piyush.passwordhackingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
         android.os.Handler handler;
        Runnable delayRunnable;
        handler=new android.os.Handler();
        delayRunnable=new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(i);

            }
        };
        handler.postDelayed(delayRunnable,2000);



    }
}
