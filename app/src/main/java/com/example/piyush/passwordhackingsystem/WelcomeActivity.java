package com.example.piyush.passwordhackingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

public class WelcomeActivity extends AppCompatActivity {

    TextView textView;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        relativeLayout = (RelativeLayout) findViewById(R.id.activity_welcome);
        textView = (TextView) findViewById(R.id.textView6);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });

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
