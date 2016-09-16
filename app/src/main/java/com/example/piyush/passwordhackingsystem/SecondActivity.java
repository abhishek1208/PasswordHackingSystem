package com.example.piyush.passwordhackingsystem;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class SecondActivity extends AppCompatActivity {

    int minRange, maxRange;
    String startsFrom;
    boolean containsLowerCase, containsUpperCase, containsNumber, containsSpecialCharacter;

    EditText minR, maxR, passwordStartFrom;
    CheckBox lowerCase, upperCase, number, specialCharacter;
    Button submit;

    BigInteger totalPermutations;
    BigDecimal ETA;

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        this.minR = (EditText) findViewById(R.id.second_activity_et_minRange);
        this.maxR = (EditText) findViewById(R.id.second_activity_et_maxRange);
        this.passwordStartFrom = (EditText) findViewById(R.id.second_activity_et_startsFrom);
        this.lowerCase = (CheckBox) findViewById(R.id.second_activity_cb_lowerCase);
        this.upperCase = (CheckBox) findViewById(R.id.second_activity_cb_upperCase);
        this.number = (CheckBox) findViewById(R.id.second_activity_cb_number);
        this.specialCharacter = (CheckBox) findViewById(R.id.second_activity_cb_specialCharacter);
        this.submit = (Button) findViewById(R.id.second_activity_btn_submit);




        this.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                minRange = (Integer.valueOf(minR.getText().toString()));
                maxRange = (Integer.valueOf(maxR.getText().toString()));
                startsFrom = passwordStartFrom.getText().toString();
                containsLowerCase = lowerCase.isChecked();
                containsUpperCase = upperCase.isChecked();
                containsNumber = number.isChecked();
                containsSpecialCharacter =specialCharacter.isChecked();

                totalPermutations = getTotalPermutations();
                ETA = getETA();

                tv = (TextView) findViewById(R.id.test);
                tv.setText(ETA.toString());
            }
        });


    }

    public BigInteger getTotalPermutations() {


        BigInteger retVal = new BigInteger("0");
        Integer totalPossibilities = 0;

        if (containsLowerCase) {
            totalPossibilities += 26;
        }

        if (containsUpperCase) {
            totalPossibilities += 26;
        }

        if (containsNumber) {
            totalPossibilities += 10;
        }

        if (containsSpecialCharacter) {
            totalPossibilities += 10;
        }

        int lengthOfStartsFrom = this.startsFrom.length();
        int temp = this.minRange;
        BigInteger total = new BigInteger(totalPossibilities.toString());
        while (temp <= this.maxRange) {

            Integer spots = temp - lengthOfStartsFrom;

            if (spots > 0) {

                Integer tempForSpots = spots;
                BigInteger toBeAdded = new BigInteger("1");
                while(tempForSpots > 0) {

                    toBeAdded=toBeAdded.multiply(total);

                    tempForSpots--;
                }

                retVal=retVal.add(toBeAdded);

            }
            temp++;
        }

        return retVal;

    }


    public BigDecimal getETA() {

        BigDecimal totalPers = new BigDecimal(totalPermutations);

        double startTime =  System.currentTimeMillis();

        for (double i = 0; i < 1.79769313486231570E+6 ; i++) {
            if ("abcdqewadvavfdsbffbdfgnhfjgdntyutdcbtfynbsv".equals("vndalisvdavdadbfddsfbadfdvnvlfa")) {
                //Random Comparison between two strings.
            }
        }

        double endTime =  System.currentTimeMillis();

        Double sampleTimeElapsed = endTime - startTime;

        BigDecimal retVal = new BigDecimal("1");

        BigDecimal ste = new BigDecimal(sampleTimeElapsed.toString());



        ste = ste.divide(new BigDecimal("1.79769313486231570E+6"),1000,RoundingMode.HALF_UP);
        retVal = totalPers.multiply(ste);

        return retVal;

    }

}
