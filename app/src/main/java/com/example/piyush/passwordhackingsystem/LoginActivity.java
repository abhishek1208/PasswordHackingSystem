package com.example.piyush.passwordhackingsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText passwordText;
    Button strengthBtn;
    Button hackingBtn;
    CheckBox showBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        passwordText = (EditText) findViewById(R.id.editText);
        strengthBtn = (Button) findViewById(R.id.btn_strength);
        hackingBtn = (Button) findViewById(R.id.btn_hacking);
        showBox = (CheckBox) findViewById(R.id.checkBox);

        showBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked) {
                    passwordText.setInputType(129);
                } else {

                    passwordText.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        hackingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,SecondActivity.class);
                i.putExtra("actualPassword",passwordText.getText().toString());
                startActivity(i);
            }
        });

        strengthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this,StrengthActivity.class);
                i.putExtra("actualPassword",passwordText.getText().toString());
                startActivity(i);
            }
        });

    }
}
