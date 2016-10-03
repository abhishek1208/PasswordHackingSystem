package com.example.piyush.passwordhackingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class PasswordGuessingActivity extends AppCompatActivity {

    public static final String TAG = "GuessingActivity";

    Button btn_brute;
    Button btn_dictionary;
    String ETA;
    TextView eta;
    int minR;
    int maxR;
    String startsFrom;
    boolean containsNumber;
    boolean containsUpper;
    boolean containsLower;
    boolean containsSpecial;
    String actualPassword;
    TextView toShowPassword;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_guessing);

        Intent i = getIntent();
        ETA = i.getStringExtra("ETA");
        minR = i.getIntExtra("minRange",0);
        maxR = i.getIntExtra("maxRange",0);
        startsFrom = i.getStringExtra("startsFrom");
        containsUpper = i.getBooleanExtra("containsUpperCase",false);
        containsLower = i.getBooleanExtra("containsLowerCase",false);
        containsNumber = i.getBooleanExtra("containsNumber",false);
        containsSpecial = i.getBooleanExtra("containsSpecialCharacter",false);
        actualPassword = i.getStringExtra("actualPassword");

        eta = (TextView) findViewById(R.id.activity_password_guessing_tv_eta);
        eta.setText(ETA);
        toShowPassword = (TextView) findViewById(R.id.activity_password_guessing_tv_password);

        btn_brute = (Button) findViewById(R.id.activity_password_guessing_btn_bruteFroce);
        btn_dictionary = (Button) findViewById(R.id.activity_password_guessing_btn_dictionary);

        btn_brute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckerPOJO checkerPOJO = new CheckerPOJO(actualPassword,minR,maxR,startsFrom,containsNumber,
                        containsUpper,containsLower,containsSpecial);

                new BruteForceTask() {

                    @Override
                    protected void onPostExecute(Pair pair) {
                        super.onPostExecute(pair);
                        if(pair.checker) {

                            toShowPassword.setText(pair.password);
                        }
                        else{
                            toShowPassword.setText("Parameters Were Wrong");
                        }
                    }
                }.execute(checkerPOJO);

            }
        });

        btn_dictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

}