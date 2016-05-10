package com.github.miki48ru.ledlampeconomist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        findViewById(R.id.main_activity).setOnTouchListener(activitySwiped);

    }



    public void onClickStart(View view) {
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(intent);
    }


    View.OnTouchListener activitySwiped = new OnSwipeTouchListener(this) {
        public boolean onSwipeRight() {
            return true;
        }

        public boolean onSwipeLeft() {
            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }

        public boolean onSwipeBottom() {
            return true;
        }

    };
}
