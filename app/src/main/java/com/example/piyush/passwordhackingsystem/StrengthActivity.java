package com.example.piyush.passwordhackingsystem;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class StrengthActivity extends AppCompatActivity {
    TextView tv_strengths_numberofcharacters, tv_strengths_uppercaseletters, tv_strengths_lowercaseletters,
            tv_strengths_numbers, tv_strengths_symbols, tv_strengths_midnumorsymbol, tv_weakness_lettersonly,
            tv_weakness_numbersonly, tv_weakness_repeatchars, tv_consecutiveupper, tv_consecutivelower,
            tv_consecutivenumbers, tv_sequentialletters, tv_sequentialnumbers, tv_strentghperc, tv_common;
    Button btn_commonpass;

    String actualPassword;

    public static final int PERM_REQ_CODE_STRENGTH_ACT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strength);
        tv_strengths_numberofcharacters = (TextView) findViewById(R.id.tv_strengths_numberofcharacters);
        tv_strengths_uppercaseletters = (TextView) findViewById(R.id.tv_strengths_uppercaseletters);
        tv_strengths_lowercaseletters = (TextView) findViewById(R.id.tv_strengths_lowercaseletters);
        tv_strengths_numbers = (TextView) findViewById(R.id.tv_strengths_numbers);
        tv_strengths_symbols = (TextView) findViewById(R.id.tv_strengths_symbols);
        tv_strengths_midnumorsymbol = (TextView) findViewById(R.id.tv_strengths_midnumorsymbol);
        tv_weakness_lettersonly = (TextView) findViewById(R.id.tv_weakness_lettersonly);
        tv_weakness_numbersonly = (TextView) findViewById(R.id.tv_weakness_numbersonly);
        tv_weakness_repeatchars = (TextView) findViewById(R.id.tv_weakness_repeatchars);
        tv_consecutiveupper = (TextView) findViewById(R.id.tv_consecutiveupper);
        tv_consecutivelower = (TextView) findViewById(R.id.tv_consecutivelower);
        tv_consecutivenumbers = (TextView) findViewById(R.id.tv_consecutivenumbers);
        tv_sequentialletters = (TextView) findViewById(R.id.tv_sequentialletters);
        tv_sequentialnumbers = (TextView) findViewById(R.id.tv_sequentialnumbers);

        tv_strentghperc = (TextView) findViewById(R.id.tv_strentghperc);
        btn_commonpass = (Button) findViewById(R.id.btn_commonpass);
        tv_common = (TextView) findViewById(R.id.tv_common);

        Intent i = getIntent();
        actualPassword = i.getStringExtra("actualPassword");

        int numOfChars = numberOfCharacters(actualPassword);
        int upperCaseLetters = upperCaseLetters(actualPassword);
        int lowerCaseLetters = lowerCaseLetters(actualPassword);
        int numbers = numbers(actualPassword);
        int specialCharacters = specialChars(actualPassword);
        int lettersOnly = lettersOnly(actualPassword);
        int numbersOnly = numbersOnly(actualPassword);
        int midNumOrSymbols = midNumOrSymbols(actualPassword);
        int repeatCharacters = repeatChars(actualPassword);
        int consecutiveUpperCase = consecutiveUpperCaseLetters(actualPassword);
        int consecutiveLowerCase = consecutiveLowerCaseLetters(actualPassword);
        int consecutiveNumbers = consecutiveNumbers(actualPassword);
        int sequentialNumbers = sequentialNumbers(actualPassword);
        int sequentialLetters = sequentialLetters(actualPassword);

        tv_strengths_numberofcharacters.setText(String.valueOf(numOfChars));
        tv_strengths_uppercaseletters.setText(String.valueOf(upperCaseLetters));
        tv_strengths_lowercaseletters.setText(String.valueOf(lowerCaseLetters));
        tv_strengths_numbers.setText(String.valueOf(numbers));
        tv_strengths_symbols.setText(String.valueOf(specialCharacters));
        tv_strengths_midnumorsymbol.setText(String.valueOf(midNumOrSymbols));
        tv_weakness_lettersonly.setText(String.valueOf(lettersOnly));
        tv_weakness_numbersonly.setText(String.valueOf(numbersOnly));
        tv_weakness_repeatchars.setText(String.valueOf(repeatCharacters));
        tv_consecutiveupper.setText(String.valueOf(consecutiveUpperCase));
        tv_consecutivelower.setText(String.valueOf(consecutiveLowerCase));
        tv_consecutivenumbers.setText(String.valueOf(consecutiveNumbers));
        tv_sequentialnumbers.setText(String.valueOf(sequentialNumbers));
        tv_sequentialletters.setText(String.valueOf(sequentialLetters));


        btn_commonpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File f = new File(Environment.getExternalStorageDirectory(), "passwords.txt");

                int permResult = ContextCompat.checkSelfPermission(StrengthActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE);


                if (permResult == PackageManager.PERMISSION_GRANTED) {

                    if (!f.exists()) {

                        if(checkInternet()) {
                        DictionaryDownloadTask dictionaryDownloadTask = new DictionaryDownloadTask();
                        dictionaryDownloadTask.execute();

                        new DictionaryReadTask() {
                            @Override
                            protected void onPostExecute(Pair pair) {
                                super.onPostExecute(pair);
                                if (pair.checker) {

                                    tv_common.setText("YES");
                                } else {
                                    tv_common.setText("NO");
                                }
                            }
                        }.execute(actualPassword);}
                        else {
                            Toast.makeText(StrengthActivity.this, "Connect to the Internet", Toast.LENGTH_SHORT).show();
                        }
                    } else {

                        new DictionaryReadTask() {
                            @Override
                            protected void onPostExecute(Pair pair) {
                                super.onPostExecute(pair);
                                if (pair.checker) {

                                    tv_common.setText("YES");
                                } else {
                                    tv_common.setText("NO");
                                }
                            }
                        }.execute(actualPassword);
                    }

                } else {

                    ActivityCompat.requestPermissions(StrengthActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            PERM_REQ_CODE_STRENGTH_ACT);

                }


            }
        });


        int score = 0;

        score = score + numOfChars * 4 + (upperCaseLetters) * 2 +
                (lowerCaseLetters) * 2 + numbers * 4 + specialCharacters * 6 +
                midNumOrSymbols * 2 - lettersOnly - numbersOnly - repeatCharacters - consecutiveUpperCase * 2 -
                consecutiveLowerCase * 2 - consecutiveNumbers * 2 - sequentialLetters * 3 - sequentialNumbers * 3;

        int maxScore = 100;

        int percentage = score * 100;
        percentage = percentage / maxScore;

        if (percentage > 100) {
            percentage = 99;
        }

        tv_strentghperc.setText(String.valueOf(percentage));


    }

    public int numberOfCharacters(String str) {
        return str.length();
    }

    public int upperCaseLetters(String str) {
        int retVal = 0;
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) >= 65 && str.charAt(i) <= 90) {
                retVal++;
            }
        }

        return retVal;

    }

    public int lowerCaseLetters(String str) {
        int retVal = 0;
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) >= 97 && str.charAt(i) <= 122) {
                retVal++;
            }
        }

        return retVal;

    }

    public int numbers(String str) {
        int retVal = 0;
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                retVal++;
            }
        }

        return retVal;

    }

    public int specialChars(String str) {
        int retVal = 0;
        for (int i = 0; i < str.length(); ++i) {
            if ((str.charAt(i) >= 33 && str.charAt(i) <= 40) || str.charAt(i) == 64 || str.charAt(i) == 95) {
                retVal++;
            }
        }

        return retVal;

    }

    public int lettersOnly(String str) {
        int retVal = 0;
        if (str.length() == lowerCaseLetters(str) + upperCaseLetters(str)) {
            retVal = str.length();

        }

        return retVal;

    }

    public int numbersOnly(String str) {
        int retVal = 0;
        if (str.length() == numbers(str)) {
            retVal = str.length();

        }

        return retVal;

    }

    public int midNumOrSymbols(String str) {
        int retVal = 0;
        for (int i = 1; i < str.length() - 1; ++i) {
            if ((str.charAt(i) >= 48 && str.charAt(i) <= 57)
                    || (((str.charAt(i) >= 33 && str.charAt(i) <= 40) || str.charAt(i) == 64 || str.charAt(i) == 95))) {
                retVal++;
            }
        }
        return retVal;

    }

    public int repeatChars(String str) {
        int retVal = 0;
        char[] array = new char[256];
        for (int i = 0; i < str.length(); ++i) {
            ++array[str.charAt(i)];
        }
        for (int i = 0; i < array.length; ++i) {
            if (array[i] > 1) {
                retVal++;

            }
        }
        return retVal;
    }

    public int consecutiveUpperCaseLetters(String str) {
        int retVal = 0;
        for (int i = 1; i < str.length(); ++i) {
            if ((str.charAt(i) >= 65 && str.charAt(i) <= 90) && (str.charAt(i - 1) >= 65 && str.charAt(i - 1) <= 90)) {
                for (int j = i; j < str.length(); ++j, ++i) {
                    if ((str.charAt(i) >= 65 && str.charAt(i) <= 90)
                            && (str.charAt(i - 1) >= 65 && str.charAt(i - 1) <= 90)) {
                        retVal++;

                    } else {
                        retVal++;
                        break;
                    }
                }
            }
        }

        return retVal;

    }

    public int consecutiveLowerCaseLetters(String str) {
        int retVal = 0;
        for (int i = 1; i < str.length(); ++i) {
            if ((str.charAt(i) >= 97 && str.charAt(i) <= 122) && (str.charAt(i - 1) >= 97 && str.charAt(i - 1) <= 122)) {

                for (int j = i; j < str.length(); ++j, ++i) {
                    if ((str.charAt(i) >= 97 && str.charAt(i) <= 122)
                            && (str.charAt(i - 1) >= 97 && str.charAt(i - 1) <= 122)) {
                        retVal++;

                    } else {
                        retVal++;
                        break;
                    }

                }
            }
        }

        return retVal;

    }

    public int consecutiveNumbers(String str) {
        int retVal = 0;
        for (int i = 1; i < str.length(); ++i) {
            if ((str.charAt(i) >= 48 && str.charAt(i) <= 57) && (str.charAt(i - 1) >= 48 && str.charAt(i - 1) <= 57)) {
                for (int j = i; j < str.length(); ++j, ++i) {
                    if ((str.charAt(i) >= 48 && str.charAt(i) <= 57)
                            && (str.charAt(i - 1) >= 48 && str.charAt(i - 1) <= 57)) {
                        retVal++;

                    } else {
                        retVal++;
                        break;
                    }
                }
            }
        }

        return retVal;
    }

    public int sequentialNumbers(String str) {


        int retVal = 0;
        for (int i = 0; i < str.length(); i++) {

            int thisResult = 0;
            while (i < str.length() - 1 && str.charAt(i + 1) - str.charAt(i) == 1 && str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                thisResult++;
                i++;
            }
            thisResult++;

            if (thisResult >= 3) {
                retVal += thisResult;
            }

        }

        return retVal;


    }

    public int sequentialLetters(String str) {


        int retVal = 0;
        for (int i = 0; i < str.length(); i++) {

            int thisResult = 0;
            while (i < str.length() - 1 && str.charAt(i + 1) - str.charAt(i) == 1 && ((str.charAt(i) <= 90 && str.charAt(i) >= 65) || (str.charAt(i) >= 97 && str.charAt(i) <= 122))) {
                thisResult++;
                i++;
            }
            thisResult++;

            if (thisResult >= 3) {
                retVal += thisResult;
            }

        }

        return retVal;


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERM_REQ_CODE_STRENGTH_ACT) {
            if (permissions[0].equals(Manifest.permission.READ_EXTERNAL_STORAGE)
                    && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {

                File f = new File(Environment.getExternalStorageDirectory(), "passwords.txt");
                if (!f.exists()) {
                    if(checkInternet()) {
                        DictionaryDownloadTask dictionaryDownloadTask = new DictionaryDownloadTask();
                        dictionaryDownloadTask.execute();

                        new DictionaryReadTask() {
                            @Override
                            protected void onPostExecute(Pair pair) {
                                super.onPostExecute(pair);
                                if (pair.checker) {

                                    tv_common.setText("YES");
                                } else {
                                    tv_common.setText("NO");
                                }
                            }
                        }.execute(actualPassword);}
                    else {
                        Toast.makeText(StrengthActivity.this, "Connect to the Internet", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    new DictionaryReadTask() {
                        @Override
                        protected void onPostExecute(Pair pair) {
                            super.onPostExecute(pair);
                            if (pair.checker) {

                                tv_common.setText("YES");
                            } else {
                                tv_common.setText("NO");
                            }
                        }
                    }.execute(actualPassword);
                }
            } else {
                Toast.makeText(StrengthActivity.this,
                        "Permission not granted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public boolean checkInternet () {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null && ni.isConnected()) {
            return true;
        }
//        Log.d(TAG, "checkInternet: typeName" + ni.getTypeName());
//        Log.d(TAG, "checkInternet: state(toString)" + ni.getState().toString());
//        Log.d(TAG, "checkInternet: extraInfo" + ni.getExtraInfo());
//        Log.d(TAG, "checkInternet: subtypeName" + ni.getSubtypeName());
        return false;
    }
}