package com.bkcd.soulace.sample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;
import com.yalantis.contextmenu.R;

public class IntroSplashScreen extends AppCompatActivity {

    ShimmerTextView tv, tv1;
    Shimmer shimmer;
    private static int SPLASH_TIME_OUT = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_splash_screen);

        tv = (ShimmerTextView) findViewById(R.id.shimmer_tv);
        tv1 = (ShimmerTextView) findViewById(R.id.shimmer_tv1);
        shimmer = new Shimmer();


        shimmer.start(tv1);
        shimmer.start(tv);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(IntroSplashScreen.this, RecognizeActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);


    }
}