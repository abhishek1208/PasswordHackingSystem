package com.example.piyush.passwordhackingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

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
    String totalPermutations;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_guessing);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(100);

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
        totalPermutations = i.getStringExtra("totalPermutations");
        final BigInteger totalPers = new BigInteger(totalPermutations);
        eta = (TextView) findViewById(R.id.activity_password_guessing_tv_eta);
        eta.setText(ETA);
        toShowPassword = (TextView) findViewById(R.id.activity_password_guessing_tv_password);

        btn_brute = (Button) findViewById(R.id.activity_password_guessing_btn_bruteFroce);
        btn_dictionary = (Button) findViewById(R.id.activity_password_guessing_btn_dictionary);



        btn_brute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final long startTime=System.currentTimeMillis();

                CheckerPOJO checkerPOJO = new CheckerPOJO(actualPassword,minR,maxR,startsFrom,containsNumber,
                        containsUpper,containsLower,containsSpecial);

                if(!actualPassword.startsWith(startsFrom)){
                    toShowPassword.setText("Parameters Were Wrong");
                }
                else{
                new BruteForceTask(totalPermutations) {

                    @Override
                    protected void onPostExecute(Pair pair) {
                         long endTime;

                        super.onPostExecute(pair);
                        if(pair.checker) {

                            toShowPassword.setText(pair.password);
                        }
                        else{
                            toShowPassword.setText("Parameters Were Wrong");
                        }
                        endTime=System.currentTimeMillis();
                        Log.d(TAG, "onPostExecute: Time taken"+String.valueOf(endTime-startTime));
                    }

                    @Override
                    protected void onProgressUpdate(BigInteger... values) {
                        super.onProgressUpdate(values);
//                        BigDecimal decimalCount = new BigDecimal(values[0]);
//                        BigDecimal decimalTotalPers = new BigDecimal(totalPers);
//
////                        BigDecimal perc = decimalCount.divideToIntegralValue(decimalTotalPers);
//                        BigDecimal perc = decimalCount.divide(decimalTotalPers,2, RoundingMode.HALF_UP);
//                        perc = perc.multiply(new BigDecimal("100"));
//
//                        int prog = Integer.valueOf(perc.toBigInteger().toString());

                        progressBar.setProgress(Integer.valueOf(values[1].toString()));
                    }
                }.execute(checkerPOJO);}

            }
        });

        btn_dictionary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new DictionaryReadTask() {
                    @Override
                    protected void onPostExecute(Pair pair) {
                        super.onPostExecute(pair);
                        if(pair.checker) {

                            toShowPassword.setText(pair.password);
                        }
                        else{
                            toShowPassword.setText("Not in dictionary");
                        }
                    }
                }.execute(actualPassword);

            }
        });

    }

}
