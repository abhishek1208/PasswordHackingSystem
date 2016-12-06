package com.example.piyush.passwordhackingsystem.activities;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.example.piyush.passwordhackingsystem.R;
import com.example.piyush.passwordhackingsystem.ui.CustomProgressBar;

public class StrengthScore extends AppCompatActivity {

    CustomProgressBar customProgressBar;
    TextView tv;
    Button button;
    float perc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strength_score);

        customProgressBar = (CustomProgressBar) findViewById(R.id.customProgressBar);
        tv = (TextView) findViewById(R.id.activity_strength_score_tv_SCORE);
        button = (Button) findViewById(R.id.activity_strength_score_btn_details);
        perc = getIntent().getFloatExtra("perc",100);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StrengthScore.this,StrengthActivity.class);
                i.putExtra("actualPassword",getIntent().getStringExtra("actualPassword"));
                startActivity(i);
                finish();
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        animate();
    }

    public void animate() {

        animateAngle();
        animateColor();
        animateText();

    }

    int getTrafficlightColor(double value){
        return android.graphics.Color.HSVToColor(new float[]{(float)value*120f,1f,1f});
    }

    public void animateAngle() {
        float endAngle = (perc/100)*360;
        ObjectAnimator animator = ObjectAnimator.ofFloat(customProgressBar,"sweepAngle",0f,endAngle);
        animator.setDuration(2500);
        animator.setInterpolator(new BounceInterpolator());
        animator.start();

    }

    public void animateColor() {
        final float[] from = new float[3],
                to =   new float[3];

        Color.colorToHSV(Color.parseColor("#FF00FF00"), from);
        double d = perc/100;

        Color.colorToHSV(getTrafficlightColor(1-d), to);

        ValueAnimator anim = ValueAnimator.ofFloat(0, 1);
        anim.setDuration(2500);
        anim.setInterpolator(new BounceInterpolator());
        final float[] hsv  = new float[3];
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
            @Override public void onAnimationUpdate(ValueAnimator animation) {

                hsv[0] = from[0] + (to[0] - from[0])*animation.getAnimatedFraction();
                hsv[1] = from[1] + (to[1] - from[1])*animation.getAnimatedFraction();
                hsv[2] = from[2] + (to[2] - from[2])*animation.getAnimatedFraction();

                customProgressBar.setColor(Color.HSVToColor(hsv));
            }
        });

        anim.start();
    }

    public void animateText() {
        final ValueAnimator valAn = ValueAnimator.ofInt(0,(int)perc);

        valAn.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {


            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                tv.setText(String.valueOf(valAn.getAnimatedValue()));
            }

        });


        valAn.setDuration(2500);
        valAn.setInterpolator(new BounceInterpolator());
        valAn.start();
    }

}
