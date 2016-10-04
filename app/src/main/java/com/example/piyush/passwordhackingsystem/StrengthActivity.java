package com.example.piyush.passwordhackingsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class StrengthActivity extends AppCompatActivity {
    TextView tv_strengths_numberofcharacters,tv_strengths_uppercaseletters,tv_strengths_lowercaseletters,
            tv_strengths_numbers,tv_strengths_symbols,tv_strengths_midnumorsymbol,tv_weakness_lettersonly,
            tv_weakness_numbersonly,tv_weakness_repeatchars,tv_consecutiveupper,tv_consecutivelower,
            tv_consecutivenumbers,tv_sequentialletters,tv_sequentialnumbers,tv_sequentialsymbols,tv_strentghperc;
    Button btn_commonpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strength);
        tv_strengths_numberofcharacters= (TextView) findViewById(R.id.tv_strengths_numberofcharacters);
        tv_strengths_uppercaseletters= (TextView) findViewById(R.id.tv_strengths_uppercaseletters);
        tv_strengths_lowercaseletters= (TextView) findViewById(R.id.tv_strengths_lowercaseletters);
        tv_strengths_numbers= (TextView) findViewById(R.id.tv_strengths_numbers);
        tv_strengths_symbols= (TextView) findViewById(R.id.tv_strengths_symbols);
        tv_strengths_midnumorsymbol= (TextView) findViewById(R.id.tv_strengths_midnumorsymbol);
        tv_weakness_lettersonly= (TextView) findViewById(R.id.tv_weakness_lettersonly);
        tv_weakness_numbersonly= (TextView) findViewById(R.id.tv_weakness_numbersonly);
        tv_weakness_repeatchars= (TextView) findViewById(R.id.tv_weakness_repeatchars);
        tv_consecutiveupper= (TextView) findViewById(R.id.tv_consecutiveupper);
        tv_consecutivelower= (TextView) findViewById(R.id.tv_consecutivelower);
        tv_consecutivenumbers= (TextView) findViewById(R.id.tv_consecutivenumbers);
        tv_sequentialletters= (TextView) findViewById(R.id.tv_sequentialletters);
        tv_sequentialnumbers= (TextView) findViewById(R.id.tv_sequentialnumbers);
        tv_sequentialsymbols= (TextView) findViewById(R.id.tv_sequentialsymbols);
        tv_strentghperc= (TextView) findViewById(R.id.tv_strentghperc);
        btn_commonpass= (Button) findViewById(R.id.btn_commonpass);

    }
    
    public  int numberOfCharacters(String str) {
        return str.length();
    }

    public  int upperCaseLetters(String str) {
        int retVal = 0;
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) >= 65 && str.charAt(i) <= 90) {
                retVal++;
            }
        }

        return retVal;

    }

    public  int lowerCaseLetters(String str) {
        int retVal = 0;
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) >= 97 && str.charAt(i) <= 122) {
                retVal++;
            }
        }

        return retVal;

    }

    public  int numbers(String str) {
        int retVal = 0;
        for (int i = 0; i < str.length(); ++i) {
            if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
                retVal++;
            }
        }

        return retVal;

    }

    public  int specialChars(String str) {
        int retVal = 0;
        for (int i = 0; i < str.length(); ++i) {
            if ((str.charAt(i) >= 33 && str.charAt(i) <= 40) || str.charAt(i) == 64 || str.charAt(i) == 95) {
                retVal++;
            }
        }

        return retVal;

    }

    public  int lettersOnly(String str) {
        int retVal = 0;
        if (str.length() == lowerCaseLetters(str) + upperCaseLetters(str)) {
            retVal = str.length();

        }

        return retVal;

    }

    public  int numbersOnly(String str) {
        int retVal = 0;
        if (str.length() == numbers(str)) {
            retVal = str.length();

        }

        return retVal;

    }

    public  int midNumOrSymbols(String str) {
        int retVal = 0;
        for (int i = 1; i < str.length() - 1; ++i) {
            if ((str.charAt(i) >= 48 && str.charAt(i) <= 57)
                    || (((str.charAt(i) >= 33 && str.charAt(i) <= 40) || str.charAt(i) == 64 || str.charAt(i) == 95))) {
                retVal++;
            }
        }
        return retVal;

    }

    public  int repeatChars(String str) {
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

    public  int consecutiveUpperCaseLetters(String str) {
        int retVal = 0;
        for (int i = 2; i < str.length(); ++i) {
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

    public  int consecutiveLowerCaseLetters(String str) {
        int retVal = 0;
        for (int i = 1; i < str.length(); ++i) {
            if ((str.charAt(i) >= 97 && str.charAt(i) <= 122)
                    && (str.charAt(i - 1) >= 97 && str.charAt(i - 1) <= 122)) {

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

    public  int consecutiveNumbers(String str) {
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
}