package com.example.ignadwiutami.examinaphone;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


public class SplashAct extends AppCompatActivity {

    Animation splash_screen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        splash_screen = AnimationUtils.loadAnimation(this, R.anim.splash_screen);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent mainAct = new Intent(SplashAct.this, MainActivity.class);
                startActivity(mainAct);
                finish();
            }
        },1500); //1,5s
    }
}
