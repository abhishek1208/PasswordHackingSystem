package com.example.piyush.passwordhackingsystem;

import android.os.AsyncTask;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Piyush on 04-10-2016.
 */

public class DictionaryReadTask extends AsyncTask<String, Void, Pair> {
    @Override
    protected Pair doInBackground(String... params) {
        Pair retVal = new Pair();
        retVal.checker = false;
        retVal.password = "NULL";

        File f = new File(Environment.getExternalStorageDirectory(), "passwords.txt");

        FileInputStream fStream = null;
        try {
            fStream = new FileInputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(fStream));
        String buffer = "";
        while (buffer != null) {
            try {
                buffer = fileReader.readLine();
                if (buffer.equals(params[0])) {
                    retVal.checker = true;
                    retVal.password = buffer;
                    return retVal;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return retVal;
    }

}
